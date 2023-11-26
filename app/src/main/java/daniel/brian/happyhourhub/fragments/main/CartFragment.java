package daniel.brian.happyhourhub.fragments.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import daniel.brian.happyhourhub.databinding.FragmentCartBinding;

public class CartFragment extends Fragment {
    FragmentCartBinding fragmentCartBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentCartBinding = FragmentCartBinding.inflate(getLayoutInflater());
        return fragmentCartBinding.getRoot();


    }
}
