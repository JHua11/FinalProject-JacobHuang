package org.jhuang;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import util.Util;
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Address {
    private int streetNo;
    private String street;
    private String city;
    private Province province;
    private String postalCode;

    /**
     * checks whether a postal code is valid. A valid postal code has length 6
     * and is structure as CDCDCD where C is a letter and D is a digit
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

    public Address(int streetNo, String street, String city, Province province, String postalCode) {
        if (isValidPostalCode(postalCode)) {
            this.streetNo = streetNo;
            this.street = Util.toTitleCase(street);
            this.city = Util.toTitleCase(city);
            this.province = province;
            this.postalCode = postalCode.toUpperCase();
        } else {
            this.streetNo = -1;
            this.street = null;
            this.city = null;
            this.province = null;
            this.postalCode = null;
        }
    }

    public void setPostalCode(String postalCode) {
        if (isValidPostalCode(postalCode)){
            this.postalCode = postalCode;
        } else {
            System.out.println("Invalid Postal Code");
        }
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
