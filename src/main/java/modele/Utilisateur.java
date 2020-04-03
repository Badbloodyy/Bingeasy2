package modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "utilisateur")
@Entity
public class Utilisateur {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String login;
	private String motdepasse;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMotdepasse() {
		return motdepasse;
	}
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}
	
	@OneToMany(mappedBy="PlageHoraireDispo")
	List<PlageHoraireDispo> listeplagehoraire = new ArrayList<>();
	
	@OneToMany(mappedBy="EvalEpisode")
	List<EvalEpisode> listeevalepisode = new ArrayList<>();
	
	@OneToMany(mappedBy="EvalSerie")
	List<EvalSerie> listeevalserie = new ArrayList<>();

}
