package org.bingetest.modele;

import java.util.Set;

import javax.persistence.CascadeType;
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


@Entity
@Table(name = "episode")
@EntityListeners(AuditingEntityListener.class) // permet d'indiquer à la JPA
//les tables à créer automatiquement quand on met la commande la et @EnableJpaAuditing
public class Episode {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@JsonView({MyJsonView.EpisodeDB.class, MyJsonView.EvalEpisodeDB.class})
private Integer id;

@JsonView({MyJsonView.Episode.class, MyJsonView.EvalEpisode.class})
private int numero;

@JsonView({MyJsonView.Episode.class, MyJsonView.EvalEpisode.class})
private String nom;

@JsonView({MyJsonView.Episode.class, MyJsonView.EvalEpisode.class})
private String resume;

@JsonView({MyJsonView.Episode.class, MyJsonView.EvalEpisode.class})
private Double duree;


public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public int getNumero() {
	return numero;
}
public void setNumero(int numero) {
	this.numero = numero;
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

@OneToMany(mappedBy="episode")
Set<EvalEpisode> listeevalepisode;

@ManyToMany(mappedBy="listeepisode")
Set<Utilisateur> listeutilisateur;

@ManyToOne(cascade = CascadeType.PERSIST)
@JoinColumn(name= "id_saison", nullable=false)
@JsonView(MyJsonView.Episode.class)
private Saison saison;


public Set<EvalEpisode> getListeevalepisode() {
	return listeevalepisode;
}
public void setListeevalepisode(Set<EvalEpisode> listeevalepisode) {
	this.listeevalepisode = listeevalepisode;
}
public Set<Utilisateur> getListeutilisateur() {
	return listeutilisateur;
}
public void setListeutilisateur(Set<Utilisateur> listeutilisateur) {
	this.listeutilisateur = listeutilisateur;
}
public Saison getSaison() {
	return saison;
}
public void setSaison(Saison saison) {
	this.saison = saison;
}


	
}
