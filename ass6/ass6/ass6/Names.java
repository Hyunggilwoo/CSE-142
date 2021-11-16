import java.util.*;
import java.io.*;
import java.awt.*;

public class Names {

    public static final int NUMBER_OF_DECADES = 14;
    
    public static final int FIRST_YEAR = 1880;
    
    public static final int HORIZONTAL_WIDTH = 70;
    
    public static void main(String[] theArgs) throws FileNotFoundException {
        Scanner input = new Scanner(new File("names.txt")); //"names2.txt"
        Scanner console = new Scanner(System.in);
        intro();
        System.out.print("name? ");
        String name = console.nextLine();
        System.out.print("sex (M or F) ? ");
        String sex = console.next();
        String line = find(input, name, sex);
        if (line.length() > 0) {
            print(line);
            DrawingPanel panel = new DrawingPanel(NUMBER_OF_DECADES * 
                                                HORIZONTAL_WIDTH, 550);
            Graphics g = panel.getGraphics();
            drawGraph(g, line);
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
    public static String find(Scanner input, String theName, String theSex) {
        while (!input.equals("")) { // input.hasNext()
            String line = "";
            String name = input.next();
            String sex = input.next();
//             String ranks = input.nextLine();
            if (name.toLowerCase().equals(theName.toLowerCase())
                && sex.toLowerCase().equals(theSex.toLowerCase())) {
                while (!input.equals("")) {
                    String ranks = input.next();
                    line = ranks + " ";
                }
                return name + " " + sex + " " + line;
            }  
        }
        return "";
    }
    
    // Prints the ranks of the popular name in each decade
       
    public static void print(String theLine) {
        Scanner data = new Scanner(theLine);
        int year = FIRST_YEAR - 10;
        String name = data.next();
        String sex = data.next();
        String totalRank = "";
        for (int num = 0; num < NUMBER_OF_DECADES; num++) {
            int rank = data.nextInt();
            year+=10;
            System.out.print( "(" + name + " " + sex + " " + year + ", " + rank + ") ");
        }
    }
    // The horizontal line at the top is 25 pixels below the top;
    // the horizontal line at the bottom is 25 pixels above the bottom;
    // The panel is divided into the NUMBER_OF_DECADES;
    // These sections have horizontal width of 70 pixels each
    // Vertical line to the left of each of the 14 sections is drawn
    // including x coordinate of 0;
    // Decades are labeled at the bottom panel using drawString method;
    // The int value of the year is turned into a String by
    // concatenating empty String as "" + 1880;
    // There are 500 pixels to plot the values; The rank range from
    // 1 to 1000 (i.e. rank of 1 or 2 should be drawn at y - coordiante
    // of 25, rank of 3 or 4 should be drawn at y - coordinate of 26...)
    // up to ranks of 999 to 1000 should be drawn at y-coordinate of 524;
    // A rank of 0 should be printed at the very bottom (525);
    public static void drawGraph(Graphics g, String theLine) {
        Scanner data = new Scanner(theLine);
        int year = FIRST_YEAR;
        String name = data.next();
        String sex = data.next();
        g.drawLine(0, 25, NUMBER_OF_DECADES * HORIZONTAL_WIDTH, 25);
        g.drawLine(0, 525, NUMBER_OF_DECADES * HORIZONTAL_WIDTH, 525);
        g.drawLine(0, 0, 0, 550);
        String totalRank = "";
//         int rank = data.nextInt();
        // {# of token > 0}
        while (data.hasNext()) {
            for (int num = 0; num < NUMBER_OF_DECADES; num++) {
                int rank = data.nextInt();
                g.drawLine(70 * num, 0, 70 * num, 550);
                g.drawString(String.valueOf(year), 70 * num, 550);
                year+=10;
                g.drawString(name + " " + sex + " " + rank, 70 * num, findY(rank));    
            }

        }
        // {# of token = 0}
        
    }
    
    /**
    * Computes the y coordinate of a name depending on the rank
    * (if rank is greater 0); otherwise returns 525 on the GUI
    *
    * @param rank is the number for the name
    */
    public static int findY(int rank) {
        int result = 0;
        if (rank > 0) {
            result = 24 + Math.round((float)Math.ceil(0.5 * rank));
        } else {
            result = 525;
        }
        return result; 
    }
        /** {rank > 0, rank = {2, 4, ... 999} result = {25, 26, ... 524} 
                        1                       25                          (?? * 1) = 1
                        2                       25                      (int) 24 + (?? * rank) = 25
                                                                            (?? * 2) = 1
                                                                            ?? = 1/2
                                                                            

                        4                       26
                                                
                        1000                    524                     (int) 24 + (?? * 1000) = 524
                                                                            (?? * 1000) = 500
                                                                            ?? = 500/1000
                                                                            ?? = 0.5
                                                                        
        *    
        **/ 
    
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