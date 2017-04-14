package main.client;


import main.domain.*;
import main.facade.InvoiceAccountFacade;
import main.facade.InvoiceAccountFacadeImpl;
import main.facade.InvoiceFacade;
import main.facade.InvoiceFacadeImpl;

import java.time.LocalDate;
import java.util.*;

import static main.domain.Invoice.Builder.anInvoice;

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


        try{
            int nr = scanner.nextInt();
            if(nr==1){
                System.out.println("1: create InvoiceAccount: " );
                System.out.println("2: get InvoiceAccount (2): ");
                int invoice = scanner.nextInt();
                invoiceAccount(invoice);
            }else if(nr==2){
                handleInvoice();
            }else if(nr==3){
                listAllInvoicesPassedDueDate();
            }
            else{
                System.out.println("Unknown number, press any key to continue " + nr);

            }
        } catch (InputMismatchException  e){
            System.out.println("wrong input.. redirecting to main");
            scanner.next();
            startGui();
        }
    }

    private void handleInvoice() {
        System.out.println("--------------------------HANDLE INVOICE----------------------");
        System.out.println("---------------Chooose by pressing digit and press enter----------------------");
        System.out.println("1: create invoice");
        System.out.println("2: get invoice");
        System.out.println("3: modify invoice");
        System.out.println("4: main");
        try {
            int invoice = scanner.nextInt();

            if (invoice == 1) {
                createInvoice(invoice);
            } else if (invoice == 2) {
                getInvoice();
            } else if (invoice == 3) {
                updateInvoice();
            } else {
                mainGui();
            }
        } catch (InputMismatchException  e){
            System.out.println("wrong input.. redirecting t");
            scanner.next();
            mainGui();
        }
    }

    private void updateInvoice() {
        System.out.println("----------------------MODIFY INVOICE----------------------");
        System.out.println("Invoice id:");
        try{
            String invoiceId = scanner.next();
            InvoiceId id = InvoiceId.valueOf(invoiceId.trim());
            Invoice invoice = invoiceFacade.getInvoice(id);
            printInvoice(invoice);

            System.out.println("1 : cancel this invoice");
            System.out.println("2 : return");

            int nr = scanner.nextInt();
            if(nr==1){
                invoiceFacade.deleteInvoice(id);
                System.out.println("invoice delete...now returning to handleInvoice");
                handleInvoice();
            }else{
                System.out.println("invaild number");
                handleInvoice();
            }
        }catch (InputMismatchException e){
            System.out.println("wrong input.. redirecting to handle invoice");
            scanner.next();
            System.out.println("wrong input.. redirecting to handle invoice");
        }

    }

    private void getInvoice() {
        System.out.println("----------------------GET INVOICE----------------------");
        System.out.println("1: Invoice By Invoice account Id");
        System.out.println("2: Invoice By Invoice id");
        System.out.println("3: return to previous");
        try {
            int invoice = scanner.nextInt();
            if (invoice == 1) {
                getInvoicesByInvoiceAccount();

            } else if (invoice == 2) {
                getInvoiceByInvoiceId();

            } else {
                System.out.println("invaild input, returing to previous");
            }
        } catch (InputMismatchException e){
            System.out.println("error");
            mainGui();
        }
        handleInvoice();
    }

    private void getInvoiceByInvoiceId() {
        System.out.println("Invoice id:");
        String invoiceId = scanner.next();
        InvoiceId id = InvoiceId.valueOf(invoiceId.trim());
        Invoice invoice = invoiceFacade.getInvoice(id);
        printInvoice(invoice);

    }

    private void getInvoicesByInvoiceAccount() {
        System.out.println("Invoice account id:");
        String invoiceAccountId = scanner.next();
        InvoiceAccountId id = InvoiceAccountId.valueOf(invoiceAccountId.trim());
        Set<Invoice> invoices = invoiceFacade.getInvoices(id);
        printAllInvoices(invoices);
    }

    private void printAllInvoices(Set<Invoice> invoices) {
        System.out.println("-------Listing all Invoices for invoiceAccountId----");
        invoices.stream().forEach(this::printInvoice);

    }

    private void printInvoice(Invoice invoice) {
        System.out.println("");
        System.out.println("--------Invoice info-------");
        System.out.println("Invoice Id: " + invoice.getInvoiceId());
        System.out.println("debt: " + invoice.getDebt());
        System.out.println("dueDate " + invoice.getDueDate());
        System.out.println("dueDate has passed ?:"+ invoice.isPassedDue());
        printInvoiceRow(invoice.getInvoiceRows());
        System.out.println("------------------");
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
            System.out.print("create with InvoiceAccountId:");
            String invoiceAccountId = scanner.next();
            InvoiceAccountId invoiceAccountId1 = InvoiceAccountId.valueOf(invoiceAccountId.trim());

            System.out.println("add due date for this invoice:");
            System.out.println("year: ");
            int year= scanner.nextInt();

            System.out.println("month: ");
            int month= scanner.nextInt();

            System.out.println("day: ");
            int day =scanner.nextInt();

            LocalDate dueDate = LocalDate.of(year, month, day);

            createInvoice(invoiceAccountId1, dueDate);
        }

        handleInvoice();
    }

    private void createInvoice(InvoiceAccountId invoiceAccountId1, LocalDate dueDate) {
        System.out.println("1 : add new invoice rows:");
        System.out.println("2 : go back ");
        Invoice invoice;
        try {
            int nr = scanner.nextInt();

            if (nr == 1) {
                Invoice invoice1 = anInvoice()
                        .withInvoiceId(InvoiceId.generate())
                        .withInvoiceAccountId(invoiceAccountId1)
                        .withInvoiceRows(createInvoiceRows())
                        .withDueDate(dueDate)
                        .build();
                invoiceFacade.createInvoice(invoice1);
            }else{
                handleInvoice();
            }

        }catch (InputMismatchException e){
            System.out.println("wrong input.. redirecting t");
            scanner.next();
            handleInvoice();
        }

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
        try {
            System.out.println("create customer id: ");
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

            System.out.println("your account has been created, and invoice account id is: "+invoiceAccountId);
        } catch (InputMismatchException  e){
            scanner.next();
            System.out.println("wrong input.. redirecting to handle invoice");
            handleInvoice();
        }
        mainGui();
    }

    private void getInvoiceAccount(int i) {
        try {
            System.out.print("get customer invoice by invoiceAccountId: ");
            String invoiceAccountId = scanner.next();

            InvoiceAccountId invoiceAccountId1 = InvoiceAccountId.valueOf(invoiceAccountId.trim());
            System.out.println("searching for.... " + invoiceAccountId1);
            InvoiceAccount invoiceAccount = invoiceAccountFacade.getInvoiceAccount(invoiceAccountId1);
            System.out.println(invoiceAccount);
        }catch (InputMismatchException  e){
            scanner.next();
            System.out.println("wrong input.. redirecting to handle invoice");
            handleInvoice();
        }

        mainGui();
    }


    public void listAllInvoicesPassedDueDate() {
        Set<Invoice> allInvoices = invoiceFacade.getAllPassedDueInvoices();
        printAllInvoices(allInvoices);
        mainGui();
    }

}
