package main.facade;

import main.domain.Invoice;
import main.domain.InvoiceAccountId;
import main.domain.InvoiceId;
import main.domain.InvoiceRow;
import main.service.InvoiceService;
import main.testdata.InvoiceBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static main.testdata.InvoiceBuilder.anInvoice;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InvoiceFacadeImplTest {

    private static final InvoiceId INVOICE_ID = InvoiceId.valueOf("123");
    private static final InvoiceAccountId INVOICE_ACCOUNT_ID = InvoiceAccountId.valueOf("465");
    private static final LocalDate LOCAL_DATE = LocalDate.of(2017,3,5);

    private InvoiceFacadeImpl invoiceFacadeImpl;
    private InvoiceService invoiceServiceMock;

    @Before
    public void setUp(){
        invoiceServiceMock = Mockito.mock(InvoiceService.class);
        invoiceFacadeImpl = new InvoiceFacadeImpl(invoiceServiceMock);
    }

    @Test
    public void createInvoice()  {
        Invoice invoice = anInvoice().build();

        invoiceFacadeImpl.createInvoice(invoice);

        verify(invoiceServiceMock).createInvoice(invoice);
    }

    @Test
    public void getInvoice()  {
        Invoice invoice =  anInvoice().build();
        when(invoiceServiceMock.getInvoice(INVOICE_ID)).thenReturn(invoice);

        Invoice invoice1 = invoiceFacadeImpl.getInvoice(INVOICE_ID);

        verify(invoiceServiceMock).getInvoice(INVOICE_ID);
        assertThat(invoice1, is(invoice));
    }

    @Test
    public void deleteInvoice()  {
        Invoice invoice= anInvoice().build();
        when(invoiceServiceMock.getInvoice(INVOICE_ID)).thenReturn(invoice);

        invoiceFacadeImpl.deleteInvoice(INVOICE_ID);

        verify(invoiceServiceMock).deleteInvoice(INVOICE_ID);
    }

    @Test
    public void getInvoices()  {
        Invoice invoice = anInvoice().build();
        when(invoiceServiceMock.getInvoices(INVOICE_ACCOUNT_ID)).thenReturn(Collections.singleton(invoice));

        Set<Invoice> invoices = invoiceFacadeImpl.getInvoices(INVOICE_ACCOUNT_ID);

        verify(invoiceServiceMock).getInvoices(INVOICE_ACCOUNT_ID);
        assertThat(invoices.size(), is(1));
        assertThat(invoices, contains(invoice));
    }

    @Test
    public void getAllPassedDueInvoices(){
        LocalDate localDatePassed =LocalDate.of(2015,10,2);
        LocalDate localDateNotPassed =LocalDate.of(2017,10,2);
        Invoice invoicePassed  = anInvoice().withDueDate(localDatePassed).build();
        /*Invoice invoiceNotPassed =  anInvoice().withDueDate(localDateNotPassed).build();
        Set<Invoice> invoices = new HashSet<>();
        invoices.add(invoicePassed);
        invoices.add(invoiceNotPassed);
*/
        /*when(invoiceServiceMock.getAllInvoices()).thenReturn(invoices);
        Set<Invoice> allPassedDueInvoices = invoiceFacadeImpl.getAllPassedDueInvoices();

        verify(invoiceServiceMock).getAllInvoices();
        assertThat(allPassedDueInvoices.size(), is(1));
        assertThat(allPassedDueInvoices, contains(invoicePassed));*/
    }

}