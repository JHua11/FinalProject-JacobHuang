package org.jhuang;

import java.util.ArrayList;
import java.util.List;

public class Address {
    private int streetNo;
    private String street;
    private String city;
    private Province province;
    private String postalCode;

    /**
     * checks whether a postal code is valid
     * @param postalCode the postal code being checked
     * @return whether the postal code is valid (true) or not (false)
     */
    private static boolean isValidPostalCode(String postalCode) {
        if (postalCode.length() == 6) {
            for (int i = 0; i < 6; i++) {
                if (!(Character.isLetter(postalCode.charAt(i)) && i % 2 == 0
                || Character.isDigit(postalCode.charAt(i)) && i % 2 == 1)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public enum Province {
        BC,
        SK,
        AB,
        MA,
        ON,
        QC,
        NS,
        PEI,
        NB,
        NL
    }
}
