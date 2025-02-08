import java.util.Scanner;
public class PaymentProcessingSystem{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    CardPayment creditCard = new CreditCard();
    OnlinePayment payPal = new PayPal();
    OnlinePayment googlePay = new GooglePay();
    PaymentProcessor service = new PaymentProcessor();
    
    System.out.println("Choose ur Payment Method");
    System.out.println("1 for CreditCard\n2 for Paypal\n3 for GooglePay");
    int choice = sc.nextInt();
    
    switch(choice){
      case 1: service.paymentServiceCard(creditCard); break;
      case 2:service.paymentServiceOnline(payPal); break;
      case 3: service.paymentServiceOnline(googlePay); break;
      default:System.out.println("invalid choice");return;   
    }
    sc.close(); 
 }
} 

//here each class like googlpay has single responsibility,thus following SRP
//every method is following OCP because we can extend the interfaces and no modification can be done in concrete class
//it follows LSP since every superclass in implemented by subclass without changing the behavior
//DIP is induced using abstract class paymentprocessor accepts the object of any class implemented the interface giving flexibility over the structure and code.without the need of changing the concrete code.
//ISP is done when the main interface had method like paywithcard and payOnline, segregated to single interfaces, thus following interface segregation principle
