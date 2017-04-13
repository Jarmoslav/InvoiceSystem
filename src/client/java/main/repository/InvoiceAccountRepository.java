package main.repository;

import main.domain.InvoiceAccount;
import main.domain.InvoiceAccountId;

public interface InvoiceAccountRepository {

    void createInvoiceAccount(InvoiceAccount invoiceAccount);

    InvoiceAccount getInvoiceAccount(InvoiceAccountId invoiceAccountId);
}
