package daniel.brian.happyhourhub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import daniel.brian.happyhourhub.R;
import daniel.brian.happyhourhub.databinding.ActivityRecipeDescriptionBinding;
import daniel.brian.happyhourhub.repository.GetCocktailDetailsRepository;
import daniel.brian.happyhourhub.viewmodel.CocktailDetailsViewModel;
import daniel.brian.happyhourhub.viewmodel.CocktailDetailsViewModelFactory;

public class RecipeDescriptionActivity extends AppCompatActivity {
ActivityRecipeDescriptionBinding recipeDescriptionBinding;
GetCocktailDetailsRepository getCocktailDetailsRepository;
CocktailDetailsViewModel cocktailDetailsViewModel;
CocktailDetailsViewModelFactory cocktailDetailsViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeDescriptionBinding = ActivityRecipeDescriptionBinding.inflate(getLayoutInflater());
        setContentView(recipeDescriptionBinding.getRoot());

        getCocktailDetailsRepository = new GetCocktailDetailsRepository();
        cocktailDetailsViewModelFactory = new CocktailDetailsViewModelFactory(getCocktailDetailsRepository);
        cocktailDetailsViewModel = new ViewModelProvider(this,cocktailDetailsViewModelFactory).get(CocktailDetailsViewModel.class);

    }
}