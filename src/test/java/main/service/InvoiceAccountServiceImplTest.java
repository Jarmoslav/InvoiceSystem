package main.service;

import main.facade.InvoiceAccountFacade;
import main.domain.InvoiceAccount;
import main.domain.InvoiceAccountId;
import main.repository.InvoiceAccountRepository;
import main.testdata.InvoiceAccountBuilder;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;


public class InvoiceAccountServiceImplTest {

    private static final InvoiceAccountId INVOICE_ACCOUNT_ID = InvoiceAccountId.valueOf("465");

    private InvoiceAccountFacade invoiceAccountFacadeMock;
    private InvoiceAccountServiceImpl invoiceAccountServiceImpl;
    private InvoiceAccountRepository invoiceAccountRepositoryMock;

    @Before
    public void setUp(){
        invoiceAccountFacadeMock = Mockito.mock(InvoiceAccountFacade.class);
        invoiceAccountRepositoryMock = Mockito.mock(InvoiceAccountRepository.class);
        invoiceAccountServiceImpl = new InvoiceAccountServiceImpl(invoiceAccountRepositoryMock);
    }

    @Test
    public void createInvoiceAccount() throws Exception {
        InvoiceAccount invoiceAccount = InvoiceAccountBuilder.anInvoiceAccount()
                .build();

        invoiceAccountServiceImpl.createInvoiceAccount(invoiceAccount);

        Mockito.verify(invoiceAccountRepositoryMock).createInvoiceAccount(invoiceAccount);
    }

    @Test
    public void getInvoiceAccount() throws Exception {
        InvoiceAccount invoiceAccount = InvoiceAccount.builder()
                .withDescription("invoiceAccount")
                .withInvoiceAccountId(INVOICE_ACCOUNT_ID)
                .withInvoices(null)
                .build();

        when(invoiceAccountRepositoryMock.getInvoiceAccount(INVOICE_ACCOUNT_ID)).thenReturn(invoiceAccount);

        InvoiceAccount invoiceAccount1= invoiceAccountServiceImpl.getInvoiceAccount(INVOICE_ACCOUNT_ID);
        Assert.assertThat(invoiceAccount1, Matchers.is(invoiceAccount));
    }

}