import java.util.*;

public class Main {

    public static String[] reverse(String[] words) {
        // write your code here
        String[] reverse = new String[words.length];
        for(int i = words.length - 1; i >= 0; i--) {
            reverse[words.length - 1 - i] = words[i];
        }
        return reverse;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split("\\s+");
        String[] reversed = reverse(words);
        Arrays.stream(reversed).forEach(e -> System.out.print(e + " "));
    }
}