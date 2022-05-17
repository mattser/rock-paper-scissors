package com.nology;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.*;

public class Game {

    private String playerOneName;
    private String playerTwoName;
    private final HashMap<String,Integer> scores = new HashMap<>();

    public Game() {
        run();
    }

    public void run() {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        changeNames();

        while (running) {
            System.out.println("1. Play a round");
            System.out.println("2. Print score history");
            System.out.println("3. Change players");
            System.out.println("4. Show high scores");
            System.out.println("0. Quit");
            System.out.println("Select an option:");
            int input = scanner.nextInt();

            if (input == 1) play();
            else if (input == 2) scores.forEach((key, value) -> System.out.println(key + "has won: " + value + " times"));
            else if (input == 3) changeNames();
            else if (input == 4) printTopThree();
            else if (input == 0) running = false;
        }
    }

    public void changeNames () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter player one name:");
        playerOneName = scanner.next();
        if (!scores.containsKey(playerOneName)) scores.put(playerOneName,0);
        System.out.println("Enter player two name:");
        playerTwoName = scanner.next();
        if (!scores.containsKey(playerTwoName)) scores.put(playerTwoName,0);
    }

    public void printTopThree () {
        TreeMap<Integer,String> sortedScores = new TreeMap<>();
        for (Map.Entry entry: scores.entrySet()) {
            sortedScores.put((Integer) entry.getValue(), (String) entry.getKey());
        }

        Set set = sortedScores.entrySet();
        Iterator i = set.iterator();
        int counter = 0;

        sortedScores.entrySet().forEach(entry -> System.out.println(entry.getValue() + "has won: " + entry.getKey() + " times"));

//        while(i.hasNext()) {
////            if (counter == 3) break;
//            Map.Entry item = (Map.Entry) i.next();
//            System.out.println(item.getValue() + " has won: "+ item.getKey() + " times");
//            counter++;
//        }


    }

    public void play () {

        System.out.println(playerOneName + " type: 'ROCK', 'PAPER' or 'SCISSORS'");
        Scanner s = new Scanner(System.in);
        RPS playerOne = RPS.values()[RPS.valueOf(s.next().toUpperCase()).ordinal()];

        System.out.println(playerTwoName + " type: 'ROCK', 'PAPER' or 'SCISSORS'");
        RPS playerTwo = RPS.values()[RPS.valueOf(s.next().toUpperCase()).ordinal()];

        System.out.println(playerOneName + " plays "+ playerOne);
        System.out.println(playerTwoName + " plays " + playerTwo);
        gameLogic(playerOne,playerTwo);


    }

    public RPS computerPlay() {
        return RPS.values()[(int) Math.floor(Math.random()*RPS.values().length)-1];
    }

    public void gameLogic(RPS playerOne, RPS playerTwo) {



        if (playerOne.name().equals(playerTwo.name()) ) {
            System.out.println("DRAW");

        } else if (playerOne == RPS.ROCK) {
            if (playerTwo == RPS.PAPER) {
                System.out.println(playerTwoName + " wins!");
                scores.put(playerTwoName,scores.get(playerTwoName) + 1);
            } else {
                System.out.println(playerOneName + " wins!");
                scores.put(playerOneName,scores.get(playerOneName) + 1);
            }
        } else if (playerOne == RPS.PAPER) {
            if (playerTwo == RPS.SCISSORS) {
                System.out.println(playerTwoName + " wins!");
                scores.put(playerTwoName,scores.get(playerTwoName) + 1);
            } else {
                System.out.println(playerOneName + " wins!");
                scores.put(playerOneName,scores.get(playerOneName) + 1);
            }
        } else {
            if (playerTwo == RPS.ROCK) {
                System.out.println(playerTwoName + " wins!");
                scores.put(playerTwoName,scores.get(playerTwoName) + 1);
            } else {
                System.out.println(playerOneName + " wins!");
                scores.put(playerOneName,scores.get(playerOneName) + 1);
            }
        }


    }

}
