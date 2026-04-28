package eci.edu.byteProgramming.ejercicio.paper.src;

// ========================
// DigitalMovie.java
// ========================

/**
 * Representa una pelicula en formato digital (streaming, descarga).
 * Extiende Movie y define su tipo como "Digital".
 */
public class DigitalMovie extends Movie {

    /**
     * Construye una pelicula digital con los datos provistos.
     *
     * @param title     titulo de la pelicula
     * @param price     precio de alquiler
     * @param available disponibilidad en la plataforma digital
     */
    public DigitalMovie(String title, double price, boolean available) {
        super(title, price, available);
    }

    @Override
    public String getType() {
        return "Digital";
    }
}

