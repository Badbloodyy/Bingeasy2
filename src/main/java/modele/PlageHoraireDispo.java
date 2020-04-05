package modele;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "plagehorairedispo")
@Entity
@EntityListeners(AuditingEntityListener.class) // permet d'indiquer à la JPA
//les tables à créer automatiquement quand on met la commande la et @EnableJpaAuditing
public class PlageHoraireDispo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int dureeplage;
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
	
	@ManyToOne
	@JoinColumn(name = "id_utilisateur")
	private Utilisateur utilisateur;
	

	
	
}
