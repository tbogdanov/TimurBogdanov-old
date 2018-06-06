package enums;

/**
 * Created by Timur Bogdanov on 01.06.18.
 */
public enum Strings {

    TITLE("Home Page"),
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
    SUB_HEADER_TITLE("JDI GITHUB");

    private String elem;

    Strings(String elem) {
        this.elem = elem;
    }

    public String getText() {
        return elem;
    }

}
