package com.epam.brest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Port {

    private int numberOfShips;
    private int numberOfBerths;
    private int fullnessPort;
    private int maxFullnessPort;
    private List<Ship> ships;

    public Port() {
        this.numberOfShips = enterNumber("Введите количество кораблей (целое положительное число): ");
        this.numberOfBerths = enterNumber("Введите количество причалов (целое положительное число): ");
        this.maxFullnessPort = 500;
        this.fullnessPort = (int)(Math.random()*this.maxFullnessPort);
        this.ships = createList();
    }

    private List<Ship> createList() {
        List<Ship> ships = new ArrayList<Ship>();
        for(int i = 1; i <= this.numberOfShips; i++) {
            ships.add(new Ship("Корабль" + i));
        }
        return ships;
    }

    private int enterNumber(String str) {
        Scanner sc = new Scanner(System.in);
        System.out.print(str);
        while(!sc.hasNextInt()) {
            sc.next();
            System.out.print(str);
        }
        int i = sc.nextInt();
        while (i <= 0) {
            System.out.print(str);
            while(!sc.hasNextInt()) {
                sc.next();
                System.out.print(str);
            }
            i = sc.nextInt();
        }
        return i;
    }

    public synchronized void deleteShip() {
        Ship ship = ships.get(0);
        System.out.print(ship.getShipName()+" прибыл в порт. ");
        int i = (int)(Math.random()*3);
        if(i == 0) {
            loadPort(ship);
        } else if(i == 1) {
            unloadPort(ship);
        } else {
            loadPort(ship);
            unloadPort(ship);
        }
        System.out.print(ship.getShipName()+" уплыл.");
        ships.remove(0);
        System.out.println("\nЗагрузка порта: "+this.fullnessPort+" контейнеров из "+this.maxFullnessPort+" возможных");
    }

    private synchronized void loadPort(Ship ship) {
        int numberOfContainers = ship.unload();
        int freePlaceInPort = this.maxFullnessPort - this.fullnessPort;
        // Сравниваем, сколько контейнеров хочет выгрузить корабль, и свободное место в порту
        if(numberOfContainers <= freePlaceInPort) {
            this.fullnessPort = this.fullnessPort + numberOfContainers;
            ship.setFullness(ship.getFullness() - numberOfContainers);
            System.out.print("Порт принял "+numberOfContainers+" контейнеров груза. ");
        } else {
            this.fullnessPort = this.fullnessPort + freePlaceInPort;
            ship.setFullness(ship.getFullness() - freePlaceInPort);
            System.out.print("Порт принял "+freePlaceInPort+" контейнеров груза. ");
        }
    }

    private synchronized void unloadPort(Ship ship) {
        int numberOfContainers = ship.load();
        // Сравниваем, сколько контейнеров хочет загрузить корабль, и сколько контейнеров есть в порту
        if(numberOfContainers <= this.fullnessPort) {
            this.fullnessPort = this.fullnessPort - numberOfContainers;
            ship.setFullness(ship.getFullness() + numberOfContainers);
            System.out.print("Порт загрузил "+numberOfContainers+" контейнеров груза. ");
        } else {
            ship.setFullness(ship.getFullness() + this.fullnessPort);
            System.out.print("Порт загрузил "+numberOfContainers+" контейнеров груза. ");
            this.fullnessPort = 0;
        }
    }

    public int getNumberOfShips() {
        return numberOfShips;
    }

    public void setNumberOfShips(int numberOfShips) {
        this.numberOfShips = numberOfShips;
    }

    public int getNumberOfBerths() {
        return numberOfBerths;
    }

    public void setNumberOfBerths(int numberOfBerths) {
        this.numberOfBerths = numberOfBerths;
    }

    public int getFullnessPort() {
        return fullnessPort;
    }

    public void setFullnessPort(int fullnessPort) {
        this.fullnessPort = fullnessPort;
    }

    public int getMaxFullnessPort() {
        return maxFullnessPort;
    }

    public void setMaxFullnessPort(int maxFullnessPort) {
        this.maxFullnessPort = maxFullnessPort;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Port port = (Port) o;
        return numberOfShips == port.numberOfShips && numberOfBerths == port.numberOfBerths && fullnessPort == port.fullnessPort && maxFullnessPort == port.maxFullnessPort && Objects.equals(ships, port.ships);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfShips, numberOfBerths, fullnessPort, maxFullnessPort, ships);
    }

    @Override
    public String toString() {
        return "Port{" +
                "numberOfShips=" + numberOfShips +
                ", numberOfBerths=" + numberOfBerths +
                ", fullnessPort=" + fullnessPort +
                ", maxFullnessPort=" + maxFullnessPort +
                ", ships=" + ships +
                '}';
    }
}
