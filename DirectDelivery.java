package Store;

public class DirectDelivery extends DeliverySystem{
    private String city;
    private String streetName;
    private int houseNumber;
    private int flatNumber;

    public DirectDelivery(int orderId, int clientId, int invoiceNumber, String city, String streetName, int houseNumber, int flatNumber) {
        super(orderId, clientId, invoiceNumber);
        this.city = city;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }
}
