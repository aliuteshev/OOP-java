package Store;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TastamatDelivery extends DeliverySystem{
    private ArrayList<Tastamat> ListOfPostomat;

    public TastamatDelivery(int orderId, int clientId, int invoiceNumber, ArrayList<Tastamat> listOfPostomat) {
        super(orderId, clientId, invoiceNumber);
        this.ListOfPostomat = listOfPostomat;
    }

}
