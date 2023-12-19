package Day2;

import java.io.File;
import java.util.Scanner;

/**
 * I had this really long solution involving accumulator variables that would then
 * be triggers to store into hashmaps inside hashmaps, but then
 * I saw github - nightsplinter's solution day 2 and
 * thought it would be better to learn more about the string
 * splitting most folks were doing
 *
 * nightsplinter - <a href="https://github.com/nightsplinter/AdventOfCodeJava/blob/main/2023/days/02/src/PartOne.java">...</a>
 * regex and matcher should probably be next to explore more
 */

public class Day2Part2 {
    public static void main(String[] args) {
        //to keep track of the minimum cubes that enable the game
        int totalMinCubes = 0;

        try {
            Scanner in = new Scanner(new File("src\\Day2\\DaysGames.txt"));

            //reading in
            while (in.hasNextLine()){
                String input = in.nextLine();

                //again, finding the index after the game declaration
                int getIndexOf = input.indexOf(":");
                //and removes the game number: part to retain that stuff after
                input = input.substring(getIndexOf + 1);

                //again, splitting by ; to get the sets of pulls
                String[] splitBySet = input.split(";");

                //vars for fewest cubes possible
                int minRed = 0;
                int minBlue = 0;
                int minGreen = 0;

                //loop to split game by cube pull
                for (int i = 0; i < splitBySet.length; i++){
                    //array to hold the splits
                    String[] splitByComma = splitBySet[i].split(",");

                    //again, parses that subset - I don't think the trim()
                    //is necess..going to comment out and see

                    for (int j = 0; j <splitByComma.length; j++){
                        String current = splitByComma[j].trim();
                        //again breaks the string down between whitespaces
                        String numOfCubes = current.substring(0, current.indexOf(" "));
                        //grabs the integer from the subsplit
                        int num = Integer.parseInt(numOfCubes);

                        //test loop for if the current number exceeds allowable
                        if (current.contains("red") && num > minRed){
                            minRed = num;
                        }
                        else if (current.contains("green") && num > minGreen) {
                            minGreen = num;
                        }
                        //originally I elsed this without a testing condition...thinking
                        //it was just the only poss left, but it reassigned num and came in too low
                        else if (current.contains("blue") && num > minBlue) {
                            minBlue = num;
                        }
                    }
             }
            //calculates the 'power of the set,' which is the min num of cubes to
                //satisfy condition
                int powerOfSet = minBlue * minGreen * minRed;
                totalMinCubes += powerOfSet;
            }
            //scanner close - which is good practice but not like strictly necess
            //in.close();
        }
        catch (Exception e){
            System.out.println("Exception: "+ e);
        }
        System.out.println("Result: " + totalMinCubes);


    }
}