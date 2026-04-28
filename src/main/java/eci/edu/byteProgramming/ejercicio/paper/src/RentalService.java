
// ========================
// RentalService.java
// ========================

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio principal que orquesta el proceso de alquiler.
 * Gestiona el catalogo de peliculas disponibles y coordina
 * la seleccion del cliente con el calculo del recibo.
 *
 * Depende de abstracciones (MembershipStrategy, Movie) y no
 * de implementaciones concretas, cumpliendo el principio DIP.
 */
public class RentalService {

    private final List<Movie> catalog;
    private final ReceiptPrinter receiptPrinter;

    /**
     * Inicializa el servicio con un catalogo vacio y una impresora de recibos.
     */
    public RentalService() {
        this.catalog        = new ArrayList<>();
        this.receiptPrinter = new ReceiptPrinter();
    }

    /**
     * Agrega una pelicula al catalogo del videoclub.
     *
     * @param movie pelicula a registrar
     */
    public void addMovieToCatalog(Movie movie) {
        catalog.add(movie);
    }

    /**
     * Retorna el catalogo completo de peliculas registradas.
     *
     * @return lista de peliculas en el catalogo
     */
    public List<Movie> getCatalog() {
        return catalog;
    }

    /**
     * Muestra en consola las peliculas disponibles del catalogo,
     * numeradas a partir de 1 para facilitar la seleccion del cliente.
     */
    public void displayAvailableMovies() {
        System.out.println("\nPeliculas Disponibles:");
        for (int i = 0; i < catalog.size(); i++) {
            Movie movie = catalog.get(i);
            String availability = movie.isAvailable() ? "Disponible" : "No disponible";
            System.out.printf(" %d. [%s] %s - $%,.0f - %s%n",
                    i + 1,
                    movie.getType(),
                    movie.getTitle(),
                    movie.getPrice(),
                    availability);
        }
    }

    /**
     * Procesa el alquiler para un cliente segun los indices seleccionados
     * y su tipo de membresia. Valida disponibilidad de cada pelicula.
     *
     * @param selectedIndices lista de indices (base 1) elegidos por el cliente
     * @param membership      estrategia de membresia del cliente
     */
    public void processRental(List<Integer> selectedIndices, MembershipStrategy membership) {
        List<Movie> selectedMovies = new ArrayList<>();
        double subtotal = 0;

        for (int index : selectedIndices) {
            if (index < 1 || index > catalog.size()) {
                System.out.println("Numero invalido ignorado: " + index);
                continue;
            }

            Movie movie = catalog.get(index - 1);

            if (!movie.isAvailable()) {
                System.out.println("La pelicula \"" + movie.getTitle() + "\" no esta disponible y fue omitida.");
                continue;
            }

            selectedMovies.add(movie);
            subtotal += movie.getPrice();
        }

        if (selectedMovies.isEmpty()) {
            System.out.println("No se seleccionaron peliculas validas para el alquiler.");
            return;
        }

        receiptPrinter.printReceipt(selectedMovies, membership, subtotal);
    }
}
