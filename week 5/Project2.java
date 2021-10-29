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
* @version 2021/10/25
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
    System.out.println("How many employees are there?");
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

    for (int employee = 1; employee <= theEmployeeCnt; employee++) {
      System.out.print("Enter Hours Worked, Pay Rate, and Employee name separated by a space:  ");
      double hours = theConsole.nextInt();
      double payRate = theConsole.nextInt();
      String name = theConsole.next();
      double grossPay = getGrossPay(hours, payRate);
      
      displayEmployeePay(name, hours, payRate, grossPay);
      displayTopEmployee(name, hours);
    }
  }
  
  public static void displayTopEmployee(String theName, double theHours) {
    double mostHours = 0;
    String topEmployee = "";
    if (theHours > 56.0) {
      mostHours = theHours;
      topEmployee = theName;
      System.out.println("More than 56 hours:  WOW!!!" + 
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
      System.out.println("Most hours but no one worked over 40 " + 
                        topEmployee + " Worked " + mostHours + 
                        " Hours this WEEK!");                   
    }
  }
   
  /**
  * 
  * @param theName of an employee
  * @param theHours worked in a week
  * @param thePayRate of an employee
  * @param theGrossPay that accounts for the overtime pay
  */
  public static void displayEmployeePay(String theName, double theHours,
                                        double thePayRate, double theGrossPay) {
    System.out.println("Employee Name: " + theName);
    System.out.println("Hourw worked: " + theHours);
    System.out.println("Pay Rate: " + thePayRate);
    System.out.println("Gross Pay: " + theGrossPay);
  }
  
  /**
  * Calculates the employee's pay that also accounts 
  * for the overtime pay.
  * 
  * @param theHours The number of hours the employee worked
  * @param thePayRate The employee's pay rate
  * @return The weekly gross pay
  */
  public static double getGrossPay(double theHours, double thePayRate) {
    double result = 0.00;
    if (theHours <= 40.00) {
      result = under40(theHours, thePayRate);
    }
    else if (theHours > 40.00 && theHours <= 48.00) {
      result = (theHours - 40.00) * 1.5 * thePayRate + 40 * thePayRate;
    } else {
      result = (theHours - 48) * 2 * thePayRate + (theHours - 40.00) * 1.5 * thePayRate + 40 * thePayRate;
    }
    return result;
  }
  
  /**
  * Calculates the pay that is worked less than 40 hours.
  *
  * @param theHours The number of hours the employee worked
  * @param thePayRate The employee's pay rate
  * @return The weekly gross pay
  */
  public static double under40 (double theHours, double thePayRate) {
    return theHours * thePayRate;
  }

  /**
  * Calculates the pay that is worked less than 48 hours.
  *
  * @param theHours The number of hours the employee worked
  * @param thePayRate The employee's pay rate.
  * @return The weekly gross pay.
  */
  public static double under48 (double theHours, double thePayRate) {
    double result = under40(theHours, thePayRate);
    result += (theHours - 40.00) * 1.5 * thePayRate;
    return result;
  }
}