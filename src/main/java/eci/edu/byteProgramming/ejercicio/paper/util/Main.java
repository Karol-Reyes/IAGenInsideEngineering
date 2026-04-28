package eci.edu.byteProgramming.ejercicio.paper.util;

/**
 * Punto de entrada del sistema de pagos ECI.
 * Demuestra el flujo completo con los tres metodos de pago disponibles.
 */
public class Main {

    public static void main(String[] args) {

        // --- Infraestructura compartida ---
        Inventory    inventory    = new Inventory();
        Facturation  facturation  = new Facturation();
        Notification notification = new Notification();

        PaymentEventObserver observer = new PaymentEventObserver(inventory, facturation, notification);

        ECIPayment eciPayment = new ECIPayment();
        eciPayment.addObserver(observer);

        System.out.println("========================================");
        System.out.println("  TEST 1: Credit Card Payment");
        System.out.println("========================================");
        PaymentFactory creditCardFactory = new CreditCardFactory(
                0, "", "",
                "4111111111111111", "Juan Perez", "12/26", "123", "Calle 100"
        );
        eciPayment.processPayment(creditCardFactory, 1200.00, "CLIENT001",
                "Gaming Laptop purchase", "Juan Perez", "juan@email.com", "LAPTOP001");

        System.out.println("\n========================================");
        System.out.println("  TEST 2: PayPal Payment");
        System.out.println("========================================");
        PaymentFactory paypalFactory = new PaypalFactory(
                0, "", "",
                "maria@paypal.com", "AUTH_TOKEN_SECURE_12345"
        );
        eciPayment.processPayment(paypalFactory, 800.00, "CLIENT002",
                "Smartphone purchase", "Maria Lopez", "maria@email.com", "PHONE001");

        System.out.println("\n========================================");
        System.out.println("  TEST 3: Crypto Payment");
        System.out.println("========================================");
        PaymentFactory cryptoFactory = new CryptoFactory(
                0, "", "",
                "1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P", "BTC", 500.00
        );
        eciPayment.processPayment(cryptoFactory, 45.99, "CLIENT003",
                "Java Programming Book", "Carlos Ruiz", "carlos@email.com", "BOOK001");
    }
}