package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.Publication;
import com.example.demo.service.PublicationService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PublicationController {
	@Autowired
	PublicationService publicationService ; 
	 @CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/publications", method = RequestMethod.GET)
	public List<Publication> findPublications() {
		return publicationService.findAll();
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/publications/{id}")
	public Publication findById(@PathVariable Long id){
		return publicationService.findPublication(id); 
 
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/publications/search/titres")
	public Publication findByTitre(@RequestParam String titre) {
		return publicationService.findPublicationByTitre(titre); 
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/publications")
	public Publication addPublication (@RequestBody Publication p) {
		return publicationService.addPublication(p);
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/publications/{id}")
	public Publication updatePublications(@PathVariable Long id , @RequestBody Publication pb) {
		pb.setId(id);
		return publicationService.updatePublication(pb); 
		
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	 @DeleteMapping(value = "/publications/{id}")
		public Publication deletePublication(@PathVariable Long id)
		{
		 Publication mbr=publicationService.findPublication(id);  
			
		
		 publicationService.deleteById(id);
			return mbr;
		}
	
	

}
