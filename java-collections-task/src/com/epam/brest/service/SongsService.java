package com.epam.brest.service;

import com.epam.brest.dao.SongsDao;

import java.util.Scanner;

public class SongsService {

    private SongsDao dao = new SongsDao();

    public void menu() {
        String menuString = "Main menu:\n"+
                "\tpress '1' to get all songs\n" +
                "\tpress '2' to sort songs\n" +
                "\tpress '3' to filter by duration\n"+
                "\tpress '4' to select a song for writing to file\n" +
                "\tpress '5' to get a list of selected songs\n"+
                "\tpress '6' to write selected songs to file\n"+
                "\tpress '0' to finish\n"+
                "Enter your number: ";
        int i = enterNumber(menuString, 6);
        while(i != 0) {
            switch (i) {
                case (1):
                    showAllSongs();
                    break;
                case (2):
                    sortSongs();
                    break;
                case (3):
                    filterSongsByDuration();
                    break;
                case (4):
                    addSongToSelectedList();
                    break;
                case (5):
                    showSelectedSongs();
                    break;
                case (6):
                    writeSongsToFile();
                    break;
            }
            i = enterNumber(menuString, 6);
        }
    }

    private void showAllSongs() {
        dao.getAllSongs();
    }

    private void sortSongs() {
        String str = "Sort menu: \n"+
                "\tpress '1' to sort by singer\n"+
                "\tpress '2' to sort by genre\n"+
                "\tpress '3' to sort by duration\n"+
                "\tpress '0' to cancel\n"+
                "Enter your number: ";
        int i = enterNumber(str, 3);
        switch (i) {
            case (1):
                dao.getAllSongsSortedBySinger();
                break;
            case (2):
                dao.getAllSongsSortedByGenre();
                break;
            case (3):
                dao.getAllSongsSortedByDuration();
                break;
        }
    }

    private void filterSongsByDuration() {
        int minDuration = enterMinDuration("Please, enter a non-negative integer: ");
        int maxDuration = enterMaxDuration("Please, enter an integer greater than "+minDuration+": ", minDuration);
        dao.getSongsByDuration(minDuration, maxDuration);
    }

    private void addSongToSelectedList() {
        int id = enterSongId("Enter the song ID: ");
        dao.addToSelectedSongs(id);
    }

    private void showSelectedSongs() {
        dao.getSelectedSongs();
    }

    private void writeSongsToFile() {
        String fileName = enterFileName("Enter the name of new file: ");
        dao.writeSelectedSongsToFile(fileName);
    }

    ////////////////////////////////////////////////////////////////

    private int enterNumber(String str, int number) {
        Scanner sc = new Scanner(System.in);
        System.out.print(str);
        while(!sc.hasNextInt()) {
            sc.next();
            System.out.print("Please, enter an integer from 0 to "+number+": ");
        }
        int i = sc.nextInt();
        while (i < 0 || i > number) {
            System.out.print("Please, enter an integer from 0 to "+number+": ");
            while(!sc.hasNextInt()) {
                sc.next();
                System.out.print("Please, enter an integer from 0 to "+number+": ");
            }
            i = sc.nextInt();
        }
        return i;
    }

    private int enterMinDuration(String str) {
        Scanner sc = new Scanner(System.in);
        System.out.print(str);
        while(!sc.hasNextInt()) {
            sc.next();
            System.out.print("Please, enter a non-negative integer: ");
        }
        int i = sc.nextInt();
        while (i < 0) {
            System.out.print("Please, enter a non-negative integer: ");
            while(!sc.hasNextInt()) {
                sc.next();
                System.out.print(str);
            }
            i = sc.nextInt();
        }
        return i;
    }

    private int enterMaxDuration(String str, int minDuration) {
        Scanner sc = new Scanner(System.in);
        System.out.print(str);
        while(!sc.hasNextInt()) {
            sc.next();
            System.out.print("Please, enter an integer greater than "+minDuration+": ");
        }
        int i = sc.nextInt();
        while (i <= minDuration) {
            System.out.print("Please, enter an integer greater than "+minDuration+": ");
            while(!sc.hasNextInt()) {
                sc.next();
                System.out.print(str);
            }
            i = sc.nextInt();
        }
        return i;
    }

    private int enterSongId(String str) {
        Scanner sc = new Scanner(System.in);
        System.out.print(str);
        while(!sc.hasNextInt()) {
            sc.next();
            System.out.print("Please, enter an integer greater than zero: ");
        }
        int i = sc.nextInt();
        while (i <= 0) {
            System.out.print("Please, enter an integer greater than zero: ");
            while(!sc.hasNextInt()) {
                sc.next();
                System.out.print(str);
            }
            i = sc.nextInt();
        }
        return i;
    }

    private String enterFileName(String str) {
        Scanner sc = new Scanner(System.in);
        System.out.print(str);
        return sc.nextLine();
    }

}
