package daniel.brian.happyhourhub.fragments.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import daniel.brian.happyhourhub.R;
import daniel.brian.happyhourhub.activities.AdminActivity;
import daniel.brian.happyhourhub.activities.ShoppingActivity;
import daniel.brian.happyhourhub.db.LoginDB;

@SuppressWarnings({"ConstantConditions", "resource", "PointlessBooleanExpression"})
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);


        // Accessing the IDs of components
        TextView loginToRegister = view.findViewById(R.id.loginToRegister);
        TextView forgotPassword = view.findViewById(R.id.forgotPassword);
        EditText userEmail = view.findViewById(R.id.textEmail);
        EditText userPassword = view.findViewById(R.id.textPassword);
        Button buttonLogin = view.findViewById(R.id.btnLogin);
        LoginDB loginDB = new LoginDB(this.getContext());

        // Navigating to the Forgot password fragment
        forgotPassword.setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_loginFragment_to_forgotPasswordFragment);
        });

        // Navigating to Register Fragment if not Registered
        loginToRegister.setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_loginFragment_to_registerFragment);
        });

        // Implementing the button Login functionality
        buttonLogin.setOnClickListener(view12 -> {
            String email = userEmail.getText().toString();
            String password = userPassword.getText().toString();

            if(email.isEmpty() || password.isEmpty()){
                Snackbar.make(getView(),"Please Enter all Fields!",Snackbar.LENGTH_LONG).show();
            }else{
                if(email.matches("admin@happyhourhub.com") && password.matches("admin@hhb")){
                   Snackbar.make(getView(),"Logging as Admin successful!",Snackbar.LENGTH_LONG).show();
                   Intent intent = new Intent(getContext(), AdminActivity.class);
                   startActivity(intent);
                }else{
                    boolean checkUser = loginDB.checkUserEmailAndPassword(email,password);
                    if(checkUser == true){
                        Snackbar.make(getView(),"Login Successful!",Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent(getContext(), ShoppingActivity.class);
                        startActivity(intent);
                    }else{
                        Snackbar.make(getView(),"Invalid Credentials",Snackbar.LENGTH_LONG).show();
                    }
                }

            }
        });
        return view;
    }
}