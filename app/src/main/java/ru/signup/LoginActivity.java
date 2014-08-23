package ru.signup;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jberry.services.user.UserService;
import com.jberry.services.user.UserServiceFactory;

import jBerry.MySugar.R;
import ru.menu.MenuActivity;

/**
 * Created by Anna on 25.6.2014.
 */
public class LoginActivity extends ActionBarActivity {

    EditText name, password;
    Button logIn, goToSignUp;

    AsyncTask<String, Integer, Double> access;
    boolean accessSignup;

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_login);

        name = (EditText) findViewById(R.id.UserName);
        password = (EditText) findViewById(R.id.UserPassword);
        goToSignUp = (Button) findViewById(R.id.buttonGoToSignUp);
        logIn = (Button) findViewById(R.id.ButtonLogIn);

        //If the username is missing the log in button is not clickable
        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                //if the username or password is missing the log in button is not clickable
                logIn.setEnabled((name.getText().toString().length() != 0
                        && password.getText().toString().length() != 0));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        name.addTextChangedListener(tw);
        password.addTextChangedListener(tw);
        logIn.addTextChangedListener(tw);



        //If the signup button is clicked, they will go to the signup view
        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ru.signup.LoginActivity.this, SignupActivity.class);
                startActivityForResult(intent, 1);


            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String u = name.getText().toString();
                final String p = password.getText().toString();
                try {

                  new MyAsyncTask().execute(u, p);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                /*if (!access) {
                    Toast.makeText(getBaseContext(), "wrong username or password", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivityForResult(intent, 1);
                }
                */
            }
        });
    }



    private class MyAsyncTask extends AsyncTask<String, Boolean, Boolean>{

        protected Boolean doInBackground(String... params){

            try {
                return loginCaller(params[0], params[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Boolean result){
            if(result == true){
                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                startActivityForResult(intent, 1);
            }
            else if(result == false){
                Toast.makeText(getBaseContext(), "wrong username or password", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getBaseContext(), "to fast nigger", Toast.LENGTH_SHORT).show();
            }
        }
        public boolean loginCaller(String u, String p) throws Exception {
            UserService service = UserServiceFactory.getUserService();
            return service.login(u, p);
        }
    }
}

