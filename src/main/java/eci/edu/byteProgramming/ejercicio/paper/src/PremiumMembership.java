
package eci.edu.byteProgramming.ejercicio.paper.src;

// ========================
// PremiumMembership.java
// ========================

/**
 * Implementacion de membresia Premium.
 * Aplica un descuento del 20% sobre el subtotal.
 */
public class PremiumMembership implements MembershipStrategy {

    private static final double DISCOUNT_RATE = 0.20;

    @Override
    public double calculatePrice(double subtotal) {
        return subtotal - (subtotal * DISCOUNT_RATE);
    }

    @Override
    public String getMembershipName() {
        return "Premium";
    }

    @Override
    public double getDiscountPercentage() {
        return DISCOUNT_RATE * 100;
    }
}