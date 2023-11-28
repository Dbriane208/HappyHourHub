package daniel.brian.happyhourhub.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import daniel.brian.happyhourhub.databinding.CocktailLayoutBinding;
import daniel.brian.happyhourhub.dtos.Alcoholic;

public class AlcoholicCocktailAdapter extends RecyclerView.Adapter<AlcoholicCocktailAdapter.AlcoholicCocktailViewHolder> {
    private ArrayList<Alcoholic> alcoholicList = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void setAlcoholicCocktail(ArrayList<Alcoholic> alcoholicList){
        this.alcoholicList = alcoholicList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlcoholicCocktailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlcoholicCocktailViewHolder(
                CocktailLayoutBinding.inflate(
                        LayoutInflater.from(parent.getContext()),parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AlcoholicCocktailViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(alcoholicList.get(position).getStrDrinkThumb())
                .into(holder.cocktailLayoutBinding.cocktailImage);
        Alcoholic drink = alcoholicList.get(position);
        holder.cocktailLayoutBinding.cocktailName.setText(drink.getStrDrink());
    }

    @Override
    public int getItemCount() {
        return alcoholicList.size();
    }

    public static class AlcoholicCocktailViewHolder extends RecyclerView.ViewHolder {
        public final CocktailLayoutBinding cocktailLayoutBinding;
        public AlcoholicCocktailViewHolder(CocktailLayoutBinding cocktailLayoutBinding) {
            super(cocktailLayoutBinding.getRoot());
            this.cocktailLayoutBinding = cocktailLayoutBinding;
        }
    };
}
