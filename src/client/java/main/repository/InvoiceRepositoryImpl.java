package main.repository;


import main.domain.Invoice;
import main.domain.InvoiceAccountId;
import main.domain.InvoiceId;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InvoiceRepositoryImpl implements InvoiceRepository {

    private final Map<InvoiceId, Invoice> inMemoryRepository = new ConcurrentHashMap();

    @Override
    public void createInvoice(Invoice invoice) {
        inMemoryRepository.put(invoice.getInvoiceId(), invoice);
    }

    @Override
    public Invoice getInvoice(InvoiceId invoiceId)  {
        return inMemoryRepository.get(invoiceId);

    }

    @Override
    public void updateInvoice(Invoice invoice) {
        inMemoryRepository.put(invoice.getInvoiceId(), invoice);
    }

    @Override
    public void deleteInvoice(InvoiceId invoiceId) {
        inMemoryRepository.remove(invoiceId);
    }

    @Override
    public Set<Invoice> getInvoices(InvoiceAccountId id) {
        return inMemoryRepository.entrySet().stream()
                .filter(invoiceIdInvoiceEntry -> invoiceIdInvoiceEntry.getValue().getInvoiceAccountId().equals(id))
                .map(map->map.getValue())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Invoice> getAllInvoices() {
        return inMemoryRepository.entrySet().stream()
                .filter(this::isNotNull)
                .map(invoiceIdInvoiceEntry -> invoiceIdInvoiceEntry.getValue())
                .collect(Collectors.toSet());
    }

    private boolean isNotNull(Map.Entry<InvoiceId, Invoice> invoiceIdInvoiceEntry) {
        return invoiceIdInvoiceEntry.getValue()!=null;
    }


}
