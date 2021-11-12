import java.util.*;
import java.io.*;

public class Names {

    public static final int NUMBER_OF_DECADES = 14;
    
    public static void main(String[] theArgs) throws FileNotFoundException {
        Scanner input = new Scanner(new File("names.txt"));
        Scanner console = new Scanner(System.in);
        intro();
        System.out.print("name? ");
        String name = console.nextLine();
        System.out.print("sex (M or F) ? ");
        String sex = console.next();
        String line = find(input, name, sex);
        if (line.length() > 0) {
             print(line);

        } else {
            System.out.println("name/sex combination not found");
        }
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
        while (input.hasNextLine()) { //!line.equals("")
            String line = input.nextLine();
//             String ranks = input.nextLine();
            
            if (line.toLowerCase().contains(name.toLowerCase())
                && line.toLowerCase().contains(sex.toLowerCase())) {
                return line;
            }  
        }
        return "";
    }
    
    // Prints the ranks of the popular name in each decade
       
    public static void print(String theLine) {
        Scanner data = new Scanner(theLine);
        int year = 1880;
        String name = data.next();
        String sex = data.next();
        String totalRank = "";
        for (int num = 0; num < NUMBER_OF_DECADES; num++) {
            int rank = data.nextInt();
            year+=10;
            System.out.print( "(" + name + ", " + year + ", " + rank + ") ");
        }
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