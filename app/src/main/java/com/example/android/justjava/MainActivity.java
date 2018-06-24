package com.example.android.justjava;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        CheckBox toppingCheckBox = (CheckBox) findViewById(R.id.checkBox);
        boolean hasCreamTopping = toppingCheckBox.isChecked();
        String priceMessage = createOrderSummary(price, hasCreamTopping);
       // priceMessage += "\nThank you!";
        displayMessage(priceMessage);

    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice(){
        return quantity * 5;
    }

    /**
     *
     * @param price the total price of the order
     * @param addWhippedCream indicate whether Cream Topping is required or not
     * @return the order summary "Name , quantity , price"
     */
    private String createOrderSummary(int price, boolean addWhippedCream) {
        String message = "Name: Kaptain Kunal\n" ;
        message += "Add Whipped Cream? " + addWhippedCream + "\n";
        message += "Quantity:" + quantity +"\n" ;
        message += "Total: $" + price + "\n" + "Thank you!";
        return message;
    }
    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



}
