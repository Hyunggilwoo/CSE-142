/*
* Course: TCSS142 – Introduction to Object-Oriented Programming
* File Name: Project1.java
* Assignment: 2
* Due Date: October 19, 2021
* Instructor: Mr. Schuessler
*/

import java.util.Scanner;

/**
* This program draws an image of the Space Needle.
* I used the nested for loops and used a constant SIZE
* to vary the the size of the space needle. I also used
* the for loop table to draw the output of this ASCII art.
*
* @author Hyunggil Woo
* @version 2021/11/04
*/
public class Project2 {

    /**
    * This is the main driver of the employee project.
    * 
    * @param theArgs to process methods
    */
    public static void main(String[] theArgs) {
        Scanner console = new Scanner(System.in);   
        int employeeCnt = getEmployeeCnt(console);
        processEmployeePay(console, employeeCnt);
    }

    /**
    * Asks a user to enter the number of employee.
    * 
    * @param theConsole to prompt for number of employees
    * @return the number of employees
    */
    public static int getEmployeeCnt(Scanner theConsole) {
        System.out.print("How many employees are there?");
        int EmployeeCnt = theConsole.nextInt();
        return EmployeeCnt;
    }

    /**
    * Continues to process the total pay for each employees
    * until all employees are processed.
    * 
    * @param theConsole to prompt for inputs to the questions
    * @param theEmployeeCnt the number of times to process the pay
    */
    public static void processEmployeePay(Scanner theConsole, int theEmployeeCnt) {
        double mostHours = 0;
        String mostHoursEmployee = "";
        for (int employee = 1; employee <= theEmployeeCnt; employee++) {
            System.out.print("Enter Hours Worked, Pay Rate, and Employee name separated by a space:  ");
            double hours = theConsole.nextDouble();
            double payRate = theConsole.nextDouble();
            String name = theConsole.nextLine();
            double grossPay = getGrossPay(hours, payRate);
            
            if (hours > mostHours) {
                mostHours = hours;
                mostHoursEmployee = name;
            }
                
            displayEmployeePay(name, hours, payRate, grossPay);
        }
        displayTopEmployee(mostHoursEmployee, mostHours);
    }
    
    /**
    * Displays the name of the employee that worked the most hours.
    * 
    * @param theName of the top employee
    * @param theHours worked by the top employee
    */
    public static void displayTopEmployee(String theName, double theHours) {
        System.out.println();
        double mostHours = 0;
        String topEmployee = "";
        if (theHours > 56.0) {
            mostHours = theHours;
            topEmployee = theName;
            System.out.println("More than 56 hours:  WOW!!! " + 
                                "What a Dynamo! " + topEmployee + 
                                " Worked " + mostHours + " Hours this WEEK!");
        } else if (theHours > 48) {
            mostHours = theHours;
            topEmployee = theName;
            System.out.println("More than 48 hours:  " + topEmployee + 
                                " is Such a WorkHorse! " + 
                                "Looks Like You Worked " + mostHours + 
                                " Hours this WEEK!");
        } else if (theHours > 40) {
            mostHours = theHours;
            topEmployee = theName;
            System.out.println("More than 40 hours:  Well, Good For YOU " + 
                            topEmployee + ", Who Worked " + mostHours + 
                                " Hours this WEEK!");
        } else {
            mostHours = theHours;
            topEmployee = theName;
            System.out.println("Most hours but no one worked over 40: " + 
                            topEmployee + " Worked " + mostHours + 
                            " Hours this WEEK!");                   
        }
    }
   
    /**
    * Displays the name, the hours worked, the pay rate, and
    * the gross pay of an employee. 
    *
    * @param theName of an employee
    * @param theHours worked in a week
    * @param thePayRate of an employee
    * @param theGrossPay of an employee
    */
    public static void displayEmployeePay(String theName, double theHours,
                                            double thePayRate, double theGrossPay) {
        System.out.println(String.format("%27s", "Employee Name:") + theName);
        System.out.println(String.format("%27s", "Hours Worked:") + String.format("%13.2f", theHours));
        System.out.println(String.format("%27s", "Pay Rate:") + String.format("%13.2f", thePayRate));
        System.out.println(String.format("%27s", "Gross Pay:") + String.format("$%,12.2f", theGrossPay));
        System.out.println();
    }
    
    /**    
    * Calculates the employee's pay that also accounts 
    * for the overtime pay.
    * 
    * @param theHours that the employee worked
    * @param thePayRate of an employee
    * @return weekly gross pay
    */
    public static double getGrossPay(double theHours, double thePayRate) {
        double pay = 0.00;
        if (theHours <= 40.00) {
            pay = under40(theHours, thePayRate);
        } else if (theHours <= 48.00) {
            pay = under48(theHours, thePayRate);
        } else {
            pay = over48(theHours, thePayRate);
        }
        return pay;
    }  
         
    /**
    * Calculates the pay that is worked less than 40 hours.
    *
    * @param theHours that the employee worked
    * @param thePayRate of an employee
    * @return weekly gross pay
    */
    public static double under40(double theHours, double thePayRate) {
        return theHours * thePayRate;
    }

    /**
    * Calculates the pay that is worked less than 48 hours.
    *
    * @param theHours that the employee worked
    * @param thePayRate of an employee
    * @return weekly gross pay
    */
    public static double under48(double theHours, double thePayRate) {
        double pay = under40(theHours, thePayRate);
        pay += (theHours - 40.00) * 1.5 * thePayRate;
        return pay;
    }

    /**
    * Calculates the pay that is worked over 48 hours.
    *
    * @param theHours that the employee worked
    * @param thePayRate of an employee
    * @return weekly gross pay
    */  
    public static double over48(double theHours, double thePayRate) {
        double pay = under48(theHours, thePayRate);
        pay += (theHours - 48.00) * 2 * thePayRate;
        return pay;
    }
}