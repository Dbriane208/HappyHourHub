package daniel.brian.happyhourhub.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

import daniel.brian.happyhourhub.databinding.ActivityDescriptionBinding;
import daniel.brian.happyhourhub.db.CartDB;

public class DescriptionActivity extends AppCompatActivity {
    ActivityDescriptionBinding descriptionBinding;

    CartDB cartDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        descriptionBinding = ActivityDescriptionBinding.inflate(getLayoutInflater());
        setContentView(descriptionBinding.getRoot());

        // Creating an Instance of the database
        cartDB = new CartDB(this);

        Intent intent = getIntent();

        int adapterPosition = intent.getIntExtra("position",-1);
        byte[] imageData = intent.getByteArrayExtra("productImage");
        ArrayList<String> productName = intent.getStringArrayListExtra("productName");
        ArrayList<String> productPrice = intent.getStringArrayListExtra("productPrice");
        ArrayList<String> productType = intent.getStringArrayListExtra("productType");
        ArrayList<String> productDescription = intent.getStringArrayListExtra("productDescription");

        if(adapterPosition != -1 && imageData != null && productName != null && productPrice != null && productType != null && productDescription != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData,0,imageData.length);
            descriptionBinding.cocktailImage.setImageBitmap(bitmap);
            descriptionBinding.cocktailName.setText(productName.get(adapterPosition));
            descriptionBinding.cocktailPrice.setText(productPrice.get(adapterPosition));
            descriptionBinding.cocktailType.setText(productType.get(adapterPosition));
            descriptionBinding.cocktailDescription.setText(productDescription.get(adapterPosition));
        }else{
            Snackbar.make(Objects.requireNonNull(this.getCurrentFocus()),"No data Received",Snackbar.LENGTH_LONG).show();
        }

        descriptionBinding.addToCartButton.setOnClickListener(view -> {
            if (adapterPosition != -1 && imageData != null && productName != null && productPrice != null && productType != null && productDescription != null) {
                String name = productName.get(adapterPosition);
                String price = productPrice.get(adapterPosition);
            boolean insert = cartDB.insertCocktailData(name, price, imageData);
            if (insert) {
                Toast.makeText(this, "Product Added To Cart", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show();
            }
        }
        });
    }
}