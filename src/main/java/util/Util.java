package util;

public class Util {
    /**
     * Converts each word in a string into title case (first letter upper case, rest
     * of word lower case)
      * @param str the string to be converted
     * @return the string in title case
     */
    public static String toTitleCase(String str) {
        if (str == null) {
            return null;
        }
        String[] strs = str.split(" ");
        String finalWordString = "";
        for (String word : strs) {
            if (word.length() == 1) {
                finalWordString += word.toUpperCase();
                continue;
            }
            finalWordString += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ";
        }
        return finalWordString;
    }
}
