package dev.maullu.runnerz.user;

public record Address(String street,
                      String suit,
                      String city,
                      String zipcode,
                      Coordinate geo) {
}
