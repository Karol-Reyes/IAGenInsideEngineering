package eci.edu.byteProgramming.ejercicio.paper.src;

// ========================
// MembershipStrategy.java
// ========================

/**
 * Interfaz que define la estrategia de calculo de precio segun la membresia.
 * Patron Strategy: permite intercambiar la logica de descuento sin modificar
 * las clases que la usan.
 */
public interface MembershipStrategy {

    /**
     * Calcula el precio final aplicando las reglas de la membresia.
     *
     * @param subtotal precio base antes de aplicar descuentos
     * @return precio final luego de aplicar la logica de la membresia
     */
    double calculatePrice(double subtotal);

    /**
     * Retorna el nombre descriptivo de la membresia para mostrar en el recibo.
     *
     * @return nombre de la membresia
     */
    String getMembershipName();

    /**
     * Retorna el porcentaje de descuento aplicado por esta membresia.
     *
     * @return porcentaje de descuento (0 si no aplica descuento)
     */
    double getDiscountPercentage();
}