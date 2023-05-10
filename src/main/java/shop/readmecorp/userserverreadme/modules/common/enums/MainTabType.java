package shop.readmecorp.userserverreadme.modules.common.enums;

public enum MainTabType {

    ALL("전체", "all"),
    BESTSELLER("베스트셀러", "best-sellers"),
    RECOMMEND("추천", "recommends"),
    NEW("신간", "new");

    private String name;
    private String requestName;

    MainTabType(String name, String requestName) {
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
