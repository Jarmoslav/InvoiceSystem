package repository;

import api.domain.InvoiceAccount;
import api.domain.InvoiceAccountId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class InvoiceAccountRepositoryImpl implements InvoiceAccountRepository {

    private final Map<InvoiceAccountId, InvoiceAccount> inMemoryRepository = new ConcurrentHashMap();

    public void createInvoiceAccount(InvoiceAccount invoiceAccount) {
        inMemoryRepository.put(invoiceAccount.getInvoiceAccountId(), invoiceAccount);
    }

    public InvoiceAccount getInvoiceAccount(InvoiceAccountId invoiceAccountId) {
        return inMemoryRepository.get(invoiceAccountId);
    }
}
