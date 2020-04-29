package org.bingetest.controleur;


import java.util.List;
import java.util.Optional;


import org.bingetest.daobinge.EvalEpisodeDao;
import org.bingetest.daobinge.EvalSerieDao;
import org.bingetest.modele.Episode;
import org.bingetest.modele.EvalEpisode;
import org.bingetest.modele.EvalSerie;
import org.bingetest.modele.Saison;
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
public class EvalSerieControleur {
	
	EvalSerieDao evalseriedao;
	
	@Autowired
	public EvalSerieControleur(final EvalSerieDao evalseriedao)
	{
	this.evalseriedao = evalseriedao;	
	}
	
	// Un utilisateur pourra gérer une liste d'épisodes à voir. De base vide, il pourra ajouter des épisodes à voir, en enlever aprés qu'un épisode
	//soit vu (de manière automatique)
	
	@GetMapping("/listeevalserie") // possibilité de faire des classements vis à vis de la durée, du nom ou de la série dont l'épisode provient.
	@JsonView(MyJsonView.EvalSerie.class)
	public List<EvalSerie> listeevalserie() {
		return evalseriedao.findAll();
		// https://www.baeldung.com/spring-data-sorting > pour faire les classements.
	}
	
	@GetMapping("/evalseries/{id}")
	public Optional<EvalSerie> findById(@PathVariable int id)
	{
		return evalseriedao.findById(id);
	}
	
	@PutMapping("/ajouterevalserie")
	public boolean ajouterevalserie(@RequestBody EvalSerie evalserie)
	{
		evalseriedao.save(evalserie);
		return true;
	}
	
	// @GetMapping("/ajouterepisode/{episode}")


	
}
