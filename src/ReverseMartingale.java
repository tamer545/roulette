import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ReverseMartingale {
    private ArrayList<Integer> redEntries = new ArrayList<>(Arrays.asList(1, 7, 16, 19, 34, 32, 5, 3, 9, 12, 18, 21, 27, 30, 36, 0));
    private ArrayList<Integer> blackEntries = new ArrayList<>(Arrays.asList(4, 10, 13, 22, 28, 31, 35, 29, 26, 20, 17, 11, 2, 6, 33, 24, 15, 0));
    Random rand = new Random();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ReverseMartingale reverseMartingale = new ReverseMartingale();
        // Reading data using readLine
        System.out.println("Please enter your budet");
        int budget = Integer.parseInt(reader.readLine());
        System.out.println("Please enter your bet");
        int bet = Integer.parseInt(reader.readLine());
        System.out.println("Please enter your prediction [odd, even, red, black]");
        String evenOrOdd = reader.readLine();

        //Initial Bet and win/loose counters
        int initialBet = bet;
        int initialBudget = budget;
        int wonCount = 0;
        int loseCount = 0;
        //For-Loop for 1000 games
        for (int i = 0; i < 5000; i++) {
            Integer betNumber = reverseMartingale.generateRoulette();
            if (budget < bet) {
                break;
            }

            if (betNumber != 0) {
                if (evenOrOdd.equals("black") && reverseMartingale.blackEntries.contains(betNumber) || evenOrOdd.equals("red") && reverseMartingale.redEntries.contains(betNumber) || evenOrOdd.equals("even") && betNumber % 2 == 0 || evenOrOdd.equals("odd") && betNumber % 2 == 1) {
                    budget += bet;
                    bet += bet;
                    wonCount++;
                } else {
                    budget -= bet;
                    bet = initialBet;
                    loseCount++;
                }
            } else {
                budget -= bet;
                bet = initialBet;
                loseCount++;
            }
        }

    int totalNumberOfGames = wonCount + loseCount;
    double winProbability = Double.parseDouble(String.valueOf(wonCount)) / Double.parseDouble(String.valueOf(totalNumberOfGames)) * 100;
    double loseProbability = Double.parseDouble(String.valueOf(loseCount)) / Double.parseDouble(String.valueOf(totalNumberOfGames)) * 100;
            System.out.println("You won: "+wonCount +" Games");
            System.out.println("You lost: "+loseCount +" Games");
            System.out.println("You have a: "+Math.round(winProbability *Math.pow(10,4))/Math.pow(10,4)+" win probability");
            System.out.println("You have a: "+Math.round(loseProbability *Math.pow(10,4))/Math.pow(10,4)+" lose probability");
            System.out.println("Your budget: "+budget);
            System.out.println("Your bet: "+bet);

}

    public Integer generateRoulette() {
        var randomArray = rand.nextInt(2);
        switch (randomArray) {
            case 0 -> {
                return redEntries.get(rand.nextInt(14));
            }
            case 1 -> {
                return blackEntries.get(rand.nextInt(14));
            }
            default -> {
                return 0;
            }
        }

    }
}
