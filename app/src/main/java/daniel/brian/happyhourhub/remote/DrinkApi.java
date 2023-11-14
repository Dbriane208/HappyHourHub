package daniel.brian.happyhourhub.remote;

import daniel.brian.happyhourhub.dtos.AlcoholicList;
import daniel.brian.happyhourhub.dtos.CocktailsList;
import daniel.brian.happyhourhub.dtos.NonAlcoholicList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DrinkApi {
    @GET("lookup.php")
    Call<CocktailsList> getCocktails(@Query("i") String id);

    @GET("filter.php")
    Call<AlcoholicList> getAlcoholicDrink(@Query("a")  String alcoholic);

    @GET("filter.php")
    Call<NonAlcoholicList> getNonAlcoholic(@Query("a") String nonAlcoholic);
}
