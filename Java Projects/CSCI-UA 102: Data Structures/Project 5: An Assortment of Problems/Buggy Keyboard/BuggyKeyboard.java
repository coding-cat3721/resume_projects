//package project5;

 
import java.util.Scanner;

public class BuggyKeyboard {
    public static void main(String[] args) throws Exception {

      
        Scanner userInput = new Scanner(System.in);
        StringBuilder current = new StringBuilder();
        char letter = ',';// skip sign
        char preLetter = ',';// skip sign
        StringBuilder line = new StringBuilder(userInput.nextLine());
        int length = line.length();
       // int maxLength = (int) Math.pow(10, 6);
      
       // if (length > maxLength)
       //     length = maxLength;
        int endIndex = length - 1;
       
        for (int i = 0; i < length; i++) {
            letter = line.charAt(i);
            if (letter >= 'a' && letter <= 'z' || letter == ' ') {
                if (preLetter != ',')
                    current.append(preLetter);
                if (i == endIndex) {
                    current.append(letter);                
                }
                preLetter = letter;
            } else if (letter == '<') {   
                if (preLetter != ',')
                    preLetter = ',';
                else {
                    if (current.length() > 0)
                        current.setLength(current.length() - 1);
                }
            } else if (letter == '\n') {
                current.append(letter);
            }
        }

        int index = current.length() - 1;
        boolean space = true;
        while (space && index >= 0) {
            if (current.charAt(index) == ' ' || current.charAt(index) == '\n')
                index--;
            else
                space = false;
        }
        if (index >= 0) {
            System.out.print(current.substring(0, index + 1));
        }
        userInput.close();
    }
}
