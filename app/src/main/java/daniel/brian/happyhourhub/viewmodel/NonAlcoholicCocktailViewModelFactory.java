package daniel.brian.happyhourhub.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import daniel.brian.happyhourhub.repository.GetNonAlcoholicCocktailRepository;

public class NonAlcoholicCocktailViewModelFactory implements ViewModelProvider.Factory {
    private final GetNonAlcoholicCocktailRepository getNonAlcoholicCocktailRepository;

    public NonAlcoholicCocktailViewModelFactory(GetNonAlcoholicCocktailRepository getNonAlcoholicCocktailRepository) {
        this.getNonAlcoholicCocktailRepository = getNonAlcoholicCocktailRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(NonAlcoholicCocktailViewModel.class)){
            return (T)  new NonAlcoholicCocktailViewModel(getNonAlcoholicCocktailRepository);
        }
        throw new IllegalArgumentException("Unknown viewModel");
    }
}
