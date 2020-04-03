package controleur;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import daobinge.PlageHoraireDispoDao;
import modele.PlageHoraireDispo;


@RestController
@CrossOrigin
public class PlageHoraireDispoControleur {

	PlageHoraireDispoDao plagehorairedispodao;
	
	@Autowired
	public PlageHoraireDispoControleur(final PlageHoraireDispoDao plagehorairedispodao)
	{
		this.plagehorairedispodao = plagehorairedispodao;
	}
	
	@GetMapping({"/listeplagehoraire"})
	public List<PlageHoraireDispo> listeplagehoraire()
	{
		return plagehorairedispodao.findAll();
	}
}
