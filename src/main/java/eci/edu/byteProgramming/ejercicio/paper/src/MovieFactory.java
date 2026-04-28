
// ========================
// MovieFactory.java
// ========================

/**
 * Fabrica de peliculas. Aplica el patron Factory Method para centralizar
 * la creacion de instancias de Movie segun su tipo, evitando que el codigo
 * cliente dependa de clases concretas.
 */
public class MovieFactory {

    public static final String TYPE_PHYSICAL = "FISICA";
    public static final String TYPE_DIGITAL  = "DIGITAL";

    /**
     * Crea y retorna una instancia de Movie segun el tipo especificado.
     *
     * @param type      tipo de pelicula: "FISICA" o "DIGITAL"
     * @param title     titulo de la pelicula
     * @param price     precio de alquiler
     * @param available disponibilidad de la pelicula
     * @return instancia concreta de PhysicalMovie o DigitalMovie
     * @throws IllegalArgumentException si el tipo no es reconocido
     */
    public static Movie create(String type, String title, double price, boolean available) {
        switch (type.toUpperCase()) {
            case TYPE_PHYSICAL:
                return new PhysicalMovie(title, price, available);
            case TYPE_DIGITAL:
                return new DigitalMovie(title, price, available);
            default:
                throw new IllegalArgumentException("Tipo de pelicula no reconocido: " + type);
        }
    }
}

