package ru.signup;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
public class SignupActivity extends LoginActivity {


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

                //User doesn't fill everything out
                if (password.getText().toString().length() == 0 ||
                        passwordVerify.getText().toString().length() == 0 ||
                        email.getText().toString().length() == 0)
                    Toast.makeText(getBaseContext(), "Please fill everything out", Toast.LENGTH_SHORT).show();

                //passwords don't match
                else if (!passwordVerify.getText().toString().equals(password.getText().toString())) {
                    Toast.makeText(getBaseContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();

                }
                //if everything is fine, the data gets returned to the login activity.
                else {
                    if (name.getText().toString().length() != 0 &&
                            password.getText().toString().length() != 0 &&
                            passwordVerify.getText().toString().length() != 0 &&
                            email.getText().toString().length() != 0) {

                        Intent intent = new Intent();
                        intent.putExtra("loginName", name.getText().toString());
                        intent.putExtra("loginPassword", password.getText().toString());
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }

            }

        });
        //If user clicks the signup button in the log in activity they will go to the sign up activity
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.signupcontainer, new PlaceholderFragment())
                    .commit();
        }


    }
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.activity_signup,
                    container, false);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }


}