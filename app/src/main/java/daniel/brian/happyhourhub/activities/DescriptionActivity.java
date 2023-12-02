package daniel.brian.happyhourhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.util.ArrayList;

import daniel.brian.happyhourhub.R;
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
        ArrayList<String> productName = intent.getStringArrayListExtra("productName");
        ArrayList<String> productPrice = intent.getStringArrayListExtra("productPrice");
        ArrayList<String> productType = intent.getStringArrayListExtra("productType");
        ArrayList<String> productDescription = intent.getStringArrayListExtra("productDescription");

        if(adapterPosition != -1){
            descriptionBinding.cocktailName.setText(productName.get(adapterPosition));
            descriptionBinding.cocktailPrice.setText(productPrice.get(adapterPosition));
            descriptionBinding.cocktailType.setText(productType.get(adapterPosition));
            descriptionBinding.cocktailDescription.setText(productDescription.get(adapterPosition));
        }
    }
}