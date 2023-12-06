package daniel.brian.happyhourhub.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import daniel.brian.happyhourhub.dtos.Cocktails;
import daniel.brian.happyhourhub.dtos.CocktailsList;
import daniel.brian.happyhourhub.remote.RetrofitInstance;
import daniel.brian.happyhourhub.util.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCocktailDetailsRepository {
    public LiveData<Result<List<Cocktails>>> getCocktailDetails(){
        MutableLiveData<Result<List<Cocktails>>> cocktailsDetailsLiveData = new MutableLiveData<>();
        RetrofitInstance.drinkApiInstance().getCocktails("details").enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<CocktailsList> call, Response<CocktailsList> response) {
                List<Cocktails> cocktails = response.body() != null ? response.body().getDrinks() : null;
                if(cocktails != null){
                    cocktailsDetailsLiveData.postValue(new Result.Success<>(cocktails));
                }
            }
            @Override
            public void onFailure(Call<CocktailsList> call, Throwable t) {
                Log.d("CocktailDetailsRepository",t.getMessage());
            }
        });
        return cocktailsDetailsLiveData;
    }
}
