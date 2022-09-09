package com.shubh.talenttrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shubh.talenttrack.FirebaseVar.FirebaseVarClass;
import com.shubh.talenttrack.databinding.ActivityDetailJobBinding;

public class DetailJobActivity extends AppCompatActivity {

    ActivityDetailJobBinding binding;
    int applicants, highAge, highPay, lowAge, lowPay;

    FirebaseDatabase mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser mUSer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailJobBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //instances
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUSer = mAuth.getCurrentUser();



        //Getting data from previous Activity
        applicants = getIntent().getIntExtra(FirebaseVarClass.APPLICANTS, 0);
        highAge = getIntent().getIntExtra(FirebaseVarClass.HIGHAGE, 0);
        highPay = getIntent().getIntExtra(FirebaseVarClass.HIGHPAY, 0);
        lowAge = getIntent().getIntExtra(FirebaseVarClass.LOWAGE, 0);
        lowPay = getIntent().getIntExtra(FirebaseVarClass.LOWPAY, 0);


        //setting up data into layout
        binding.genderJobDet.setText(getIntent().getStringExtra(FirebaseVarClass.GENDER));
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra(FirebaseVarClass.IMG)).into(binding.logoJobDet) ;
        binding.titleJobDet.setText(getIntent().getStringExtra(FirebaseVarClass.TITLE));
        binding.desJobDet.setText(getIntent().getStringExtra(FirebaseVarClass.DES));
        binding.locationJobDet.setText(getIntent().getStringExtra(FirebaseVarClass.LOCATION));
        binding.recruiterJobDet.setText(getIntent().getStringExtra(FirebaseVarClass.RECRUITER));
        binding.roleJobDet.setText(getIntent().getStringExtra(FirebaseVarClass.ROLE));

        binding.payRangeJobDet.setText("₹ "+ Integer.toString(lowPay) + " - " + "₹ " + Integer.toString(highPay));
        binding.ageRangeJobDet.setText(Integer.toString(lowAge) + " - " + Integer.toString(highAge) + " years");
        binding.postedDateJobDet.setText(getIntent().getStringExtra(FirebaseVarClass.POSTEDDATE));
        binding.expireDateJobDet.setText(getIntent().getStringExtra(FirebaseVarClass.EXPIRESDATE));


        //On clicking Apply JOB button
        binding.applyJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference reference =  mDatabase.getReference().child(FirebaseVarClass.PROFILE).child(mUSer.getUid()).child(FirebaseVarClass.MYJOBS).push();
                reference.child(FirebaseVarClass.KEY).setValue(getIntent().getStringExtra(FirebaseVarClass.KEY)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(DetailJobActivity.this, "Applied successfully", Toast.LENGTH_SHORT).show();
                        binding.applyJob.setClickable(false);
                        binding.applyJob.setText("Applied");
                    }
                });

            }
        });



    }
}