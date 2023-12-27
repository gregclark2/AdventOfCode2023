package Day4;


import java.io.File;
import java.util.Scanner;

/*
read in line
split line at | into winning number and numbers that we have
remove 'Card *:' from winning numbers
split both arrays at " " to make winning array and numbers that we have array
enhanced for loop of numbers we have array...if winningNumbers.contains(numbersHave[i])
then increment power from 0
when done - 2 ^ incremented count
add this to total
print total

ended up using some nightsplinter when my original solution of nested enhanced for loops
went nutty
 */
public class Day4Part1 {
    public static void main(String[] args){
        int pileTotal = 0;
        int cardTotal = 0;


        try {
            Scanner scanner = new Scanner(new File("src\\Day4\\day4Input.txt"));
            while (scanner.hasNextLine()){
                //not bothering to convert to int until math
                //should match either way (equals or == for int)
                String totalLine = scanner.nextLine();
                String trimmedLine = totalLine.replaceAll("\\w+\\s+\\d+:", "");
                String[] numbersArray = trimmedLine.split("\\|");
                String[] winningNumbers = numbersArray[0].split(" ");
                String[] ourNumbers = numbersArray[1].split(" ");
                int count = -1;

                for (int i = 0; i < ourNumbers.length; i++){

                    for (int j = 0; j < winningNumbers.length; j++){
                        //nightsplinter always provides a continue validation...not sure
                        //this is best practice
                        if (ourNumbers[i].isEmpty() || winningNumbers[j].isEmpty()) {
                            continue;
                        }

                        int ourNum = Integer.parseInt(ourNumbers[i]);
                        int winNum = Integer.parseInt(winningNumbers[j]);

                        if (ourNum == winNum){
                            count++;
                        }
                    }
                }
                if (count < 0){
                    pileTotal += 0;
                }
                else {
                    pileTotal += Math.pow(2, count);
                }
            }

        }
        catch (Exception e){
            System.out.println(e + " occured.");
        }
        System.out.println(pileTotal);

    }
}
