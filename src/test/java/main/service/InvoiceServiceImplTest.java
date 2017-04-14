package main.service;

import main.domain.Invoice;
import main.domain.InvoiceAccountId;
import main.domain.InvoiceId;
import main.domain.InvoiceRow;
import main.facade.InvoiceFacade;
import main.repository.InvoiceRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;


public class InvoiceServiceImplTest {

    private InvoiceFacade invoiceFacadeMock;
    private InvoiceServiceImpl invoiceServiceImpl;
    private InvoiceRepository invoiceRepositoryMock;

    @Before
    public void setUp(){
        invoiceFacadeMock = Mockito.mock(InvoiceFacade.class);
        invoiceRepositoryMock = Mockito.mock(InvoiceRepository.class);
        invoiceServiceImpl = new InvoiceServiceImpl(invoiceRepositoryMock);
    }

    @Test
    public void createInvoice() throws Exception {
        Invoice invoice = new Invoice(InvoiceId.generate(), InvoiceAccountId.generate(), Collections.<InvoiceRow>emptySet(), LocalDate.now());

        invoiceServiceImpl.createInvoice(invoice);

        Mockito.verify(invoiceRepositoryMock).createInvoice(invoice);
    }

    @Test
    public void getInvoice() throws Exception {
        InvoiceId invoiceId = InvoiceId.generate();
        Invoice invoice = new Invoice(invoiceId, InvoiceAccountId.generate(), Collections.<InvoiceRow>emptySet(), LocalDate.now());
        Mockito.when(invoiceRepositoryMock.getInvoice(invoiceId)).thenReturn(invoice);

        Invoice invoice1 = invoiceServiceImpl.getInvoice(invoiceId);

        Assert.assertThat(invoice1, Matchers.is(invoice));
    }

    @Test
    public void updateInvoice() throws Exception {


    }

}