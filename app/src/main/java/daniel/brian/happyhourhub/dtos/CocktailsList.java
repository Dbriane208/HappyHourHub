
package daniel.brian.happyhourhub.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.processing.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CocktailsList {

    @SerializedName("drinks")
    private List<Cocktails> mDrinks;

    public List<Cocktails> getDrinks() {
        return mDrinks;
    }

    public void setDrinks(List<Cocktails> drinks) {
        mDrinks = drinks;
    }

}
