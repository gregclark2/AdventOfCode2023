package Day4;

import java.io.File;
import java.util.*;

/*
my recursion cut off too soon (I think) and yielded an answer several orders of magnitude too
low...but I'm moving on...I added nightsplinter's working solution for reference
 */
/*
The number of card matches, n,  make you win copies of cards from 1 to n below that
card.  Matches in those copy cards do the same. Total number of card instances is the answer

Make a hashmap to total the instances of cards (orig + copies). Hashmap.size() is the answer.

Iterate hashmaps for matching rounds....
master hashmap to tell you the matches # for each card

round 2 match hashmap contains key...key+matches card

round 3 match hashmap does the same..

until no more matches

stick each round hashmap in a master hashmap, the total size of that hashmap is the answer

recursive

 */

public class Day4Part2 {

    public static void main(String[] args) {

        int part2Answer = 0;
        int matchTotal = 0;
        int masterMatchesRow = 1;
        HashMap<Integer, Integer> masterMatchesMap = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> tallyMap = new HashMap<Integer, Integer>();
        masterMatchesMap.put(0,0); //this just fills 0 key (no card there)
                                    //so I don't get a null pointer iterating through the map
                                    //it DOES add a useless size to the map, so REMEMBER to use map.size() -1
        //read all matches into master hashmap
        try {
            Scanner scanner = new Scanner(new File("src\\Day4\\day4Input.txt"));
            while (scanner.hasNextLine()) {
                //not bothering to convert to int until math
                //should match either way (equals or == for int)
                String totalLine = scanner.nextLine();
                String trimmedLine = totalLine.replaceAll("\\w+\\s+\\d+:", "");
                String[] numbersArray = trimmedLine.split("\\|");
                String[] winningNumbers = numbersArray[0].split(" ");
                String[] ourNumbers = numbersArray[1].split(" ");
                int count = 0;

                for (int i = 0; i < ourNumbers.length; i++) {

                    for (int j = 0; j < winningNumbers.length; j++) {
                        //nightsplinter always provides a continue validation...not sure
                        //this is best practice - also these arrays would never be empty
                        //in this scenario
                        if (ourNumbers[i].isEmpty() || winningNumbers[j].isEmpty()) {
                            continue;
                        }

                        int ourNum = Integer.parseInt(ourNumbers[i]);
                        int winNum = Integer.parseInt(winningNumbers[j]);

                        if (ourNum == winNum) {
                            count++;
                        }
                    }
                }
                matchTotal = count;
                masterMatchesMap.put(masterMatchesRow, matchTotal);
                masterMatchesRow++;
                //up to here is good...map filled with the # of matches
                //each card give us

            }
        } catch (Exception e) {
            System.out.println(e + "has occured.");
        }
        try {
            for (Integer key : masterMatchesMap.keySet()){
              part2Answer += getExtraMatches(key, masterMatchesMap);
            }
        } catch (NullPointerException e) {
            System.out.println("Null pointer");
        }
        System.out.println("part 2: " + part2Answer);

    }

    public static int getExtraMatches(int currentCardKey, HashMap<Integer, Integer> map){
        int matchesOffsetUpperLimit = currentCardKey + map.get(currentCardKey);
        int totalMatches = 0;
        if (matchesOffsetUpperLimit > currentCardKey && matchesOffsetUpperLimit < map.size()){ //check here for limit issues
            for (int i = matchesOffsetUpperLimit; i >= currentCardKey + 1; i--){
                totalMatches += map.get(i);
                getExtraMatches(i, map);
            }
        }
        return totalMatches;
    }



    }






