package model;

import java.util.Arrays;
import java.util.List;

public class CardLibrary {
    public static final List<Card> ALL_CARDS = Arrays.asList(
            new Card("Spitfire", 25, WeaponStyle.HEAT),
            new Card("Blindside", 30, WeaponStyle.ENERGY),
            new Card("Freeze Fire", 40, WeaponStyle.ICE),
            new Card("Blazing Fury", 80, WeaponStyle.FURY),
            new Card("Spitfire", 25, WeaponStyle.HEAT),
            new Card("Icicle", 10, WeaponStyle.ICE),
            new Card("Iceberg", 15, WeaponStyle.ICE),
            new Card("Blindside", 30, WeaponStyle.ENERGY),
            new Card("Showdown", 60, WeaponStyle.FURY),
            new Card("Blindside", 30, WeaponStyle.ENERGY),
            new Card("Icicle", 10, WeaponStyle.ICE),
            new Card("Spitfire", 25, WeaponStyle.HEAT),
            new Card("Shutdown", 100, WeaponStyle.FURY),
            new Card("Blazing Fury", 80, WeaponStyle.FURY),
            new Card("Blindside", 30, WeaponStyle.ENERGY),
            new Card("Icicle", 10, WeaponStyle.ICE)
    );
}
