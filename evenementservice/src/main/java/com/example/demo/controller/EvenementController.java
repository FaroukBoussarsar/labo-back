package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Evenement;

import com.example.demo.service.EvenementService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EvenementController {
	@Autowired
	EvenementService evenementService;
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/evenements")
	public List<Evenement> findAllevenements() {
		return evenementService.findAll();
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/evenements/{id}")
	public Evenement findoneMembre(@PathVariable Long id) {
		return evenementService.findEvenement(id);
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	 @GetMapping("/evenements/lieu/{id}")
		public List<Evenement> findbylieu(@PathVariable String id ){
		return evenementService.findByLieu(id); 
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	 @GetMapping("/evenements/title/{id}")
		public List<Evenement> findbyTitle(@PathVariable String id ){
			return evenementService.findByTitle(id); 
		}
	 @CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/evenements")
	public Evenement addEvenement(@RequestBody Evenement evt) {
		 return evenementService.addEvenement(evt); 
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/evenements/{id}")
	public Evenement updateEvenement(@PathVariable Long id , @RequestBody Evenement evt) {
		evt.setId(id);
		return evenementService.updateEvenement(evt); 
		
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping(value = "/evenements/{id}")
	public Evenement deleteEvenment(@PathVariable Long id)
	{
		Evenement mbr=evenementService.findEvenement(id);
		
	
		evenementService.deleteEvenement(mbr);
		return mbr;
	}
	


	


}
