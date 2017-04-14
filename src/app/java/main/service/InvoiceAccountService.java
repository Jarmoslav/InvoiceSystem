package main.service;


import main.domain.InvoiceAccount;
import main.domain.InvoiceAccountId;

public interface InvoiceAccountService {

    void createInvoiceAccount(InvoiceAccount invoiceAccount);

    InvoiceAccount getInvoiceAccount(InvoiceAccountId invoiceAccountId);
}
