package com.example.android_lesson_7;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edtEmail = findViewById(R.id.editTextTextEmailAddress);
        EditText edtPassword = findViewById(R.id.editTextTextPassword);

        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnLogin = findViewById(R.id.btnLogin);

        Intent intent = getIntent();
        String startActivity = intent.getStringExtra(Activity.ACTIVITY_SERVICE);
        String activityToCompare = RegisterActivity.class.getSimpleName();
        if (startActivity != null && startActivity.equalsIgnoreCase(activityToCompare)) {
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_LONG).show();
        }

        Intent intentGoToRegister = new Intent(this, RegisterActivity.class);
        btnRegister.setOnClickListener(v -> {
            startActivity(intentGoToRegister);
        });
        Intent intentGoToHome = new Intent(this, MainActivity.class);
        btnLogin.setOnClickListener(v -> {
            mAuth.signInWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                startActivity(intentGoToHome);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

    }
}