//this is the main method which calls all methods  
public class Manager{
  public static void main(String[] args){

   Employee e1 = new Employee("Sai",12000.0);//passing arguments to the employee class constructor
   StorageService service = new StorageService();//creating high class of db and file
   Storage db = new SaveToDatabase();//creating object saveToDatabase class of Storage interface
   Storage file = new SaveToFile();
   PrintingService print = new PrintingService();//creating the object of the PrintingService   
 
   service.storeData(db);//save data to database
   service.storeData(file);//save data to file storage
   print.printPaySlip(e1);//prints employee details  	 
  }
}

//before all the different responsibilities were on the same class violationg SRP,
//now each responsibilty has its own class
