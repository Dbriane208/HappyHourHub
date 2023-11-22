package daniel.brian.happyhourhub.fragments.main;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

import daniel.brian.happyhourhub.R;
import daniel.brian.happyhourhub.adapters.HomeScreenAdapter;
import daniel.brian.happyhourhub.databinding.FragmentHomeBinding;
import daniel.brian.happyhourhub.db.AdminDB;

public class HomeFragment extends Fragment {
    FragmentHomeBinding fragmentHomeBinding;
    RecyclerView recyclerView;
    AdminDB adminDB;
    HomeScreenAdapter homeScreenAdapter;
    ArrayList<String> productName, productPrice, productDescription, productType;
    ArrayList<byte[]> Add_image_button;

    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Implementing the viewFlipper
        fragmentHomeBinding = FragmentHomeBinding.inflate(getLayoutInflater());

        // setting up the view flipper
        setUpFlipper();

        adminDB = new AdminDB(this.getContext());
        productName = new ArrayList<>();
        productPrice = new ArrayList<>();
        productDescription = new ArrayList<>();
        productType = new ArrayList<>();
        Add_image_button = new ArrayList<byte[]>();

        recyclerView = fragmentHomeBinding.availableCocktails;
        homeScreenAdapter = new HomeScreenAdapter(this.getContext(), productName, productPrice, productDescription, productType, Add_image_button);
        recyclerView.setAdapter(homeScreenAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        displayData();

        return fragmentHomeBinding.getRoot();
    }

    private void setUpFlipper() {
        ViewFlipper viewFlipper = fragmentHomeBinding.famousCocktails;
        viewFlipper.setInAnimation(getContext(), R.anim.from_right);

        int[] imageList = {
                R.drawable.cocktailone,
                R.drawable.cocktailtwo,
                R.drawable.cocktailthree,
                R.drawable.cocktailfour,
                R.drawable.cocktailfive,
                R.drawable.cocktailsix,
                R.drawable.cocktailseven,
                R.drawable.cocktaileight,
                R.drawable.cocktailnine,
                R.drawable.cocktailten
        };

        for (int image : imageList) {
            ImageView imageView = new ImageView(this.getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );

            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(image);
            viewFlipper.addView(imageView);
        }

        viewFlipper.startFlipping();
    }

    private void displayData() {
        Cursor cursor = adminDB.getAlcoholicCocktails();
        if (cursor.getCount() == 0) {
            Toast.makeText(requireActivity(), "No Entry Exists", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                productName.add(cursor.getString(0));
                productPrice.add(cursor.getString(1));
                productDescription.add(cursor.getString(2));
                productType.add(cursor.getString(3));
                Add_image_button.add(cursor.getBlob(4));
            }
        }
    }
}
