package api.domain;


public class InvoiceRowId {

    private final String id;

    private InvoiceRowId(String id) {
        this.id = id;
    }

    public static InvoiceRowId valueOf(String id) {
        return new InvoiceRowId(id);
    }

    public static InvoiceRowId generate() {
        long currentTimeMillis = System.currentTimeMillis();
        return InvoiceRowId.valueOf(Long.toString(currentTimeMillis));
    }
}

