package controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import daobinge.EpisodeDao;
import modele.Episode;

@RestController
@CrossOrigin
public class EpisodeControleur {

	EpisodeDao episodedao;
	
	@Autowired
	public EpisodeControleur(final EpisodeDao episodedao)
	{
	this.episodedao = episodedao;	
	}
	
	@GetMapping({"/listeepisode"})
	public List<Episode> listeepisode() {
		return episodedao.findAll();
	}

	
}
