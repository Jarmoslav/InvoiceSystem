package main.testdata;


import main.domain.CustomerId;
import main.domain.Invoice;
import main.domain.InvoiceAccount;
import main.domain.InvoiceAccountId;

import java.util.Collections;

public class InvoiceAccountBuilder {

    private InvoiceAccountBuilder() {
        // Intentionally empty.
    }

    public static InvoiceAccount.Builder anInvoiceAccount(){
        return InvoiceAccount.builder()
                .withCustomerId(CustomerId.valueOf("c1"))
                .withInvoiceAccountId(InvoiceAccountId.valueOf("123"))
                .withDescription("name")
                .withInvoices(Collections.<Invoice>emptyList());
    }
}
