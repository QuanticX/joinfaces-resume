package org.joinfaces.resume.security.module;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Luke Taylor
 * @since 3.1
 */
public interface MutableUserDetails extends UserDetails {

	void setPassword(String password);

}
