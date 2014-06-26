package ru.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import jBerry.MySugar.R;

/**
 * Created by Anna on 25.6.2014.
 */
public class LoginActivity extends ActionBarActivity {

    EditText name, password;
    Button logIn, goToSignUp;

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_login);

        name = (EditText) findViewById(R.id.UserName);
        password = (EditText) findViewById(R.id.UserPassword);
        goToSignUp = (Button) findViewById(R.id.buttonGoToSignUp);
        logIn = (Button) findViewById(R.id.ButtonLogIn);

        //If the username is missing the log in button is not clickable
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                logIn.setEnabled(!(name.getText().toString().length() == 0));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        //if the password is missing the long in button is not clickable
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                logIn.setEnabled(!(password.getText().toString().length() == 0));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
    //If the signup button is clicked, they will go to the signup view
    public void goOnClick(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
}

