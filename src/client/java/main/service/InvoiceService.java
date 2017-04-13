package main.service;

import main.domain.Invoice;
import main.domain.InvoiceId;


public interface InvoiceService {

    void createInvoice(Invoice invoice);

    Invoice getInvoice(InvoiceId invoiceId);

    void updateInvoice(Invoice invoice);

}
