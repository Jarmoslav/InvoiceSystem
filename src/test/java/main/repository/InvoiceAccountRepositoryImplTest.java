package main.repository;

import main.domain.CustomerId;
import main.domain.Invoice;
import main.domain.InvoiceAccount;
import main.domain.InvoiceAccountId;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class InvoiceAccountRepositoryImplTest {

    private InvoiceAccountRepositoryImpl invoiceAccountRepositoryImpl;

    @Before
    public void setUp(){
        invoiceAccountRepositoryImpl = new InvoiceAccountRepositoryImpl();
    }

    @Test
    public void createAndGetInvoiceAccount() throws Exception {
        InvoiceAccountId invoiceAccountId = InvoiceAccountId.generate();
        InvoiceAccount invoiceAccount = InvoiceAccount.builder()
                .withCustomerId(CustomerId.generate())
                .withInvoiceAccountId(invoiceAccountId)
                .withDescription("name")
                .withInvoices(Collections.<Invoice>emptyList())
                .build();

        invoiceAccountRepositoryImpl.createInvoiceAccount(invoiceAccount);

        InvoiceAccount actual = invoiceAccountRepositoryImpl.getInvoiceAccount(invoiceAccountId);

        assertThat(actual.getInvoiceAccountId(), is(invoiceAccount.getInvoiceAccountId()));
        assertThat(actual, is(invoiceAccount));
    }

    @Test
    public void getInvoiceAccountWithNoWrongAccointInvoiceIdShouldRetrunNull() throws Exception {
        InvoiceAccountId invoiceAccountId = InvoiceAccountId.generate();

        InvoiceAccount actual = invoiceAccountRepositoryImpl.getInvoiceAccount(invoiceAccountId);

        assertNull(actual);
    }

}