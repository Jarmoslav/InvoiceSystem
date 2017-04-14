package main.testdata;


import api.domain.Invoice;
import api.domain.InvoiceAccount;
import api.domain.InvoiceAccountId;

import java.util.Collections;

public class InvoiceAccountBuilder {

    private InvoiceAccountBuilder() {
        // Intentionally empty.
    }

    public static InvoiceAccount.Builder anInvoiceAccount(){
        return InvoiceAccount.builder()
                .withInvoiceAccountId(InvoiceAccountId.valueOf("123"))
                .withDescription("name")
                .withInvoices(Collections.<Invoice>emptyList());
    }
}
