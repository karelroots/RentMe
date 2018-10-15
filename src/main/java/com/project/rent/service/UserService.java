package com.project.rent.service;

import com.project.rent.model.Role;
import com.project.rent.model.User;
import com.project.rent.repository.RoleRepository;
import com.project.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserService { // meetodid kasutajatega toimingute tegemiseks

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserById(int id) { return userRepository.findById(id); }

    public void saveUser(User user) { //funktsioon, mis salvestab antud kasutaja andmed andmebaasi
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("KASUTAJA"); //by default salvestame rolliks Kasutaja, administraatoreid tuleb manuaalselt lisada
        user.setRoles(new HashSet<>(Arrays.asList(userRole))); //anname kasutajale need rollid (hetkel ainult üks roll)
        userRepository.save(user);
    }

    public void updateUser(int userId, User user) { //funktsioon, mis uuendab kasutaja andmeid andmebaasis, kasutades html vormist võetud infot
        User existingUser = userRepository.findById(userId); //leiame olemasoleva kasutaja andmebaasis

        existingUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); //uuendame kõik andmed vastavalt uutele
        existingUser.setName(user.getName());

        existingUser.setEmail(user.getEmail());
        existingUser.setLastName(user.getLastName());

        String location = user.getLocation();
        if(user.getLocation() != null) {
            existingUser.setLocation(location);
        }

        String telephone = user.getTelephone();
        if(telephone != null) {
            existingUser.setTelephone(telephone);
        }

        existingUser.setUsername(user.getUsername());

        userRepository.save(existingUser);
    }

    public void saveAvatar(String avatarName, User user) { // Kasutaja konto avatari nime salvestamine
        user.setAvatarName(avatarName);
        userRepository.save(user);
    }

    public void saveActive(int value, User user) { // Kasutaja konto aktiveerimine/deaktiveerimine
        user.setActive(value);

        if(value == 0) {
            userRepository.save(removeRole(user, "KASUTAJA"));
        } else {
            userRepository.save(addRole(user, "KASUTAJA"));
        }
    }

    private User removeRole(User user, String roleName) { // kasutajalt rolli eemaldamine
        Set<Role> roles = user.getRoles();

        for(Role role:roles) {
            if (role.getRole().equals(roleName)) {
                roles.remove(role);
            }
        }

        user.setRoles(roles);

        return user;
    }

    private User addRole(User user, String roleName) { // kasutajale uue rolli lisamine
        Set<Role> roles = user.getRoles();

        roles.add(roleRepository.findByRole("KASUTAJA"));
        user.setRoles(roles);

        return user;
    }

    public List<User> getUserList() { // tagastame kõikide registreeritud kasutajate listi
        return userRepository.findAll();
    }

    public String getSum() {
        return userRepository.findCount();
    }

}
