package org.joinfaces.resume.security.service;

import org.joinfaces.resume.common.service.AbstractService;
import org.joinfaces.resume.security.dto.UserCredentialsDto;
import org.joinfaces.resume.security.entity.UserCredentialsEntity;
import org.joinfaces.resume.security.repository.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserCredentialsService extends AbstractService<UserCredentialsEntity, UserCredentialsDto> {

    private static final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    public Boolean verifyIsExisting(String username){
        return this.userCredentialsRepository.findByUsername(username).isPresent();
    }

    public Optional<UserCredentialsEntity> verifyIsExistingAndGet(String username){
        return this.userCredentialsRepository.findByUsername(username);
    }
    @Override
    public UserCredentialsDto add(UserCredentialsDto userCredentialsDto) {
        UserCredentialsEntity userCredentialsEntity = UserCredentialsEntity.builder()
                .username(userCredentialsDto.getUsername())
                .authorities(userCredentialsDto.getAuthorities())
                .password(encoder.encode(userCredentialsDto.getPassword()))
                .accountNonExpired(userCredentialsDto.getAccountNonExpired())
                .accountNonLocked(userCredentialsDto.getAccountNonLocked())
                .credentialsNonExpired(userCredentialsDto.isCredentialsNonExpired())
                .enabled(userCredentialsDto.isEnabled())
                .build();
        userCredentialsEntity = this.userCredentialsRepository.save(userCredentialsEntity);
        userCredentialsDto = UserCredentialsDto.builder()
                .username(userCredentialsEntity.getUsername())
                .authorities(userCredentialsEntity.getAuthorities())
                .password(userCredentialsEntity.getPassword())
                .accountNonExpired(userCredentialsEntity.getAccountNonExpired())
                .accountNonLocked(userCredentialsEntity.getAccountNonLocked())
                .credentialsNonExpired(userCredentialsEntity.isCredentialsNonExpired())
                .enabled(userCredentialsEntity.isEnabled())
                .build();
        return userCredentialsDto;
    }
    @Override
    public List<UserCredentialsDto> readAll() {
        return this.userCredentialsRepository.findAll().stream().map(userCredentialsEntity -> UserCredentialsDto.builder()
                .username(userCredentialsEntity.getUsername())
                .authorities(userCredentialsEntity.getAuthorities())
                .password(encoder.encode(userCredentialsEntity.getPassword()))
                .accountNonExpired(userCredentialsEntity.getAccountNonExpired())
                .accountNonLocked(userCredentialsEntity.getAccountNonLocked())
                .credentialsNonExpired(userCredentialsEntity.isCredentialsNonExpired())
                .enabled(userCredentialsEntity.isEnabled())
                .build()).collect(Collectors.toList());
    }

    @Override
    public UserCredentialsDto read(Long id) {
        Optional<UserCredentialsEntity> optionalUserCredentialsEntity = this.userCredentialsRepository.findById(id);
        if (optionalUserCredentialsEntity.isPresent()) {
            return UserCredentialsDto.builder()
                    .username(optionalUserCredentialsEntity.get().getUsername())
                    .authorities(optionalUserCredentialsEntity.get().getAuthorities())
                    .password(encoder.encode(optionalUserCredentialsEntity.get().getPassword()))
                    .accountNonExpired(optionalUserCredentialsEntity.get().getAccountNonExpired())
                    .accountNonLocked(optionalUserCredentialsEntity.get().getAccountNonLocked())
                    .credentialsNonExpired(optionalUserCredentialsEntity.get().isCredentialsNonExpired())
                    .enabled(optionalUserCredentialsEntity.get().isEnabled())
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public List<UserCredentialsDto> readAllIn(List<Long> id) {
        return this.userCredentialsRepository.findAllById(id).stream().map(userCredentialsEntity -> UserCredentialsDto.builder()
                .username(userCredentialsEntity.getUsername())
                .authorities(userCredentialsEntity.getAuthorities())
                .password(encoder.encode(userCredentialsEntity.getPassword()))
                .accountNonExpired(userCredentialsEntity.getAccountNonExpired())
                .accountNonLocked(userCredentialsEntity.getAccountNonLocked())
                .credentialsNonExpired(userCredentialsEntity.isCredentialsNonExpired())
                .enabled(userCredentialsEntity.isEnabled())
                .build()).collect(Collectors.toList());
    }

    @Override
    public UserCredentialsDto update(UserCredentialsDto userCredentialsDto) {
        return add(userCredentialsDto);
    }

    @Override
    public void delete(Long id) {
        this.userCredentialsRepository.deleteById(id);
    }

    @Override
    public void delete(UserCredentialsDto userCredentialsDto) {
        this.userCredentialsRepository.delete(UserCredentialsEntity.builder()
                .username(userCredentialsDto.getUsername())
                .authorities(userCredentialsDto.getAuthorities())
                .password(encoder.encode(userCredentialsDto.getPassword()))
                .accountNonExpired(userCredentialsDto.getAccountNonExpired())
                .accountNonLocked(userCredentialsDto.getAccountNonLocked())
                .credentialsNonExpired(userCredentialsDto.isCredentialsNonExpired())
                .enabled(userCredentialsDto.isEnabled())
                .build());
    }
}
