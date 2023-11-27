package daniel.brian.happyhourhub.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import daniel.brian.happyhourhub.dtos.Alcoholic;
import daniel.brian.happyhourhub.dtos.AlcoholicList;
import daniel.brian.happyhourhub.remote.RetrofitInstance;
import daniel.brian.happyhourhub.util.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAlcoholicCocktailRepository {
    public LiveData<Result<List<Alcoholic>>> getAlcoholicCocktails(){
        MutableLiveData<Result<List<Alcoholic>>> alcoholicCocktailLiveData = new MutableLiveData<>();
        RetrofitInstance.drinkApiInstance().getAlcoholicDrink("alcoholic").enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<AlcoholicList> call, @NonNull Response<AlcoholicList> response) {
                List<Alcoholic> alcoholics = response.body() != null ? response.body().getDrinks() : null;
                if (alcoholics != null) {
                    alcoholicCocktailLiveData.postValue(new Result.Success<>(alcoholics));
                }
            }

            @Override
            public void onFailure(@NonNull Call<AlcoholicList> call, @NonNull Throwable t) {
                Log.d("GetAlcoholicRepository", t.getMessage());
            }
        });

        return alcoholicCocktailLiveData;
    }
}
