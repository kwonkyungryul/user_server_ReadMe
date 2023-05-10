package shop.readmecorp.userserverreadme.modules.common.enums;

public enum StorageBoxType {
    recent("최근 본", "recent-list"),
    scrap("스크랩", "scraps"),
    purchase("구매", "purchases"),
    bookmark("북마크", "bookmarks");

    private String name;
    private String requestName;

    StorageBoxType(String name, String requestName) {
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
