package com.demo.mcctiendademo.Service;

import com.demo.mcctiendademo.Entity.UserEntity;
import com.demo.mcctiendademo.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("UserEntity not found with username: " + username));

        List<SimpleGrantedAuthority>simpleGrantedAuthorities = new ArrayList<>();

        userEntity.getRoles().stream()
                .forEach( role -> {
                    simpleGrantedAuthorities.add(
                            new SimpleGrantedAuthority(
                                    "ROLE_".concat( role.getRole().name() )
                            )
                    );
                });

        userEntity.getRoles().stream()
                .flatMap( role -> role.getPermissions().stream())
                .forEach( permission -> {
                    simpleGrantedAuthorities.add(
                            new SimpleGrantedAuthority(
                                    permission.getName()
                            )
                    );
                });

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getIsEnabled(),
                userEntity.getAccountNonExpired(),
                userEntity.getCredentialsNonExpired(),
                userEntity.getAccountNonLocked(),
                simpleGrantedAuthorities
        );
    }

}
