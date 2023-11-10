package daniel.brian.happyhourhub.fragments.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import daniel.brian.happyhourhub.R;
import daniel.brian.happyhourhub.activities.ShoppingActivity;
import daniel.brian.happyhourhub.db.LoginDB;

@SuppressWarnings({"ConstantConditions", "resource"})
public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_register, container, false);

        Button register = myView.findViewById(R.id.btnRegister);
        EditText firstName = myView.findViewById(R.id.firstName);
        EditText lastName = myView.findViewById(R.id.lastName);
        EditText phoneNo = myView.findViewById(R.id.phoneNo);
        EditText email = myView.findViewById(R.id.emailTxt);
        EditText password = myView.findViewById(R.id.passwordTxt);
        TextView registerToLogin = myView.findViewById(R.id.registerToLogin);
        LoginDB loginDB = new LoginDB (this.getContext());

        // Navigating to Login Fragment incase you had Registered
        registerToLogin.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_registerFragment_to_loginFragment);
        });

        register.setOnClickListener(view -> {
            String firstname = firstName.getText().toString().trim();
            String lastname = lastName.getText().toString().trim();
            String userPhone = phoneNo.getText().toString();
            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString();

            if(firstname.isEmpty() || lastname.isEmpty() || userPhone.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()){
                Snackbar.make(getView(),"Please Enter all Fields",Snackbar.LENGTH_LONG).show();
            }else{
                if(userPhone.length() == 10){
                    boolean checkUser = loginDB.checkUser(userPhone,userEmail);
                    if(!checkUser){
                        boolean insert =   loginDB.insertUserData(firstname,lastname,userPhone,userEmail,userPassword);
                        if(insert){
                            Intent intent = new Intent(getContext(), ShoppingActivity.class);
                            startActivity(intent);
                            Snackbar.make(getView(),"Yaaay!! Registration Successful!",Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(getView(),"Oops! Registration Failed. Please Try again!",Snackbar.LENGTH_LONG).show();
                        }
                    }else {
                        Snackbar.make(getView(),"User already exists.",Snackbar.LENGTH_LONG).show();
                    }
                }else {
                    Snackbar.make(getView(),"Phone Number must be 10 digits.",Snackbar.LENGTH_LONG).show();
                }

            }
        });

        return myView;
    }

}
