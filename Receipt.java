package coursework;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Receipt {
    private double amount;
    private String paymentMethod;
    private String paymentDetails;
    private String date;
    private Address deliveryAddress;

    public Receipt(double amount, String paymentMethod, String paymentDetails, Address deliveryAddress) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDetails = paymentDetails;
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        if ("PayPal".equals(paymentMethod)) {
            return String.format("%s paid by PayPal using %s on %s, and the delivery address is %s",
                    amount, paymentDetails, date, deliveryAddress);
        } else if ("Credit Card".equals(paymentMethod)) {
            return String.format("%s paid by Credit Card using %s on %s, and the delivery address is %s",
                    amount, paymentDetails, date, deliveryAddress);
        }
        return "Invalid payment method.";
    }
}
