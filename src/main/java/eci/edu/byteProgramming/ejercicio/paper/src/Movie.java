

// ========================
// Movie.java
// ========================

/**
 * Clase abstracta que representa una pelicula en el videoclub.
 * Aplica encapsulamiento sobre sus atributos y define el contrato
 * de polimorfismo mediante el metodo abstracto getType().
 */
public abstract class Movie {

    private final String title;
    private final double price;
    private final boolean available;

    /**
     * Constructor base para toda pelicula.
     *
     * @param title     titulo de la pelicula
     * @param price     precio de alquiler
     * @param available indica si la pelicula esta disponible para alquilar
     */
    public Movie(String title, double price, boolean available) {
        this.title = title;
        this.price = price;
        this.available = available;
    }

    /**
     * Retorna el tipo de pelicula (Fisica o Digital).
     * Cada subclase debe proporcionar su propia implementacion.
     *
     * @return tipo de pelicula como cadena de texto
     */
    public abstract String getType();

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }
}
