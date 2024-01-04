package daniel.brian.happyhourhub.fragments.main;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import daniel.brian.happyhourhub.adapters.CartAdapter;
import daniel.brian.happyhourhub.databinding.FragmentCartBinding;
import daniel.brian.happyhourhub.db.CartDB;

public class CartFragment extends Fragment {
    FragmentCartBinding fragmentCartBinding;
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    ArrayList<String> productName, productPrice;
    ArrayList<byte[]> productImage;
    CartDB cartDB;
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentCartBinding = FragmentCartBinding.inflate(getLayoutInflater());

        cartDB = new CartDB(this.getContext());
        productName = new ArrayList<>();
        productPrice = new ArrayList<>();
        productImage = new ArrayList<>();

        recyclerView = fragmentCartBinding.recyclerCart;
        cartAdapter = new CartAdapter(this.getContext(),productName,productPrice,productImage);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        displayCartItems();

        //updating the no of items in the cart
        int items = cartDB.getItemsInCart();
        fragmentCartBinding.items.setText(String.valueOf(items));

        // updating the cost of items int the cart
        int cost = cartDB.getTotalCost();
        fragmentCartBinding.amount.setText("Ksh " + cost);

        return fragmentCartBinding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void displayCartItems() {
        Cursor cursor = cartDB.getAllCartCocktails();
      if(cursor.getCount() == 0){
          Toast.makeText(requireActivity(),"Cart is Empty",Toast.LENGTH_LONG).show();
      }else{
          productName.clear();
          productPrice.clear();
          productImage.clear();

          while(cursor.moveToNext()){
              productName.add(cursor.getString(0));
              productPrice.add(cursor.getString(1));
              productImage.add(cursor.getBlob(2));
          }

          cartAdapter.notifyDataSetChanged();
      }
    }
}
