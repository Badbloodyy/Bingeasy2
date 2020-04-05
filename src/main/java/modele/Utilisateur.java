package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "utilisateur")
@Entity
@EntityListeners(AuditingEntityListener.class) // permet d'indiquer à la JPA
//les tables à créer automatiquement quand on met la commande la et @EnableJpaAuditing
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
	
	@OneToMany(mappedBy="utilisateur")
	Set<PlageHoraireDispo> listeplagehorairedispo;
	
	@OneToMany(mappedBy="utilisateur")
	Set<EvalEpisode> listeevalepisode;
	
	@OneToMany(mappedBy="utilisateur")
	Set<EvalSerie> listeevalserie;
	
	@ManyToMany
	@JoinTable(
			name ="utilisateur_serieAbonne",
			joinColumns=@JoinColumn(name= "id_utilisateur"),
			inverseJoinColumns=@JoinColumn(name= "id_serie")
			)
	Set<Serie> listeseriea;
	
	@ManyToMany
	@JoinTable(
	name ="utilisateur_serieFavori",
	joinColumns=@JoinColumn(name= "id_utilisateur"),
	inverseJoinColumns =@JoinColumn(name ="id_serie")
	)
	Set<Serie> listeserief;
	
	@ManyToMany
	@JoinTable(
			name="utilisateur_episode",
			joinColumns = @JoinColumn(name= "id_utilisateur"),
			inverseJoinColumns = @JoinColumn(name ="id_episode")
			)
	Set<Episode> listeepisode;
	
	
}
