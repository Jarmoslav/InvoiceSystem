package api.domain;


import java.util.List;

public class InvoiceAccount {

    private  InvoiceAccountId invoiceAccountId;
    private  String description;
    private List<Invoice> invoices;

    private InvoiceAccount() {
    }

    public InvoiceAccountId getInvoiceAccountId() {
        return invoiceAccountId;
    }

    public String getDescription() {
        return description;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder{

        private final InvoiceAccount invoiceAccount;

        private Builder() {
            this.invoiceAccount = new InvoiceAccount();
        }

        public static Builder builder(){
            return new Builder();
        }

        public Builder withInvoiceAccountId(InvoiceAccountId invoiceAccountId){
            invoiceAccount.invoiceAccountId = invoiceAccountId;
            return this;
        }


        public Builder withDescription(String description){
            invoiceAccount.description = description;
            return this;
        }

        public Builder withInvoices(List<Invoice> invoices){
            invoiceAccount.invoices = invoices;
            return this;
        }

        public InvoiceAccount build(){
            return invoiceAccount;
        }


    }

}
