package daniel.brian.happyhourhub.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import daniel.brian.happyhourhub.R;
import daniel.brian.happyhourhub.databinding.ActivityShoppingBinding;
import daniel.brian.happyhourhub.fragments.bottom_navigation.HomeFragment;
import daniel.brian.happyhourhub.fragments.bottom_navigation.RecipeFragment;
import daniel.brian.happyhourhub.fragments.bottom_navigation.cartFragment;

public class ShoppingActivity extends AppCompatActivity {
    ActivityShoppingBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShoppingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       binding.bottomNavigation.setOnItemSelectedListener(item -> {
          if(item.getItemId() == R.id.homeFragment){
              replaceFragment(new HomeFragment());
          } else if (item.getItemId() == R.id.recipeFragment) {
              replaceFragment(new RecipeFragment());
          } else if (item.getItemId() == R.id.cartFragment) {
              replaceFragment(new cartFragment());
          }
          return true;
       });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.shoppingHostFragment,fragment);
        fragmentTransaction.commit();
    }
}