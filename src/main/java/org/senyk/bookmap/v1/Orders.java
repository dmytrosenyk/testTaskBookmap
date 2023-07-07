package org.senyk.bookmap.v1;

public enum Orders {
    BID("bid"),
    ASK("ask"),
    SPREAD;
    private String name;
    public final Integer MAX_PRICE= (int) Math.pow(10,9);
    public final Integer MIN_PRICE= 1;
    public final Integer MAX_SIZE= (int) Math.pow(10,8);
    public final Integer MIN_SIZE= 0;
    Orders() {
    }
    Orders(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
