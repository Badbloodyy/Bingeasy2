package controleur;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import daobinge.SerieDao;
import modele.Serie;

@RestController
@CrossOrigin
public class SerieControleur {

	SerieDao seriedao;
	
	@Autowired
	public SerieControleur(final SerieDao seriedao)
	{
		this.seriedao = seriedao;
	}
	
	@GetMapping({"/listeserie"})
	public List<Serie> listeserie()
	{
		return seriedao.findAll();
	}
}