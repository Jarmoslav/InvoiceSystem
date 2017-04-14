package client.client.facade;

import api.domain.InvoiceAccount;
import api.domain.InvoiceAccountId;
import api.service.InvoiceAccountService;
import api.service.InvoiceAccountServiceImpl;


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
