package css.cis3334.pizzaorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements updateViewInterface {

    RadioButton rbSmall;
    RadioButton rbMedium;
    RadioButton rbLarge;
    CheckBox chkbxCheese;
    CheckBox chkbxDelivery;
    TextView txtTotal;
    TextView txtStatus;
    PizzaOrderInterface pizzaOrderSystem;
    Spinner pizzaToppingsSpinner;
    Boolean extraCheese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbSmall = (RadioButton) findViewById(R.id.radioButtonSmall);
        rbMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
        rbLarge = (RadioButton) findViewById(R.id.radioButtonLarge);

        chkbxCheese = (CheckBox) findViewById(R.id.checkBoxCheese);
        chkbxDelivery = (CheckBox) findViewById(R.id.checkBoxDeluvery);

        txtTotal = (TextView) findViewById(R.id.textViewTotal);
        txtStatus = (TextView) findViewById(R.id.textViewStatus);

        pizzaToppingsSpinner = (Spinner) findViewById(R.id.PizzaSelectSpinner);

        pizzaOrderSystem = new PizzaOrder(this);
    }

    @Override
    public void updateView(String orderStatus) {
        txtStatus.setText("Order Status: " + orderStatus);
    }

    public void onClickOrder(View view) {

        pizzaOrderSystem.setDelivery(chkbxDelivery.isChecked());

        String topping = pizzaToppingsSpinner.getSelectedItem().toString();
        String size = "large";
        if (rbSmall.isChecked()){
            size = "small";
        }
        if (rbMedium.isChecked()){
            size = "medium";
        }

        if (chkbxCheese.isChecked()){
            extraCheese = true;
        } else {
            extraCheese = false;
        }

        String orderDescription = pizzaOrderSystem.OrderPizza(topping, size, extraCheese);

        if (chkbxDelivery.isChecked()){
            orderDescription += " for delivery.";
        } else {
            orderDescription += " for pickup.";
        }

        //display a pop up message for a long period of time
        Toast.makeText(getApplicationContext(), "You have ordered a "+ orderDescription, Toast.LENGTH_LONG).show();
        txtTotal.setText("Total Due: " + pizzaOrderSystem.getTotalBill().toString());
    }
}
