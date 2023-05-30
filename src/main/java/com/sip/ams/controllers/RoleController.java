package com.sip.ams.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sip.ams.entities.Article;
import com.sip.ams.entities.Role;
import com.sip.ams.repositories.RoleRepository;
import com.sip.ams.services.ArticlerService;
import com.sip.ams.services.RoleService;




@RestController
@RequestMapping("/role")
@CrossOrigin(origins="*")
public class RoleController {
	
	
    @Autowired
    RoleService roleService;
    
	private final RoleRepository roleRepository;
	@Autowired
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @GetMapping("/")
    public List<Role> getAllArticles() {

        return roleService.getAllRoles();

    }
	/*@GetMapping("/")
    public String listRoles(Model model) {
    	
    	List<Role> roles = (List<Role>) roleRepository.findAll();
    	long nbr =  roleRepository.count();
    	if(roles.size()==0)
    		roles = null;
        model.addAttribute("roles", roles);
        model.addAttribute("nbr", nbr);
        return "role/listRoles";
    }*/
    
    @GetMapping("add")
    public String showAddRoleForm() {

    	//m.addAttribute("Role",new Role("Admin"));
        return "role/addRole";
    }
    
    @PostMapping("add")
    public String addRole(@RequestParam("role") String role) {
        
        System.out.println(role);
        Role r = new Role(role);
        Role rSaved = roleRepository.save(r);
        System.out.println("role = "+ rSaved);
        return "redirect:list";
    }


}