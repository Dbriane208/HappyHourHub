package daniel.brian.happyhourhub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import daniel.brian.happyhourhub.adapters.AlcoholicCocktailAdapter;
import daniel.brian.happyhourhub.databinding.ActivityRecipeDescriptionBinding;
import daniel.brian.happyhourhub.fragments.main.RecipeFragment;
import daniel.brian.happyhourhub.repository.GetCocktailDetailsRepository;
import daniel.brian.happyhourhub.viewmodel.CocktailDetailsViewModel;
import daniel.brian.happyhourhub.viewmodel.CocktailDetailsViewModelFactory;

public class RecipeDescriptionActivity extends AppCompatActivity {
    ActivityRecipeDescriptionBinding recipeDescriptionBinding;
    GetCocktailDetailsRepository getCocktailDetailsRepository;
    CocktailDetailsViewModel cocktailDetailsViewModel;
    CocktailDetailsViewModelFactory cocktailDetailsViewModelFactory;

    String cocktailId,cocktailImage,cocktailName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeDescriptionBinding = ActivityRecipeDescriptionBinding.inflate(getLayoutInflater());
        setContentView(recipeDescriptionBinding.getRoot());

        getCocktailDetailsRepository = new GetCocktailDetailsRepository();
        cocktailDetailsViewModelFactory = new CocktailDetailsViewModelFactory(getCocktailDetailsRepository);
        cocktailDetailsViewModel = new ViewModelProvider(this,cocktailDetailsViewModelFactory).get(CocktailDetailsViewModel.class);

       //getting the cocktail information
        getInformationViews();
        setInformationViews();
    }

    private void setInformationViews() {
        Glide.with(getApplicationContext())
                .load(cocktailImage)
                .into(recipeDescriptionBinding.cocktailImage);
        recipeDescriptionBinding.cocktailInstructions.setText(cocktailName);
    }

    private void getInformationViews() {
        Intent intent = getIntent();
         cocktailId = intent.getStringExtra("id");
         cocktailImage = intent.getStringExtra("image");
         cocktailName = intent.getStringExtra("name");
    }

}