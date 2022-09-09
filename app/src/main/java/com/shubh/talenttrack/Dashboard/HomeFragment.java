package com.shubh.talenttrack.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.talenttrack.FirebaseVar.FirebaseVarClass;
import com.shubh.talenttrack.Premium.PremiumFeatureAdActivity;
import com.shubh.talenttrack.R;
import com.shubh.talenttrack.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    FragmentHomeBinding binding;
    FirebaseDatabase mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser mUSer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);


        //instances
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUSer = mAuth.getCurrentUser();


        //fetching the data from Database
        mDatabase.getReference().child(FirebaseVarClass.PROFILE).child(mUSer.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String img = snapshot.child(FirebaseVarClass.IMG).getValue(String.class);
                String name = snapshot.child(FirebaseVarClass.NAME).getValue(String.class);
                binding.greetingHome.setText("Hello, " + name);
                if (img != null) {
                    if (getActivity() != null) {
                        Glide.with(getContext()).load(img).into(binding.homeImg);
                        binding.profilePicAlert.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDatabase.getReference().child(FirebaseVarClass.INFLUENCER).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (getActivity() != null) {

                    Glide.with(getContext()).load(snapshot.child("one").child(FirebaseVarClass.IMG).getValue(String.class)).into(binding.firstInf);
                    Glide.with(getContext()).load(snapshot.child("two").child(FirebaseVarClass.IMG).getValue(String.class)).into(binding.twoInf);
                    Glide.with(getContext()).load(snapshot.child("third").child(FirebaseVarClass.IMG).getValue(String.class)).into(binding.thirdInf);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDatabase.getReference().child(FirebaseVarClass.TIMELINE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (getActivity() != null) {

                    Glide.with(getContext()).load(snapshot.child(FirebaseVarClass.IMG).getValue(String.class)).into(binding.timelineImg);
                    binding.timelineDes.setText(snapshot.child(FirebaseVarClass.DES).getValue(String.class));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.premiumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PremiumFeatureAdActivity.class));
            }
        });


        //Buttons for goto Profile Fragment

        binding.profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ProfileFragment();
                getFragmentManager().beginTransaction().replace(R.id.container_frag, fragment).commit();

            }
        });

        binding.updateSocialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ProfileFragment();
                getFragmentManager().beginTransaction().replace(R.id.container_frag, fragment).commit();

            }
        });

        binding.suggestPhotosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ProfileFragment();
                getFragmentManager().beginTransaction().replace(R.id.container_frag, fragment).commit();

            }
        });


        return binding.getRoot();
    }
}