package main;

import main.client.InvoiceSystemClient;
import main.facade.InvoiceAccountFacade;
import main.facade.InvoiceAccountFacadeImpl;

public class Main {


    public static void main(String[] args) {
        InvoiceSystemClient invoiceSystemClient = new InvoiceSystemClient();
        invoiceSystemClient.startGui();

    }

}
