class CreditCard implements CardPayment {
  public void payWithCard(){
   System.out.println("Payment done using Credit card");
  }
}

class PayPal implements OnlinePayment {
  public void payOnline(){
  System.out.println("Payment done using Paypal");
  }
}

class GooglePay implements OnlinePayment {
  public void payOnline(){
  System.out.println("Payment done using GooglePay");
  }
}

public class PaymentProcessor { //follows DIP
  public void paymentServiceCard(CardPayment method){
   method.payWithCard();
  }
  
  public void paymentServiceOnline(OnlinePayment method){
    method.payOnline();
  }
} 
