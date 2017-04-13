package main.repository;


import main.domain.Invoice;
import main.domain.InvoiceId;

public interface InvoiceRepository {

    void createInvoice(Invoice invoice);

    Invoice getInvoice(InvoiceId invoiceId);

    void updateInvoice(Invoice invoiceId);
}
