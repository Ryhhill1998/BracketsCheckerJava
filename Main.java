import java.util.*;

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

    private static HashSet<String> getBracketsSet(String[] characters) {
        return new HashSet<>(Arrays.asList(characters));
    }

    private static boolean bracketsAreValid(String input) {
        Stack<String> bracketStack = new Stack<>();
        String[] splitInput = input.split("");
        HashSet<String> brackets = getBracketsSet("(){}[]".split(""));
        HashSet<String> openingBrackets = getBracketsSet("({[".split(""));
        HashMap<String, String> bracketPairs = new HashMap<>();
        bracketPairs.put("(", ")");
        bracketPairs.put("{", "}");
        bracketPairs.put("[", "]");

        boolean isValid = true;

        for (String character : splitInput) {
            if (!brackets.contains(character)) {
                continue;
            }

            if (bracketStack.isEmpty()) {
                if (openingBrackets.contains(character)) {
                    bracketStack.push(character);
                } else {
                    isValid = false;
                    break;
                }
            } else if (openingBrackets.contains(character)) {
                bracketStack.push(character);
            } else {
                String topBracket = bracketStack.pop();
                if (!bracketPairs.get(topBracket).equals(character)) {
                    isValid = false;
                    break;
                }
            }
        }

        if (!bracketStack.isEmpty()) {
            isValid = false;
        }

        return isValid;
    }
}