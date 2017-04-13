package main.repository;


import main.domain.Invoice;
import main.domain.InvoiceId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InvoiceRepositoryImpl implements InvoiceRepository {

    private final Map<InvoiceId, Invoice> inMemoryRepository = new ConcurrentHashMap();

    public void createInvoice(Invoice invoice) {
        inMemoryRepository.put(invoice.getInvoiceId(), invoice);
    }

    public Invoice getInvoice(InvoiceId invoiceId) {
        return inMemoryRepository.get(invoiceId);
    }

    public void updateInvoice(Invoice invoice) {
        inMemoryRepository.put(invoice.getInvoiceId(), invoice);
    }
}
