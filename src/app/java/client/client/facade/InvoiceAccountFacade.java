package client.client.facade;


import api.domain.InvoiceAccount;
import api.domain.InvoiceAccountId;

public interface InvoiceAccountFacade {

    void createInvoiceAccount(InvoiceAccount invoiceAccount);

    InvoiceAccount getInvoiceAccount(InvoiceAccountId invoiceAccountId);

}
