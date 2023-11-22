package daniel.brian.happyhourhub.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import daniel.brian.happyhourhub.dtos.Alcoholic;
import daniel.brian.happyhourhub.repository.GetAlcoholicCocktailRepository;
import daniel.brian.happyhourhub.util.Result;

public class CocktailViewModel extends ViewModel {
     private final GetAlcoholicCocktailRepository getAlcoholicCocktailRepository;

    public CocktailViewModel(GetAlcoholicCocktailRepository getAlcoholicCocktailRepository) {
        this.getAlcoholicCocktailRepository = getAlcoholicCocktailRepository;
    }

    public LiveData<Result<List<Alcoholic>>> getAlcoholicCocktails (){
        return getAlcoholicCocktailRepository.getAlcoholicCocktails();
    }
}
