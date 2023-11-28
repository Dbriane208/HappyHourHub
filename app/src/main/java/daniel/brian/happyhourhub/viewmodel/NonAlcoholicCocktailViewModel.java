package daniel.brian.happyhourhub.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import daniel.brian.happyhourhub.dtos.NonAlcoholic;
import daniel.brian.happyhourhub.repository.GetNonAlcoholicCocktailRepository;
import daniel.brian.happyhourhub.util.Result;

public class NonAlcoholicCocktailViewModel extends ViewModel {
    private final GetNonAlcoholicCocktailRepository getNonAlcoholicCocktailRepository;

    public NonAlcoholicCocktailViewModel(GetNonAlcoholicCocktailRepository getNonAlcoholicCocktailRepository) {
        this.getNonAlcoholicCocktailRepository = getNonAlcoholicCocktailRepository;
    }

    public LiveData<Result<List<NonAlcoholic>>> getNonAlcoholicCocktails(){
        return getNonAlcoholicCocktailRepository.getNonAlcoholicCocktails();
    }
}
