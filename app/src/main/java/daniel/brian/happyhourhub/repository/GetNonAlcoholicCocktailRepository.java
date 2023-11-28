package daniel.brian.happyhourhub.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import daniel.brian.happyhourhub.dtos.NonAlcoholic;
import daniel.brian.happyhourhub.dtos.NonAlcoholicList;
import daniel.brian.happyhourhub.remote.RetrofitInstance;
import daniel.brian.happyhourhub.util.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNonAlcoholicCocktailRepository {
  public LiveData<Result<List<NonAlcoholic>>> getNonAlcoholicCocktails(){

      MutableLiveData<Result<List<NonAlcoholic>>> nonAlcoholicCocktailsLiveData = new MutableLiveData<>();

      RetrofitInstance.drinkApiInstance().getNonAlcoholic("non alcoholic").enqueue(new Callback<>() {
          @Override
          public void onResponse(@NonNull Call<NonAlcoholicList> call, @NonNull Response<NonAlcoholicList> response) {
              List<NonAlcoholic> nonAlcoholics = response.body() != null ? response.body().getDrinks() : null;
              if (nonAlcoholics != null) {
                  nonAlcoholicCocktailsLiveData.postValue(new Result.Success<>(nonAlcoholics));
              }
          }

          @Override
          public void onFailure(@NonNull Call<NonAlcoholicList> call, @NonNull Throwable t) {
              Log.d("Empty non Alcoholic drinks", t.getMessage());
          }
      });

      return  nonAlcoholicCocktailsLiveData;
  }
}
