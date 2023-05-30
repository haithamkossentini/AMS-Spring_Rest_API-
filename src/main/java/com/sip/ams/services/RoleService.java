package com.sip.ams.services;

import com.sip.ams.entities.Article;
import com.sip.ams.entities.Role;
import com.sip.ams.repositories.ArticleRepository;
import com.sip.ams.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRoles(){
            return roleRepository.findAll();
    }

}
