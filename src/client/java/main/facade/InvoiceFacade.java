package main.facade;


import main.domain.Invoice;
import main.domain.InvoiceAccountId;
import main.domain.InvoiceId;

import java.util.Set;

public interface InvoiceFacade {

    void createInvoice(Invoice invoice);

    Invoice getInvoice(InvoiceId invoiceId);

    void updateInvoice(Invoice invoiceId);

    void deleteInvoice(InvoiceId invoiceId);

    Set<Invoice> getInvoices(InvoiceAccountId id);

    Set<Invoice> getAllPassedDueInvoices();
}
