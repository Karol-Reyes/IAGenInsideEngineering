package eci.edu.byteProgramming.ejercicio.paper.src;

// ========================
// Main.java
// ========================

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Punto de entrada del sistema de alquiler del Videoclub de Don Mario.
 * Inicializa el catalogo, captura la entrada del usuario y lanza el proceso
 * de alquiler a traves de RentalService.
 */
public class Main {

    public static void main(String[] args) {

        // --- Inicializacion del catalogo usando MovieFactory ---
        RentalService rentalService = new RentalService();

        rentalService.addMovieToCatalog(
                MovieFactory.create(MovieFactory.TYPE_PHYSICAL, "Interestellar", 8000, true));
        rentalService.addMovieToCatalog(
                MovieFactory.create(MovieFactory.TYPE_PHYSICAL, "El Padrino", 7000, false));
        rentalService.addMovieToCatalog(
                MovieFactory.create(MovieFactory.TYPE_DIGITAL, "Inception", 5000, true));
        rentalService.addMovieToCatalog(
                MovieFactory.create(MovieFactory.TYPE_DIGITAL, "Matrix", 6000, true));

        // --- Entrada del usuario ---
        Scanner scanner = new Scanner(System.in);

        rentalService.displayAvailableMovies();

        System.out.print("\nMembresia del cliente (Basica / Premium): ");
        String membershipInput = scanner.nextLine().trim();

        MembershipStrategy membership;
        if (membershipInput.equalsIgnoreCase("Premium")) {
            membership = new PremiumMembership();
        } else {
            membership = new BasicMembership();
        }

        System.out.print("Seleccione peliculas (numeros separados por coma): ");
        String selectionInput = scanner.nextLine().trim();

        // --- Parseo de indices seleccionados ---
        List<Integer> selectedIndices = new ArrayList<>();
        for (String token : selectionInput.split(",")) {
            try {
                selectedIndices.add(Integer.parseInt(token.trim()));
            } catch (NumberFormatException e) {
                System.out.println("Valor ignorado (no es un numero): " + token.trim());
            }
        }

        // --- Procesamiento del alquiler ---
        rentalService.processRental(selectedIndices, membership);

        scanner.close();
    }
}