package api.domain;


public class InvoiceRow {

    private final InvoiceRowId invoiceRowId;
    private final double amount;
    private final String description;

    public InvoiceRow(double amount, String description) {
        this.invoiceRowId =  InvoiceRowId.generate();
        this.amount = amount;
        this.description = description;
    }

    public InvoiceRowId getInvoiceRowId() {
        return invoiceRowId;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "InvoiceRow{" +
                "invoiceRowId=" + invoiceRowId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceRow that = (InvoiceRow) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (invoiceRowId != null ? !invoiceRowId.equals(that.invoiceRowId) : that.invoiceRowId != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = invoiceRowId != null ? invoiceRowId.hashCode() : 0;
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
