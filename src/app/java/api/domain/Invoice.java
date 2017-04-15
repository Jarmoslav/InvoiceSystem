package api.domain;


import client.rules.BusinessRules;

import java.time.LocalDate;
import java.util.Set;

public class Invoice {

    private InvoiceId invoiceId;
    private InvoiceAccountId invoiceAccountId;
    private Set<InvoiceRow> invoiceRows;
    private LocalDate dueDate;

    private Invoice() {
        // Empty
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Double getDebt() {
        return invoiceRows.stream().mapToDouble(InvoiceRow::getAmount).sum();
    }

    public boolean isPassedDue() {
        return BusinessRules.isPassedDue(this);
    }

    public static class Builder {

        private final Invoice invoice;

        private Builder() {
            this.invoice = new Invoice();
        }

        public static Builder anInvoice(){
            return new Builder();
        }

        public Builder withInvoiceId(InvoiceId invoiceId){
            invoice.invoiceId = invoiceId;
            return this;
        }

        public Builder withInvoiceAccountId(InvoiceAccountId invoiceAccountId){
            invoice.invoiceAccountId = invoiceAccountId;
            return this;
        }

        public Builder withInvoiceRows(Set<InvoiceRow> invoiceRows){
            invoice.invoiceRows = invoiceRows;
            return this;
        }

        public Builder withDueDate(LocalDate dueDate){
            invoice.dueDate = dueDate;
            return this;
        }

        public Invoice build(){
            return invoice;
        }

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
        return dueDate != null ? dueDate.equals(invoice.dueDate) : invoice.dueDate == null;
    }

    @Override
    public int hashCode() {
        int result = invoiceId != null ? invoiceId.hashCode() : 0;
        result = 31 * result + (invoiceAccountId != null ? invoiceAccountId.hashCode() : 0);
        result = 31 * result + (invoiceRows != null ? invoiceRows.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        return result;
    }
}
