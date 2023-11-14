
package daniel.brian.happyhourhub.dtos;

import java.util.List;
import javax.annotation.processing.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NonAlcoholicList {

    @SerializedName("drinks")
    private List<NonAlcoholic> mDrinks;

    public List<NonAlcoholic> getDrinks() {
        return mDrinks;
    }

    public void setDrinks(List<NonAlcoholic> drinks) {
        mDrinks = drinks;
    }

}
