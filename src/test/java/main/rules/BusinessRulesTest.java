package main.rules;

import api.domain.Invoice;
import client.client.rules.BusinessRules;
import org.junit.Test;

import java.time.LocalDate;

import static main.testdata.InvoiceBuilder.anInvoice;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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