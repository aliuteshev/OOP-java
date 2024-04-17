package Store;

import java.util.ArrayList;

public class Client {
    private int id;
    private String name;
    private String surname;
    private ArrayList<Order> orderHistory;

    public Client(int id, String name, String surname, ArrayList<Order> orderHistory) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.orderHistory = orderHistory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(ArrayList<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }
}
