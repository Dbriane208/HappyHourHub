package daniel.brian.happyhourhub.fragments.bottom_navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ViewFlipper;

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
//        viewFlipper.setOutAnimation(getContext(),R.anim.from_left);

        int[] imageList = {
                R.drawable.cocktail1,
                R.drawable.cocktail2,
                R.drawable.cocktail4,
                R.drawable.cocktail7
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