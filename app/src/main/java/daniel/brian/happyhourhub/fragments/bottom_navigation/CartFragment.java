package daniel.brian.happyhourhub.fragments.bottom_navigation;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import daniel.brian.happyhourhub.R;

public class CartFragment extends Fragment {

    private View view;
    private LinearLayout cartLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        cartLayout = view.findViewById(R.id.fragment_container);

        // Check if arguments contain image, name, and price
        if (getArguments() != null) {
            Bitmap imageBitmap = getArguments().getParcelable("image");
            String productName = getArguments().getString("productName");
            String price = getArguments().getString("price");

            // Add a new card to the cart with the received information
            addCardToCart(imageBitmap, productName, price);
        }

        return view;
    }

    // Helper method to add a new card to the cart
    private void addCardToCart(Bitmap imageBitmap, String productName, String price) {
        // Create a new card layout
        LinearLayout cardView = new LinearLayout(getContext());
        cardView.setOrientation(LinearLayout.HORIZONTAL);
        cardView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Add ImageView for the image
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(imageBitmap);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                200, // Adjust the width as needed
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Add TextView for the product name
        TextView nameTextView = new TextView(getContext());
        nameTextView.setText(productName);
        nameTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Add TextView for the price
        TextView priceTextView = new TextView(getContext());
        priceTextView.setText(price);
        priceTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Add the ImageView, product name TextView, and price TextView to the card layout
        cardView.addView(imageView);
        cardView.addView(nameTextView);
        cardView.addView(priceTextView);

        // Add the card layout to the main cart layout
        cartLayout.addView(cardView);
    }
}
