// the problem in the initial code was bird was a concrete class which had method of flying,but as super class, the subclass such as penguin could not extend the bird, although its a bird because penguin cannot fly,so it violated the LSP.

interface Flyable{//so i created a different interface for flying and abstract class for bird
  void fly();     //because every bird cannot fly. 
}

abstract class Bird{
  Bird(){
   System.out.println("I m a bird");
  }
}

class Penguin extends Bird{//penguin is also a bird who can swim
  public void swim(){
  System.out.println("I can swim");
  }
}

class Parrot extends Bird implements Flyable{//parrot is bird which can also fly
  @Override
   public void fly(){
   System.out.println("I can fly");
  }
}

public class BirdSolution{
  public static void main(String [] args){
   Penguin penguin =  new Penguin();
   Parrot parrot = new Parrot();
   penguin.swim();
   parrot.fly();
  }
} 
