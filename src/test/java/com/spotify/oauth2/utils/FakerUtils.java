package com.spotify.oauth2.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName() {
        Faker faker = new Faker();
        return faker.regexify("[A-Za-z]{5}");
    }
    public static String generateDescription() {
        Faker faker = new Faker();
        return "Description" + faker.regexify("[ A-Za-z0-9_@.#&+-]{50}");
    }
    public static String generateEmail() {
        Faker faker = new Faker();
        return "pulkit.agrawal+" + faker.regexify("[0-9]{10}")+"@clover.com";
    }
    public static String generatePhoneNumber() {
        Faker faker = new Faker();
        return faker.regexify("[0-9]{10}");
    }
    public static String generateUtmCompaign(){
        Faker faker = new Faker();
        return "UtmCompaign" + faker.regexify("[ A-Za-z0-9_@.#&+-]{20}");
    }
}
