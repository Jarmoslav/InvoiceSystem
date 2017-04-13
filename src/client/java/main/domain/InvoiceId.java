package main.domain;

public class InvoiceId {

    private final String id;

    private InvoiceId(String id) {
        this.id = id;
    }

    public static InvoiceId valueOf(String id) {
        return new InvoiceId(id);
    }

    public static InvoiceId generate() {
        long currentTimeMillis = System.currentTimeMillis();
        return InvoiceId.valueOf(Long.toString(currentTimeMillis));
    }
}
