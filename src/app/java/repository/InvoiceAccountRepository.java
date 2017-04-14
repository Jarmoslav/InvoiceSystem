package repository;

import api.domain.InvoiceAccount;
import api.domain.InvoiceAccountId;

public interface InvoiceAccountRepository {

    void createInvoiceAccount(InvoiceAccount invoiceAccount);

    InvoiceAccount getInvoiceAccount(InvoiceAccountId invoiceAccountId);
}
