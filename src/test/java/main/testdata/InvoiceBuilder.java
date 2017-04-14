package main.testdata;


import main.domain.Invoice;
import main.domain.InvoiceAccountId;
import main.domain.InvoiceId;
import main.domain.InvoiceRow;

import java.time.LocalDate;
import java.util.Collections;

public class InvoiceBuilder {

    private InvoiceBuilder(){
        // Intentionally empty.
    }

    public static Invoice.Builder anInvoice(){
        return Invoice.Builder.anInvoice()
                .withInvoiceAccountId(InvoiceAccountId.valueOf("123"))
                .withInvoiceId(InvoiceId.valueOf("567"))
                .withInvoiceRows(Collections.singleton(new InvoiceRow(10.1d, "porche")))
                .withDueDate(LocalDate.of(2017,2,1));
    }

}
