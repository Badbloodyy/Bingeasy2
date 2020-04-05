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


@Entity
@Table(name = "episode")
@EntityListeners(AuditingEntityListener.class) // permet d'indiquer à la JPA
//les tables à créer automatiquement quand on met la commande la et @EnableJpaAuditing
public class Episode {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Integer id;
private int numero;
private String nom;
private String resume;
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

@ManyToOne
@JoinColumn(name= "id_saison", nullable=false)
private Saison saison;


	
}
