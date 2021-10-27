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
      System.out.println("Employee Name: ");
      String name = theConsole.next();
      System.out.println("Hours Worked: ");
      double hours = theConsole.nextInt();
      System.out.println("Pay Rate: ");
      double payRate = theConsole.nextInt();
      System.out.println("Gross Pay: ");
      double grossPay = getGrossPay(hours, payRate);
    }
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
    if ( theHours <= 40.00) {
//       result = theHours * thePayRate;
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