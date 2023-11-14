package daniel.brian.happyhourhub.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static  final DrinkApi drinkApiInstance = new Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DrinkApi.class);

    public DrinkApi getDrinkApi(){
        return drinkApiInstance;
    }
}
