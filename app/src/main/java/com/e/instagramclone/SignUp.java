package com.e.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail, edtUsername, edtPassword;
    private Button btnSignUp, btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtEmail = findViewById(R.id.edtSignUpEmail);
        edtUsername = findViewById(R.id.edtSignUpUsername);
        edtPassword = findViewById(R.id.edtSignUpPassword);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnSignUp);
                }
                return false;
            }
        });

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogInSignUpActivity);

        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
            //transitionToSocialMediaActivity();
        }
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {

            case R.id.btnSignUp:

                if(edtUsername.getText().toString().equals("") || edtEmail.getText().toString().equals("") || edtPassword.getText().toString().equals("")) {

                    FancyToast.makeText(SignUp.this, "Email, Username, Password is required!", Toast.LENGTH_SHORT, FancyToast.INFO, false).show();

                } else {

                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUsername.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up " + edtUsername.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e == null) {
                                transitionToSocialMediaActivity();
                                FancyToast.makeText(SignUp.this, appUser.getUsername() + " is signed up", Toast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                            } else {
                                FancyToast.makeText(SignUp.this, "There was an error: " + e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, false).show();
                            }

                            progressDialog.dismiss();
                        }
                    });
                }

                break;

            case R.id.btnLogInSignUpActivity:
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);

                break;
        }

    }

    public void rootLayoutTapped(View view) {

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void transitionToSocialMediaActivity() {
        Intent intent = new Intent(SignUp.this, SocialMediaActivity.class);
        startActivity(intent);
    }

}
