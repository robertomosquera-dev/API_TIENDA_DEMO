package com.demo.mcctiendademo.Config;

import com.demo.mcctiendademo.Entity.Enum.RoleEnum;
import com.demo.mcctiendademo.Entity.Permission;
import com.demo.mcctiendademo.Entity.Role;
import com.demo.mcctiendademo.Entity.UserEntity;
import com.demo.mcctiendademo.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final IUserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        /*
        Permission p1 = Permission.builder().name("READ").build();
        Permission p2 = Permission.builder().name("UPDATE").build();
        Permission p3 = Permission.builder().name("DELETE").build();
        Permission p4 = Permission.builder().name("CREATE").build();

        Role r1 = Role.builder()
                .role(RoleEnum.ADMIN)
                .permissions(Set.of(p1, p2, p3, p4))
                .build();

        Role r2 = Role.builder()
                .role(RoleEnum.DEVELOPER)
                .permissions(Set.of(p1, p2, p3, p4))
                .build();

        Role r3 = Role.builder()
                .role(RoleEnum.USER)
                .permissions(Set.of(p1, p4))
                .build();

        Role r4 = Role.builder()
                .role(RoleEnum.INVITED)
                .permissions(Set.of(p1))
                .build();

        // 🔹 Usuario ADMIN
        UserEntity userEntityAdmin = UserEntity.builder()
                .username("santiago")
                .password(new BCryptPasswordEncoder().encode("admin123"))
                .isEnabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .roles(Set.of(r1))
                .build();

        // 🔹 Usuario DEVELOPER
        UserEntity userEntityDev = UserEntity.builder()
                .username("roberto")
                .password(new BCryptPasswordEncoder().encode("dev123"))
                .isEnabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .roles(Set.of(r2))
                .build();

        // 🔹 Usuario USER
        UserEntity userUserEntity = UserEntity.builder()
                .username("juan")
                .password(new BCryptPasswordEncoder().encode("user123"))
                .isEnabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .roles(Set.of(r3))
                .build();

        // 🔹 Usuario INVITED
        UserEntity userEntityInvited = UserEntity.builder()
                .username("invitado")
                .password(new BCryptPasswordEncoder().encode("guest123"))
                .isEnabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .roles(Set.of(r4))
                .build();

        userRepository.saveAll(List.of(userEntityAdmin, userEntityDev, userUserEntity, userEntityInvited));

        // aquí normalmente iría userRepository.save(...)

        */
    }


}
