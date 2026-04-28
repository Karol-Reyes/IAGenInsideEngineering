package eci.edu.byteProgramming.ejercicio.paper.src;


// ========================
// BasicMembership.java
// ========================

/**
 * Implementacion de membresia Basica.
 * No aplica ningun descuento sobre el subtotal.
 */
public class BasicMembership implements MembershipStrategy {

    @Override
    public double calculatePrice(double subtotal) {
        return subtotal;
    }

    @Override
    public String getMembershipName() {
        return "Basica";
    }

    @Override
    public double getDiscountPercentage() {
        return 0.0;
    }
}