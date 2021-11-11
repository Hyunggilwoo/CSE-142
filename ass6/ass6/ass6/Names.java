import java.util.*;
import java.io.*;

public class Names {
    public static void main(String[] theArgs) throws FileNotFoundException {
        Scanner input = new Scanner(new File("names.txt"));
        Scanner console = new Scanner(System.in);
        intro();
        System.out.print("name? ");
        String name = console.nextLine();
        System.out.print("sex (M or F) ? ");
        String sex = console.next();
        String line = find(input, name, sex);
        System.out.println(line);
    }
    
    /**
    * First scanner finds thename in the input text file;
    * second scanner finds the sex;
    * third scanner finds the popularity rank
    *
    * @param input scans the entire input file
    * @param the name given to examine
    * @return the line (if there is one)
    */
    public static String find(Scanner input, String name, String sex) {
        while (input.hasNext()) { //!line.equals("")
            String token1 = input.next();
            String token2 = input.next();
            String ranks = input.nextLine();
            
            if (token1.toLowerCase().equals(name.toLowerCase())
                && token2.toLowerCase().equals(sex.toLowerCase())) {
                return token1 + " " + token2 + ranks;
            }  
        }
        return "";
    }
    
        
    
    
    /**
    * Introduces the game to the user.
    */
    public static void intro() {
        System.out.println("This program allows you to search " +
                "through the \n" + "data from the Social Security " + 
                "Administration \n" + " to see how popular a " +
                "particular name has been\n" + "since 1920.");
    }    
    
}