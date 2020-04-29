package org.bingetest.controleur;



import java.util.List;

import org.bingetest.daobinge.SerieDao;
import org.bingetest.modele.Serie;
import org.bingetest.view.MyJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@CrossOrigin
public class SerieControleur {

	SerieDao seriedao;
	
	@Autowired
	public SerieControleur(final SerieDao seriedao)
	{
		this.seriedao = seriedao;
	}
	
	@GetMapping("/listeserie")
	@JsonView(MyJsonView.Serie.class)
	public List<Serie> listeserie()
	{
		return seriedao.findAll();
	}
	
	@PutMapping("/ajouterserie")
	public boolean ajouterSerie(@RequestBody Serie serie)
	{
		seriedao.save(serie);
		return true;
	}
}