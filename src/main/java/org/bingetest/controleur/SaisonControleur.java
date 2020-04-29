package org.bingetest.controleur;

import java.util.List;

import org.bingetest.daobinge.SaisonDao;
import org.bingetest.modele.Saison;
import org.bingetest.modele.Serie;
import org.bingetest.view.MyJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@CrossOrigin
public class SaisonControleur {

	SaisonDao saisondao;
	
	@Autowired
	public SaisonControleur(final SaisonDao saisondao)
	{
		this.saisondao = saisondao;
	}
	
	@GetMapping({"/listesaison"})
	@JsonView(MyJsonView.Saison.class)
	public List<Saison> listesaison()
	{
		System.out.println("J'ai acces!");
		return saisondao.findAll();
		
	}
	
	@PutMapping("/ajoutersaison")
	public boolean ajouterSaison(@RequestBody Saison saison)
	{
		saisondao.save(saison);
		return true;
	}
}
