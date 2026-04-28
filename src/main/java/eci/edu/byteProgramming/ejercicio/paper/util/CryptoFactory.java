package eci.edu.byteProgramming.ejercicio.paper.util;

/**
 * Factory concreta que crea pagos con criptomonedas.
 * Implementa PaymentFactory para integrarse con ECIPayment.
 */
public class CryptoFactory extends PaymentMethod implements PaymentFactory {

    private String walletAddress;
    private String cryptoType;
    private double walletBalance;
    private String blockchainHash;

    public CryptoFactory(double amount, String customerId, String description,
                         String walletAddress, String cryptoType, double walletBalance) {
        super(amount, customerId, description);
        this.walletAddress = walletAddress;
        this.cryptoType    = cryptoType;
        this.walletBalance = walletBalance;
        // ← CORRECCIÓN: se eliminó this.token = token (parámetro inexistente en el constructor)
    }

    /**
     * Implementacion de PaymentFactory: crea una nueva instancia de CryptoFactory
     * reutilizando la wallet y tipo de cripto configurados.
     *
     * @param amount      monto del pago
     * @param customerId  id del cliente
     * @param description descripcion del pago
     * @return nueva instancia de CryptoFactory lista para procesar
     */
    @Override
    public PaymentMethod createPaymentMethod(double amount, String customerId, String description) {
        return new CryptoFactory(amount, customerId, description,
                walletAddress, cryptoType, walletBalance);
    }

    @Override
    public boolean validatePaymentMethod() {
        return validateWalletAddress() && validateBalance();
    }

    private boolean validateWalletAddress() {
        return walletAddress != null && walletAddress.length() >= 26;
    }

    private boolean validateBalance() {
        return walletBalance >= amount;
    }

    @Override
    public boolean processPayment() {
        System.out.println("Processing Cryptocurrency payment...");

        if (!validatePaymentMethod()) {
            System.out.println("Crypto validation failed!");
            setStatus(PaymentStatus.FAILED);
            return false;
        }

        setStatus(PaymentStatus.PROCESSING);

        try {
            Thread.sleep(3000);
            this.blockchainHash = generateBlockchainHash();
            System.out.println("Transaction broadcasted to blockchain");
            System.out.println("Blockchain hash: " + blockchainHash);
            setStatus(PaymentStatus.COMPLETED);
            return true;
        } catch (Exception e) {
            setStatus(PaymentStatus.FAILED);
            return false;
        }
    }

    @Override
    public String getPaymentMethod() { return "CRYPTOCURRENCY";  }
    public String getWalletAddress() { return walletAddress;     }
    public String getCryptoType()    { return cryptoType;        }
    public String getBlockchainHash(){ return blockchainHash;    }
}