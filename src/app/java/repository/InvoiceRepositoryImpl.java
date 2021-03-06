package repository;


import api.domain.Invoice;
import api.domain.InvoiceAccountId;
import api.domain.InvoiceId;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
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
    public void deleteInvoice(InvoiceId invoiceId) {
        inMemoryRepository.remove(invoiceId);
    }

    @Override
    public Set<Invoice> getInvoices(InvoiceAccountId id) {
        return inMemoryRepository.entrySet().stream()
                .filter(entry->isInvoiceAccount(entry,id))
                .map(map->map.getValue())
                .collect(Collectors.toSet());
    }

    private boolean isInvoiceAccount(Map.Entry<InvoiceId, Invoice> entry, InvoiceAccountId id) {
        return entry.getValue().getInvoiceAccountId().equals(id);
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
