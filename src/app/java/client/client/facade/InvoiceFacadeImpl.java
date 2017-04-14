package client.client.facade;

import api.domain.Invoice;
import api.domain.InvoiceAccountId;
import api.domain.InvoiceId;
import api.service.InvoiceService;
import api.service.InvoiceServiceImpl;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;


public class InvoiceFacadeImpl implements InvoiceFacade {


    private final InvoiceService invoiceService;

    public InvoiceFacadeImpl(){
        this.invoiceService = new InvoiceServiceImpl();
    }

    //only used for testing
    public InvoiceFacadeImpl(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @Override
    public void createInvoice(Invoice invoice) {
        invoiceService.createInvoice(invoice);
    }

    @Override
    public Invoice getInvoice(InvoiceId invoiceId) {
        return invoiceService.getInvoice(invoiceId);
    }

    @Override
    public void deleteInvoice(InvoiceId invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
    }

    @Override
    public Set<Invoice> getInvoices(InvoiceAccountId id) {
        return  invoiceService.getInvoices(id);
    }

    @Override
    public Set<Invoice> getAllPassedDueInvoices() {
        Set<Invoice> invoices = invoiceService.getAllInvoices().stream()
                .filter(Invoice::isPassedDue)
                .collect(Collectors.toSet());
        if(invoices==null){
            return Collections.emptySet();
        }
        else {
            return invoices;
        }
    }


}
