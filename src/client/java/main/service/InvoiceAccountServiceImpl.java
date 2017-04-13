package main.service;


import main.domain.InvoiceAccount;
import main.domain.InvoiceAccountId;
import main.repository.InvoiceAccountRepository;
import main.repository.InvoiceAccountRepositoryImpl;

public class InvoiceAccountServiceImpl implements InvoiceAccountService {

    private final InvoiceAccountRepository invoiceAccountRepository;

    public InvoiceAccountServiceImpl() {
        this.invoiceAccountRepository = new InvoiceAccountRepositoryImpl();
    }

    public InvoiceAccountServiceImpl(InvoiceAccountRepository invoiceAccountRepository) {
        this.invoiceAccountRepository = invoiceAccountRepository;
    }

    public void createInvoiceAccount(InvoiceAccount invoiceAccount) {
        invoiceAccountRepository.createInvoiceAccount(invoiceAccount);
    }

    public InvoiceAccount getInvoiceAccount(InvoiceAccountId invoiceAccountId) {

        return invoiceAccountRepository.getInvoiceAccount(invoiceAccountId);
    }
}
