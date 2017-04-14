package main.service;

import main.domain.Invoice;
import main.domain.InvoiceAccountId;
import main.domain.InvoiceId;
import main.repository.InvoiceRepository;
import main.repository.InvoiceRepositoryImpl;

import java.util.Set;


public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl() {
        this.invoiceRepository = new InvoiceRepositoryImpl();
    }

    //used for testing only
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public void createInvoice(Invoice invoice) {
        invoiceRepository.createInvoice(invoice);
    }

    @Override
    public Invoice getInvoice(InvoiceId invoiceId) {
        return invoiceRepository.getInvoice(invoiceId);
    }

    @Override
    public void deleteInvoice(InvoiceId invoiceId) {
        invoiceRepository.deleteInvoice(invoiceId);
    }

    @Override
    public Set<Invoice> getInvoices(InvoiceAccountId id) {
        return invoiceRepository.getInvoices(id);
    }

    @Override
    public Set<Invoice> getAllInvoices() {
        return invoiceRepository.getAllInvoices();
    }
}
