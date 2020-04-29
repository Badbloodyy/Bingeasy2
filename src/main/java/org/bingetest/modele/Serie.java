package org.bingetest.modele;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import org.bingetest.view.MyJsonView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonView;

@Table(name ="serie")
@Entity
@EntityListeners(AuditingEntityListener.class) // permet d'indiquer à la JPA
//les tables à créer automatiquement quand on met la commande la et @EnableJpaAuditing
public class Serie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({MyJsonView.SerieDB.class, MyJsonView.SaisonDB.class, MyJsonView.EvalSerieDB.class})
	private Integer id;
	
	@Column(nullable = false, unique = true)
	@JsonView({MyJsonView.Serie.class, MyJsonView.Saison.class, MyJsonView.EvalSerie.class})
	private String nom;
	
	@JsonView({MyJsonView.Serie.class, MyJsonView.Saison.class, MyJsonView.EvalSerie.class})
	private String resume;
	
	
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
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name ="id_genre")
	@JsonView(MyJsonView.Serie.class)
	private Genre genre;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_plateforme")
	@JsonView(MyJsonView.Serie.class)
	private PlateForme plateforme;
	
	@ManyToMany(mappedBy="listeseriea")
	Set<Utilisateur> listeutilisateurabonne;
	
	@ManyToMany(mappedBy="listeserief")
	Set<Utilisateur> listeutilisateurfavori;
	
	@OneToMany(mappedBy = "serie")
	Set<EvalSerie> listeevalserie;
	
	@OneToMany(mappedBy = "serie")
	Set<Saison> listesaison;


	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public PlateForme getPlateforme() {
		return plateforme;
	}
	public void setPlateforme(PlateForme plateforme) {
		this.plateforme = plateforme;
	}
	public Set<Utilisateur> getListeutilisateurabonne() {
		return listeutilisateurabonne;
	}
	public void setListeutilisateurabonne(Set<Utilisateur> listeutilisateurabonne) {
		this.listeutilisateurabonne = listeutilisateurabonne;
	}
	public Set<Utilisateur> getListeutilisateurfavori() {
		return listeutilisateurfavori;
	}
	public void setListeutilisateurfavori(Set<Utilisateur> listeutilisateurfavori) {
		this.listeutilisateurfavori = listeutilisateurfavori;
	}
	public Set<EvalSerie> getListeevalserie() {
		return listeevalserie;
	}
	public void setListeevalserie(Set<EvalSerie> listeevalserie) {
		this.listeevalserie = listeevalserie;
	}
	public Set<Saison> getListesaison() {
		return listesaison;
	}
	public void setListesaison(Set<Saison> listesaison) {
		this.listesaison = listesaison;
	}
	
	
}
