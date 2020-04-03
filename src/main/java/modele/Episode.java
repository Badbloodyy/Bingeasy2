package modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "episode")
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






	
}
