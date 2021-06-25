package com.epam.brest;

import java.util.Objects;

public class Ship {

    private String shipName;
    private int fullness;
    private int maxFullness;

    public Ship() {
    }

    public Ship(String shipName) {
        this.shipName = shipName;
        this.maxFullness = (int)(Math.random()*100);
        this.fullness = (int)(Math.random()*this.maxFullness);
    }

    public int load() {
        int i = (int)(Math.random()*(this.maxFullness-this.fullness));
        System.out.print(this.shipName + " просит загрузить " +i+ " контейнеров груза. ");
        return i;
    }

    public int unload() {
        int i = (int)(Math.random()*this.fullness);
        System.out.print(this.shipName + " просит выгрузить " +i+ " контейнеров груза. ");
        return i;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public int getFullness() {
        return fullness;
    }

    public void setFullness(int fullness) {
        this.fullness = fullness;
    }

    public int getMaxFullness() {
        return maxFullness;
    }

    public void setMaxFullness(int maxFullness) {
        this.maxFullness = maxFullness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return fullness == ship.fullness && maxFullness == ship.maxFullness && Objects.equals(shipName, ship.shipName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipName, fullness, maxFullness);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "shipName='" + shipName + '\'' +
                ", fullness=" + fullness +
                ", maxFullness=" + maxFullness +
                '}';
    }
}
