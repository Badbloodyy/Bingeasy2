package org.bingetest.modele;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.bingetest.view.MyJsonView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "genre")
@EntityListeners(AuditingEntityListener.class) // permet d'indiquer à la JPA
//les tables à créer automatiquement quand on met la commande la et @EnableJpaAuditing
public class Genre {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@JsonView({MyJsonView.SerieDB.class, MyJsonView.GenreDB.class})
	private Integer id;
	
	@Column(nullable = false, unique = true)
	@JsonView({MyJsonView.Serie.class, MyJsonView.Genre.class})
	private String nom;
	
	
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
	
	@OneToMany(mappedBy = "genre")
	Set<Serie> listeserie;
	
	
	public Set<Serie> getListeserie() { 
		return listeserie;
	}
	public void setListeserie(Set<Serie> listeserie) {
		this.listeserie = listeserie;
	} 
	

}
