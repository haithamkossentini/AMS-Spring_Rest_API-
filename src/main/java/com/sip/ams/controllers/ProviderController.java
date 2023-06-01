package com.sip.ams.controllers;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.validation.Path;
import javax.validation.Valid;

import com.sip.ams.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;

import java.io.IOException;

import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;

@RestController
@RequestMapping({"/providers"})
@CrossOrigin(origins="*")
public class ProviderController {
    @Autowired
    //private ProviderRepository providerRepository;
    ProviderService providerService;
	private final java.nio.file.Path root = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/uploads");
	@Autowired
	private ProviderRepository providerRepository;

    @GetMapping("/")
    public List<Provider> getAllProviders() {

        return providerService.getAllProviders();

    }

   /* @PostMapping("/")
    public Provider createProvider(@Valid @RequestBody Provider provider) {
        return providerService.saveProvider(provider);
    }
*/
    @PostMapping("/")
	public Provider uplaodImage(@RequestParam("imageFile") MultipartFile file, @RequestParam("name") String name,
			@RequestParam("email") String email, @RequestParam("address") String address,
			@RequestParam("imageName") String imageName) throws IOException {

		String newImageName = getSaltString().concat(file.getOriginalFilename());
		try {
			Files.copy(file.getInputStream(), this.root.resolve(newImageName));
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}

		Provider provider = new Provider(name, address, email, newImageName);

		providerRepository.save(provider);

		return provider;
	}

    @PutMapping("/{providerId}")
    public Provider updateProvider(@PathVariable Long providerId, @Valid @RequestBody Provider providerRequest) {
        return providerService.updateProvider(providerId,providerRequest);
    }


    @DeleteMapping("/{providerId}")
    public Provider deleteProvider(@PathVariable Long providerId) {
        return providerService.deleteProvider(providerId);
    }

    @GetMapping("/{providerId}")
    public Provider getProvider(@PathVariable Long providerId) {
        return providerService.getOneProviderById(providerId);
    }
 // rundom string to be used to the image name
 	protected static String getSaltString() {
 		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
 		StringBuilder salt = new StringBuilder();
 		Random rnd = new Random();
 		while (salt.length() < 18) { // length of the random string.
 			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
 			salt.append(SALTCHARS.charAt(index));
 		}
 		String saltStr = salt.toString();
 		return saltStr;

 	}

}

