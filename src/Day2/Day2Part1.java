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

public class Day2Part1 {
    public static void main(String[] args){

        //the set of cubes we have
        final int RED_CUBES = 12;
        final int BLUE_CUBES = 14;
        final int GREEN_CUBES = 13;

        //var for total possible games
        int possibleGames = 0;

        try {
            Scanner in = new Scanner(new File("src\\Day2\\DaysGames.txt"));

            //collect and split line input
            while (in.hasNextLine()){
                String input = in.nextLine();
            //gets the index of the ':' in the input line as an anchor index for string
            //split and game # collection
                int getIndexOf = input.indexOf(':');
                //gets the game # by parsing the int in the space between the word game +1
                //(to deal with the space, and the ':', which is the char after the game #
                //she has it with input.substring("Game".length() + 1) but that's always 5
                //so I change it
                int getGameNum = Integer.parseInt(input.substring(5, getIndexOf));

                //get the rest of the line from after the : to the end, since we're
                //done with the beginning
                input = input.substring(getIndexOf +1);

                //this is the new bit for me...split() breaks a string down into an array
                //split at a delimiter
                //the delimiter here is the semi colon between cube pulls
                String[] splitBySet = input.split(";");
                //boolean to break checking loop if a cube count exceeds maximum at any point
                boolean validGame = true;

                //loop that splits the array that is split by pull tries into individual
                //pulls and counts that # of colored cubes, adds it to the current running
                //total of cubes of that type, checks if the count exceeds allowed
                //breaks out of loop if false or if it makes it through, adds the game number
                //to the running total sum of game numbers amassed to answer the question

                //outer loop
                //no () on .length I guess...weird
                for (int i = 0; i < splitBySet.length;i++){
                    //vars for accumulated cube count
                    int redCount = 0;
                    int blueCount = 0;
                    int greenCount = 0;
                    // boolean test for break if already exceeded
                    //this probably works but seems odd stylistically to me
                    if (!validGame){break;}

                    //further split of array elements into array separated
                    //by the comma delimiter that seps each color
                    String[] splitByComma = splitBySet[i].split(",");

                    //loop trims and then parses int the space between last white space
                    //I'm not sure the trim is necessary for the parseInt(), but it may be
                    //for the index counting
                    for (int j = 0; j < splitByComma.length; j++) {
                        //trims the current part of the array split (by the current index
                        //j
                        String current = splitByComma[j].trim();
                        //she calls this number of cubes, but it's really the string
                        //of text between beginning of current substring and the next
                        //whitespace
                        String numOfCubes = current.substring(0, current.indexOf(" "));
                        //this is the true number of cubes in the int variable
                        int num = Integer.parseInt(numOfCubes);

                        //this section searches the 'current' substring for the color
                        //regex and then assigns the numer to the appropriate color
                        // - this is MUCH better than what I was doing with triggering on
                        //the first letter
                        if (current.contains("red")){
                            redCount += num;
                        }
                        else if (current.contains("green")) {
                            greenCount += num;
                        }
                        else {
                            blueCount += num;
                            }

                        //conditional that sets valid game boolean to false
                        //to break out of the loop and NOT set the game accumulator
                        //I got rid of an unnecessary boolean test here
                        if (redCount > RED_CUBES || greenCount > GREEN_CUBES || blueCount > BLUE_CUBES){
                            validGame = false;
                        }
                    }
                }
                //if the game is still a valid game...add the game number to the running
                //total of game numbers
                if (validGame) {
                    possibleGames += getGameNum;
                }

            }
        }
        catch (Exception e){
            System.out.println("An exception: " + e.getMessage() + "occured.");
        }

        //print out accumulated total
        System.out.println("Solution: " + possibleGames);

    }
}