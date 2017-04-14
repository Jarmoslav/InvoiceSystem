package main.rules;

import main.domain.Invoice;
import main.domain.InvoiceAccountId;
import main.domain.InvoiceId;
import main.domain.InvoiceRow;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static main.testdata.InvoiceBuilder.anInvoice;
import static org.junit.Assert.*;

/**
 * Created by simjar on 2017-04-14.
 */
public class BusinessRulesTest {

    @Test
    public void ifPassedDueHasPassedShouldReturnTrue() throws Exception {
        Invoice invoice = anInvoice().withDueDate(LocalDate.of(2017,3,14)).build();
        BusinessRules businessRules = new BusinessRules();

        boolean passedDue = businessRules.isPassedDue(invoice);

        assertTrue(passedDue);
    }

    @Test
    public void ifInvoiceHasNotPassedDueShouldReturnFalse() throws Exception {
        Invoice invoice =  anInvoice().withDueDate(LocalDate.now()).build();
        BusinessRules businessRules = new BusinessRules();

        boolean passedDue = businessRules.isPassedDue(invoice);

        assertFalse(passedDue);
    }

}