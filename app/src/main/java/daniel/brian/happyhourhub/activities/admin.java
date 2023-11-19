package daniel.brian.happyhourhub.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import daniel.brian.happyhourhub.R;
import daniel.brian.happyhourhub.db.AdminDB;

public class admin extends AppCompatActivity {

    private AdminDB adminDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_layout);

        adminDB = new AdminDB(this); // Create an instance of AdminDB

        EditText name = (EditText) findViewById(R.id.productName);
        EditText price = (EditText) findViewById(R.id.productPrice);
        EditText description = (EditText) findViewById(R.id.productDescription);
        EditText type = (EditText) findViewById(R.id.productType);
        Button post = (Button) findViewById(R.id.buttonSend);


        post.setOnClickListener(view -> {
            // Validate input fields
            String productName = name.getText().toString().trim();
            String productPrice = price.getText().toString().trim();
            String productDescription = description.getText().toString().trim();
            String productType = type.getText().toString().trim();

            if (productName.isEmpty() || productPrice.isEmpty() || productDescription.isEmpty() || productType.isEmpty()) {
                Toast.makeText(this, "Please Enter all Fields", Toast.LENGTH_LONG).show();
            } else {
                // Insert product data into the database
                boolean insert = adminDB.insertCocktailData(productName, productPrice, productDescription, productType);

                if (insert) {
                    // Product inserted successfully
                    Toast.makeText(this, "Product Added Successfully", Toast.LENGTH_LONG).show();
                } else {
                    // Failed to insert product
                    Toast.makeText(this, "Failed to Add Product", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
