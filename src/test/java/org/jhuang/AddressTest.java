package org.jhuang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    @DisplayName("postCode check valid input")
    void isPostalCodeValid1() {
        String postalCode = "A1B2C3";
        boolean expected = true;
        boolean result = Address.isPostalCodeValid(postalCode);
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("postCode check valid lower case input")
    void isPostalCodeValid2() {
        String postalCode = "a1b2c3";
        boolean expected = true;
        boolean result = Address.isPostalCodeValid(postalCode);
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("postCode Check invalid length input")
    void isPostalCodeValid3() {
        String postalCode = "A1B2C3A";
        boolean expected = false;
        boolean result = Address.isPostalCodeValid(postalCode);
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("postCode check invalid format input (extra letter)")
    void isPostalCodeValid4() {
        String postalCode = "AAB2C3";
        boolean expected = false;
        boolean result = Address.isPostalCodeValid(postalCode);
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("invalid format input (extra number)")
    void isPostalCodeValid() {
        String postalCode = "11B2C3";
        boolean expected = false;
        boolean result = Address.isPostalCodeValid(postalCode);
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("constructor valid postal code input")
    void Address1() {
        int streetNo = 1;
        String street = "Street";
        String city = "Mtl";
        Address.Province province = Address.Province.AB;
        String postalCode = "A1B2C3";
        Address result = new Address(streetNo, street, city, province, postalCode);
        Assertions.assertEquals(streetNo, result.getStreetNo());
        Assertions.assertEquals(street, result.getStreet());
        Assertions.assertEquals(city, result.getCity());
        Assertions.assertEquals(province, result.getProvince());
        Assertions.assertEquals(postalCode, result.getPostalCode());
    }
    @Test
    @DisplayName("constructor invalid postal code input")
    void Address2() {
        int streetNo = 1;
        String street = "Street";
        String city = "Mtl";
        Address.Province province = Address.Province.AB;
        String postalCode = "A1B2C";
        Address result = new Address(streetNo, street, city, province, postalCode);
        Assertions.assertEquals(-1, result.getStreetNo());
        Assertions.assertEquals(null, result.getStreet());
        Assertions.assertEquals(null, result.getCity());
        Assertions.assertEquals(null, result.getProvince());
        Assertions.assertEquals(null, result.getPostalCode());
    }

    @Test
    @DisplayName("setter valid postal code input")
    void setPostalCode1() {
        String postalCode = "A1B2C3";
        Address expected = new Address(1, "Street", "City", Address.Province.AB, "Z9Z9Z9");
        Address result = new Address(1, "Street", "City", Address.Province.AB, "A1B2C3");
        result.setPostalCode("Z9Z9Z9");
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("setter invalid postal code input")
    void setPostalCode2() {
        String postalCode = "A1B2C3";
        Address expected = new Address(1, "Street", "City", Address.Province.AB, "A1B2C3");
        Address result = new Address(1, "Street", "City", Address.Province.AB, "A1B2C3");
        result.setPostalCode("999999");
        Assertions.assertEquals(expected, result);
    }
}