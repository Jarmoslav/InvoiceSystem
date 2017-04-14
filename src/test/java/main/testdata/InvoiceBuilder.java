package main.testdata;


import api.domain.Invoice;
import api.domain.InvoiceAccountId;
import api.domain.InvoiceId;
import api.domain.InvoiceRow;

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
