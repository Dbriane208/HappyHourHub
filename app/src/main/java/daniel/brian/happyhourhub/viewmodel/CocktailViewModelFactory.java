package daniel.brian.happyhourhub.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import daniel.brian.happyhourhub.repository.GetAlcoholicCocktailRepository;

public class CocktailViewModelFactory implements ViewModelProvider.Factory {
    public  final GetAlcoholicCocktailRepository getAlcoholicCocktailRepository;

    public CocktailViewModelFactory(GetAlcoholicCocktailRepository getAlcoholicCocktailRepository) {
        this.getAlcoholicCocktailRepository = getAlcoholicCocktailRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(CocktailViewModel.class)){
            return (T) new CocktailViewModel(getAlcoholicCocktailRepository);
        }
       throw new IllegalArgumentException("Unknown viewModel");
    }
}
