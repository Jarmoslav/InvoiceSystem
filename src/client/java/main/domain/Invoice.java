package main.domain;


import java.util.Collections;
import java.util.Set;

public class Invoice {

    private final InvoiceId invoiceId;
    private final InvoiceAccountId invoiceAccountId;
    private Set<InvoiceRow> invoiceRows;


    public Invoice(InvoiceId invoiceId, InvoiceAccountId invoiceAccountId, Set<InvoiceRow> invoiceRows) {
        this.invoiceId = invoiceId;
        this.invoiceAccountId = invoiceAccountId;
        this.invoiceRows = Collections.emptySet();
    }

    public InvoiceId getInvoiceId() {
        return invoiceId;
    }

    public InvoiceAccountId getInvoiceAccountId() {
        return invoiceAccountId;
    }

    public Set<InvoiceRow> getInvoiceRows() {
        return invoiceRows;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", invoiceAccountId=" + invoiceAccountId +
                ", invoiceRows=" + invoiceRows +
                '}';
    }
}
