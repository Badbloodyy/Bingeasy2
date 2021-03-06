package org.bingetest.controleur;


import java.util.List;

import org.bingetest.daobinge.PlateFormeDao;
import org.bingetest.modele.PlateForme;
import org.bingetest.view.MyJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;


@RestController
@CrossOrigin
public class PlateFormeControleur {

	PlateFormeDao plateformedao;
	
	@Autowired
	public PlateFormeControleur(final PlateFormeDao plateformedao)
	{
		this.plateformedao = plateformedao;
	}
	@JsonView(MyJsonView.PlateformeDB.class)
	@GetMapping({"/listeplateforme"})
	public List<PlateForme> listeplateforme()
	{
		return plateformedao.findAll();
	}
}