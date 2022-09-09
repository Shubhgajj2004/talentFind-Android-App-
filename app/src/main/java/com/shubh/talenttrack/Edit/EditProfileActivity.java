package com.shubh.talenttrack.Edit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.talenttrack.DashBoardActivity;
import com.shubh.talenttrack.FirebaseVar.FirebaseVarClass;
import com.shubh.talenttrack.R;
import com.shubh.talenttrack.databinding.ActivityEditProfileBinding;

public class EditProfileActivity extends AppCompatActivity {

    ActivityEditProfileBinding binding;
    FirebaseDatabase mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser mUSer;

    String [] roles= {"Singer" , "Dancer" , "Actor" , "Comedian"};
    String [] gender = {"Male" , "Female"};
    String selectedRole;
    String selectedGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        //Role selection
        ArrayAdapter<String> adapterRole;
        adapterRole=new ArrayAdapter<>(getApplicationContext(), R.layout.array,roles);
        binding.roleEdit2.setAdapter(adapterRole);

        binding.roleEdit2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedRole=parent.getItemAtPosition(position).toString();
            }
        });

        //Gender selection
        ArrayAdapter<String> adapterGender;
        adapterGender=new ArrayAdapter<>(getApplicationContext(), R.layout.array,gender);
        binding.genderEdit2.setAdapter(adapterGender);

        binding.genderEdit2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedGender=parent.getItemAtPosition(position).toString();
            }
        });



        //instances
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUSer = mAuth.getCurrentUser();

        //Fetch the data

        mDatabase.getReference().child(FirebaseVarClass.PROFILE).child(mUSer.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.nameEdit2.setText(snapshot.child(FirebaseVarClass.NAME).getValue(String.class));
                binding.roleEdit2.setText(snapshot.child(FirebaseVarClass.ROLE).getValue(String.class));
                binding.locationEdit2.setText(snapshot.child(FirebaseVarClass.LOCATION).getValue(String.class));
                binding.mobileEdit2.setText(snapshot.child(FirebaseVarClass.MOB).getValue(String.class));
                binding.emailEdit2.setText(snapshot.child(FirebaseVarClass.EMAIL).getValue(String.class));
                binding.genderEdit2.setText(snapshot.child(FirebaseVarClass.GENDER).getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.nameEdit.getEditText().getText().toString().isEmpty()){
                    binding.nameEdit.setError("Empty");
                }
                else if(binding.locationEdit.getEditText().getText().toString().isEmpty()){
                    binding.locationEdit.setError("Empty");
                }
                else if(binding.emailEdit.getEditText().getText().toString().isEmpty()){
                    binding.emailEdit.setError("Empty");
                }
                else if(binding.mobileEdit.getEditText().getText().toString().isEmpty()){
                    binding.mobileEdit.setError("Empty");
                }
                else{

                    DatabaseReference reference =  mDatabase.getReference().child(FirebaseVarClass.PROFILE).child(mUSer.getUid());
                    reference.child(FirebaseVarClass.NAME).setValue(binding.nameEdit2.getText().toString());
                    reference.child(FirebaseVarClass.ROLE).setValue(selectedRole);
                    reference.child(FirebaseVarClass.LOCATION).setValue(binding.locationEdit2.getText().toString());
                    reference.child(FirebaseVarClass.MOB).setValue(binding.mobileEdit2.getText().toString());
                    reference.child(FirebaseVarClass.EMAIL).setValue(binding.emailEdit2.getText().toString());
                    reference.child(FirebaseVarClass.GENDER).setValue(selectedGender).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            startActivity(new Intent(getApplicationContext() , DashBoardActivity.class));
                        }
                    });

                }



            }
        });

    }
}