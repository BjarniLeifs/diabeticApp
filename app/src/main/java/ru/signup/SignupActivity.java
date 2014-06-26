package ru.signup;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jBerry.MySugar.R;

/**
 * Created by Anna on 19.6.2014.
 */
public class SignupActivity extends ActionBarActivity {


    EditText name, password, passwordVerify, email;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText) findViewById(R.id.nameText);
        password = (EditText) findViewById(R.id.passwordText);
        passwordVerify = (EditText) findViewById(R.id.passwordAgainText);
        email = (EditText) findViewById(R.id.emailText);
        signUp = (Button) findViewById(R.id.signUpButton);

            //Button is enabled when name is inserted, otherwise it is not clickable
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                signUp.setEnabled(name.getText().toString().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        //If user hasn't inserted all information a toast error message will appear.
        //If the passwords don't match, an error message will appear.
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View menuView) {
                if (password.getText().toString().length() == 0 ||
                        passwordVerify.getText().toString().length() == 0 ||
                        email.getText().toString().length() == 0)
                    Toast.makeText(getBaseContext(), "Please fill everything out", Toast.LENGTH_SHORT).show();

                if (!passwordVerify.getText().toString().equals(password.getText().toString())) {
                    Toast.makeText(getBaseContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //If user clicks the signup button in the log in activity they will go to the sign up activity
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_signup,
                    container, false);
            return rootView;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}