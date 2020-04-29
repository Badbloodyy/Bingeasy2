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

@Table(name = "saison")
@Entity
@EntityListeners(AuditingEntityListener.class) // permet d'indiquer à la JPA
//les tables à créer automatiquement quand on met la commande la et @EnableJpaAuditing
public class Saison {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({MyJsonView.SaisonDB.class, MyJsonView.EpisodeDB.class})
	private Integer id;
	
	@JsonView({MyJsonView.Saison.class, MyJsonView.Episode.class})
	private int numero;
	// private int nombretotalepisode; Ceci est un attribut calculé
	
	
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

	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_serie", nullable=false)
	@JsonView(MyJsonView.Saison.class)
	private Serie serie;
	
	@OneToMany(mappedBy="saison")
	Set<Episode> listeepisode;


	public Serie getSerie() {
		return serie;
	}
	public void setSerie(Serie serie) {
		this.serie = serie;
	}
	public Set<Episode> getListeepisode() {
		return listeepisode;
	}
	public void setListeepisode(Set<Episode> listeepisode) {
		this.listeepisode = listeepisode;
	}
	
	
	
	
}
