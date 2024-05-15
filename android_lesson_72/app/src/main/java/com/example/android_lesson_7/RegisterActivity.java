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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private boolean isDataCorrect = true;
    private final String EMAIL_REGEX =
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText edtEmail = findViewById(R.id.edtRegEmail);
        EditText editPassword = findViewById(R.id.edtRegPassword);
        EditText editConfirmPassword = findViewById(R.id.edtConfirmPassword);
        EditText editPhone = findViewById(R.id.editTextPhone);

        FirebaseApp.initializeApp(getApplicationContext());

        Button btnRegister = findViewById(R.id.btnSignUp);

        Intent intentGoToLogin = new Intent(this, LoginActivity.class);

        btnRegister.setOnClickListener(v -> {
            isDataCorrect = true;
            if (edtEmail.getText().toString().isEmpty()) {
                isDataCorrect = false;
                edtEmail.setError("required");
            }
            if (editPhone.getText().toString().isEmpty()) {
                isDataCorrect = false;
                editPhone.setError("required");
            }
            if (editPassword.getText().toString().isEmpty() || editPassword.getText().toString().length() < 6) {
                isDataCorrect = false;
                editPassword.setError("required > 6 symbols");
            }
            if (editConfirmPassword.getText().toString().isEmpty()) {
                isDataCorrect = false;
                editConfirmPassword.setError("required");
            }

            if (!isDataCorrect) {
                Toast.makeText(getApplicationContext(), "Some fields are missing or incorrect!", Toast.LENGTH_LONG).show();
            }

            if (!editPassword.getText().toString().equals(editConfirmPassword.getText().toString())) {
                editPassword.setError("doesn't match");
                editConfirmPassword.setError("doesn't match");
                isDataCorrect = false;
                Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_LONG).show();
            }

            if (!edtEmail.getText().toString().matches(EMAIL_REGEX)) {
                isDataCorrect = false;
                edtEmail.setError("incorrect format");
                Toast.makeText(getApplicationContext(), "Invalid email format!", Toast.LENGTH_LONG).show();
            }

            if (isDataCorrect) {
                intentGoToLogin.putExtra(Activity.ACTIVITY_SERVICE, RegisterActivity.class.getSimpleName());
                firebaseAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), editPassword.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "createUserWithEmail:success");
                                    startActivity(intentGoToLogin);
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    if (user != null) {
                                        System.out.println(user.getDisplayName() + user.getEmail() + user.getUid());
                                    }

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        });

    }
}