package com.vn.fit.identity.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vn.fit.identity.constant.PredefinedRole;
import com.vn.fit.identity.model.Permission;
import com.vn.fit.identity.model.Role;
import com.vn.fit.identity.model.User;
import com.vn.fit.identity.repository.PermissionRepository;
import com.vn.fit.identity.repository.RoleRepository;
import com.vn.fit.identity.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_USER_PASSWORD = "admin";

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(
            UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        log.info("Initializing application");
        return args -> {
            // Check if the admin user already exists
            if (userRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {

                // Create and save roles
                Role userRole = Role.builder()
                        .name(PredefinedRole.USER_ROLE)
                        .description("User role")
                        .build();
                Role adminRole = Role.builder()
                        .name(PredefinedRole.ADMIN_ROLE)
                        .description("Admin role")
                        .build();
                roleRepository.save(userRole);
                adminRole = roleRepository.save(adminRole); // Update reference to saved adminRole

                // Create and save permissions
                Permission readPermission = Permission.builder()
                        .name("READ")
                        .description("Read permission")
                        .build();
                Permission writePermission = Permission.builder()
                        .name("WRITE")
                        .description("Write permission")
                        .build();
                Set<Permission> permissions = new HashSet<>();
                permissions.add(readPermission);
                permissions.add(writePermission);
                permissionRepository.saveAll(permissions);

                // Assign permissions to adminRole
                adminRole.setPermissions(permissions);
                roleRepository.save(adminRole);

                // Create and save the admin user with adminRole
                Set<Role> roles = new HashSet<>();
                roles.add(adminRole);
                User user = User.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_USER_PASSWORD))
                        .roles(roles)
                        .build();
                userRepository.save(user);

                log.warn("Admin user has been created with default password: admin, please change it");
            }
            log.info("Application initialization completed .....");
        };
    }
}
