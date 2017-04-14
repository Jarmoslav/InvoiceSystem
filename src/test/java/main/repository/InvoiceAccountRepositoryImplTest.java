package main.repository;

import main.domain.InvoiceAccount;
import main.domain.InvoiceAccountId;
import org.junit.Before;
import org.junit.Test;

import static main.testdata.InvoiceAccountBuilder.anInvoiceAccount;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;


public class InvoiceAccountRepositoryImplTest {

    private InvoiceAccountRepositoryImpl invoiceAccountRepositoryImpl;

    @Before
    public void setUp(){
        invoiceAccountRepositoryImpl = new InvoiceAccountRepositoryImpl();
    }

    @Test
    public void createAndGetInvoiceAccount() throws Exception {
        InvoiceAccount invoiceAccount = anInvoiceAccount()
                .build();
        invoiceAccountRepositoryImpl.createInvoiceAccount(invoiceAccount);

        InvoiceAccount actual = invoiceAccountRepositoryImpl.getInvoiceAccount(invoiceAccount.getInvoiceAccountId());

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