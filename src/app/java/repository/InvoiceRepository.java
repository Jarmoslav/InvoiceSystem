package repository;


import api.domain.Invoice;
import api.domain.InvoiceAccountId;
import api.domain.InvoiceId;

import java.util.Set;

public interface InvoiceRepository {

    void createInvoice(Invoice invoice);

    Invoice getInvoice(InvoiceId invoiceId);

    void deleteInvoice(InvoiceId invoiceId);

    Set<Invoice> getInvoices(InvoiceAccountId id);

    Set<Invoice> getAllInvoices();

}
