package utils;

/**
 * Created by Timur Bogdanov on 05.06.18.
 */
public class TestUtils {

    public static Object[][] extractTestParamsFromString(String paramStr, String delimiter) {

        /* Split a string (usually from Strings enum) using given delimiter
           and make an array of {id, string} with id starting from 1
           (it'll be the 0th in the resulting array) which can be used later
           in parameterised tests.
           Id starts with 1 because Javascript arrays starts with 1.
         */
        String[] paramStrings = paramStr.split(delimiter);
        Object[][] paramObjects = new Object[paramStrings.length][2];

        int id = 1;
        for (String str : paramStrings) {
            paramObjects[id-1] = new Object[] {id, str};
            id++;
        }

        return paramObjects;
    }

}
