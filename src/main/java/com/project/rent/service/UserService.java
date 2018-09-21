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
public class UserService { // meetodid kasutajatega toimingute tegemiseks

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository, UserRoleRepository userRoleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) { //funktsioon, mis salvestab antud kasutaja andmed andmebaasi
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    public void updateUser(int userId, User user) { //funktsioon, mis uuendab kasutaja andmeid andmebaasis, kasutades html vormist võetud infot
        User existingUser = userRepository.findById(userId); //leiame olemasoleva kasutaja andmebaasis
        existingUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); //uuendame kõik andmed vastavalt uutele
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setLastName(user.getLastName());
        existingUser.setLocation(user.getLocation());
        existingUser.setTelephone(user.getTelephone());
        existingUser.setUsername(user.getUsername());
        userRepository.save(existingUser);
    }

}
