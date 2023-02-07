package com.comicreading.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comicreading.domain.User;
import com.comicreading.repository.RoleRepository;
import com.comicreading.repository.UserRepository;
import com.comicreading.security.UserAlreadyExistException;
import com.comicreading.security.UserDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserService implements IUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User registerNewUserAccount(final UserDto accountDto) throws UserAlreadyExistException {
        log.debug(" =============================== registerNewUserAccount().");
        log.debug(accountDto.toString());
        log.debug(" ==== email test: " + emailExists(accountDto.getEmail()));

        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException(
                    "There is an account with that email address: " + accountDto.getEmail());
        }

        final User user = new User();

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public User getUserFromEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            log.error("No user found for email: " + email);
            throw new UsernameNotFoundException("No user found for email: " + email);
        }

        return user;
    }
}
