package api.domain;


public class InvoiceAccountId {

    private final String id;

    private InvoiceAccountId(String id) {
        this.id = id;
    }

    public static InvoiceAccountId valueOf(String id) {

        return new InvoiceAccountId(id);
    }

    public static InvoiceAccountId generate() {
        long currentTimeMillis = System.currentTimeMillis();
        return InvoiceAccountId.valueOf(Long.toString(currentTimeMillis));
    }

    @Override
	public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceAccountId that = (InvoiceAccountId) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
