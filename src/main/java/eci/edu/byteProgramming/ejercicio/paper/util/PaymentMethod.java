package eci.edu.byteProgramming.ejercicio.paper.util;

import java.util.Date;

/**
 * Clase abstracta base para todos los metodos de pago.
 * Implementa ValidatePayment y define el contrato comun
 * que deben cumplir CreditCardFactory, PaypalFactory y CryptoFactory.
 */
public abstract class PaymentMethod implements ValidatePayment {

    protected double amount;
    protected String transactionID;
    protected String customerID;
    protected String currency;
    protected Date timestamp;
    protected PaymentStatus status;
    protected String description;

    /**
     * Constructor base del metodo de pago.
     *
     * @param amount      monto de la transaccion
     * @param customerId  identificador del cliente   ← nombre corregido
     * @param description descripcion del pago
     */
    public PaymentMethod(double amount, String customerId, String description) {
        this.amount      = amount;
        this.customerID  = customerId;   // ← CORRECCIÓN: antes asignaba la variable nula customerID
        this.description = description;
        this.currency    = "USD";
        this.status      = PaymentStatus.PENDING;
        this.timestamp   = new Date();
        this.transactionID = generateTransactionId();
    }

    public abstract boolean processPayment();
    public abstract String getPaymentMethod();

    /**
     * Genera un identificador unico de transaccion basado en timestamp y numero aleatorio.
     *
     * @return ID de transaccion con prefijo TXN
     */
    protected String generateTransactionId() {
        long ts     = System.currentTimeMillis();
        int  random = (int)(Math.random() * 9999);
        return String.format("TXN%d%04d", ts, random);
    }

    /**
     * Genera un ID de transaccion con prefijo segun el tipo de pago.
     *
     * @param paymentType tipo de pago (CREDIT_CARD, PAYPAL, CRYPTO)
     * @return ID de transaccion con prefijo especifico
     */
    protected String generateTransactionIdWithPrefix(String paymentType) {
        String prefix = getPaymentTypePrefix(paymentType);
        long   ts     = System.currentTimeMillis();
        int    random = (int)(Math.random() * 9999);
        return String.format("%s%d%04d", prefix, ts, random);
    }

    private String getPaymentTypePrefix(String paymentType) {
        switch (paymentType) {
            case "CREDIT_CARD": return "CC";
            case "PAYPAL":      return "PP";
            case "CRYPTO":      return "CR";
            default:            return "TX";
        }
    }

    /**
     * Establece un nuevo monto para la transaccion.
     *
     * @param amount nuevo monto
     */
    public void setAmount(double amount) {   // ← CORRECCIÓN: era double setAmount (retorno incorrecto)
        this.amount = amount;
    }

    public double      getAmount()      { return amount;      }
    public String      getTransactionId() { return transactionID; }
    public PaymentStatus getStatus()    { return status;      }
    public void        setStatus(PaymentStatus status) { this.status = status; }
    public String      getCustomerId()  { return customerID;  }
    public String      getDescription() { return description; }
    public Date        getTimestamp()   { return timestamp;   }
}