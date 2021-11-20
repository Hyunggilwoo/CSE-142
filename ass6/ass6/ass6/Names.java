/*
* Course: CSE 142 – Introduction to Object-Oriented Programming
* File Name: Names.java
* Due Date: November 30, 2021
* Instructor: Professor Stuart Reges
*/

import java.util.*;
import java.io.*;
import java.awt.*;

/**
* This program prints a line graph of a baby names based on their popularity
* from 1880 to 2010. 
*
* @author Hyunggil Woo
* @version 2021/11/17
*/

public class Names {

    public static final int NUMBER_OF_DECADES = 14; //14 
    
    public static final int FIRST_YEAR = 1880; //1880
    
    public static final int HORIZONTAL_WIDTH = 70;
    
    public static void main(String[] theArgs) throws FileNotFoundException {
        Scanner input = new Scanner(new File("names.txt")); //"names.txt"
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
    *  Pre: Expects the method to return the line that has the name and the sex.
    *  The method is not parsing through the entire text files. 
    * 
    *  Post: Returns a line that contains the name and the sex. This method passes
    *  the String to find method.
    *
    * @param input scans the entire input file
    * @param the name given to examine
    * @return the line (if there is one)
    */
    public static String findLine(Scanner input, String theName, 
                                    String theSex) {
        while (input.hasNextLine()) { //!line.equals("")
            String line = input.nextLine();
            if (line.toLowerCase().contains(theName.toLowerCase())
                && line.toLowerCase().contains(theSex.toLowerCase())) {
                return line;
            }  
        }
        return "";
    }

    /**
    *  Pre: Accepts a String from a findLine().
    * 
    *  Post: Return the correct line of input
    *  containing the exact name and sex on the line.
    *
    * @param scanned line from findLine method
    * @param the name given to examine
    * @return the line (if there is one)
    */    
    public static String find(Scanner input, String theName, String theSex) {
        while (input.hasNextLine()) { //!line.equals("")
            String line = input.nextLine();
            Scanner console = new Scanner(line);
            while (console.hasNext()) {
                String name = console.next();
                System.out.println(name); //
                String sex = console.next();

                if (name.toLowerCase().equals(theName.toLowerCase())
                    && sex.toLowerCase().equals(theSex.toLowerCase())) {
                        return line;
                } // {return line 
            } //{""} 
        } // {""}
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
    
    /*
    *  Pre: The first token is a string of a name, the second token is either
    *  M or F, then the rest of the tokens in the line should be integers 
    *  indicating the ranks. The rank must be between 1 and 1000.
    *  (If rank is greater than 1000 or a negative number, incorrect output
    *  is given.)
    *
    *  Post: Uses the DrawingPanel and accepts a scanner of a line of text.
    *  Prints a name on each year depending on their ranking. Graph is 
    *  printed with a line connecting the ranks of the name to its
    *  adjacent names on the graph. The lower number in ranking is printed 
    *  higher on on y-axis. If the name was not ranked, it will be given 
    *  a rank of 0.
    */
    public static void drawGraph(Graphics g, String theLine) {
        Scanner data = new Scanner(theLine);
        int year = FIRST_YEAR;
        String name = data.next();
        String sex = data.next();
        g.drawLine(0, 25, NUMBER_OF_DECADES * HORIZONTAL_WIDTH, 25);
        g.drawLine(0, 525, NUMBER_OF_DECADES * HORIZONTAL_WIDTH, 525);
        g.drawLine(0, 0, 0, 550);
        String totalRank = "";
        while (data.hasNext()) {
            // fencepost: 
            int rank = data.nextInt();
            g.drawLine(0, 0, 0, 550);
            g.drawString(String.valueOf(year), 0, 550); 
            g.drawString(name + " " + sex + " " + rank, 0, findY(rank));
            int temp = findY(rank); // value used to connect values on a line      
            for (int num = 1; num < NUMBER_OF_DECADES; num++) {
                rank = data.nextInt();
                year+=10; 
                g.drawLine(70 * num, 0, 70 * num, 550);
                g.drawLine(70 * (num - 1), temp, 70 * num, findY(rank)); 
                temp = findY(rank); // reassigning temp to the rank in a previous line
                g.drawString(String.valueOf(year), 70 * num, 550); 
                g.drawString(name + " " + sex + " " + rank, 70 * num, findY(rank));    
            } 

        } 
        
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