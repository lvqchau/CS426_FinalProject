package com.example.planter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.List;

public class LogInActivity extends AppCompatActivity {

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputUsername;
    private TextInputLayout textInputFullname;
    private TextInputLayout textInputPass;

    private TextInputLayout textInputUsernameLI;
    private TextInputLayout textInputPassLI;
    DatabaseHelper db;
    Button btnCheck, btnInsert, btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);


        textInputEmail = findViewById(R.id.SU_email);
        textInputFullname = findViewById(R.id.SU_name);
        textInputUsername = findViewById(R.id.SU_username);

        textInputPassLI = findViewById(R.id.LI_pass);
        textInputUsernameLI = findViewById(R.id.LI_username);


        btnCheck= findViewById(R.id.btnLogIn);
        btnInsert = findViewById(R.id.btnSignUp);
        btnGo = findViewById(R.id.btnApp);
        btnOnClick();

    }

    public void btnOnClick() {
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LogInActivity.this, MainActivity.class);
                LogInActivity.this.startActivity(myIntent);
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateSignUp(view);
            }
        });
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = textInputUsernameLI.getEditText().getText().toString().trim();
                String password = textInputPassLI.getEditText().getText().toString().trim();
                Boolean res = db.checkUser(username, password);
                if (res) {
                    Toast.makeText(LogInActivity.this, "successful log in", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LogInActivity.this, "log in fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputEmail.setError("(*) Field can not be empty!");
        } else {
            textInputEmail.setError(null);
            return true;
        }
        return false;
    }
    private boolean validateUsername() {
        String usernameInput = textInputUsername.getEditText().getText().toString().trim();
        if (usernameInput.isEmpty()) {
            textInputUsername.setError("(*) Field can not be empty!");
        } else if (usernameInput.length() > 15) {
            textInputUsername.setError("(*) Username must be less than 16 characters");
        } else {
            textInputUsername.setError(null);
            return true;
        }
        return false;
    }
    private boolean validateName() {
        String firstInput = textInputFullname.getEditText().getText().toString().trim();
        if (firstInput.isEmpty()) {
            textInputFullname.setError("(*) Field can not be empty!");
        } else {
            textInputFullname.setError(null);
            return true;
        }
        return false;
    }
    private boolean validatePassword() {
        String passInput = textInputPass.getEditText().getText().toString().trim();
        if (passInput.isEmpty()) {
            textInputPass.setError("(*) Field can not be empty!");
        } else if (passInput.length() > 12) {
            textInputPass.setError("(*) Password must be less than 13 characters");
        } else {
            textInputPass.setError(null);
            return true;
        }
        return false;
    }


    public void validateSignUp(View v) {
//        if (!validateEmail() | !validatePassword() | !validateName() | !validateUsername() | !validateUsername()) {
        if (!validatePassword() | !validateUsername() | !validateEmail()) {
            return;
        }
        String username = textInputUsername.getEditText().getText().toString().trim();
        String password = textInputPass.getEditText().getText().toString().trim();
        String email = textInputEmail.getEditText().getText().toString().trim();
        String fullname = textInputFullname.getEditText().getText().toString().trim();
        long val = db.addUser(username, password, email, fullname);
        if (val > 0) {
            Toast.makeText(LogInActivity.this, "successful sign up", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LogInActivity.this, "sign up fail", Toast.LENGTH_SHORT).show();
        }

    }
}
