package org.bingetest.controleur;


import java.util.List;

import org.bingetest.daobinge.PlageHoraireDispoDao;
import org.bingetest.modele.EvalSerie;
import org.bingetest.modele.PlageHoraireDispo;
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
public class PlageHoraireDispoControleur {

	PlageHoraireDispoDao plagehorairedispodao;
	
	@Autowired
	public PlageHoraireDispoControleur(final PlageHoraireDispoDao plagehorairedispodao)
	{
		this.plagehorairedispodao = plagehorairedispodao;
	}
	
	@GetMapping({"/listeplagehoraire"})
	@JsonView(MyJsonView.PlageHoraireDispo.class)
	public List<PlageHoraireDispo> listeplagehoraire()
	{
		return plagehorairedispodao.findAll();
	}
	
	@PutMapping("ajouterplagehoraire")
	public boolean ajouterplatehoraire(@RequestBody PlageHoraireDispo plageHoraireDispo)
	{
	 plagehorairedispodao.save(plageHoraireDispo);
		return true;
	}
}
