package modele;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name ="serie")
@Entity
@EntityListeners(AuditingEntityListener.class) // permet d'indiquer à la JPA
//les tables à créer automatiquement quand on met la commande la et @EnableJpaAuditing
public class Serie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String resume;
	private Double duree;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public Double getDuree() {
		return duree;
	}
	public void setDuree(Double duree) {
		this.duree = duree;
	}
	
	@ManyToOne
	@JoinColumn(name ="id_genre")
	private Genre genre;
	
	@ManyToOne
	@JoinColumn(name = "id_plateforme")
	private PlateForme plateforme;
	
	@ManyToMany(mappedBy="listeseriea")
	Set<Utilisateur> listeutilisateurabonne;
	
	@ManyToMany(mappedBy="listeserief")
	Set<Utilisateur> listeutilisateurfavori;
	
	@OneToMany(mappedBy = "serie")
	Set<EvalSerie> listeevalserie;
	
	@OneToMany(mappedBy = "serie")
	Set<Saison> listesaison;
	
	
}
