package daniel.brian.happyhourhub.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import daniel.brian.happyhourhub.repository.GetCocktailDetailsRepository;

public class CocktailDetailsViewModelFactory implements ViewModelProvider.Factory {
    public final GetCocktailDetailsRepository getCocktailDetailsRepository;

    public CocktailDetailsViewModelFactory(GetCocktailDetailsRepository getCocktailDetailsRepository) {
        this.getCocktailDetailsRepository = getCocktailDetailsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(CocktailDetailsViewModel.class)){
            return (T) new CocktailDetailsViewModel(getCocktailDetailsRepository);
        }
        return ViewModelProvider.Factory.super.create(modelClass);
    }
}
