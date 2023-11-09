package daniel.brian.happyhourhub.fragments.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import daniel.brian.happyhourhub.R;

public class IntroductionFragment extends Fragment {

      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_introudction,container,false);

        Button buttonStart = view.findViewById(R.id.start);
        buttonStart.setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(view1);
            navController.navigate(R.id.action_introductionFragment_to_accountsOptionsFragment);
        });

        return view;
    }
}