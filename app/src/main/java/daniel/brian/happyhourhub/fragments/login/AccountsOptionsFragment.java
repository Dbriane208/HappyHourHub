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

public class AccountsOptionsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accounts_options, container, false);

        Button buttonRegister = view.findViewById(R.id.btnRegister);
        Button buttonLogin = view.findViewById(R.id.btnLogin);

        buttonRegister.setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(view1);
            navController.navigate(R.id.action_accountsOptionsFragment_to_registerFragment);
        });

        buttonLogin.setOnClickListener(view12 -> {
            NavController navController = Navigation.findNavController(view12);
            navController.navigate(R.id.action_accountsOptionsFragment_to_loginFragment);
        });


        return view;
    }
}