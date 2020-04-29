package org.bingetest.modele;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.bingetest.view.MyJsonView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="role")
@EntityListeners(AuditingEntityListener.class)
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(MyJsonView.RoleDB.class)
	private int id;
	
	@JsonView(MyJsonView.Role.class)
	private String nom;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@ManyToMany(mappedBy="listerole")
	@JsonIgnore
	Set<Utilisateur> listeutilisateur;


	public Set<Utilisateur> getListeutilisateur() {
		return listeutilisateur;
	}
	public void setListeutilisateur(Set<Utilisateur> listeutilisateur) {
		this.listeutilisateur = listeutilisateur;
	} 
}
