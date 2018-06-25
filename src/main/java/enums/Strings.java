package enums;

/**
 * Created by Timur Bogdanov on 01.06.18.
 */
public enum Strings {

    HOME_PAGE_TITLE("Home Page"),
    HEADER_NAVBAR("HOME; CONTACT FORM; SERVICE; METALS & COLORS"),
    BENEFITS("To include good practices\nand ideas from successful\nEPAM project; " +
            "To be flexible and\ncustomizable; " +
            "To be multiplatform; " +
            "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more\u2026"),

    MAIN_HEADER_TITLE("EPAM FRAMEWORK WISHES\u2026"),
    MAIN_HEADER_TEXT("LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, "+
                             "SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. "+
                             "UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS "+
                             "NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT "+
                             "IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR."),
    SUB_HEADER_TITLE("JDI GITHUB"),

    SERVICE_MENU("Support; Dates; Complex Table; Simple Table; User Table; "+
            "Table with Pages; Different Elements; Performance"),

    LEFT_SERVICE_DIFF_ELEMENTS_TEXT("Different Elements"),

    DIFFERENT_ELEMENTS_TITLE("Different Elements"),

    CB_WATER("Water"), CB_EARTH("Earth"), CB_WIND("Wind"), CB_FIRE("Fire"),
    RB_GOLD("Gold"), RB_SILVER("Silver"), RB_BRONZE("Bronze"), RB_SELEN("Selen"),
    DD_RED("RED"), DD_GREEN("Green"), DD_BLUE("Blue"), DD_YELLOW("Yellow"),
    TRUE("true"), FALSE("false");


    private String elem;

    Strings(String elem) {
        this.elem = elem;
    }

    @Override
    public String toString() {
        return elem;
    }

}
