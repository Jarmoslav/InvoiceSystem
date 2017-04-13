package main.domain;

import java.util.Date;

/**
 * Created by simjar on 2017-04-13.
 */
public class InvoiceRow {

    private final InvoiceRowId invoiceRowId;
    private final double amount;
    private final Date date;

    public InvoiceRow(double amount, Date date) {
        this.invoiceRowId =  InvoiceRowId.generate();
        this.amount = amount;
        this.date = date;
    }

    public InvoiceRowId getInvoiceRowId() {
        return invoiceRowId;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }


}
