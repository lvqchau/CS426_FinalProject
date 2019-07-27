package com.example.planter;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
    private TextInputLayout textInputUsername, textInputUsernameLI;
    private TextInputLayout textInputPass, textInputPassLI;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputFirstname;
    private TextInputLayout textInputLastname;

    public LinearLayout loginLayout, signupLayout;
    public Button btnSignUp, btnLogIn, btnCheck, btnInsert, btnGo;
    private TextView textView;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);

        btnLogIn = findViewById(R.id.login);
        btnSignUp = findViewById(R.id.signup);
        signupLayout = findViewById(R.id.SU_layout);
        loginLayout = findViewById(R.id.LI_layout);

        textInputEmail = findViewById(R.id.SU_email);
        textInputFirstname = findViewById(R.id.SU_first);
        textInputLastname = findViewById(R.id.SU_last);

        textInputUsername = findViewById(R.id.SU_username);
        textInputPass = findViewById(R.id.SU_pass);
        textInputPassLI = findViewById(R.id.LI_pass);
        textInputUsernameLI = findViewById(R.id.LI_username);
        textView = findViewById(R.id.txt);

        btnCheck= findViewById(R.id.btnLogIn);
        btnInsert = findViewById(R.id.btnSignUp);
        btnGo = findViewById(R.id.btnApp);

        btnOnClick();
        toggleLogIn();

    }

    //Click button to Sign up or to Log in
    public void btnOnClick() {
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.listPlantUsers(textView);
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateSignUp(view)) {
                    String username = textInputUsername.getEditText().getText().toString().trim();
                    String password = textInputPass.getEditText().getText().toString().trim();
                    String email = textInputEmail.getEditText().getText().toString().trim();
                    String first = textInputFirstname.getEditText().getText().toString().trim();
                    String last = textInputLastname.getEditText().getText().toString().trim();
                    long val = db.addUser(username, password, email, first, last);
                    long val2 = db.addPlantUser(username);
                    if (val > 0) {
                        Toast.makeText(LogInActivity.this, "successful sign up", Toast.LENGTH_SHORT).show();
                        switchLayoutOnLaunch(btnLogIn, btnSignUp, loginLayout, signupLayout);
                        textInputUsernameLI.getEditText().setText(username);
                        textInputPassLI.getEditText().setText(password);
                    } else {
                        Toast.makeText(LogInActivity.this, "sign up fail", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(LogInActivity.this, "log in fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateLogIn(view)) {
                    String username = textInputUsernameLI.getEditText().getText().toString().trim();
                    String password = textInputPassLI.getEditText().getText().toString().trim();
                    Boolean res = db.checkUser(username, password);
                    if (res) {
                        Toast.makeText(LogInActivity.this, "successful log in", Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(LogInActivity.this, MainActivity.class);
                        myIntent.putExtra("Username", username);
                        startActivity(myIntent);
                    } else {
                        Toast.makeText(LogInActivity.this, "log in fail", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LogInActivity.this, "log in fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Validate: if the field is not inputted, will raise error
    public boolean validateSignUp(View v) {
//        if (!validateEmail() | !validatePassword() | !validateName() | !validateUsername() | !validateUsername()) {
        if (!validatePassword(textInputPass) | !validateUsername(textInputUsername) | !validateEmail()) {
            return false;
        }
        return true;

    }
    public boolean validateLogIn(View v) {
        if (!validatePassword(textInputPassLI) | !validateUsername(textInputUsernameLI)) {
            return false;
        }
        return true;
    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputEmail.setError("e");
        } else {
            textInputEmail.setError(null);
            return true;
        }
        return false;
    }
    private boolean validateUsername(TextInputLayout textInputLayout) {
        String usernameInput = textInputLayout.getEditText().getText().toString().trim();
        if (usernameInput.isEmpty()) {
            textInputLayout.setError("e");
        } else {
            textInputLayout.setError(null);
            return true;
        }
        return false;
    }
    private boolean validatePassword(TextInputLayout textInputLayout) {
        String passInput = textInputLayout.getEditText().getText().toString().trim();
        if (passInput.isEmpty()) {
            textInputLayout.setError("e");
        } else if (passInput.length() > 12) {
            textInputLayout.setError("e");
        } else {
            textInputLayout.setError(null);
            return true;
        }
        return false;
    }

    //change between Log in and Sign Up layout
    public void toggleLogIn() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchLayoutOnLaunch(btnSignUp, btnLogIn, signupLayout, loginLayout);
            }
        });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchLayoutOnLaunch(btnLogIn, btnSignUp, loginLayout, signupLayout);
            }
        });
    }
    public void switchLayoutOnLaunch(Button btnCur, Button btnPrev, LinearLayout cur, LinearLayout prev) {
        btnCur.setBackgroundResource(R.drawable.topcorner);
        btnCur.setTextColor(Color.parseColor("#000000"));
        if (btnCur == btnSignUp) {
            btnCur.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_signup_selected, 0, 0, 0);
            btnPrev.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_login, 0, 0, 0);
        } else if (btnCur == btnLogIn) {
            btnCur.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_login_selected, 0, 0, 0);
            btnPrev.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_signup, 0, 0, 0);
        }

        btnPrev.setBackgroundColor(Color.parseColor("#ffffff"));
        btnPrev.setTextColor(Color.parseColor("#8F8F8F"));


        cur.setVisibility(View.VISIBLE);
        prev.setVisibility(View.GONE);
    }

}
