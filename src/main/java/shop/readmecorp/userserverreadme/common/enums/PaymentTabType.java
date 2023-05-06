package shop.readmecorp.userserverreadme.common.enums;

public enum PaymentTabType {
    MEMBERSHIP("멤버십", "memberships"),
    PURCHASE("도서 구매", "purchases");

    private String name;
    private String requestName;

    PaymentTabType(String name, String requestName) {
        this.name = name;
        this.requestName = requestName;
    }

    public String getName() {
        return name;
    }

    public String getRequestName() {
        return requestName;
    }

}
