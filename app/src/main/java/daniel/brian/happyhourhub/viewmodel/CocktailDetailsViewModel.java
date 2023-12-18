package daniel.brian.happyhourhub.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import daniel.brian.happyhourhub.dtos.Cocktails;
import daniel.brian.happyhourhub.repository.GetCocktailDetailsRepository;
import daniel.brian.happyhourhub.util.Result;

public class CocktailDetailsViewModel extends ViewModel {
    public final GetCocktailDetailsRepository getCocktailDetailsRepository;

    public CocktailDetailsViewModel(GetCocktailDetailsRepository getCocktailDetailsRepository) {
        this.getCocktailDetailsRepository = getCocktailDetailsRepository;
    }

    public LiveData<Result<List<Cocktails>>> getCocktailsDetails(String id){
        return getCocktailDetailsRepository.getCocktailDetails(id);
    }
}
