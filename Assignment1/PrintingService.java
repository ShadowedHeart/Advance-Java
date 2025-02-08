public class PrintingService{//since Printing payslip is not the responsibility employee who is getting the salary
 public  void printPaySlip(Employee e){//so we created another class following SRP, it takes object of employee as as parameter for displaying the details
  System.out.println("Employee: "+e.name+"Salary: "+e.salary); 
 }
} 
