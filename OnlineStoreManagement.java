package Store;

import java.io.*;
import java.util.*;

public class OnlineStoreManagement {
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Client> clients = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Tastamat> tastamats = new ArrayList<>();
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        addInitialProducts();
        initializeTastamats();
        initializeClients();
        initializeOrdersFromFile("orders.txt");
        while (running) {
            System.out.println();
            System.out.println("Welcome to the Online Store Management System!");
            System.out.println("1. Enter the Store");
            System.out.println("2. Administrative Functions");
            System.out.println("3. Exit");

            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    adminmenu();
                    break;
                case 3:
                    System.out.println("Exiting the system...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
    private static void adminmenu(){
        boolean adminSession = true;
        while (adminSession) {
            System.out.println();
            System.out.println("Administrative Functions:");
            System.out.println("1. Manage products");
            System.out.println("2. Manage clients");
            System.out.println("3. Return to Main Menu");
            System.out.print("Please choose an option: ");
            int adminChoice = scanner.nextInt();

            switch (adminChoice) {
                case 1:
                    manageProducts();
                    break;
                case 2:
                    manageClients();
                    break;
                case 3:
                    adminSession = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void manageProducts() {
        boolean adminSession = true;
        while (adminSession) {
            System.out.println();
            System.out.println("Administrative Functions:");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Return to Main Menu");
            System.out.print("Please choose an option: ");
            int adminChoice = scanner.nextInt();
            switch (adminChoice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    adminSession = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void manageClients() {
        boolean adminSession = true;
        while (adminSession) {
            System.out.println();
            System.out.println("Administrative Functions:");
            System.out.println("1. Add client");
            System.out.println("2. View Clients");
            System.out.println("3. Update client information");
            System.out.println("4. Delete client");
            System.out.println("5. Return to Main Menu");

            System.out.print("Please choose an option: ");
            int adminChoice = scanner.nextInt();

            switch (adminChoice) {
                case 1:
                    addClient();
                    break;
                case 2:
                    viewAllClients();
                    break;
                case 3:
                    updateClient();
                    break;
                case 4:
                    deleteClient();
                    break;
                case 5:
                    adminSession = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addClient() {
        File file = new File("users.txt");
        int id = 1;
        String name, surname;

        try {
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String lastLine = "";
                    String currentLine;
                    while ((currentLine = reader.readLine()) != null) {
                        if (!currentLine.trim().isEmpty()) {
                            lastLine = currentLine;
                        }
                    }
                    if (!lastLine.isEmpty()) {
                        String[] parts = lastLine.split(",\\s*");
                        if (parts.length > 0) {
                            id = Integer.parseInt(parts[0]) + 1;
                        }
                    }
                }
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter client name:");
            name = scanner.nextLine();

            System.out.println("Enter client surname:");
            surname = scanner.nextLine();

            try (FileWriter fw = new FileWriter(file, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(id + ", " + name + ", " + surname);
                System.out.println("Client with ID " + id + " added successfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void viewAllClients() {
        File file = new File("users.txt");
        System.out.println("List of all clients:");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the clients.");
            e.printStackTrace();
        }
    }

    private static void updateClient() {
        File file = new File("users.txt");
        ArrayList<String> clientData = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the client you wish to update:");
        int clientId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the new name for the client:");
        String newName = scanner.nextLine();

        System.out.println("Enter the new surname for the client:");
        String newSurname = scanner.nextLine();

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",\\s*");
                    if (parts.length >= 3) {
                        int id = Integer.parseInt(parts[0].trim());
                        if (id == clientId) {
                            line = id + ", " + newName + ", " + newSurname;
                            found = true;
                        }
                    }
                    clientData.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading clients for update.");
            e.printStackTrace();
            return;
        }

        if (found) {
            try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
                for (String client : clientData) {
                    out.println(client);
                }
                System.out.println("Client with ID " + clientId + " has been updated.");
            } catch (IOException e) {
                System.out.println("An error occurred while writing the updated client data.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Client with ID " + clientId + " not found.");
        }
    }

    private static void deleteClient() {
        File file = new File("users.txt");
        ArrayList<String> clients = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the client you wish to delete:");
        int clientId = scanner.nextInt();
        scanner.nextLine();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",\\s*");
                    if (parts.length >= 3) {
                        int id = Integer.parseInt(parts[0].trim());
                        if (id != clientId) {
                            clients.add(line);
                        } else {
                            found = true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the clients for deletion.");
            e.printStackTrace();
            return;
        }

        if (found) {
            try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
                for (String client : clients) {
                    out.println(client);
                }
                System.out.println("Client with ID " + clientId + " has been deleted.");
            } catch (IOException e) {
                System.out.println("An error occurred while writing the updated client data.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Client with ID " + clientId + " not found. No deletion was made.");
        }
    }

    public static void initializeOrdersFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("The orders file does not exist.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 4) {
                    int orderId = Integer.parseInt(parts[0]);
                    int clientId = Integer.parseInt(parts[1]);

                    String productDetail = parts[2];
                    String productName = productDetail.substring(0, productDetail.indexOf(" x "));
                    int quantity = Integer.parseInt(productDetail.substring(productDetail.indexOf(" x ") + 3));
                    int productId = findProductIdByName(productName);

                    double totalPrice = Double.parseDouble(parts[3].replace("₸", "").replace(",", "."));

                    OrderProducts orderProduct = new OrderProducts(productId, totalPrice, quantity);
                    ArrayList<OrderProducts> productList = new ArrayList<>();
                    productList.add(orderProduct);

                    Order order = new Order(orderId, clientId, productList, totalPrice);
                    orders.add(order);
                } else {
                    System.out.println("Invalid order format: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the orders file.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the orders file.");
        } catch (NumberFormatException e) {
            System.out.println("There was a problem parsing the orders file.");
        }
    }
    private static int findProductIdByName(String productName) {
        for (Product product : products){
            if (Objects.equals(productName, product.getName())){
                return product.getId();
            }
        }
        return 1;
    }

    private static void initializeClients() {
        File file = new File("users.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length >= 3) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        String surname = parts[2].trim();
                        clients.add(new Client(id, name, surname, new ArrayList<>()));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format in line: " + line);
                    }
                } else {
                    System.out.println("Invalid client data format in line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading the clients.");
            e.printStackTrace();
        }
    }

    private static void addInitialProducts() {
        File file = new File("products.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length >= 3) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        double price = Double.parseDouble(parts[2].trim());
                        int amount = Integer.parseInt(parts[3].trim());
                        products.add(new Product(id, name, price, amount));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format in line: " + line);
                    }
                } else {
                    System.out.println("Invalid client data format in line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading the clients.");
            e.printStackTrace();
        }
    }

    public static void initializeTastamats() {
        try (Scanner fileScanner = new Scanner(new File("Tastamats.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\s+", 3);
                if (parts.length < 3) {
                    System.err.println("Skipping invalid Tastamat entry: " + line);
                    continue;
                }
                int id = Integer.parseInt(parts[0]);
                String city = parts[1];
                String location = parts[2];
                tastamats.add(new Tastamat(id, city, location));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error loading Tastamats: " + e.getMessage());
        }
    }

    private static void addProduct() {
        System.out.println("Enter product details:");
        int id = getNextProductId();
        System.out.print("Name: ");
        String name = scanner.next();

        System.out.print("Price: ");
        double price = scanner.nextDouble();

        System.out.print("Amount in stock: ");
        int amountInStock = scanner.nextInt();
        scanner.nextLine();
        try (FileWriter fw = new FileWriter("products.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(id + ", " + name + ", " + String.format("%.2f", price) + ", " + amountInStock);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to products.txt.");
            e.printStackTrace();
        }
        System.out.println("Product added successfully!");
    }

    private static int getNextProductId() {
        int highestId = 0;
        for (Product product : products) {
            if (product.getId() > highestId) {
                highestId = product.getId();
            }
        }
        return highestId + 1;
    }

    private static void viewProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void updateProduct() {
        File file = new File("products.txt");
        ArrayList<String> clientData = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the product you wish to update:");
        int clientId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the new name for the product:");
        String newName = scanner.nextLine();

        System.out.println("Enter the new price for the product:");
        double price = scanner.nextDouble();

        System.out.println("Enter the new amount for the product:");
        int amont = scanner.nextInt();

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",\\s*");
                    if (parts.length >= 4) {
                        int id = Integer.parseInt(parts[0].trim());
                        if (id == clientId) {
                            line = id + ", " + newName + ", " + price + ", " + amont;
                            found = true;
                        }
                    }
                    clientData.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading clients for update.");
            e.printStackTrace();
            return;
        }

        if (found) {
            try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
                for (String client : clientData) {
                    out.println(client);
                }
                System.out.println("Product has been updated.");
            } catch (IOException e) {
                System.out.println("An error occurred while writing the updated client data.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Client with ID " + clientId + " not found.");
        }
    }

    private static void deleteProduct() {
        File file = new File("products.txt");
        ArrayList<String> clients = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the client you wish to delete:");
        int clientId = scanner.nextInt();
        scanner.nextLine();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",\\s*");
                    if (parts.length >= 4) {
                        int id = Integer.parseInt(parts[0].trim());
                        if (id != clientId) {
                            clients.add(line);
                        } else {
                            found = true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the clients for deletion.");
            e.printStackTrace();
            return;
        }

        if (found) {
            try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
                for (String client : clients) {
                    out.println(client);
                }
                System.out.println("Product has been deleted.");
            } catch (IOException e) {
                System.out.println("An error occurred while writing the updated client data.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Client with ID " + clientId + " not found. No deletion was made.");
        }
    }

    private static void enterStore(int id) {
        boolean store = true;
        while (store) {
            System.out.println();
            System.out.println("Store:");
            for (Product product : products) {
                System.out.println(product);
            }
            System.out.println();
            System.out.println("1. Make order");
            System.out.println("2. My orders");
            System.out.println("3. Return to Main Menu");

            System.out.print("Please choose an option: ");
            int Choice = scanner.nextInt();

            switch (Choice) {
                case 1:
                    makeOrder(id);
                    break;
                case 2:
                    showMyOrders(id);
                    break;
                case 3:
                    store = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void login() {
        System.out.println("Please enter your name:");
        String name = scanner.next();

        System.out.println("Please enter your surname:");
        String surname = scanner.next();

        Client client = findClient(name, surname);
        if (client != null) {
            System.out.println("Login successful for: " + client.getName() + " " + client.getSurname());
            enterStore(client.getId());
        } else {
            System.out.println("Client not found. Please try again or register.");
        }
    }

    private static Client findClient(String name, String surname) {
        for (Client client : clients) {
            if (client.getName().equalsIgnoreCase(name) && client.getSurname().equalsIgnoreCase(surname)) {
                return client;
            }
        }
        return null;
    }

    public static void makeOrder(int clientId) {
        System.out.println("Enter the ID of the product you wish to order:");
        int productId = scanner.nextInt();

        Product selectedProduct = getProductById(productId);

        if (selectedProduct != null) {
            System.out.println("Enter the quantity you wish to order:");
            int quantity = scanner.nextInt();
            try {
                decreaseProductStock(clientId, productId, quantity);
                OrderProducts orderProduct = new OrderProducts(productId, selectedProduct.getPrice(), quantity);
                double totalPrice = selectedProduct.getPrice() * quantity;
                System.out.println();
                if (ordersSize(clientId) >= 10){
                    System.out.println("Total price: " + totalPrice + "₸");
                    System.out.println("With 10% discount: " + (totalPrice - totalPrice*0.1) + "₸");
                    System.out.println("CashBack: " + totalPrice*0.1 + "₸");
                } else if (ordersSize(clientId) >= 1) {
                    System.out.println("Total price: " + totalPrice + "₸");
                    System.out.println("With 5% discount: " + (totalPrice - totalPrice*0.05) + "₸");
                }else {
                    System.out.println("Total price: " + totalPrice + "₸");
                }
                orders.add(new Order(nextId(), clientId, new ArrayList<>(Collections.singletonList(orderProduct)), totalPrice));
                Order newOrder = new Order(nextId(), clientId, new ArrayList<>(Collections.singletonList(orderProduct)), totalPrice);

                addOrderToClient(clientId, newOrder);
                addOrder(newOrder);
                if (ordersSize(clientId) == 10){
                    System.out.println(ANSI_GREEN + "You got Loyal account" + ANSI_RESET);
                }else if (ordersSize(clientId) == 1){
                    System.out.println(ANSI_GREEN + "You became Prime account" + ANSI_RESET);
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
    }

    private static void showMyOrders(int clientId) {
        for (Order order : orders) {
            if (order.getClientId() == clientId) {
                System.out.println("Total Price: ₸" + order.getTotalprice());
                System.out.println("Products in this order:");
                for (OrderProducts op : order.getProductId()) {
                    Product product = findProductById(op.getProductId());
                    if (product != null) {
                        System.out.println("- " + product.getName() + " x " + op.getQuantity());
                    } else {
                        System.out.println("- Product ID " + op.getProductId() + " x " + op.getQuantity());
                    }
                }
                System.out.println();
            }
        }
    }

    private static void addOrderToClient(int clientId, Order newOrder) {
        Client client = findClientById(clientId);
        if (client != null) {
            client.getOrderHistory().add(newOrder);
            payment(clientId, newOrder);
        } else {
            System.out.println("Client with ID " + clientId + " not found.");
        }
    }

    public static int ordersSize(int clientId) {
        int orderCount = 0;
        for (Order order : orders) {
            if (order.getClientId() == clientId) {
                orderCount++;
            }
        }
        return orderCount;
    }

    private static int nextId() {
        File file = new File("orders.txt");
        int lastId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length > 0) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        lastId = Math.max(lastId, id);
                    } catch (NumberFormatException e) {
                        System.err.println("There was a problem parsing the ID from the line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file");
            e.printStackTrace();
        }
        return lastId + 1;
    }
    private static Client findClientById(int clientId) {
        for (Client client : clients) {
            if (client.getId() == clientId) {
                return client;
            }
        }
        return null;
    }

    private static Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    private static double calculateTotalPrice(ArrayList<OrderProducts> orderProducts) {
        double total = 0;
        for (OrderProducts op : orderProducts) {
            Product product = findProductById(op.getProductId());
            if (product != null) {
                total += product.getPrice() * op.getQuantity();
            }
        }
        return total;
    }

    private static Product findProductById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public static void payment(int clientId, Order order) {
        System.out.println();
        System.out.println("1 to make payment:");
        int num = scanner.nextInt();

        if (num == 1){
            System.out.println("Please enter invoice number of card");
            int invoicenum = scanner.nextInt();
            ArrayList<OrderProducts> orderProducts = new ArrayList<>(order.getProductId());
            double totalPrice = calculateTotalPrice(orderProducts);
            Receipt(clientId, orderProducts, totalPrice);
            System.out.println("A receipt has been generated and saved to 'receipt.txt'.");
            chooseDelivery();
        } else {
            enterStore(clientId);
        }
    }
    private static void Receipt(int clientId, ArrayList<OrderProducts> orderProducts, double totalPrice) {
        Client client = findClientById(clientId);
        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        Date date = new Date();

        StringBuilder receiptContent = new StringBuilder();
        receiptContent.append("Receipt for: ").append(client.getName()).append(" ").append(client.getSurname()).append("\n")
                .append("Date: ").append(date).append("\n\n")
                .append("Products:\n");

        for (OrderProducts op : orderProducts) {
            Product product = getProductById(op.getProductId());
            if (product != null) {
                receiptContent.append("- ").append(product.getName())
                        .append(", Quantity: ").append(op.getQuantity())
                        .append(", Price per unit: ₸").append(op.getPriceOfProduct())
                        .append("\n");
            }
        }

        receiptContent.append("\nTotal Price: ₸").append(totalPrice);

        try (FileWriter writer = new FileWriter("receipt.txt")) {
            writer.write(receiptContent.toString());
        } catch (IOException e) {
            System.out.println("An error occurred while generating the receipt.");
            e.printStackTrace();
        }
    }

    public static void chooseDelivery() {
        System.out.println();
        System.out.println("Please choose your delivery option:");
        System.out.println("1. Direct Delivery");
        System.out.println("2. Tastamat");
        int deliveryChoice = scanner.nextInt();
        scanner.nextLine();
        if (deliveryChoice == 1) {
            handleDirectDelivery();
        } else if (deliveryChoice == 2) {
            handleTastamatDelivery();
        } else {
            System.out.println("Invalid delivery option selected.");
        }
    }
    private static void handleDirectDelivery() {
        System.out.println("Please enter your city:");
        String city = scanner.nextLine();

        System.out.println("Please enter your street name:");
        String streetName = scanner.nextLine();

        System.out.println("Please enter your house number:");
        int houseNumber = scanner.nextInt();

        System.out.println("Please enter your flat number:");
        int flatNumber = scanner.nextInt();

        System.out.println("The order will be delivered within 1 day");
    }

    private static void handleTastamatDelivery() {

        System.out.println("Available Tastamat locations:");
        for (int i = 0; i < tastamats.size(); i++) {
            System.out.println(tastamats.get(i));
        }

        System.out.println("Please select a Tastamat location:");
        int locationChoice = scanner.nextInt();
        scanner.nextLine();

        if (locationChoice < 1 || locationChoice > tastamats.size()) {
            System.out.println("Invalid Tastamat location selected.");
            return;
        }
        System.out.println("The order will be delivered within 1 day");
    }
    private static void addOrder(Order order) {
        File file = new File("orders.txt");
        int id = nextId();
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            String orderLine = id + " - " + order.getClientId();

            for (OrderProducts op : order.getProductId()) {
                String productName = findProductNameById(op.getProductId());
                orderLine += " - " + productName + " x " + op.getQuantity();
            }

            orderLine += " - ₸" + String.format("%.2f", order.getTotalprice());

            out.println(orderLine);
            System.out.println();
            System.out.println("Order saved: " + orderLine);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to orders.txt.");
            e.printStackTrace();
        }
    }
    private static String findProductNameById(int productId) {
        for (Product product : products){
            if(productId == product.getId()){
                return product.getName();
            }
        }
        return "ProductName";
    }
    private static void decreaseProductStock(int ClientId, int productId, int quantityToDecrease) {
        File file = new File("products.txt");
        List<String> fileContent = new ArrayList<>();
        boolean isUpdated = false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] productDetails = line.split(",\\s*");
                if (productDetails.length == 4) {
                    int id = Integer.parseInt(productDetails[0].trim());
                    if (id == productId) {
                        int currentStock = Integer.parseInt(productDetails[3].trim());
                        if (quantityToDecrease > currentStock) {
                            System.out.println("Not enough products in stock to decrease.");
                            enterStore(ClientId);
                            return;
                        }
                        int newStock = currentStock - quantityToDecrease;
                        productDetails[3] = String.valueOf(newStock);
                        line = String.join(", ", productDetails); // Recreate the line with updated stock
                        isUpdated = true;
                    }
                }
                fileContent.add(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file.");
            e.printStackTrace();
            return;
        } catch (NumberFormatException e) {
            System.err.println("There was a problem parsing the product details.");
            e.printStackTrace();
            return;
        }

        if (isUpdated) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (String outputLine : fileContent) {
                    bw.write(outputLine);
                    bw.newLine();
                }
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
            System.out.println("Product stock decreased successfully.");
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
    }

}