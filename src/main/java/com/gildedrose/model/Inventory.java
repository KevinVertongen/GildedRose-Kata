package com.gildedrose.model;

import java.util.Arrays;
import java.util.List;


public final class Inventory {

    /*
     * Degrading items
     */
    public static final String DEXTERITY_VEST_PLUS5 = "+5 Dexterity Vest";
    public static final String ELIXIR_MONGOOSE = "Elixir of the Mongoose";
    public static final List<String> DEGRADING_ITEMS = Arrays.asList(DEXTERITY_VEST_PLUS5, ELIXIR_MONGOOSE);

    /*
     * Improving items
     */
    public static final String AGED_BRIE = "Aged Brie";
    public static final List<String> IMPROVING_ITEMS = Arrays.asList(AGED_BRIE);

    /*
     * Legendary items
     */
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final List<String> LEGENDARY_ITEMS = Arrays.asList(SULFURAS);

    /*
     * Expiring items
     */
    public static final String BACKSTAGE_TAFKAL80ETC = "Backstage passes to a TAFKAL80ETC concert";
    public static final List<String> EXPIRING_ITEMS = Arrays.asList(BACKSTAGE_TAFKAL80ETC);

    /*
     * Conjured items
     */
    public static final String MANA_CAKE = "Conjured Mana Cake";
    public static final List<String> CONJURED_ITEMS = Arrays.asList(MANA_CAKE);

    private Inventory() {
        throw new AssertionError("Constants class 'Inventory' cannot be instantiated");
    }

}
