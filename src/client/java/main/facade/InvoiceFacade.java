package main.facade;


import main.domain.Invoice;
import main.domain.InvoiceId;

public interface InvoiceFacade {

    void createInvoice(Invoice invoice);

    Invoice getInvoice(InvoiceId invoiceId);

    void updateInvoice(Invoice invoiceId);

}
