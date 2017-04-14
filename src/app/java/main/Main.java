package main;

import main.client.InvoiceSystemClient;


public class Main {


    public static void main(String[] args) {
        InvoiceSystemClient invoiceSystemClient = new InvoiceSystemClient();
        invoiceSystemClient.startGui();
    }

}
