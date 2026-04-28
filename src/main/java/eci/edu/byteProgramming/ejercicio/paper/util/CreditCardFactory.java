package eci.edu.byteProgramming.ejercicio.paper.util;


/**
 * Factory concreta que crea pagos con tarjeta de credito.
 * Implementa PaymentFactory para integrarse con ECIPayment
 * y extiende PaymentMethod para ejecutar el procesamiento.
 */
public class CreditCardFactory extends PaymentMethod implements PaymentFactory {

    private String number;
    private String name;
    private String expirationDate;
    private String cvv;
    private String cardType;
    private String address;

    public CreditCardFactory(double amount, String customerId, String description,
                             String number, String name, String expirationDate,
                             String cvv, String address) {
        super(amount, customerId, description);
        this.number         = number;
        this.name           = name;
        this.expirationDate = expirationDate;
        this.cvv            = cvv;
        this.address        = address;
        this.cardType       = determineCardType(number);
    }

    /**
     * Implementacion de PaymentFactory: crea una nueva instancia de CreditCardFactory
     * reutilizando los datos de tarjeta ya configurados en esta instancia.
     *
     * @param amount      monto del pago
     * @param customerId  id del cliente
     * @param description descripcion del pago
     * @return nueva instancia de CreditCardFactory lista para procesar
     */
    @Override
    public PaymentMethod createPaymentMethod(double amount, String customerId, String description) {
        return new CreditCardFactory(amount, customerId, description,
                number, name, expirationDate, cvv, address);
    }

    @Override
    public boolean validatePaymentMethod() {
        return validateCardNumber() && validateCVV() && validateExpirationDate();
    }

    private boolean validateCardNumber() {
        return number != null && number.length() >= 13 && number.length() <= 19;
    }

    private boolean validateCVV() {
        return cvv != null && cvv.length() >= 3 && cvv.length() <= 4;
    }

    private boolean validateExpirationDate() {
        return expirationDate != null && expirationDate.matches("\\d{2}/\\d{2}");
    }

    @Override
    public boolean processPayment() {
        System.out.println("Processing Credit Card payment...");

        if (!validatePaymentMethod()) {
            System.out.println("Credit Card validation failed!");
            setStatus(PaymentStatus.FAILED);
            return false;
        }

        setStatus(PaymentStatus.PROCESSING);

        try {
            Thread.sleep(2000);
            System.out.println("Contacting bank for card: " + maskCardNumber());
            System.out.println("Payment authorized by bank");
            setStatus(PaymentStatus.COMPLETED);
            return true;
        } catch (Exception e) {
            setStatus(PaymentStatus.FAILED);
            return false;
        }
    }

    @Override
    public String getPaymentMethod() { return "CREDIT_CARD"; }

    private String determineCardType(String cardNumber) {
        if (cardNumber.startsWith("4")) return "VISA";
        if (cardNumber.startsWith("5")) return "MASTERCARD";
        if (cardNumber.startsWith("3")) return "AMEX";
        return "UNKNOWN";
    }

    public String maskCardNumber() {
        return "**** **** **** " + number.substring(number.length() - 4);
    }

    public String getCardHolderName() { return name;     }
    public String getCardType()       { return cardType; }
}