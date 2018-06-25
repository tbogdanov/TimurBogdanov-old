package enums;

/**
 * Created by Timur Bogdanov on 01.06.18.
 */
public enum Links {

    HOME_PAGE("https://epam.github.io/JDI/index.html"),
    JDI_PAGE("https://github.com/epam/JDI");

    private String url;

    Links(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }

}
