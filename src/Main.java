import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Main {

    private static HashMap DFA = new HashMap();
    //public static List<String> terminals = new ArrayList <String>();
    private static BufferedReader bufferedReader;
    private static String inputString;
    private static String startingState;
    private static String finalState;

    public static void main(String[] args) {
        _input_();
        dfaTableConstruct();
        dfaCompare();
    }

    private static void dfaCompare(){
        String currentState = startingState;
        String nextState;
        char[] inputChar = inputString.toCharArray();
        System.out.println("***** Input *****\n" + inputString);

        for (int i = 0; i < inputChar.length; i++) {
            System.out.println("\n***** Iteration " + (i+1) + " *****");
            System.out.println("Character => " + inputChar[i]);
            String destination = (String) DFA.get(currentState);
            String[] splitDestination = destination.split("\\|");

            /**The line of code below might need a bit of explanation
             * The input string is comprised of '0's and '1's
             * From any given state, there are 2 possible states to move to, Onw for 0 value and another for 1 value
             * Also, destination states are stored in splitDestination array
             * So here the code converts the '0'/'1' from string to integer and uses it as the array index
             * This gives us a quick and easy way to determine the next state
             * If the input string contains ever more numbers like 2,3,4 and so on, this code will still work
             * If the input string contains a/b/c, then some modification is required*/
            nextState = splitDestination[Integer.parseInt(String.valueOf(inputChar[i]))].trim();

            System.out.println("From " + currentState+" to " + nextState);
            currentState = nextState;
        }

        System.out.println("\n***** Result *****");
        if (Objects.equals(currentState, finalState))
            System.out.println("Valid String");

        else
            System.out.println("Invalid String");

    }

    private static void _input_(){
        String input;
        try {
            bufferedReader = new BufferedReader(new FileReader("input.txt"));

            /*Reads the first line of input.txt which is the input string*/
            input = bufferedReader.readLine();
            inputString = input.substring(12);

            /*Reads the second line where the starting state is defined*/
            input = bufferedReader.readLine();
            startingState = input.substring(14);

            /*Reads the third line where the final state is defined*/
            input = bufferedReader.readLine();
            finalState = input.substring(11);

        } catch (FileNotFoundException e) {
            System.out.println("input.txt file not found.");
        } catch (IOException e) {
            System.out.println("Can't read file input.txt");
        }
    }

    private static void dfaTableConstruct(){
        String input;
        String[] splitInput;
        try {
            bufferedReader = new BufferedReader(new FileReader("dfaTable.txt"));
            input = bufferedReader.readLine();
            while (input != null) {
                splitInput = input.split("->");
                DFA.put(splitInput[0].trim(), splitInput[1].trim());
                input = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("dfaTable.txt File not found.");
        } catch (IOException e) {
            System.out.println("Can't read file dfaTable.txt");
        }
    }
}
