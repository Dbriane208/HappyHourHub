package daniel.brian.happyhourhub.fragments.login;

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
import daniel.brian.happyhourhub.db.LoginDB;

@SuppressWarnings({"ConstantConditions", "resource"})
public class ForgotPasswordFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        EditText phone = view.findViewById(R.id.userPhone);
        TextView password = view.findViewById(R.id.userPassword);
        Button send = view.findViewById(R.id.btnSend);
        LoginDB loginDB = new LoginDB(this.getContext());

        send.setOnClickListener(view1 -> {
            String userPhone = phone.getText().toString();
            String userPassword = password.getText().toString();
            if(!userPhone.isEmpty() & !userPassword.isEmpty()) {
                if(userPhone.length() == 10){
                    boolean user =  loginDB.CheckPhoneAndPassword(userPhone,userPassword);
                    if(user){
                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.action_forgotPasswordFragment_to_loginFragment);
                        Snackbar.make(getView(),"Password Successfully Updated!.",Snackbar.LENGTH_LONG).show();
                    }else{
                        Snackbar.make(getView(),"User Phone Number Not Found!.",Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(getView(),"User Phone Number Must be 10 digits!.",Snackbar.LENGTH_LONG).show();
                }
            }else{
                Snackbar.make(getView(),"Please Enter all Fields.",Snackbar.LENGTH_LONG).show();
            }
        });

        return view;
    }
}