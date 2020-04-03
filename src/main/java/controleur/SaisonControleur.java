package controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import daobinge.SaisonDao;
import modele.Saison;

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
	public List<Saison> listesaison()
	{
		return saisondao.findAll();
	}
}
