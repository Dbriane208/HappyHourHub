
package daniel.brian.happyhourhub.dtos;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NonAlcoholic {

    @SerializedName("idDrink")
    private String mIdDrink;
    @SerializedName("strDrink")
    private String mStrDrink;
    @SerializedName("strDrinkThumb")
    private String mStrDrinkThumb;

    public String getIdDrink() {
        return mIdDrink;
    }

    public void setIdDrink(String idDrink) {
        mIdDrink = idDrink;
    }

    public String getStrDrink() {
        return mStrDrink;
    }

    public void setStrDrink(String strDrink) {
        mStrDrink = strDrink;
    }

    public String getStrDrinkThumb() {
        return mStrDrinkThumb;
    }

    public void setStrDrinkThumb(String strDrinkThumb) {
        mStrDrinkThumb = strDrinkThumb;
    }

}
