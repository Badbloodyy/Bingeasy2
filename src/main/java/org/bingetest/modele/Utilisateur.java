package org.bingetest.modele;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.bingetest.view.MyJsonView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Table(name = "utilisateur")
@Entity
@EntityListeners(AuditingEntityListener.class) // permet d'indiquer à la JPA
//les tables à créer automatiquement quand on met la commande la et @EnableJpaAuditing
public class Utilisateur {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({MyJsonView.EvalEpisodeDB.class, MyJsonView.EvalSerieDB.class, MyJsonView.UtilisateurDB.class, MyJsonView.PlageHoraireDispoDB.class})
	private Integer id;
	
	@Column(nullable = false, unique = true) // Clé metier / clé organique?
	@JsonView({MyJsonView.EvalEpisode.class, MyJsonView.EvalSerie.class, MyJsonView.Utilisateur.class, MyJsonView.PlageHoraireDispo.class})
	private String login;
	
	// @JsonIgnore
	@JsonView(MyJsonView.UtilisateurComplet.class)
	@Column(nullable = false)
	private String motdepasse;
	
	@JsonView(MyJsonView.UtilisateurComplet.class)
	private boolean actif;
	
	private String nomImageAvatar;
	
	//@Temporal(TemporalType.TIMESTAMP)
	//private Date datecreationcompte;
	
	public String getNomImageAvatar() {
		return nomImageAvatar;
	}
	public void setNomImageAvatar(String nomImageAvatar) {
		this.nomImageAvatar = nomImageAvatar;
	}
	public boolean isActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMotdepasse() {
		return motdepasse;
	}
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}
	
	
	
	@OneToMany(mappedBy="utilisateur")
	@JsonView(MyJsonView.UtilisateurDB.class)
	Set<PlageHoraireDispo> listeplagehorairedispo;
	
	@OneToMany(mappedBy="utilisateur")
	Set<EvalEpisode> listeevalepisode;
	
	@OneToMany(mappedBy="utilisateur")
	Set<EvalSerie> listeevalserie;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name ="utilisateur_serieAbonne",
			joinColumns=@JoinColumn(name= "id_utilisateur"),
			inverseJoinColumns=@JoinColumn(name= "id_serie")
			)
	Set<Serie> listeseriea;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST) // evite le soucis de lazily logging truc, on verra plus tard a quoi ça sert
	@JoinTable(
	name ="utilisateur_role",
	joinColumns=@JoinColumn(name= "id_utilisateur"),
	inverseJoinColumns =@JoinColumn(name ="id_role")
	)
	Set<Role> listerole;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
	name ="utilisateur_serieFavori",
	joinColumns=@JoinColumn(name= "id_utilisateur"),
	inverseJoinColumns =@JoinColumn(name ="id_serie")
	)
	Set<Serie> listeserief;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name="utilisateur_episode",
			joinColumns = @JoinColumn(name= "id_utilisateur"),
			inverseJoinColumns = @JoinColumn(name ="id_episode")
			)
	Set<Episode> listeepisode;

	public Set<PlageHoraireDispo> getListeplagehorairedispo() {
		return listeplagehorairedispo;
	}
	public void setListeplagehorairedispo(Set<PlageHoraireDispo> listeplagehorairedispo) {
		this.listeplagehorairedispo = listeplagehorairedispo;
	}
	public Set<EvalEpisode> getListeevalepisode() {
		return listeevalepisode;
	}
	public void setListeevalepisode(Set<EvalEpisode> listeevalepisode) {
		this.listeevalepisode = listeevalepisode;
	}
	public Set<EvalSerie> getListeevalserie() {
		return listeevalserie;
	}
	public void setListeevalserie(Set<EvalSerie> listeevalserie) {
		this.listeevalserie = listeevalserie;
	}
	public Set<Serie> getListeseriea() {
		return listeseriea;
	}
	public void setListeseriea(Set<Serie> listeseriea) {
		this.listeseriea = listeseriea;
	}
	public Set<Serie> getListeserief() {
		return listeserief;
	}
	public void setListeserief(Set<Serie> listeserief) {
		this.listeserief = listeserief;
	}
	public Set<Episode> getListeepisode() {
		return listeepisode;
	}
	public void setListeepisode(Set<Episode> listeepisode) {
		this.listeepisode = listeepisode;
	}
	public Set<Role> getListerole() {
		return listerole;
	}
	public void setListerole(Set<Role> listerole) {
		this.listerole = listerole;
	}
	
	
	
}
