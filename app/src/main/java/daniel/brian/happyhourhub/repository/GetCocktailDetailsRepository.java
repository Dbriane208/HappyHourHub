package daniel.brian.happyhourhub.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Objects;

import daniel.brian.happyhourhub.dtos.Cocktails;
import daniel.brian.happyhourhub.dtos.CocktailsList;
import daniel.brian.happyhourhub.remote.RetrofitInstance;
import daniel.brian.happyhourhub.util.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCocktailDetailsRepository {
    public LiveData<Result<List<Cocktails>>> getCocktailDetails(String id){
        MutableLiveData<Result<List<Cocktails>>> cocktailsDetailsLiveData = new MutableLiveData<>();
        RetrofitInstance.drinkApiInstance().getCocktails(id).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<CocktailsList> call, @NonNull Response<CocktailsList> response) {
                List<Cocktails> cocktails = response.body() != null ? response.body().getDrinks() : null;
                if(cocktails != null){
                    cocktailsDetailsLiveData.postValue(new Result.Success<>(cocktails));
                }
            }
            @Override
            public void onFailure(@NonNull Call<CocktailsList> call, @NonNull Throwable t) {
                Log.d("CocktailDetailsRepository", Objects.requireNonNull(t.getMessage()));
            }
        });
        return cocktailsDetailsLiveData;
    }
}
