package daniel.brian.happyhourhub.fragments.bottom_navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import daniel.brian.happyhourhub.R;
import daniel.brian.happyhourhub.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    FragmentHomeBinding fragmentHomeBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Implementing the viewFlipper
        fragmentHomeBinding = FragmentHomeBinding.inflate(getLayoutInflater());

        // setting up the view flipper
        setUpViewFlipper();

        return fragmentHomeBinding.getRoot();
    }

    private void setUpViewFlipper() {
        ViewFlipper viewFlipper = fragmentHomeBinding.famousCocktails;
        viewFlipper.setInAnimation(getContext(),R.anim.from_right);

        int[] imageList = {
                R.drawable.cocktailone,
                R.drawable.cocktailtwo,
                R.drawable.cocktailthree,
                R.drawable.cocktailfour,
                R.drawable.cocktailfive,
                R.drawable.cocktailsix,
                R.drawable.cocktailseven,
                R.drawable.cocktaileight,
                R.drawable.cocktailnine,
                R.drawable.cocktailten
        };

        for(int image : imageList){
            ImageView imageView = new ImageView(this.getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );

            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(image);
            viewFlipper.addView(imageView);
        }

        viewFlipper.startFlipping();
    }
}