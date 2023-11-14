
package daniel.brian.happyhourhub.dtos;

import java.util.List;
import javax.annotation.processing.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class AlcoholicList {

    @SerializedName("drinks")
    private List<Alcoholic> mDrinks;

    public List<Alcoholic> getDrinks() {
        return mDrinks;
    }

    public void setDrinks(List<Alcoholic> drinks) {
        mDrinks = drinks;
    }

}
