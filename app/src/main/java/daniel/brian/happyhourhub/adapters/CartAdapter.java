package daniel.brian.happyhourhub.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.happyhourhub.R;
import daniel.brian.happyhourhub.databinding.CartLayoutBinding;

/** @noinspection ALL*/
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private ArrayList productName;
    private ArrayList productPrice;
    private ArrayList productImage;
    private Context context;
    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(
              CartLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false)
        );
    }

    public CartAdapter(Context context,ArrayList productName,ArrayList productPrice,ArrayList productImage){
        this.context = context;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
         byte [] image = (byte[]) productImage.get(position);
         if(image != null){
             Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
             holder.cartLayoutBinding.imageCart.setImageBitmap(bitmap);
         }else{
             holder.cartLayoutBinding.imageCart.setImageResource(R.drawable.cocktailone);
         }

        holder.cartLayoutBinding.cocktail.setText(String.valueOf(productName.get(position)));
        holder.cartLayoutBinding.cocktailPrice.setText(String.valueOf(productPrice.get(position)));
    }

    @Override
    public int getItemCount() {
        return productName.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder{
        private final CartLayoutBinding cartLayoutBinding;
        public CartViewHolder(CartLayoutBinding cartLayoutBinding) {
            super(cartLayoutBinding.getRoot());
            this.cartLayoutBinding = cartLayoutBinding;
        }
    }
}
