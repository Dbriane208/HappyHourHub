package daniel.brian.happyhourhub.adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URL;
import java.util.ArrayList;

import daniel.brian.happyhourhub.R;
import daniel.brian.happyhourhub.activities.PaymentActivity;
import daniel.brian.happyhourhub.databinding.CocktailHomeBinding;
import daniel.brian.happyhourhub.fragments.main.DescriptionFragment;

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.HomeScreenViewHolder> {
    private ArrayList productName,productPrice,productDescription,productType,add_image_button;
    private Context context;



    @NonNull
    @Override
    public HomeScreenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeScreenViewHolder(
                CocktailHomeBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false)
        );
    }

    public HomeScreenAdapter(Context context,ArrayList productName, ArrayList productPrice, ArrayList productDescription, ArrayList productType, ArrayList add_image_button) {
        this.context = context;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productType = productType;
        this.add_image_button = add_image_button;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeScreenViewHolder holder, int position) {
        byte[] imageData = (byte[]) add_image_button.get(position);

        if (imageData != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            holder.cocktailHomeBinding.cocktailImage.setImageBitmap(bitmap);
        } else {
            holder.cocktailHomeBinding.cocktailImage.setImageResource(R.drawable.cocktailone);
        }

        holder.cocktailHomeBinding.cocktailName.setText(String.valueOf(productName.get(position)));
        holder.cocktailHomeBinding.cocktailPrice.setText(String.valueOf(productPrice.get(position)));
        holder.cocktailHomeBinding.cocktailType.setText(String.valueOf(productType.get(position)));
        holder.cocktailHomeBinding.cocktailDescription.setText(String.valueOf(productDescription.get(position)));


        // Set click listener to open DescriptionFragment
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PaymentActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

        @Override
    public int getItemCount() {
        return productName.size();
    }

    public class HomeScreenViewHolder extends RecyclerView.ViewHolder {
        private final CocktailHomeBinding cocktailHomeBinding;
        public HomeScreenViewHolder(CocktailHomeBinding cocktailHomeBinding) {
            super(cocktailHomeBinding.getRoot());
            this.cocktailHomeBinding = cocktailHomeBinding;
        }
    }
}
