package com.sip.ams.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sip.ams.entities.User;
import com.sip.ams.entities.Article;
import com.sip.ams.entities.Role;

import com.sip.ams.repositories.RoleRepository;
import com.sip.ams.repositories.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
@Service("userService")
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

  
    public void saveUser(User user) {
        user.setPassword(user.getPassword());
        user.setActive(0);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
            return userRepository.findAll();
    }

    public User saveUser2(User user)
    {
        return userRepository.save(user);
    }
    
    public User getOneUserById(long idUser)
    {
        return userRepository.findById(idUser).orElseThrow(() -> new IllegalArgumentException("UserId " + idUser + " not found"));
    }

   
    public User updateUser(long idUser, User user)
    {
    	User temp = null;

        Optional<User> opt = userRepository.findById(idUser);

        if(opt.isPresent())
        {
            temp = opt.get();
            temp.setEmail(user.getEmail());
            temp.setLastName(user.getLastName());
            temp.setName(user.getName());
            temp.setRoles(user.getRoles());
            userRepository.save(temp);

        }
        if(temp == null) throw new IllegalArgumentException("User with id = "+ idUser +"not Found");
        return temp;

    }
    public User deleteUser(long idUser)
    {
    	User temp = null;

        Optional<User> opt = userRepository.findById(idUser);

         if(opt.isPresent())
         {
            temp = opt.get();
            userRepository.delete(temp);

         }
            if(temp == null) throw new IllegalArgumentException("User with id = "+ idUser +"not Found");
        return temp;
    }
}