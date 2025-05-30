package dev.maullu.runnerz.user;

public record User(Integer id,
                   String name,
                   String username,
                   String email,
                   Address address,
                   Integer phone,
                   String website,
                   Company company) {
}
