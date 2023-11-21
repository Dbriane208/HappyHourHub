package daniel.brian.happyhourhub.fragments.bottom_navigation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import daniel.brian.happyhourhub.R;

public class DescriptionFragment extends Fragment {

    private View view;
    private ImageView imageView3;
    private TextView textViewProductName;
    private TextView textViewPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_description, container, false);

        imageView3 = view.findViewById(R.id.imageView3);
        textViewProductName = view.findViewById(R.id.textView2);
        textViewPrice = view.findViewById(R.id.textViewPrice);

        Button addToCartButton = view.findViewById(R.id.addToCartButton);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the image, product name, and price from the DescriptionFragment
                Drawable imageDrawable = imageView3.getDrawable();
                String productName = textViewProductName.getText().toString();
                String price = textViewPrice.getText().toString();

                // Pass the information to the CartFragment
                CartFragment cartFragment = new CartFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("image", drawableToBitmap(imageDrawable));
                bundle.putString("productName", productName);
                bundle.putString("price", price);
                cartFragment.setArguments(bundle);

                // Navigate to the CartFragment
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, cartFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    // Helper method to convert Drawable to Bitmap
    private Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
