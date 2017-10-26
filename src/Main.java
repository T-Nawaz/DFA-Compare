import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Main {

    public static HashMap DFA = new HashMap();
    public static List<String> terminals = new ArrayList <String>();
    public static String inputString = "";
    public static String startingState = "";
    public static String finalState = "";

    public static void main(String[] args) {
        dfaTableConstruct();
        dfaCompare();
    }

    public static void dfaCompare(){
        inputString = "110111011";
        startingState = "A";
        finalState = "C";
        String currentState = startingState;
        String nextState = startingState;
        char[] inputChar = inputString.toCharArray();
        System.out.println("***** Input *****\n" + inputString);

        for (int i = 0; i < inputChar.length; i++) {
            System.out.println("\n***** Iteration " + (i+1) + " *****");
            System.out.println("Character => " + inputChar[i]);
            String destination = (String) DFA.get(currentState);
            String[] splitDestination = destination.split("\\|");

            nextState = splitDestination[Integer.parseInt(String.valueOf(inputChar[i]))];

            System.out.println("From " + currentState+" to " + nextState);
            currentState = nextState;
        }

        System.out.println("\n***** Result *****");
        if (Objects.equals(currentState, finalState))
            System.out.println("Valid String");

        else
            System.out.println("Invalid String");

    }

    public static void dfaTableConstruct(){
        DFA.put("A","B|A");
        DFA.put("B","A|C");
        DFA.put("C","C|B");
    }

}
