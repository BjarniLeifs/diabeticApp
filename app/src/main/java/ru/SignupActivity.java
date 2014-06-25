package ru;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jBerry.mysugar.R;


/*
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
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View menuView) {
                if (password.getText().toString().length() == 0 ||
                        passwordVerify.getText().toString().length() == 0 ||
                        email.getText().toString().length() == 0)
                    Toast.makeText(getBaseContext(), "Please fill everything out", Toast.LENGTH_SHORT).show();

                if (passwordVerify.getText().toString() != password.getText().toString()) {
                    Toast.makeText(getBaseContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}