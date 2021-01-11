package com.example.demo.entities;

import java.util.Collection;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("ens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnseignantChercheur extends Membre {
	private String grade;
	private String etablissement;

	@OneToMany(mappedBy = "encadrant")
	@JsonIgnore
	private Collection<Etudiant> listetud;

	public EnseignantChercheur(String cin, String nom, String prenom, Date date, byte[] photo, String cv, String email,
			String password, String grade, String etablissement,Collection<Etudiant> listetud1) {
		super(cin, nom, prenom, date, photo, cv, email, password);
		// TODO Auto-generated constructor stub
		this.listetud = listetud1;
		this.grade = grade;
		this.etablissement = etablissement;
		this.listetud=listetud1; 
	}

}
