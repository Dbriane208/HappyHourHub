package daniel.brian.happyhourhub.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import java.util.List;

import daniel.brian.happyhourhub.databinding.ActivityRecipeDescriptionBinding;
import daniel.brian.happyhourhub.dtos.Cocktails;
import daniel.brian.happyhourhub.repository.GetCocktailDetailsRepository;
import daniel.brian.happyhourhub.util.Result;
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
        observeCocktailDetails();
        setInformationViews();
    }

    private void observeCocktailDetails() {
        cocktailDetailsViewModel.getCocktailsDetails(cocktailId).observe(this, cocktailDetails -> {
            if (cocktailDetails != null) {
                if(cocktailDetails instanceof Result.Loading<List<Cocktails>>){
                    recipeDescriptionBinding.progressBar.setVisibility(View.VISIBLE);
                }else if (cocktailDetails instanceof Result.Success<?>) {
                    recipeDescriptionBinding.progressBar.setVisibility(View.INVISIBLE);
                    List<Cocktails> cocktails = cocktailDetails.getData();
                    for (Cocktails cocktail : cocktails) {
                        if (cocktail.getIdDrink().equals(cocktailId)) {
                            // Updating instructions views
                            recipeDescriptionBinding.cocktailContent.setText(cocktail.getStrInstructions());

                            // Updating ingredients views
                            recipeDescriptionBinding.cocktailIngredient1.setText(cocktail.getStrIngredient1());
                            recipeDescriptionBinding.cocktailIngredient2.setText(cocktail.getStrIngredient2());
                            recipeDescriptionBinding.cocktailIngredient3.setText(cocktail.getStrIngredient3());
                            recipeDescriptionBinding.cocktailIngredient4.setText(cocktail.getStrIngredient4());

                            // Updating measurements views
                            recipeDescriptionBinding.cocktailMeasure1.setText(cocktail.getStrMeasure1());
                            recipeDescriptionBinding.cocktailMeasure2.setText(cocktail.getStrMeasure2());
                            recipeDescriptionBinding.cocktailMeasure3.setText(cocktail.getStrMeasure3());
                            break;
                        }
                    }
                } else {
                    Log.d("CocktailDetails", "Received data of type: " + cocktailDetails.getClass().getSimpleName());
                }
            } else {
                Log.d("CocktailDetails", "Received null data");
            }
        });
    }

    private void setInformationViews() {
        Glide.with(getApplicationContext())
                .load(cocktailImage)
                .into(recipeDescriptionBinding.cocktailImage);
        recipeDescriptionBinding.collapsingBar.setTitle(cocktailName);
    }

    private void getInformationViews() {
        Intent intent = getIntent();
         cocktailId = intent.getStringExtra("id");
         cocktailImage = intent.getStringExtra("image");
         cocktailName = intent.getStringExtra("name");
    }

}