package com.shubh.talenttrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shubh.talenttrack.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth mAuth;
    FirebaseUser mUSer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Handle the splash screen transition.
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //instances
        mAuth = FirebaseAuth.getInstance();
        mUSer = mAuth.getCurrentUser();

        //On clicking sign in button check the details with database details, if matches then let to go in  dashboard activity
//        binding.signInBtn.setOnClickListener(v -> mAuth.signInWithEmailAndPassword(binding.emailId2.getText().toString(), binding.passwordLogin2.getText().toString()).
//                addOnCompleteListener(task -> {
//
//                    if (task.isSuccessful()) {
//
//                        Toast.makeText(MainActivity.this, "Sign In successfully", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(MainActivity.this, DashBoardActivity.class);
//                        startActivity(intent);
//
//                    } else {
//                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }));

        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(binding.emailId2.getText().toString(), binding.passwordLogin2.getText().toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Sign In successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, DashBoardActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });


    }


    //every time open this app if user object is not null means student have logged in then will automatically let in DashBoard Activity
    @Override
    protected void onStart() {
        super.onStart();

        if (mUSer != null) {
            Intent intent = new Intent(MainActivity.this, DashBoardActivity.class);
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
        }
    }

}