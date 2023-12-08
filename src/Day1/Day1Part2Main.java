package Day1;

import java.io.FileReader;
import java.util.Scanner;

public class Day1Part2Main {
    public static void main(String[] args){
        int lineCounter = 1;
        String line;
        String numericLine;
        int sum = 0;
        try {
            //filepath, filereader, scanner in
            String filePath = "C:\\Users\\gregt\\IdeaProjects\\AdventofCode2023\\src\\Day1\\Day1Text.txt";
            FileReader fr = new FileReader(filePath);
            Scanner in = new Scanner(fr);
            while (in.hasNextLine()) {
                line = in.nextLine();
                //replace number words with numeric characters - accounting for overlap like 'oneight' in the text
                line = line.replaceAll("one", "on1e");
                line = line.replaceAll("two", "tw2o");
                line = line.replaceAll("three", "th3ee");
                line = line.replaceAll("four", "fo4ur");
                line = line.replaceAll("five", "fi5ve");
                line = line.replaceAll("six", "s6x");
                line = line.replaceAll("seven", "se7en");
                line = line.replaceAll("eight", "ei8ht");
                line = line.replaceAll("nine", "n9n");
                //remove all non-numeric characters with regex
                numericLine = line.replaceAll("[^0-9]", "");
                //sum first and last number
                if (numericLine.length() > 1) {
                    System.out.println("numbers from line " + lineCounter +": " + numericLine.substring(0, 1) + " " + numericLine.substring(numericLine.length() - 1));
                    System.out.println(numericLine.substring(0, 1) + numericLine.substring(numericLine.length() - 1));
                    sum += Integer.parseInt(numericLine.substring(0, 1) + numericLine.substring(numericLine.length() - 1));
                }
                else if (numericLine.length() == 1) {
                    System.out.println("numbers from line " + lineCounter + ": " + numericLine.substring(0, 1));
                    sum += Integer.parseInt(numericLine.substring(0, 1) + numericLine.substring(0, 1));
                }
                else {
                    sum += 0;
                }
                System.out.println("Line " + lineCounter + " sum: " + sum);
                lineCounter++;
            }
            System.out.println("Answer: " + sum);
            in.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}







