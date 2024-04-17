package Store;

import java.util.ArrayList;

public class OrdinaryClient extends Client {

    public OrdinaryClient(int id, String name, String surname, ArrayList<Order> orderHistory) {
        super(id, name, surname, orderHistory);
    }
}
