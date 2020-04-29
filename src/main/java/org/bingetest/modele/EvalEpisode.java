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

@Entity
@Table(name = "evalepisode")
@EntityListeners(AuditingEntityListener.class) // permet d'indiquer à la JPA
//les tables à créer automatiquement quand on met la commande la et @EnableJpaAuditing
public class EvalEpisode {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(MyJsonView.EvalEpisodeDB.class)
	private Integer id;
	
	@JsonView(MyJsonView.EvalEpisode.class)
	private double note;
	
	@JsonView(MyJsonView.EvalEpisode.class)
	private String commentaire;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getNote() {
		return note;
	}
	public void setNote(double note) {
		this.note = note;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_utilisateur")
	@JsonView(MyJsonView.EvalEpisode.class)
	private Utilisateur utilisateur;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_episode")
	@JsonView(MyJsonView.EvalEpisode.class)
	private Episode episode;


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Episode getEpisode() {
		return episode;
	}
	public void setEpisode(Episode episode) {
		this.episode = episode;
	}
	

}
