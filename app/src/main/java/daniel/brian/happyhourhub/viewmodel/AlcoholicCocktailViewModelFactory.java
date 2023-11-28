package daniel.brian.happyhourhub.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import daniel.brian.happyhourhub.repository.GetAlcoholicCocktailRepository;

public class AlcoholicCocktailViewModelFactory implements ViewModelProvider.Factory {
    public  final GetAlcoholicCocktailRepository getAlcoholicCocktailRepository;

    public AlcoholicCocktailViewModelFactory(GetAlcoholicCocktailRepository getAlcoholicCocktailRepository) {
        this.getAlcoholicCocktailRepository = getAlcoholicCocktailRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(AlcoholicCocktailViewModel.class)){
            return (T) new AlcoholicCocktailViewModel(getAlcoholicCocktailRepository);
        }
       throw new IllegalArgumentException("Unknown viewModel");
    }
}
