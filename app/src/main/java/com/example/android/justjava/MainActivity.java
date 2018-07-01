package com.example.android.justjava;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    /**
//     * This method displays the given text on the screen.
//     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


        // Figure out if the user wants whipped cream topping
        CheckBox toppingCheckBox = (CheckBox) findViewById(R.id.checkBox);
        boolean hasCreamTopping = toppingCheckBox.isChecked();

        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.checkBox2);
        boolean hasChocolte = chocolateCheckBox.isChecked();

        //Calculate the total price
        int price = calculatePrice(hasCreamTopping, hasChocolte);

        //get the name of the user
        EditText userName = (EditText) findViewById(R.id.nameText);
        String name = userName.getText().toString();

        String priceMessage = createOrderSummary(name, price, hasCreamTopping, hasChocolte);
       // priceMessage += "\nThank you!";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

//        displayMessage(priceMessage);

    }

    /**
     * Calculates the price of the order.
     *
     * @param hasWhippedCreamT  indicates if the user wants whipped cream topping
     * @param hasChocolateT indicates if the user wants chocolate topping
     * @return total price
     */
    private int calculatePrice(boolean hasWhippedCreamT, boolean hasChocolateT){
        //the base price of one cup of coffee
        int basePrice = 5;

        //add 1$ if the user wants cream topping
        if (hasWhippedCreamT) {
            basePrice += 1;
        }

        //add 2$ if the user wants chocolate topping
        if (hasChocolateT) {
            basePrice += 2;
        }

        //calculate and return the total price
        return quantity * basePrice;
    }

    /**
     *Create summary of the order.
     *
     * @param userName the user name
     * @param price the total price of the order
     * @param addWhippedCream indicate whether Cream Topping is required or not
     * @param addChocolate   indicate whether chocolate is required or not
     * @return the order summary "Name , quantity , price"
     */
    private String createOrderSummary(String userName, int price, boolean addWhippedCream, boolean addChocolate) {
        String message = getString(R.string.order_summary_name,userName) + "\n" ;
        message += "Add Whipped Cream? " + addWhippedCream + "\n";
        message += "Add Chocolate? " + addChocolate + "\n";
        message += "Quantity:" + quantity +"\n" ;
        message += "Total: $" + price + "\n" + getString(R.string.thank_you);
        return message;
    }
    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            //exit the method if the quantity is 100 and show an error message as a Toast
            Toast.makeText(this,"You cannot have more than 100 coffee",Toast.LENGTH_LONG).show();
            return;
        }

        //increase the quantity by one;
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            //exit the method if the quantity is 1 and show an error message as a Toast
            Toast.makeText(this,"You cannot have less than 1 coffee",Toast.LENGTH_LONG).show();
        return;
        }
        //decrease the quantity by one;
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
