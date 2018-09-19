package com.project.rent.service;

import com.project.rent.exception.EmailNotFoundException;
import com.project.rent.exception.UserRoleNotFoundException;
import com.project.rent.model.Role;
import com.project.rent.model.User;
import com.project.rent.model.UserRole;
import com.project.rent.repository.RoleRepository;
import com.project.rent.repository.UserRepository;
import com.project.rent.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserRoleRepository userRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository, UserRoleRepository userRoleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    public void updateUser(int userId, User user) {
        int originalId = userId;
        User existingUser = userRepository.findById(originalId);
        UserRole existingRole = userRoleRepository.findByUserId(originalId);
        userRoleRepository.delete(existingRole);
        userRepository.delete(existingUser);
        user.setId(originalId);
        //userRoleRepository.save(existingRole);
        userRepository.save(user);
        userRoleRepository.save(existingRole);
    }

}
