package main.domain;


import main.rules.BusinessRules;

import java.time.LocalDate;
import java.util.Set;

public class Invoice {

    private final InvoiceId invoiceId;
    private final InvoiceAccountId invoiceAccountId;
    private Set<InvoiceRow> invoiceRows;
    private final LocalDate localDate;


    public Invoice(InvoiceId invoiceId, InvoiceAccountId invoiceAccountId, Set<InvoiceRow> invoiceRows, LocalDate dueDate) {
        this.invoiceId = invoiceId;
        this.invoiceAccountId = invoiceAccountId;
        this.localDate = dueDate;
        this.invoiceRows = invoiceRows;


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

    public LocalDate getLocalDate() {
        return localDate;
    }

    public Double getDebt(){
        return invoiceRows.stream().mapToDouble(InvoiceRow::getAmount).sum();
    }

    public boolean isPassedDue(){
        return BusinessRules.isPassedDue(this);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", invoiceAccountId=" + invoiceAccountId +
                ", invoiceRows=" + invoiceRows +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if (invoiceId != null ? !invoiceId.equals(invoice.invoiceId) : invoice.invoiceId != null) return false;
        if (invoiceAccountId != null ? !invoiceAccountId.equals(invoice.invoiceAccountId) : invoice.invoiceAccountId != null)
            return false;
        if (invoiceRows != null ? !invoiceRows.equals(invoice.invoiceRows) : invoice.invoiceRows != null) return false;
        return localDate != null ? localDate.equals(invoice.localDate) : invoice.localDate == null;
    }

    @Override
    public int hashCode() {
        int result = invoiceId != null ? invoiceId.hashCode() : 0;
        result = 31 * result + (invoiceAccountId != null ? invoiceAccountId.hashCode() : 0);
        result = 31 * result + (invoiceRows != null ? invoiceRows.hashCode() : 0);
        result = 31 * result + (localDate != null ? localDate.hashCode() : 0);
        return result;
    }
}
