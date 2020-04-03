package controleur;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import daobinge.PlateFormeDao;
import modele.PlateForme;


@RestController
@CrossOrigin
public class PlateFormeControleur {

	PlateFormeDao plateformedao;
	
	@Autowired
	public PlateFormeControleur(final PlateFormeDao plateformedao)
	{
		this.plateformedao = plateformedao;
	}
	
	@GetMapping({"/listeplateforme"})
	public List<PlateForme> listeplateforme()
	{
		return plateformedao.findAll();
	}
}