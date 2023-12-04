package daniel.brian.happyhourhub.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import daniel.brian.happyhourhub.databinding.ActivityDescriptionBinding;

public class DescriptionActivity extends AppCompatActivity {
    ActivityDescriptionBinding descriptionBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        descriptionBinding = ActivityDescriptionBinding.inflate(getLayoutInflater());
        setContentView(descriptionBinding.getRoot());

        Intent intent = getIntent();

        int adapterPosition = intent.getIntExtra("position",-1);
        byte[] imageData = intent.getByteArrayExtra("productImage");
        ArrayList<String> productName = intent.getStringArrayListExtra("productName");
        ArrayList<String> productPrice = intent.getStringArrayListExtra("productPrice");
        ArrayList<String> productType = intent.getStringArrayListExtra("productType");
        ArrayList<String> productDescription = intent.getStringArrayListExtra("productDescription");

        if(adapterPosition != -1 && imageData != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData,0,imageData.length);
            descriptionBinding.cocktailImage.setImageBitmap(bitmap);
            descriptionBinding.cocktailName.setText(productName.get(adapterPosition));
            descriptionBinding.cocktailPrice.setText(productPrice.get(adapterPosition));
            descriptionBinding.cocktailType.setText(productType.get(adapterPosition));
            descriptionBinding.cocktailDescription.setText(productDescription.get(adapterPosition));
        }else{
            Snackbar.make(this.getCurrentFocus(),"No data Received",Snackbar.LENGTH_LONG).show();
        }
    }
}