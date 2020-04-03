package controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import daobinge.GenreDao;
import modele.Genre;

@RestController
@CrossOrigin
public class GenreControleur {
	
	GenreDao genredao;
	
	@Autowired
	public GenreControleur(final GenreDao genredao)
	{
		this.genredao = genredao;
	}
	
	@GetMapping({"/listegenre"})
	public List<Genre> listegenre() {
		return genredao.findAll();
	}

}
