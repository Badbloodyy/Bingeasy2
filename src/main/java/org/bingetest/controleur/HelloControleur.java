package org.bingetest.controleur;

import org.bingetest.modele.Utilisateur;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HelloControleur {
	
	 @GetMapping("/hello")   
	 public Utilisateur resourcePourToutLeMonde() {
		 Utilisateur hellocmoi = new Utilisateur(); 
		 hellocmoi.setId(1);
		 hellocmoi.setLogin("MobileGundam");
		 hellocmoi.setActif(true);
		 hellocmoi.setMotdepasse("123456");
		 return hellocmoi;
		  
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
