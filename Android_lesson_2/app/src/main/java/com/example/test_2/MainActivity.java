package com.example.test_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chat();
    }

    public void chat() {
        TextView txtPerson1 = findViewById(R.id.txtChat1);
        TextView txtPerson2 = findViewById(R.id.txtChat2);
        EditText person1 = findViewById(R.id.editText1);
        EditText person2 = findViewById(R.id.editText2);
        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(v -> {
            txtPerson1.setText(person1.getText());
            txtPerson2.setText(person2.getText());
            System.out.println(person1.isAllCaps());
            if (person1.getText().toString().contains("123456")) {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("WARNING")
                        .setMessage("Password is too easy")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                            }
                        })

                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else {
                txtPerson1.setTextSize(1, 30);
            }

        });

    }
}