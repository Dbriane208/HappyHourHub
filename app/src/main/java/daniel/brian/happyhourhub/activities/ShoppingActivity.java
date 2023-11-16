package daniel.brian.happyhourhub.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import daniel.brian.happyhourhub.R;
import daniel.brian.happyhourhub.databinding.ActivityShoppingBinding;

public class ShoppingActivity extends AppCompatActivity {
    ActivityShoppingBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShoppingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navigationView = findViewById(R.id.bottomNavigation);
        NavController navController = Navigation.findNavController(this,R.id.shoppingHostFragment);
        NavigationUI.setupWithNavController(navigationView,navController);
    }
}