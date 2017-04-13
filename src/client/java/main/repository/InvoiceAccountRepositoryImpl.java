package main.repository;

import main.domain.InvoiceAccount;
import main.domain.InvoiceAccountId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class InvoiceAccountRepositoryImpl implements InvoiceAccountRepository {

    private final Map<InvoiceAccountId, InvoiceAccount> inMemoryRepository = new ConcurrentHashMap();

    public void createInvoiceAccount(InvoiceAccount invoiceAccount) {
        inMemoryRepository.put(invoiceAccount.getInvoiceAccountId(), invoiceAccount);
    }

    public InvoiceAccount getInvoiceAccount(InvoiceAccountId invoiceAccountId) {
        System.out.println("reeepo");
        return inMemoryRepository.get(invoiceAccountId);
    }
}
