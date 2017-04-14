package api.service;

import api.domain.Invoice;
import api.domain.InvoiceId;
import api.domain.InvoiceAccountId;

import java.util.Set;


public interface InvoiceService {

    void createInvoice(Invoice invoice);

    Invoice getInvoice(InvoiceId invoiceId);

    void deleteInvoice(InvoiceId invoiceId);

    Set<Invoice> getInvoices(InvoiceAccountId id);

    Set<Invoice> getAllInvoices();
}
