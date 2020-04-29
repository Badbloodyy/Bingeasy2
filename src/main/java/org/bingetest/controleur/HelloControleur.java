package org.bingetest.controleur;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HelloControleur {
	
	 @GetMapping("/")   
	 public String resourcePourToutLeMonde() {
		 return "Hello buddy";   
		 }
	 @GetMapping("/user/hello")   
	 public String resourcePourUtilisateur() {
		 return "Hello user";   
		   }
	 @GetMapping("/admin/hello")   
	 public String resourcePourAdministrateur() {
		 return "Hello admin";   
		   }

}
