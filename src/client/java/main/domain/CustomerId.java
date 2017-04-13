package main.domain;

public class CustomerId {

    private final String id;

    private CustomerId(String id) {
        this.id = id;
    }

    public static CustomerId valueOf(String id) {
        return new CustomerId(id);
    }

    public static CustomerId generate() {
        		long currentTimeMillis = System.currentTimeMillis();
        		return CustomerId.valueOf(Long.toString(currentTimeMillis));
    }

}
