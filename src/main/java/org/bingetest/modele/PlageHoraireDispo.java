package org.bingetest.modele;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.bingetest.view.MyJsonView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonView;

@Table(name = "plagehorairedispo")
@Entity
@EntityListeners(AuditingEntityListener.class) // permet d'indiquer à la JPA
//les tables à créer automatiquement quand on met la commande la et @EnableJpaAuditing
public class PlageHoraireDispo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({MyJsonView.UtilisateurDB.class, MyJsonView.PlageHoraireDispoDB.class})
	private Integer id;
	
	@JsonView({MyJsonView.Utilisateur.class, MyJsonView.PlageHoraireDispo.class})
	private int dureeplage;
	
	@JsonView({MyJsonView.Utilisateur.class, MyJsonView.PlageHoraireDispo.class})
	private int heuredebut;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getDureeplage() {
		return dureeplage;
	}
	public void setDureeplage(int dureeplage) {
		this.dureeplage = dureeplage;
	}
	public int getHeuredebut() {
		return heuredebut;
	}
	public void setHeuredebut(int heuredebut) {
		this.heuredebut = heuredebut;
	}
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_utilisateur")
	@JsonView(MyJsonView.PlageHoraireDispo.class)
	private Utilisateur utilisateur;

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	

	
	
}
