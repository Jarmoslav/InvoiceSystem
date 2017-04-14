package api.service;


import api.domain.InvoiceAccount;
import api.domain.InvoiceAccountId;

public interface InvoiceAccountService {

    void createInvoiceAccount(InvoiceAccount invoiceAccount);

    InvoiceAccount getInvoiceAccount(InvoiceAccountId invoiceAccountId);
}
