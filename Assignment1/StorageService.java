//saving data is also another responsibility, so another class is created by SRP
//here we are implementing OCP
//here we can extend the Storage function, without the need of modifying the implemented classes functions
interface Storage
{
  void saveData(); 
}

class SaveToDatabase implements Storage{
 public void saveData(){//overriding the saveData of the interface
   System.out.println("Saving Employee to database");
  }
}

class SaveToFile implements Storage{

 public void saveData(){//overriding the saveData of the interface
   System.out.println("Saving Employees to a File");
  }
}


public class StorageService{//this is high level class which accepts the object of the implemented classes using reference of interface 
  public void storeData(Storage storageMethod){
   storageMethod.saveData();
  }
} 

    
 
