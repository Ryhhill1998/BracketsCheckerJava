import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String input = getUserInput();
        System.out.println(bracketsAreValid(input));
    }

    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the text you would like to be checked:");
        return scanner.nextLine();
    }

    private static boolean bracketsAreValid(String input) {
        Stack<String> bracketStack = new Stack<>();
        String[] splitInput = input.split("");
        String brackets = "(){}[]";
        String openingBrackets = "({[";
        HashMap<String, String> bracketPairs = new HashMap<>();
        bracketPairs.put("(", ")");
        bracketPairs.put("{", "}");
        bracketPairs.put("[", "]");

        for (String character : splitInput) {
            if (!brackets.contains(character)) {
                continue;
            }

            if (bracketStack.isEmpty()) {
                if (openingBrackets.contains(character)) {
                    bracketStack.push(character);
                } else {
                    return false;
                }
            } else if (openingBrackets.contains(character)) {
                bracketStack.push(character);
            } else {
                String topBracket = bracketStack.pop();
                if (!bracketPairs.get(topBracket).equals(character)) {
                    return false;
                }
            }
        }

        return true;
    }
}