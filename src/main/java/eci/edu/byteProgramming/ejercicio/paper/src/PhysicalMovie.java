
// ========================
// PhysicalMovie.java
// ========================

/**
 * Representa una pelicula en formato fisico (disco, bluray, etc.).
 * Extiende Movie y define su tipo como "Fisica".
 */
public class PhysicalMovie extends Movie {

    /**
     * Construye una pelicula fisica con los datos provistos.
     *
     * @param title     titulo de la pelicula
     * @param price     precio de alquiler
     * @param available disponibilidad en inventario fisico
     */
    public PhysicalMovie(String title, double price, boolean available) {
        super(title, price, available);
    }

    @Override
    public String getType() {
        return "Fisica";
    }
}