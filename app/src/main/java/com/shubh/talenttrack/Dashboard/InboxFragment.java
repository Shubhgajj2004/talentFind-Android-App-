package com.shubh.talenttrack.Dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.talenttrack.FirebaseVar.FirebaseVarClass;
import com.shubh.talenttrack.R;
import com.shubh.talenttrack.databinding.FragmentInboxBinding;
import com.shubh.talenttrack.databinding.FragmentProfileBinding;


public class InboxFragment extends Fragment {

    public InboxFragment() {
        // Required empty public constructor
    }

    FragmentInboxBinding binding;
    FirebaseDatabase mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser mUSer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInboxBinding.inflate(inflater, container, false);

        //instances
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUSer = mAuth.getCurrentUser();


        mDatabase.getReference().child(FirebaseVarClass.PROFILE).child(mUSer.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String img = snapshot.child(FirebaseVarClass.IMG).getValue(String.class);
                if(img !=null){
                    Glide.with(getContext()).load(img).into(binding.convImg);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return binding.getRoot();
    }
}