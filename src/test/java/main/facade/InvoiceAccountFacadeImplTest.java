package main.facade;

import api.domain.InvoiceAccount;
import api.domain.InvoiceAccountId;
import api.service.InvoiceAccountService;
import client.client.facade.InvoiceAccountFacadeImpl;
import main.testdata.InvoiceAccountBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;


public class InvoiceAccountFacadeImplTest {

    private static final InvoiceAccountId INVOICE_ACCOUNT_ID = InvoiceAccountId.valueOf("465");

    private InvoiceAccountFacadeImpl invoiceAccountFacadeImpl;
    private InvoiceAccountService invoiceAccountServiceMock;


    @Before
    public void setUp(){
        invoiceAccountServiceMock = mock(InvoiceAccountService.class);
        invoiceAccountFacadeImpl = new InvoiceAccountFacadeImpl(invoiceAccountServiceMock);
    }
   
    @Test
    public void createInvoiceAccount() {
        InvoiceAccount invoiceAccount = InvoiceAccountBuilder.anInvoiceAccount().build();

        invoiceAccountFacadeImpl.createInvoiceAccount(invoiceAccount);

        Mockito.verify(invoiceAccountServiceMock).createInvoiceAccount(invoiceAccount);
    }

    @Test
    public void getInvoiceAccount() {
        invoiceAccountFacadeImpl.getInvoiceAccount(INVOICE_ACCOUNT_ID);

        Mockito.verify(invoiceAccountServiceMock).getInvoiceAccount(INVOICE_ACCOUNT_ID);
    }

}