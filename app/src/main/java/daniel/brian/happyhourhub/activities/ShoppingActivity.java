package daniel.brian.happyhourhub.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import daniel.brian.happyhourhub.R;


public class ShoppingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        BottomNavigationView navigationView = findViewById(R.id.bottomNavigation);
        NavController navController = Navigation.findNavController(this,R.id.shoppingHostFragment);
        NavigationUI.setupWithNavController(navigationView,navController);
    }
}