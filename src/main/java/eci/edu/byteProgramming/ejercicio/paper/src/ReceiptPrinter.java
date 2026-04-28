package eci.edu.byteProgramming.ejercicio.paper.src;

// ========================
// ReceiptPrinter.java
// ========================

import java.util.List;

/**
 * Responsable exclusivamente de imprimir el recibo de alquiler en consola.
 * Principio SRP: esta clase no calcula precios ni gestiona peliculas,
 * solo se encarga del formato y presentacion del recibo.
 */
public class ReceiptPrinter {

    /**
     * Imprime el recibo completo del alquiler con detalle de peliculas,
     * subtotal, descuento aplicado y total a pagar.
     *
     * @param selectedMovies  lista de peliculas seleccionadas por el cliente
     * @param membership      estrategia de membresia del cliente
     * @param subtotal        suma de precios sin descuento
     */
    public void printReceipt(List<Movie> selectedMovies,
                             MembershipStrategy membership,
                             double subtotal) {

        double discountAmount = subtotal - membership.calculatePrice(subtotal);
        double totalToPay     = membership.calculatePrice(subtotal);

        System.out.println("\n--- RECIBO DE ALQUILER ---");
        System.out.println("Cliente: " + membership.getMembershipName());
        System.out.println("Peliculas:");

        for (Movie movie : selectedMovies) {
            System.out.printf(" - %s (%s) - $%,.0f%n",
                    movie.getTitle(),
                    movie.getType(),
                    movie.getPrice());
        }

        System.out.printf("Subtotal: $%,.0f%n", subtotal);

        if (discountAmount > 0) {
            System.out.printf("Descuento (%.0f%%): $%,.0f%n",
                    membership.getDiscountPercentage(),
                    discountAmount);
        }

        System.out.printf("Total a pagar: $%,.0f%n", totalToPay);
        System.out.println("--------------------------");
        System.out.println("¡Disfrute su pelicula!");
    }
}

