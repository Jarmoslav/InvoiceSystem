package api.service;


import api.domain.InvoiceAccount;
import api.domain.InvoiceAccountId;
import repository.InvoiceAccountRepository;
import repository.InvoiceAccountRepositoryImpl;

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
