package main.facade;

import main.domain.InvoiceAccount;
import main.domain.InvoiceAccountId;
import main.service.InvoiceAccountService;
import main.service.InvoiceAccountServiceImpl;


public class InvoiceAccountFacadeImpl implements InvoiceAccountFacade {

    private final InvoiceAccountService invoiceAccountService;

    public InvoiceAccountFacadeImpl() {
        this.invoiceAccountService = new InvoiceAccountServiceImpl();
    }

    //Used for testing
    public InvoiceAccountFacadeImpl(InvoiceAccountService invoiceAccountService) {
        this.invoiceAccountService = invoiceAccountService;
    }

    public void createInvoiceAccount(InvoiceAccount invoiceAccount) {
        invoiceAccountService.createInvoiceAccount(invoiceAccount);
    }

    public InvoiceAccount getInvoiceAccount(InvoiceAccountId invoiceAccountId) {
        return invoiceAccountService.getInvoiceAccount(invoiceAccountId);
    }
}
