package main.facade;


import main.domain.InvoiceAccount;
import main.domain.InvoiceAccountId;

public interface InvoiceAccountFacade {

    void createInvoiceAccount(InvoiceAccount invoiceAccount);

    InvoiceAccount getInvoiceAccount(InvoiceAccountId invoiceAccountId);

}
