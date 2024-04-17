package Store;

import java.util.ArrayList;

public class PrimeClient extends Client {
    private int discount;

    public PrimeClient(int id, String name, String surname, ArrayList<Order> orderHistory) {
        super(id, name, surname, orderHistory);
        this.discount = 5;
    }
}