package eci.edu.byteProgramming.ejercicio.paper.util;

/**
 * Interfaz que define el contrato del patron Abstract Factory
 * para la creacion de metodos de pago.
 * Permite crear distintos tipos de pago sin que ECIPayment
 * dependa de implementaciones concretas.
 */
public interface PaymentFactory {

    /**
     * Crea una instancia concreta de PaymentMethod con los datos base del pago.
     *
     * @param amount      monto a cobrar
     * @param customerId  identificador del cliente
     * @param description descripcion de la transaccion
     * @return instancia lista para procesar el pago
     */
    PaymentMethod createPaymentMethod(double amount, String customerId, String description);
}