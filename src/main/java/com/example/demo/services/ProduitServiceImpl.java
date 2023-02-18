package com.example.demo.services;

import com.example.demo.entity.Categorie;
import com.example.demo.entity.Produit;
import com.example.demo.repository.CategorieRepository;
import com.example.demo.repository.ProduitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Component

public class ProduitServiceImpl implements IProduitService {
    @Autowired
    IcategorieService icategorieService;
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Override
    public Produit addProduit(Long categorieId, Produit produit) {
        var categorie = categorieRepository.findById(categorieId).orElse(null);

        if (categorie == null) {
            throw new EntityNotFoundException("Category not found");
        }

        produit.setDateCreation(new Date());
       produit.setCategorie(categorie);

        return produitRepository.save(produit);
    }




    @Override
    public Produit ajouterProduit(Produit produit) {
        return produitRepository.save(produit);
    }



    @Override
    public Produit modifierProduit(Long id, String nom, int quantite, Long categorieId, Date dateModification) {
        var produit = produitRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        produit.setNom(nom);
        produit.setQuantite(quantite);

        if (categorieId != null) {
            var newCategorie = categorieRepository.findById(categorieId).orElseThrow(() -> new RuntimeException("Category not found"));
            produit.setCategorie(newCategorie);
        }

        if (dateModification != null) {
            produit.setDateModification(dateModification);
        }

        return produitRepository.save(produit);
    }


    @Override
    public boolean supprimerProduit(Long id) {
        try {
            produitRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }




    @Override
    public List<Produit> retrieveAllProducts() {



        return  produitRepository.findAll();
    }
    @Override
    public Optional<Produit> findByID(Long id) {
        return produitRepository.findById(id);
    }

    @Override
    public List<Produit> afficherProduitByCategorie(Long idCategorie) {
        Optional<Categorie> optionalCategorie = icategorieService.findByID(idCategorie);
        if (optionalCategorie.isPresent()) {
            var categorie = optionalCategorie.get();
            return produitRepository.findByCategorie(categorie);
        }
        return Collections.emptyList();
    }


}
