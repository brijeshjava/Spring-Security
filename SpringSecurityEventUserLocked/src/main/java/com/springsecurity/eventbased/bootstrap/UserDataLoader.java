package com.springsecurity.eventbased.bootstrap;

import com.springsecurity.eventbased.respository.AuthorityRepository;
import com.springsecurity.eventbased.respository.RoleRepository;
import com.springsecurity.eventbased.respository.UserRepository;
import com.springsecurity.eventbased.security.Authority;
import com.springsecurity.eventbased.security.Role;
import com.springsecurity.eventbased.security.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Slf4j
@RequiredArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private void loadSecurityData() {
        log.info("loading logsecurity data");
        //beer auths
        Authority createOrder = authorityRepository.save(Authority.builder().permission("order.create").build());
        Authority updateOrder = authorityRepository.save(Authority.builder().permission("order.update").build());
        Authority readOrder = authorityRepository.save(Authority.builder().permission("order.read").build());
        Authority deleteOrder = authorityRepository.save(Authority.builder().permission("order.delete").build());

        Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
        Role customerRole = roleRepository.save(Role.builder().name("CUSTOMER").build());
        Role userRole = roleRepository.save(Role.builder().name("USER").build());
        Set<Authority> adminAuthority = new HashSet<>();
        adminAuthority.add(createOrder);
        adminAuthority.add(updateOrder);
        adminAuthority.add(readOrder);
        adminAuthority.add(deleteOrder);
        adminRole.setAuthorities(adminAuthority);
        Set<Authority> custAuthority = new HashSet<>();
        custAuthority.add(readOrder);
        customerRole.setAuthorities(custAuthority);
        Set<Authority> userAuthority = new HashSet<>();
        userAuthority.add(readOrder);
        userRole.setAuthorities(userAuthority);

        roleRepository.saveAll(Arrays.asList(adminRole, customerRole, userRole));

        userRepository.save(User.builder()
                .username("brijesh")
                .password(passwordEncoder.encode("password"))
                .role(adminRole)
                .build());

        userRepository.save(User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .role(userRole)
                .build());

        userRepository.save(User.builder()
                .username("customer")
                .password(passwordEncoder.encode("password"))
                .role(customerRole)
                .build());
    //roleRepository.saveAll(Arrays.asList(adminRole, customerRole, userRole));
        log.info("Users Loaded: " + userRepository.count());
    }

    @Override
    public void run(String... args) throws Exception {
       if (authorityRepository.count() == 0) {
            loadSecurityData();
       }
    }


}
