package main.rules;

import main.domain.Invoice;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class BusinessRules {

    private static final int DAYS_UNTIL_DUE = 20;

    public static boolean isPassedDue(Invoice invoice){
        long between = ChronoUnit.DAYS.between(invoice.getDueDate().atTime(0, 0), LocalDate.now().atTime(0, 0));
        return between> DAYS_UNTIL_DUE;
    }

}
