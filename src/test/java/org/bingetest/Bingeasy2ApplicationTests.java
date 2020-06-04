package org.bingetest;

import org.junit.jupiter.api.BeforeAll;import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // Va nous permettre de faire des requêtes, get, etc...
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.bingetest.modele.Utilisateur;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class) // Ajoute la possibilité de mettre dans l'ordre l'execution des tests unitaires

class Bingeasy2ApplicationTests { // Tout ce qu'on a fait avant sur la partie ploufie peuvent être fait en test ici ! Mais on va faire de nouvelles
	// choses.

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private ObjectMapper mapper; // Est necessaire pour la méthode de put.
	
	private MockMvc mvc; // A la base devait être static car on avait un beforeall mais ça faisait planter, du coup on a mis un beforeeach
	// et on a enlevé le static.
	
	@BeforeEach
	public void beforeEach()
	{
		mvc = MockMvcBuilders.webAppContextSetup(context) //  En faisant ça, on récupére le contexte de notre application web, donc tout les getteers et tout ça
				.apply(springSecurity())
				.build(); // Design pattern factory
	}
	
	@Test
	void faireHelloUser() throws Exception // Ca sort une erreur 401, droit manquants sur /hello car faut être user. Il faut
	// ajouter un mock en tant qu'user ou  changer l'adresse pour que ça accede a /authorization qui lui est accessible.
	{
		mvc.perform(get("/hello")) // On a fait un import static de mock mvc request builders, ce qui nous permet de faire une requête get.
		// C'est un postman qu'on controle à la main au final.
		.andExpect(status().isOk()); // Controlé par le résult matchers. On demande de renvoyer un status 200 ok .
		
	}

	
	@Test
	@WithMockUser(username = "admin", roles= {"ADMIN"})
	void lectureid() throws Exception {
		mvc.perform(get("/utilisateur/{id}", 2) // Il y avait "/admin/hello" a la base. On peut mettre aussi "/utilisateur/2"
		.contentType(MediaType.APPLICATION_JSON)) // Appartient aussi au get.
		// On peut changer le get par un put par exemple si on met des données, dans ce cas on mettre .accept a la place de
		// contentType , il faut aussi ensuite ajouter le contenu dans la ligne suivante. Exemple montré un peu en dessous.
		.andExpect(status().isOk()); // on peut mettre plein de type de is, isforbidden, etc ... afin de tester les resultats
		
	}
	
	@Test
	@WithMockUser(username = "admin", roles= {"ADMIN"})
	void ajoutuser() throws Exception {
		Utilisateur utilisateur = new Utilisateur(); // Ici on aurait du le faire autrement mais on l'a fait de cette manière basique
		utilisateur.setLogin("Prout3");
		utilisateur.setMotdepasse("pasdemdp3");
		String json = mapper.writeValueAsString(utilisateur); // On transforme notre objet java en json.
		
		mvc.perform(
				put("/user/utilisateur") 
				.contentType(MediaType.APPLICATION_JSON) // Appartient au put
				.accept(MediaType.APPLICATION_JSON)
				.content(json)
				)
		.andExpect(status().isOk()); // on peut mettre plein de type de is, isforbidden, etc ... afin de tester les resultats
		
	}
	
	@Test
	@WithMockUser(username = "admin", roles= {"ADMIN"})
	void explorerjson() throws Exception {
		
		mvc.perform(
				get("/listeutilisateur")) // renvoie normalement une laiste d'utilisateur
				.andExpect(MockMvcResultMatchers
						.jsonPath("$[1].login") // d'ou ces []
						.value("Prout2")
				);
		
		mvc.perform( // On peut faire deux mvc perform sans soucis, ça peut même être pratique. Par exemple on peut simuler
				// la demande d'autorisation, l'ajout d'un token, le fait qu'il modifie quelque chose ensuite, etc ...
				get("/user/utilisateur/1"))
				.andExpect(MockMvcResultMatchers
						.jsonPath("$.login") // Pour que ça marche, il faut mettre du $, pareil au dessus, va savoir pourquoi
						.value("Prout2")
				);
		
	}
	
	@Test
	// @WithMockUser(username = "admin", roles= {"ADMIN"})
	@Order(1)
	void inscriptionuser() throws Exception {
		Utilisateur utilisateur = new Utilisateur(); // Ici on aurait du le faire autrement mais on l'a fait de cette manière basique
		utilisateur.setLogin("Testuser5"); // Créer un utilisateur
		utilisateur.setMotdepasse("testmdp5");
		String json = mapper.writeValueAsString(utilisateur); // On transforme notre objet java en json.
		
		mvc.perform(
				put("/inscription") 
				.contentType(MediaType.APPLICATION_JSON) // Appartient au put
				.accept(MediaType.APPLICATION_JSON)
				.content(json)
				)
		.andExpect(status().isOk()); 
		
		String token = mvc.perform( // On créer le token et on le récupere.
				post("/authentification") 
				.contentType(MediaType.APPLICATION_JSON) // Appartient au put
				.accept(MediaType.APPLICATION_JSON)
				.content(json)
				)
		.andExpect(status().isOk()) // on retourne le statut
		.andReturn() // Retourne le résultat de la requete executé
		.getResponse() // On recupere la réponse
		.getContentAsString(); // On la met en string
		
		// System.out.println(token);
		
		mvc.perform(
				get("/hello")
				.header("authorization", "Bearer" + token)
				)
		.andExpect(status().isOk());
	}
	
}
