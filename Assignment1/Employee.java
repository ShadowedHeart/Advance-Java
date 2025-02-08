public class Employee{//this class is having a single responsibility of assigning name and salary,following SRP 

  String name;
  double salary;

  Employee(String name, double salary){//this constructor takes name and salary as its parameter
   this.name = name;
   this.salary = salary; 
  }

}
