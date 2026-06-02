package model;

import javafx.beans.property.*;

public class Card {
    private final StringProperty name;
    private final IntegerProperty attack;
    private final IntegerProperty health;
    private final WeaponStyle style;

    public Card(String name, int attack, WeaponStyle style) {
        this.name = new SimpleStringProperty(name);
        this.attack = new SimpleIntegerProperty(attack);
        this.health = new SimpleIntegerProperty(100);
        this.style = style;
    }

    //Do not remove this. You do not need to use this in your implementation.
    public static Card copy(Card card) {
        return new Card(card.name.get(), card.attack.get(), card.style);
    }

    public ReadOnlyStringProperty nameProperty() {
        return name;
    }
    public final String getName() {
        return name.get();
    }

    public ReadOnlyIntegerProperty attackProperty() {
        return attack;
    }
    public int getAttack() {
        return this.attack.get();
    }

    public ReadOnlyIntegerProperty healthProperty() {
        return health;
    }
    public int getHealth() {
        return health.get();
    }

    public ReadOnlyStringProperty styleProperty() {
        return new SimpleStringProperty(style.toString());
    }
    public String getStyle() { return style.toString(); }
    public boolean hasStyle(WeaponStyle style) {
        return this.style == style;
    }

    public void applyDamage(Card card) {
        int damage = card.getAttack();
        if (card.hasStyle(this.style)) {
            damage *= 2;
        }
        this.health.set(this.health.get() - damage);
    }

    @Override
    public String toString() {
        return name.get();
    }
}
