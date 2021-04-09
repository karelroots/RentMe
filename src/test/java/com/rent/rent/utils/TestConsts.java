package com.rent.rent.utils;

import com.project.rent.model.Offer;
import com.project.rent.model.User;
import com.project.rent.model.Wish;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toUnmodifiableList;

public class TestConsts {
    public static final Offer BANANA1 = Offer.builder().itemName("Big banana").build();
    public static final Offer BANANA2 = Offer.builder().itemName("Small banana").build();
    public static final Offer VACUUM1 = Offer.builder().itemName("Regular vacuum").build();
    public static final Offer BANANA3 = Offer.builder().itemName("Bananas").build();
    public static final Offer BANANA4 = Offer.builder().itemName("A case of bananas").build();
    public static final Offer HAMMER = Offer.builder().itemName("Large ban hammer").build();
    public static final Offer SCREWDRIVER = Offer.builder().itemName("Little screwdriver").build();
    public static final Wish VACUUM2 = Wish.builder().itemName("Philips super vacuum").build();
    public static final Wish VACUUM3 = Wish.builder().itemName("Samsung Ultra Vacuum").build();
    public static final Wish VACUUM4 = Wish.builder().itemName("Broken vacuum").build();
    public static final Wish NAILS = Wish.builder().itemName("Bunch of nails").build();
    public static final Wish UMBRELLA = Wish.builder().itemName("Just an umbrella").build();
    public static final List<Offer> OTHER_OFFERS = List.of(VACUUM1, HAMMER, SCREWDRIVER);
    public static final List<Offer> BANANA_OFFERS = List.of(BANANA1, BANANA2, BANANA3, BANANA4);
    public static final List<Wish> VACUUM_WISHES = List.of(VACUUM2, VACUUM3, VACUUM4);
    public static final List<Wish> OTHER_WISHES = List.of(NAILS, UMBRELLA);
    public static final List<Offer> ALL_OFFERS = Stream.of(OTHER_OFFERS.stream(),
                                                           BANANA_OFFERS.stream())
                                                       .reduce(Stream::concat)
                                                       .orElseGet(Stream::empty)
                                                       .collect(toUnmodifiableList());
    public static final List<Wish> ALL_WISHES = Stream.of(VACUUM_WISHES.stream(),
                                                          OTHER_WISHES.stream())
                                                      .reduce(Stream::concat)
                                                      .orElseGet(Stream::empty)
                                                      .collect(toUnmodifiableList());
    public static final List<Offer> WITHOUT_BANANA_OFFERS = ALL_OFFERS.stream()
                                                                      .filter(offer -> !BANANA_OFFERS.contains(offer))
                                                                      .collect(toUnmodifiableList());
    public static final List<Wish> WITHOUT_VACUUM_WISHES = ALL_WISHES.stream()
                                                                     .filter(wish -> !VACUUM_WISHES.contains(wish))
                                                                     .collect(toUnmodifiableList());
    public static final String USER1_EMAIL = "test@rent.me";
    public static final User USER1 = User.builder()
                                         .active(1)
                                         .id(1)
                                         .password("123")
                                         .email(USER1_EMAIL)
                                         .username("normaluser")
                                         .build();
    public static final User USER2 = User.builder()
                                         .active(1)
                                         .id(2)
                                         .password("123")
                                         .email("admin@rent.me")
                                         .username("adminuser")
                                         .build();
    public static final User USER3 = User.builder()
                                         .active(1)
                                         .id(3)
                                         .password("123")
                                         .email("tst@rent.me")
                                         .username("anothernormaluser")
                                         .build();
    public static final List<User> ALL_USERS = List.of(USER1, USER2);
    public static final List<User> FILTERED_USERS = List.of(USER1);
    public static final List<User> OTHER_USERS = List.of(USER2, USER3);
}
