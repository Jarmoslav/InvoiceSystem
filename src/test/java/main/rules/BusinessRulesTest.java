package main.rules;

import main.domain.Invoice;
import main.domain.InvoiceAccountId;
import main.domain.InvoiceId;
import main.domain.InvoiceRow;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by simjar on 2017-04-14.
 */
public class BusinessRulesTest {

    @Test
    public void ifPassedDueHasPassedShouldReturnTrue() throws Exception {
        InvoiceRow row = new InvoiceRow(10.0, "row1");
        Set<InvoiceRow> invoiceRows = Collections.singleton(row);
        Invoice invoice = new Invoice(InvoiceId.generate(), InvoiceAccountId.generate(), invoiceRows, LocalDate.of(2017,3,14));
        BusinessRules businessRules = new BusinessRules();

        boolean passedDue = businessRules.isPassedDue(invoice);

        assertTrue(passedDue);
    }

    @Test
    public void ifInvoiceHasNotPassedDueShouldReturnFalse() throws Exception {
        InvoiceRow row = new InvoiceRow(10.0, "row1");
        Set<InvoiceRow> invoiceRows = Collections.singleton(row);
        Invoice invoice = new Invoice(InvoiceId.generate(), InvoiceAccountId.generate(), invoiceRows, LocalDate.now());
        BusinessRules businessRules = new BusinessRules();

        boolean passedDue = businessRules.isPassedDue(invoice);

        assertFalse(passedDue);
    }

}