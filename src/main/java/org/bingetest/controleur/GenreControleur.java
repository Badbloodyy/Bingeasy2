package org.bingetest.controleur;

import java.util.List;

import org.bingetest.daobinge.GenreDao;
import org.bingetest.modele.Genre;
import org.bingetest.view.MyJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@CrossOrigin
public class GenreControleur {
	
	GenreDao genredao;
	
	@Autowired
	public GenreControleur(final GenreDao genredao)
	{
		this.genredao = genredao;
	}
	
	@JsonView(MyJsonView.GenreDB.class)
	@GetMapping({"/listegenre"})
	public List<Genre> listegenre() {
		return genredao.findAll();
	}

}
