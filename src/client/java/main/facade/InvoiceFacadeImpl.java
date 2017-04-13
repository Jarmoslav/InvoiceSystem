package main.facade;

import main.domain.Invoice;
import main.domain.InvoiceId;
import main.service.InvoiceService;
import main.service.InvoiceServiceImpl;


public class InvoiceFacadeImpl implements InvoiceFacade {


    private final InvoiceService invoiceService;

    public InvoiceFacadeImpl(){
        this.invoiceService = new InvoiceServiceImpl();
    }

    //only used for testing
    public InvoiceFacadeImpl(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    public void createInvoice(Invoice invoice) {
        invoiceService.createInvoice(invoice);
    }

    public Invoice getInvoice(InvoiceId invoiceId) {
        return invoiceService.getInvoice(invoiceId);
    }

    public void updateInvoice(Invoice invoice) {
        invoiceService.updateInvoice(invoice);
    }
}
