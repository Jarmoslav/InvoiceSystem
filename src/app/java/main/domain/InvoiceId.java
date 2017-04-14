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

    @Override
    public String toString() {
        return "InvoiceId{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceId invoiceId = (InvoiceId) o;

        return id != null ? id.equals(invoiceId.id) : invoiceId.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
