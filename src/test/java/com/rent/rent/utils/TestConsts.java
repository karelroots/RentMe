package com.rent.rent.utils;

import com.project.rent.model.Offer;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TestConsts {
    public static final Offer BANANA1 = Offer.builder().itemName("Big banana").build();
    public static final Offer BANANA2 = Offer.builder().itemName("Small banana").build();
    public static final Offer VACUUM = Offer.builder().itemName("Regular vacuum").build();
    public static final Offer BANANA3 = Offer.builder().itemName("Bananas").build();
    public static final Offer BANANA4 = Offer.builder().itemName("A case of bananas").build();
    public static final Offer HAMMER = Offer.builder().itemName("Large ban hammer").build();
    public static final Offer SCREWDRIVER = Offer.builder().itemName("Little screwdriver").build();
    public static final List<Offer> OTHER_OFFERS = List.of(VACUUM, HAMMER, SCREWDRIVER);
    public static final List<Offer> BANANA_OFFERS = List.of(BANANA1, BANANA2, BANANA3, BANANA4);
    public static final List<Offer> ALL_OFFERS = Stream.concat(OTHER_OFFERS.stream(),
                                                               BANANA_OFFERS.stream())
                                                       .collect(toList());
}
