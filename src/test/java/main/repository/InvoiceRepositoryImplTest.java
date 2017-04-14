package main.repository;

import main.domain.Invoice;
import main.domain.InvoiceAccountId;
import main.domain.InvoiceId;
import main.domain.InvoiceRow;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

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
        InvoiceRow row = new InvoiceRow(10.0, "row1");
        Set<InvoiceRow> invoiceRows = Collections.singleton(row);
        Invoice invoice = new Invoice(InvoiceId.generate(), InvoiceAccountId.generate(), invoiceRows, LocalDate.now());

        invoiceRepositoryImpl.createInvoice(invoice);

        Invoice invoice1 = invoiceRepositoryImpl.getInvoice(invoice.getInvoiceId());
        assertThat(invoice1.getInvoiceId(), is(invoice.getInvoiceId()));
        assertThat(invoice1.getInvoiceRows(), is(invoiceRows));
    }

    @Test
    public void getInvoice() throws Exception {
        InvoiceRow row = new InvoiceRow(10.0, "row1");
        Set<InvoiceRow> invoiceRows = Collections.singleton(row);
        Invoice invoice = new Invoice(InvoiceId.generate(), InvoiceAccountId.generate(), invoiceRows, LocalDate.now());

        Invoice actual = invoiceRepositoryImpl.getInvoice(invoice.getInvoiceId());

        assertThat(actual, Matchers.nullValue());
    }

    @Test
    public void updateInvoice() throws Exception {

    }

    @Test
    public void deleteInvoice() {
        InvoiceRow row = new InvoiceRow(10.0, "row1");
        Set<InvoiceRow> invoiceRows = Collections.singleton(row);
        Invoice invoice = new Invoice(InvoiceId.generate(), InvoiceAccountId.generate(), invoiceRows, LocalDate.now());
        invoiceRepositoryImpl.createInvoice(invoice);
        invoiceRepositoryImpl.deleteInvoice(invoice.getInvoiceId());

        Invoice invoice1 = invoiceRepositoryImpl.getInvoice(invoice.getInvoiceId());

        assertNull(invoice1);
    }

    @Test
    public void getInvoices(){
        InvoiceAccountId invoiceAccountId = InvoiceAccountId.valueOf("123");
        InvoiceRow row = new InvoiceRow(10.0, "row1");
        Set<InvoiceRow> invoiceRows = Collections.singleton(row);
        Invoice invoice = new Invoice(InvoiceId.generate(), invoiceAccountId, invoiceRows, LocalDate.now());
        invoiceRepositoryImpl.createInvoice(invoice);

        Set<Invoice> invoices = invoiceRepositoryImpl.getInvoices(invoiceAccountId);

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
        InvoiceRow row = new InvoiceRow(10.0, "row1");
        Set<InvoiceRow> invoiceRows = Collections.singleton(row);
        Invoice invoice = new Invoice(InvoiceId.generate(), InvoiceAccountId.generate(), invoiceRows, LocalDate.now());

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