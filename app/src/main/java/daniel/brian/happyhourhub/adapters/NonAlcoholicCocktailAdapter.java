package daniel.brian.happyhourhub.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import daniel.brian.happyhourhub.databinding.CocktailLayoutBinding;
import daniel.brian.happyhourhub.dtos.NonAlcoholic;
import daniel.brian.happyhourhub.util.OnAlcoholicItemClickListener;
import daniel.brian.happyhourhub.util.OnNonAlcoholicItemClickListener;

public class NonAlcoholicCocktailAdapter extends RecyclerView.Adapter<NonAlcoholicCocktailAdapter.NonAlcoholicViewHolder> {
    private ArrayList<NonAlcoholic> nonAlcoholicList = new ArrayList<>();
    private OnNonAlcoholicItemClickListener Click;

    public  void setNonAlcoholicClick(OnNonAlcoholicItemClickListener listener){this.Click = listener;}
    @SuppressLint("NotifyDataSetChanged")
    public void setNonAlcoholicCocktail(ArrayList<NonAlcoholic> nonAlcoholicList){
        this.nonAlcoholicList = nonAlcoholicList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NonAlcoholicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NonAlcoholicViewHolder(
                CocktailLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NonAlcoholicViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(nonAlcoholicList.get(position).getStrDrinkThumb())
                .into(holder.cocktailLayoutBinding.cocktailImage);
        NonAlcoholic drink = nonAlcoholicList.get(position);
        holder.cocktailLayoutBinding.cocktailName.setText(drink.getStrDrink());

        holder.itemView.setOnClickListener(view -> {
            if(Click != null){
                Click.onClick(nonAlcoholicList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return nonAlcoholicList.size();
    }

    public static class NonAlcoholicViewHolder extends RecyclerView.ViewHolder {
        private final CocktailLayoutBinding cocktailLayoutBinding;
        public NonAlcoholicViewHolder(@NonNull CocktailLayoutBinding cocktailLayoutBinding) {
            super(cocktailLayoutBinding.getRoot());
            this.cocktailLayoutBinding = cocktailLayoutBinding;
        }
    }
}
