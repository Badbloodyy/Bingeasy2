package controleur;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import daobinge.UtilisateurDao;
import modele.Utilisateur;

@RestController
@CrossOrigin
public class UtilisateurControleur {

	UtilisateurDao utilisateurdao;
	
	@Autowired
	public UtilisateurControleur(final UtilisateurDao utilisateurdao)
	{
		this.utilisateurdao = utilisateurdao;
	}
	
	@GetMapping({"/listeutilisateur"})
	public List<Utilisateur> listeutilisateur()
	{
		return utilisateurdao.findAll();
	}
}