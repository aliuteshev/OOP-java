package Store;

public abstract class DeliverySystem {
    private int orderId;
    private int clientId;
    private int invoiceNumber;

    public DeliverySystem(int orderId, int clientId, int invoiceNumber) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.invoiceNumber = invoiceNumber;
    }
}
