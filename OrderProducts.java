package Store;

public class OrderProducts {
    private int productId;
    private double priceOfProduct;
    private int quantity;

    public OrderProducts(int productId, double priceOfProduct, int quantity) {
        this.productId = productId;
        this.priceOfProduct = priceOfProduct;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPriceOfProduct() {
        return priceOfProduct;
    }

    public void setPriceOfProduct(double priceOfProduct) {
        this.priceOfProduct = priceOfProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
