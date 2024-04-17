package Store;

import java.util.ArrayList;

public class Order {
    private int id;
    private int clientId;
    private ArrayList<OrderProducts> productId;
    private double totalprice;

    public Order(int id, int clientId, ArrayList<OrderProducts> productId, double totalprice) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.totalprice = totalprice;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(id).append(", Client ID: ").append(clientId).append(", Total Price: ").append(totalprice).append("\n");
        for (OrderProducts product : productId) {
            sb.append("Product ID: ").append(product.getProductId())
                    .append(", Price: ").append(product.getPriceOfProduct())
                    .append(", Quantity: ").append(product.getQuantity()).append("\n");
        }
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public ArrayList<OrderProducts> getProductId() {
        return productId;
    }

    public void setProductId(ArrayList<OrderProducts> productId) {
        this.productId = productId;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalPrice(double totalprice) {
        this.totalprice = totalprice;
    }


}
