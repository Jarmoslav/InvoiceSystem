package main.client;


import main.domain.*;
import main.facade.InvoiceAccountFacade;
import main.facade.InvoiceAccountFacadeImpl;
import main.facade.InvoiceFacade;
import main.facade.InvoiceFacadeImpl;

import java.util.Collections;
import java.util.Scanner;

public class InvoiceSystemClient {

    Scanner scanner = new Scanner(System.in);
    private final InvoiceAccountFacade invoiceAccountFacade;
    private final InvoiceFacade invoiceFacade;

    public InvoiceSystemClient() {
        this.invoiceAccountFacade = new InvoiceAccountFacadeImpl();
        this.invoiceFacade = new InvoiceFacadeImpl();
    }

    //for testing
    public InvoiceSystemClient(InvoiceAccountFacade invoiceAccountFacade, InvoiceFacade invoiceFacade) {
        this.invoiceAccountFacade = invoiceAccountFacade;
        this.invoiceFacade = invoiceFacade;
    }

    public void startGui(){
        System.out.print("Starting invoice system");
        mainGui();


    }

    private void mainGui(){

        System.out.println("MAIN SCREEN");
        System.out.println("GOTO - press digit + enter to choose");
        System.out.println("InvoiceAccount( 1 ) :");
        System.out.println("Invoice( 2 ) :");
        int number = scanner.nextInt();

        if(number==1){
            System.out.println("create InvoiceAccount (1): " );
            System.out.println("get InvoiceAccount (2): " );
            int invoice = scanner.nextInt();
            invoiceAccount(invoice);
        }else if(number==2){
            handleInvoice();
        }
        else{
            System.out.println("Unknown digit: " + number);
        }
    }

    private void handleInvoice() {
        System.out.println("--------------------------HANDLE INVOICE----------------------");
        System.out.println("---------------Chooose by pressing digit and press enter----------------------");
        System.out.println("1: create invoice");
        System.out.println("2: get invoice");
        System.out.println("3: modify invoice");
        int invoice = scanner.nextInt();

        if(invoice==1){
            System.out.println("----------------------CREATE INVOICE----------------------");
            System.out.print("InvoiceAccountId:");
            String invoiceAccountId = scanner.next();
            InvoiceAccountId invoiceAccountId1 = InvoiceAccountId.valueOf(invoiceAccountId.trim());

            Invoice invoice1 = new Invoice(InvoiceId.generate(), invoiceAccountId1, Collections.<InvoiceRow>emptySet());

            invoiceFacade.createInvoice(invoice1);
            Invoice invoice2 = invoiceFacade.getInvoice(invoice1.getInvoiceId());
            System.out.println("getting the new invoice");
            System.out.println(invoice2);
        }



        mainGui();
    }

    private void invoiceAccount(int invoice) {
        if(invoice==1){
            createInvoiceAccount();
        }else if  (invoice==2){
            getInvoiceAccount(2);

        }else{
            mainGui();
        }

    }



    private void createInvoiceAccount() {
        System.out.print("create customer id: ");
        String customerId = scanner.next();

        System.out.print("create invoice description id: ");
        String description = scanner.next();

        InvoiceAccountId invoiceAccountId = InvoiceAccountId.generate();
        InvoiceAccount invoiceAccount = InvoiceAccount.builder()
                .withCustomerId(CustomerId.valueOf(customerId))
                .withInvoiceAccountId(invoiceAccountId)
                .withDescription(description)
                .withInvoices(Collections.<Invoice>emptyList())
                .build();

        invoiceAccountFacade.createInvoiceAccount(invoiceAccount);

        System.out.println("your account has been created, and account id is: "+invoiceAccountId);
        mainGui();
    }

    private void getInvoiceAccount(int i) {
        System.out.print("get customer invoice by invoiceAccountId: ");
        String invoiceAccountId = scanner.next();

        InvoiceAccountId invoiceAccountId1 = InvoiceAccountId.valueOf(invoiceAccountId.trim());
        System.out.println("searching for...." + invoiceAccountId1);
        InvoiceAccount invoiceAccount = invoiceAccountFacade.getInvoiceAccount(invoiceAccountId1);
        System.out.println(invoiceAccount);
        //System.out.println("INVOICE ACCOUNT:"+ " invoiceAccountId: " + invoiceAccountId + "invoiceAccountId " + invoiceAccount.getInvoiceAccountId());

        mainGui();
    }


}
