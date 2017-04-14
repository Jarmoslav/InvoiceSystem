package main.service;

import main.facade.InvoiceAccountFacade;
import main.domain.CustomerId;
import main.domain.InvoiceAccount;
import main.domain.InvoiceAccountId;
import main.repository.InvoiceAccountRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;


public class InvoiceAccountServiceImplTest {

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
        InvoiceAccount invoiceAccount = InvoiceAccount.builder()
                .withCustomerId(CustomerId.valueOf("c1"))
                .withDescription("invoiceAccount")
                .withInvoiceAccountId(InvoiceAccountId.generate())
                .withInvoices(null)
                .build();

        invoiceAccountServiceImpl.createInvoiceAccount(invoiceAccount);

        Mockito.verify(invoiceAccountRepositoryMock).createInvoiceAccount(invoiceAccount);
    }

    @Test
    public void getInvoiceAccount() throws Exception {
        InvoiceAccountId invoiceId = InvoiceAccountId.valueOf("invoiceA1");
        InvoiceAccount invoiceAccount = InvoiceAccount.builder()
                .withCustomerId(CustomerId.valueOf("c1"))
                .withDescription("invoiceAccount")
                .withInvoiceAccountId(invoiceId)
                .withInvoices(null)
                .build();

        when(invoiceAccountRepositoryMock.getInvoiceAccount(invoiceId)).thenReturn(invoiceAccount);

        InvoiceAccount invoiceAccount1= invoiceAccountServiceImpl.getInvoiceAccount(invoiceId);
        Assert.assertThat(invoiceAccount1, Matchers.is(invoiceAccount));
    }

}