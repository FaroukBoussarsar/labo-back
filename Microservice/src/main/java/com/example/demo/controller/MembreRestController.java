package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EvenementBean;
import com.example.demo.OutilBean;
import com.example.demo.PublicationBean;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Membre;
import com.example.demo.proxies.Evenementproxy;
import com.example.demo.proxies.OutilProxy;
import com.example.demo.proxies.PublicationProxy;
import com.example.demo.service.IMemberService;

@RestController
public class MembreRestController {
	@Autowired
	IMemberService memberservice;
	@Autowired
	PublicationProxy publicationproxy;
	@Autowired
	OutilProxy outilProxy; 
	@Autowired
	Evenementproxy evenementproxy; 
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/membres")
	public List<Membre> findAllmembres()
	{
		return memberservice.findAll();
	}
	 @CrossOrigin(origins = "http://localhost:4200")
		@GetMapping(value = "/membres/ens")
		public List<EnseignantChercheur> findAllmembresens()
		{
			return memberservice.findAllEnseignants();
		}
	 @CrossOrigin(origins = "http://localhost:4200")
		@GetMapping(value = "/membres/etd")
		public List<Etudiant> findAllmembresetd()
		{
		 	
			return memberservice.findAllEtudiants();
		}
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/membres/{id}")
	public Membre findoneMembre(@PathVariable Long id)
	{
		return memberservice.findMember(id);
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/membres")
	public Membre addMembre(@RequestBody Etudiant etd)
	{
		return memberservice.addMember(etd);
	}
	/* @CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/membres/enseignant")
	public Membre addMembre(@RequestBody EnseignantChercheur ens)
	{
		return memberservice.addMember(ens);
	}*/
	 @CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(value="/membres/{id}")
	public Membre updatemembre(@PathVariable Long id, @RequestBody Etudiant p)
	{
		p.setId(id);
		return memberservice.updateMember(p);
	}

	/*@PutMapping(value="/membres/enseignant/{id}")
	public Membre updateMembre(@PathVariable Long id, @RequestBody EnseignantChercheur p)
	{
		p.setId(id);
	       return memberservice.updateMember(p);
	}*/
	
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/publications")
	public CollectionModel<PublicationBean> listerpublication()
	{
		return publicationproxy.listeDesPublications();
		
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/outils")
	public CollectionModel<OutilBean> listeroutil(){
		return outilProxy.listeDesoutils(); 
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/evenements")
	public CollectionModel<EvenementBean> listerevenements(){
		return evenementproxy.listeDesEvenements();  
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/publications/{id}")
	public EntityModel<PublicationBean> listerunepublication(@PathVariable Long id)
	{
		return publicationproxy.recupererUnePublication(id);
		
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/outils/{id}")
	public EntityModel<OutilBean> listeruneoutil(@PathVariable Long id)
	{
		return outilProxy.recupererUneoutil(id); 
		
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/evenements/{id}")
	public EntityModel<EvenementBean> listeruneevenement(@PathVariable Long id)
	{
		return evenementproxy.recupererUneEvenement(id); 
		
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/publications/auteur/{id}")
	public List<PublicationBean>listerpublicationbymembre(@PathVariable(name="id") Long idaut)
	{
		return memberservice.findPublicationparauteur(idaut);		
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/outils/developpeur/{id}")
	public List<OutilBean>listeroutilbydeveloppeur(@PathVariable(name="id") Long iddev)
	{
		return memberservice.findoutilpardeveloppeur(iddev)	; 	
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/evenements/organisateur/{id}")
	public List<EvenementBean>listerevenementsbyorganisateur(@PathVariable(name="id") Long idorg)
	{
		return memberservice.findevenementparorganisateur(idorg); 	
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/fullmember/{id}")
	public Membre findAFullMember(@PathVariable(name="id") Long id)
	{
		Membre mbr=memberservice.findMember(id);
		mbr.setPubs(memberservice.findPublicationparauteur(id));
		mbr.setEvenements(memberservice.findevenementparorganisateur(id));
		mbr.setOutils(memberservice.findoutilpardeveloppeur(id));
		return mbr;		
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(value="/membres/affecterpub/{idetd}/{idevt}")
	public Membre affecterPub(@PathVariable Long idetd , @PathVariable Long idpub )
	{
		
	        memberservice.affecterauteurTopublication(idetd, idpub);
	        Membre mbr=memberservice.findMember(idetd);
			mbr.setPubs(memberservice.findPublicationparauteur(idetd));
			mbr.setEvenements(memberservice.findevenementparorganisateur(idetd));
			mbr.setOutils(memberservice.findoutilpardeveloppeur(idetd));
			return mbr;	
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(value="/membres/affecteroutil/{idetd}/{idevt}")
	public Membre affecteroutil(@PathVariable Long idetd , @PathVariable Long idoutil )
	{
		
	        memberservice.affecterdeveloppeurTooutil(idetd, idoutil);
	        Membre mbr=memberservice.findMember(idetd);
			mbr.setPubs(memberservice.findPublicationparauteur(idetd));
			mbr.setEvenements(memberservice.findevenementparorganisateur(idetd));
			mbr.setOutils(memberservice.findoutilpardeveloppeur(idetd));
			return mbr;	
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(value="/membres/affecterevt/{idetd}/{idevt}")
	public Membre affecterevt(@PathVariable Long idetd , @PathVariable Long idevt )
	{
		
	        memberservice.affecterorganisateurToevt(idetd, idevt);
	        Membre mbr=memberservice.findMember(idetd);
			mbr.setPubs(memberservice.findPublicationparauteur(idetd));
			mbr.setEvenements(memberservice.findevenementparorganisateur(idetd));
			mbr.setOutils(memberservice.findoutilpardeveloppeur(idetd));
			return mbr;	
	}
	 @CrossOrigin(origins = "http://localhost:4200")
		@PutMapping(value="/membres/etudiant/{idetd}/{idens}")
		public Membre affecter(@PathVariable Long idetd , @PathVariable Long idens )
		{
			
		       return memberservice.affecterencadrantToetudiant(idetd, idens);
		}
	 @CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping(value = "/membres/{id}")
	public Membre deleteMember(@PathVariable Long id)
	{
		 Membre mbr=memberservice.findMember(id);
		
		mbr.setPubs(memberservice.findPublicationparauteur(id));
		mbr.setEvenements(memberservice.findevenementparorganisateur(id));
		mbr.setOutils(memberservice.findoutilpardeveloppeur(id));
		memberservice.deleteMember(id);
		return mbr;
	}
	


}
