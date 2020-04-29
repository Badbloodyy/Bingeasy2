package org.bingetest.controleur;

import java.util.List;
import java.util.Optional;


import org.bingetest.daobinge.EvalEpisodeDao;
import org.bingetest.modele.Episode;
import org.bingetest.modele.EvalEpisode;
import org.bingetest.modele.EvalSerie;
import org.bingetest.view.MyJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@CrossOrigin
public class EvalEpisodeController {
	
	EvalEpisodeDao evalepisodedao;
	
	@Autowired
	public EvalEpisodeController(final EvalEpisodeDao evalepisodedao)
	{
	this.evalepisodedao = evalepisodedao;	
	}
	
	// Un utilisateur pourra gérer une liste d'épisodes à voir. De base vide, il pourra ajouter des épisodes à voir, en enlever aprés qu'un épisode
	//soit vu (de manière automatique)
	
	@GetMapping("/listeevalepisode") // possibilité de faire des classements vis à vis de la durée, du nom ou de la série dont l'épisode provient.
	@JsonView(MyJsonView.EvalEpisode.class)
	public List<EvalEpisode> listeevalepisode() {
		return evalepisodedao.findAll();
		// https://www.baeldung.com/spring-data-sorting > pour faire les classements.
	}
	
	@GetMapping("/evalepisodes/{id}")
	@JsonView(MyJsonView.EvalEpisode.class)
	public Optional<EvalEpisode> findById(@PathVariable int id)
	{
		return evalepisodedao.findById(id);
	}
	
	@PutMapping("/ajouterevalepisode")
	public boolean ajouterevalepisode(@RequestBody EvalEpisode evalepisode)
	{
		evalepisodedao.save(evalepisode);
		return true;
	}
	
	// @GetMapping("/ajouterepisode/{episode}")


	
}

