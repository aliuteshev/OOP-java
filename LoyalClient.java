package Store;

import java.util.ArrayList;

public class LoyalClient extends Client{
    private int discount;
    private double cashback;

    public LoyalClient(int id, String name, String surname, ArrayList<Order> orderHistory, int discount, double cashback) {
        super(id, name, surname, orderHistory);
        this.discount = discount;
        this.cashback = cashback;
    }
}
