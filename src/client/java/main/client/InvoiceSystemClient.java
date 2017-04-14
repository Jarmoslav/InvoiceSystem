package main.client;


import main.domain.*;
import main.facade.InvoiceAccountFacade;
import main.facade.InvoiceAccountFacadeImpl;
import main.facade.InvoiceFacade;
import main.facade.InvoiceFacadeImpl;

import java.time.LocalDate;
import java.util.*;

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
        System.out.println("Starting invoice system");
        mainGui();
    }

    private void mainGui(){
        System.out.println("--------MAIN SCREEN-----------");
        System.out.println(" press digit + enter to choose");
        System.out.println("1: Invoice Account");
        System.out.println("2: Invoice");
        System.out.println("3: List all Invoices passed due date");
        int number = scanner.nextInt();

        if(number==1){
            System.out.println("1: create InvoiceAccount: " );
            System.out.println("2: get InvoiceAccount (2): " );
            int invoice = scanner.nextInt();
            invoiceAccount(invoice);
        }else if(number==2){
            handleInvoice();
        }else if(number==3){
            listAllInvoicesPassedDueDate();
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
        System.out.println("4: main");
        int invoice = scanner.nextInt();

        if(invoice==1){
            createInvoice(invoice);
        }else if(invoice==2){
            getInvoice();
        }else if(invoice==3){
            getUpdateInvoice();
        }else{
            mainGui();
        }
    }

    private void getUpdateInvoice() {
        System.out.println("----------------------MODIFY INVOICE----------------------");
        System.out.println("Invoice id:");
        String invoiceId = scanner.next();
        InvoiceId id = InvoiceId.valueOf(invoiceId.trim());
        Invoice invoice2 = invoiceFacade.getInvoice(id);
        System.out.println(invoice2);
        System.out.println("debt:"+invoice2.getDebt());
        invoice2.getInvoiceRows().stream().forEach(System.out::println);

        System.out.println("1 : cancel this invoice");
        System.out.println("2 : return");

        int choise = scanner.nextInt();
        if(choise==1){
            invoiceFacade.deleteInvoice(id);
            System.out.println("invoice delete...now returning to handleInvoice");
        }else{
            System.out.println("unknown command");
        }
        handleInvoice();

    }

    private void getInvoice() {
        System.out.println("----------------------GET INVOICE----------------------");
        System.out.println("1: Invoice By Invoice account Id");
        System.out.println("2: Invoice By Invoice id");
        System.out.println("3: return to previous");
        int invoice = scanner.nextInt();
        if(invoice==1){
            System.out.println("Invoice account id:");
            String invoiceAccountId = scanner.next();
            InvoiceAccountId id = InvoiceAccountId.valueOf(invoiceAccountId.trim());
            Set<Invoice> invoices = invoiceFacade.getInvoices(id);
            printAllInvoices(invoices);

        }else if(invoice==2){
            System.out.println("Invoice id:");
            String invoiceId = scanner.next();
            InvoiceId id = InvoiceId.valueOf(invoiceId.trim());
            Invoice invoice2 = invoiceFacade.getInvoice(id);
            System.out.println(invoice2);
            System.out.println("debt:"+invoice2.getDebt());
            printInvoiceRow(invoice2.getInvoiceRows());

        }else{
            System.out.println("invaild input, returing to previous");
        }

        handleInvoice();
    }

    private void printAllInvoices(Set<Invoice> invoices) {
        System.out.println("-------Listing all Invoices for invoiceAccountId----");
        invoices.stream().forEach(invoice -> {
            System.out.println("--------Invoice----");
            System.out.println("Invoice Id: " + invoice.getInvoiceAccountId());
            System.out.println("debt: " + invoice.getDebt());
            System.out.println("dueDate " + invoice.getLocalDate());
            System.out.println("dueDate has passed ?:"+ invoice.isPassedDue());
            printInvoiceRow(invoice.getInvoiceRows());
            System.out.println("------------------");

        });

    }

    private void printInvoiceRow(Set<InvoiceRow> invoiceRows) {
        invoiceRows.stream().forEach(invoiceRow -> {
            System.out.println("");
            System.out.print("  name:"+ invoiceRow.getDescription());
            System.out.print("  debt:"+ invoiceRow.getAmount());
            System.out.println("");
        });
    }

    private void createInvoice(int invoice) {
        if(invoice==1){
            System.out.println("----------------------CREATE INVOICE----------------------");
            System.out.print("InvoiceAccountId:");
            String invoiceAccountId = scanner.next();
            InvoiceAccountId invoiceAccountId1 = InvoiceAccountId.valueOf(invoiceAccountId.trim());
            System.out.println("add due date for this invoice:");
            System.out.println("year: ");
            int year= scanner.nextInt();

            System.out.println("month: ");
            int month= scanner.nextInt();
            System.out.println("day: ");
            int day =scanner.nextInt();

            LocalDate localDate = LocalDate.of(year, month, day);

            System.out.println("1 : add new invoice rows:");
            int inviceRow= scanner.nextInt();
            Invoice invoice1;

            if(inviceRow==1){
                 invoice1 = new Invoice(InvoiceId.generate(), invoiceAccountId1, createInvoiceRows(), localDate);
            }else{
                 invoice1 = new Invoice(InvoiceId.generate(), invoiceAccountId1, Collections.emptySet(), LocalDate.now());
            }
            System.out.println(invoice1);

            invoiceFacade.createInvoice(invoice1);
            Invoice invoice2 = invoiceFacade.getInvoice(invoice1.getInvoiceId());
            System.out.println("invoice id is: "+invoice1.getInvoiceId());
            System.out.println("rember it!: "+invoice1.getInvoiceId());
        }

        handleInvoice();
    }

    private Set<InvoiceRow> createInvoiceRows() {
        Set<InvoiceRow> invoiceRows = new HashSet<>();

        int inviceRow= 1;
        while (inviceRow==1) {
            System.out.println("----------------Create InvoiceRow-----------------");
            System.out.print("amount :");
            Double invoiceRowAmount = scanner.nextDouble();
            System.out.println("description :");
            String invoiceRowDescription = scanner.next();

            InvoiceRow invoiceRow = new InvoiceRow(invoiceRowAmount, invoiceRowDescription);
            invoiceRows.add(invoiceRow);
            System.out.println("invoice row created");
            System.out.println("1 : add new invoice row:");
            System.out.print("2 : go back to invoice:");
            inviceRow= scanner.nextInt();
        }
        System.out.println("invoices rows");
        System.out.println(invoiceRows);
        return invoiceRows;
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
        System.out.println("searching for.... " + invoiceAccountId1);
        InvoiceAccount invoiceAccount = invoiceAccountFacade.getInvoiceAccount(invoiceAccountId1);
        System.out.println(invoiceAccount);


        mainGui();
    }


    public void listAllInvoicesPassedDueDate() {
        Set<Invoice> allInvoices = invoiceFacade.getAllPassedDueInvoices();
        printAllInvoices(allInvoices);;

    }
}
