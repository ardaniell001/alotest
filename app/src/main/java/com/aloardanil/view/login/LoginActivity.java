package com.aloardanil.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aloardanil.R;
import com.aloardanil.Session.Session;
import com.aloardanil.model.User;
import com.aloardanil.view.home.MainActivity;
import com.aloardanil.viewmodel.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private TextInputEditText username;
    private TextInputEditText password;
    private LinearLayout linearLayout;
    private Button btnsubmit;
    private Session mSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        mSession = new Session(this);

        username = findViewById(R.id.et_login_username);
        password = findViewById(R.id.et_login_password);
        btnsubmit = findViewById(R.id.btn_submit);
        linearLayout = findViewById(R.id.linearLayout);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInput()){
                    if (loginViewModel.validateUser(username.getText().toString(), password.getText().toString())){
                        User user = new User();
                        user.setUsername(username.getText().toString());
                        user.setPassword(password.getText().toString());
                        mSession.setUserLogin(user);

                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }else{
                        Snackbar.make(linearLayout, R.string.login_failed, Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public boolean validateInput() {
        boolean valid = true;
        if (username.getText().toString().length() <= 0) {
            username.clearFocus();
            username.setError(getString(R.string.required_field));
            username.requestFocus();
            valid = false;
        }

        if (password.getText().toString().length() <= 0) {
            password.clearFocus();
            password.setError(getString(R.string.required_field));
            password.requestFocus();
            valid = false;
        }
        return valid;
    }
}