package org.joinfaces.resume.security.module;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.joinfaces.resume.security.dto.UserCredentialsDto;
import org.joinfaces.resume.security.service.UserCredentialsService;
import org.joinfaces.resume.security.entity.UserCredentialsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.log.LogMessage;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.security.core.userdetails.memory.UserAttributeEditor;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
/**
 * persistent implementation of {@code UserDetailsManager} which is backed
 * in db.
 *
 * @author Mohamed ABDELNABI
 */
@Component
public class JpaUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

	protected final Log logger = LogFactory.getLog(getClass());

	private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
			.getContextHolderStrategy();

	@Autowired
	private UserCredentialsService userCredentialsService;

	private AuthenticationManager authenticationManager;

	public JpaUserDetailsManager() {
	}

	public JpaUserDetailsManager(Collection<UserDetails> users) {
		for (UserDetails user : users) {
			createUser(user);
		}
	}

	public JpaUserDetailsManager(UserDetails... users) {
		for (UserDetails user : users) {
			createUser(user);
		}
	}

	public JpaUserDetailsManager(Properties users) {
		Enumeration<?> names = users.propertyNames();
		UserAttributeEditor editor = new UserAttributeEditor();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			editor.setAsText(users.getProperty(name));
			UserAttribute attr = (UserAttribute) editor.getValue();
			Assert.notNull(attr,
					() -> "The entry with username '" + name + "' could not be converted to an UserDetails");
			createUser(createUserDetails(name, attr));
		}
	}

	private User createUserDetails(String name, UserAttribute attr) {
		return new User(name, attr.getPassword(), attr.isEnabled(), true, true, true, attr.getAuthorities());
	}

	@Override
	public void createUser(UserDetails user) {
		Assert.isTrue(!userCredentialsService.verifyIsExisting(user.getUsername()), "user should not exist");
		this.userCredentialsService.add(UserCredentialsDto.builder()
				.accountNonExpired(user.isAccountNonExpired())
				.authorities(user.getAuthorities().stream()
						.map(ga -> ga.getAuthority()).collect(Collectors.toList())
				)
				.accountNonLocked(user.isAccountNonLocked())
				.credentialsNonExpired(user.isCredentialsNonExpired())
				.enabled(user.isEnabled())
				.username(user.getUsername())
				.password(user.getPassword())
				.build());
	}

	@Override
	public void deleteUser(String username) {
		Optional<UserCredentialsEntity> optionalUserCredentialsEntity = this.userCredentialsService.verifyIsExistingAndGet(username);
		if(optionalUserCredentialsEntity.isPresent()){
			this.userCredentialsService.delete(optionalUserCredentialsEntity.get().getId());
		}
	}

	@Override
	public void updateUser(UserDetails user) {
		Assert.isTrue(userCredentialsService.verifyIsExisting(user.getUsername()), "user should exist");
		this.userCredentialsService.update(UserCredentialsDto.builder()
				.accountNonExpired(user.isAccountNonExpired())
				.authorities(user.getAuthorities().stream()
						.map(ga -> ga.getAuthority()).collect(Collectors.toList())
				)
				.accountNonLocked(user.isAccountNonLocked())
				.credentialsNonExpired(user.isCredentialsNonExpired())
				.enabled(user.isEnabled())
				.username(user.getUsername())
				.password(user.getPassword())
				.build());
	}

	@Override
	public boolean userExists(String username) {
		return this.userCredentialsService.verifyIsExisting(username);
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		Authentication currentUser = this.securityContextHolderStrategy.getContext().getAuthentication();
		if (currentUser == null) {
			// This would indicate bad coding somewhere
			throw new AccessDeniedException(
					"Can't change password as no Authentication object found in context " + "for current user.");
		}
		String username = currentUser.getName();
		this.logger.debug(LogMessage.format("Changing password for user '%s'", username));
		// If an authentication manager has been set, re-authenticate the user with the
		// supplied password.
		if (this.authenticationManager != null) {
			this.logger.debug(LogMessage.format("Reauthenticating user '%s' for password change request.", username));
			this.authenticationManager
					.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(username, oldPassword));
		}
		else {
			this.logger.debug("No authentication manager set. Password won't be re-checked.");
		}
		MutableUserDetails user = this.userCredentialsService.verifyIsExistingAndGet(username).get();
		Assert.state(user != null, "Current user doesn't exist in database.");
		user.setPassword(newPassword);
	}

	@Override
	public UserDetails updatePassword(UserDetails user, String newPassword) {
		String username = user.getUsername();
		MutableUserDetails mutableUser = this.userCredentialsService.verifyIsExistingAndGet(username).get();
		mutableUser.setPassword(newPassword);
		return mutableUser;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = this.userCredentialsService.verifyIsExistingAndGet(username).get();
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
				user.isCredentialsNonExpired(), user.isAccountNonLocked(), user.getAuthorities());
	}

	/**
	 * Sets the {@link SecurityContextHolderStrategy} to use. The default action is to use
	 * the {@link SecurityContextHolderStrategy} stored in {@link SecurityContextHolder}.
	 *
	 * @since 5.8
	 */
	public void setSecurityContextHolderStrategy(SecurityContextHolderStrategy securityContextHolderStrategy) {
		Assert.notNull(securityContextHolderStrategy, "securityContextHolderStrategy cannot be null");
		this.securityContextHolderStrategy = securityContextHolderStrategy;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
