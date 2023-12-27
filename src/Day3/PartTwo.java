package Day3;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

/**
 * again totally copied for understanding from this is nightsplinter's solution - with me having used it as a study tool
 */
public class PartTwo {

    public static void main(String[] args){
        int total = 0; //total of gear ratios
        int height = 140; //grid 2d array dimensions
        int width = 140;
        String[][] grid = new String[height][width]; //grid array
        HashSet<int[]> numbersStartAt = new HashSet<>(); //I'm not entirely sure..
                                                    //tracks beginning of int in row?
        try {
            Scanner scanner = new Scanner(new File("src\\Day3\\day3input.txt"));
            int hIndex = 0; //starts entering at top row

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                grid[hIndex] = input.split(""); //break line into chars in grid array
                hIndex++; //moves to the next...this is just populating the grid array
            }
            scanner.close();

            for (int h = 0; h < grid.length; h++){ //runs through current line char by char
                String[] row = grid[h]; //sets a row reference to current grid row - not
                                        //he could probably just use grid[h]
                for (int j = 0; j < row.length; j++) {//loops through current row
                    String value = row[j]; //puts current row into a string
                    Character c = value.charAt(0); //gets first char in string

                    numbersStartAt = new HashSet<>(); //creates a new hashset for
                                                        //pair of 'adjacent' ints that mult for gear ratio
                    if (c.equals('*')){ //looks for trigger for gear pair
                        //searches above and below that trigger
                        int hAbove = h - 1; //row above index
                        int hBelow = h + 1; //row below index

                        //checks for left and right in lines above and below for
                        //int (if present) for a gear pair
                        for (int line = hAbove; line <= hBelow; line++){
                            //checks adjacent to right above and below *
                            int rLeft = j-1;
                            int rRight = j+1;

                            //test loop to see if integer and collect if pair
                            for (int index = rLeft; index <= rRight; index++) {

                                //loop to test both range and if is integer, if bad cond
                                //true breaks from this loop
                                if (index < 0 || index >= row.length || line < 0 ||
                                        !Character.isDigit(grid[line][index].charAt(0))) {
                                    continue;//not really necessary
                                }
                                //sets var to track column place along array row
                                int column = index;
                                while (column >= 0 && Character.isDigit(grid[line][column].charAt(0))) {
                                    column -= 1; //moves back on integer in row to find beginning char of int
                                }


                                //creates a new array and cycles through it to see if the
                                //pair already exists - I'm not sure why - maybe I misread
                                //the rules, but it seems like even if it already exists
                                //it should be added
                                int[] pair = new int[]{line, column + 1};// this syntax is odd can be:
                                //dataType [ ] nameOfArray = {value1, value2, value3, value4}
                                boolean alreadyExists = false;
                                for (int[] p : numbersStartAt) {
                                    if (p[0] == pair[0] && p[1] == pair[1]) {
                                        alreadyExists = true;
                                        continue; //breaks out of loop if number is already there
                                    }
                                }

                                if (!alreadyExists) {
                                    numbersStartAt.add(pair); //adds pair to hashset - again, why hashset?
                                    //that will exclude matching numbers even if they DO occur twice
                                    //maybe I misread the rules - oh wait...this collects pair by pair
                                    //make sure it's not collecting the up and/or down number twice?
                                }
                            }
                            if (numbersStartAt.size() != 2) { //if it hasn't actually found a pair, filled the pair
                                continue;               //array, then move on
                                    }

                            String[] gearPair = new String[2];  //interesting why a string array? why not just a string?
                                                                //just for the enhanced for loop?

                            for (int[] pair : numbersStartAt){
                                int pH = pair[0];
                                int pR = pair[1];

                                String[] pRow = grid[pH];
                                String pValue = "";
                                int index = pR;
                                while (index < pRow.length && Character.isDigit(pRow[index].charAt(0))) {
                                    pValue += pRow[index];
                                    index += 1;
                                }
                                if (gearPair[0] == null) {
                                    gearPair[0] = pValue; }
                                        else {
                                            gearPair[1] = pValue;
                                            total += Integer.parseInt(gearPair[0]) * Integer.parseInt(gearPair[1]);
                                            gearPair = new String[2];
                                            numbersStartAt = new HashSet<>();

                                    }
                                }
                            }
                        }

                    }

                }





        }
        catch (Exception e){
            System.out.println(e + "occurred.");

        }
        System.out.println(total);
    }
}
