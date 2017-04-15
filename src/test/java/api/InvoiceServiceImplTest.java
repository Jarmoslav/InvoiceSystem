package api;

import api.domain.Invoice;
import api.service.InvoiceServiceImpl;
import repository.InvoiceRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static main.testdata.InvoiceBuilder.anInvoice;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class InvoiceServiceImplTest {

    private InvoiceServiceImpl invoiceServiceImpl;
    private InvoiceRepository invoiceRepositoryMock;

    @Before
    public void setUp(){
        invoiceRepositoryMock = Mockito.mock(InvoiceRepository.class);
        invoiceServiceImpl = new InvoiceServiceImpl(invoiceRepositoryMock);
    }

    @Test
    public void createInvoice() {
        Invoice invoice = anInvoice().build();

        invoiceServiceImpl.createInvoice(invoice);

        verify(invoiceRepositoryMock).createInvoice(invoice);
    }

    @Test
    public void getInvoice() {
        Invoice invoice = anInvoice().build();
        when(invoiceRepositoryMock.getInvoice(invoice.getInvoiceId())).thenReturn(invoice);

        Invoice invoice1 = invoiceServiceImpl.getInvoice(invoice.getInvoiceId());

        assertThat(invoice1, is(invoice));
    }

}