package com.example.demo;

import com.example.demo.controller.ProduitController;
import com.example.demo.entity.Categorie;
import com.example.demo.entity.Produit;

import com.example.demo.services.IcategorieService;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
@Transactional
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private ProduitController produitController;
	@Autowired
	private IcategorieService categorieService;


	@Test
	public void testAdddProduit() throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = dateFormat.parse("2020-02-02");

		Categorie categorie = new Categorie();
		categorie.setNom("Test Category");

		Categorie savedCategorie = categorieService.ajouterCategorie(categorie);

		Produit produit = new Produit();
		produit.setNom("produit test final");
		produit.setDisponible(true);
		produit.setCategorie(savedCategorie);
		produit.setQuantite(10);
		produit.setDateCreation(parsedDate);
       Long categorieId = savedCategorie.getId();
		Produit produitSaved = produitController.addProduit(categorieId,produit);
		assertThat(produitSaved).isNotNull();
		assertThat(produitSaved.getNom()).isEqualTo("produit test final");
		assertEquals(produitSaved.getDisponible(), produit.getDisponible());
		assertThat(produitSaved.getQuantite()).isEqualTo(10);
	}
}
