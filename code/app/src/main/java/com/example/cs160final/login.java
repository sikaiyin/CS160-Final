package com.example.cs160final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mRegisterBtn;
    TextView mLogBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.editText_Name);
        mPassword = findViewById(R.id.editText_Password);
        mRegisterBtn = findViewById(R.id.sign_up);
        mLogBtn = findViewById(R.id.SignInBtn);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            if(getIntent().hasExtra("ImagebyteArray")) {
                intent.putExtra("PhotobyteArray", getIntent().getByteArrayExtra("ImagebyteArray"));
            }
            if(getIntent().hasExtra("User")) {
                intent.putExtra("UserName", getIntent().getStringExtra("User"));
            }
            startActivity(intent);
            finish();
        }

        mLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required.");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password must be at least 6 characters");
                    return;
                }

                // authorize the user
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                            if(getIntent().hasExtra("ImagebyteArray")) {
                                intent2.putExtra("PhotobyteArray", getIntent().getByteArrayExtra("ImagebyteArray"));
                            }
                            if(getIntent().hasExtra("User")) {
                                intent2.putExtra("UserName", getIntent().getStringExtra("User"));
                            }
                            startActivity(intent2);
                        }else{
                            Toast.makeText(login.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), registeration.class);
                startActivity(intent);
            }
        });
    }
}
