package main.service;

import main.domain.Invoice;
import main.domain.InvoiceAccountId;
import main.domain.InvoiceId;

import java.util.Set;


public interface InvoiceService {

    void createInvoice(Invoice invoice);

    Invoice getInvoice(InvoiceId invoiceId);

    void updateInvoice(Invoice invoice);

    void deleteInvoice(InvoiceId invoiceId);

    Set<Invoice> getInvoices(InvoiceAccountId id);

    Set<Invoice> getAllInvoices();
}
