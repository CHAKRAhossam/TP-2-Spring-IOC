package com.example.presentation;

import com.example.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.example.dao", "com.example.metier"})
public class Presentation2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.register(Presentation2.class);
        context.refresh();

        // Récupération du bean IMetier
        IMetier metier = context.getBean(IMetier.class);

        // Exécution et affichage du résultat
        System.out.println("Résultat = " + metier.calcul());
    }
}