package org.bingetest.controleur;

import java.util.List;
import java.util.Optional;

import org.bingetest.daobinge.EpisodeDao;
import org.bingetest.modele.Episode;
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
public class EpisodeControleur {

	EpisodeDao episodedao;
	
	@Autowired
	public EpisodeControleur(final EpisodeDao episodedao)
	{
	this.episodedao = episodedao;	
	}
	
	// Un utilisateur pourra gérer une liste d'épisodes à voir. De base vide, il pourra ajouter des épisodes à voir, en enlever aprés qu'un épisode
	//soit vu (de manière automatique)
	
	@GetMapping("/listeepisode") // possibilité de faire des classements vis à vis de la durée, du nom ou de la série dont l'épisode provient.
	@JsonView(MyJsonView.Episode.class)
	public List<Episode> listeepisode() {
		return episodedao.findAll();
		// https://www.baeldung.com/spring-data-sorting > pour faire les classements.
	}
	
	@GetMapping("/episodes/{id}")
	@JsonView(MyJsonView.Episode.class)
	public Optional<Episode> findById(@PathVariable int id)
	{
		return episodedao.findById(id);
	}
	
	@PutMapping("/ajouterepisode")
	public boolean ajouterepisode(@RequestBody Episode episode)
	{
		episodedao.save(episode);
		return true;
	}
	
	// @GetMapping("/ajouterepisode/{episode}")


	
}
