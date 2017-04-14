package main.repository;

import main.domain.Invoice;
import main.domain.InvoiceAccountId;
import main.domain.InvoiceId;
import main.domain.InvoiceRow;
import main.testdata.InvoiceBuilder;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import static main.testdata.InvoiceBuilder.anInvoice;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class InvoiceRepositoryImplTest {

    private InvoiceRepositoryImpl invoiceRepositoryImpl;

    @Before
    public void setUp(){
        invoiceRepositoryImpl = new InvoiceRepositoryImpl();
    }

    @Test
    public void createInvoice() throws Exception {
        Invoice invoice = anInvoice().build();
        invoiceRepositoryImpl.createInvoice(invoice);

        Invoice actual = invoiceRepositoryImpl.getInvoice(invoice.getInvoiceId());

        assertThat(actual.getInvoiceId(), is(invoice.getInvoiceId()));
        assertThat(actual.getInvoiceRows(), is(invoice.getInvoiceRows()));
    }

    @Test
    public void getInvoice() throws Exception {
        Invoice invoice = anInvoice().build();

        Invoice actual = invoiceRepositoryImpl.getInvoice(invoice.getInvoiceId());

        assertThat(actual, Matchers.nullValue());
    }

    @Test
    public void deleteInvoice() {
        Invoice invoice = anInvoice().build();
        invoiceRepositoryImpl.createInvoice(invoice);

        invoiceRepositoryImpl.deleteInvoice(invoice.getInvoiceId());

        Invoice invoice1 = invoiceRepositoryImpl.getInvoice(invoice.getInvoiceId());
        assertNull(invoice1);
    }

    @Test
    public void getInvoices(){
        Invoice invoice = anInvoice().build();
        invoiceRepositoryImpl.createInvoice(invoice);

        Set<Invoice> invoices = invoiceRepositoryImpl.getInvoices(invoice.getInvoiceAccountId());

        assertThat(invoices.size(), is(1));
        assertThat(invoices, contains(invoice));
    }

    @Test
    public void getInvoicesWithMissingInvoiceAccountId(){
        InvoiceAccountId invoiceAccountId = InvoiceAccountId.valueOf("123");

        Set<Invoice> invoices = invoiceRepositoryImpl.getInvoices(invoiceAccountId);

        assertThat(invoices.size(), is(0));
    }

    @Test
    public void getAllInvoices(){
        Invoice invoice =  anInvoice().build();
        invoiceRepositoryImpl.createInvoice(invoice);

        Set<Invoice> invoices = invoiceRepositoryImpl.getAllInvoices();

        assertThat(invoices.size(), is(1));
    }

    @Test
    public void getAllInvoicesWhenEmpty(){
        Set<Invoice> invoices = invoiceRepositoryImpl.getAllInvoices();

        assertThat(invoices.size(), is(0));
    }

}