package main.service;

import main.domain.Invoice;
import main.domain.InvoiceId;
import main.repository.InvoiceRepository;
import main.repository.InvoiceRepositoryImpl;


public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl() {
        this.invoiceRepository = new InvoiceRepositoryImpl();
    }

    //used for testing only
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public void createInvoice(Invoice invoice) {
        invoiceRepository.createInvoice(invoice);
    }

    public Invoice getInvoice(InvoiceId invoiceId) {
        return invoiceRepository.getInvoice(invoiceId);
    }

    public void updateInvoice(Invoice invoice) {
        invoiceRepository.updateInvoice(invoice);
    }
}
