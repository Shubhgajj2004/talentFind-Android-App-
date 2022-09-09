package com.shubh.talenttrack.Dashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.shubh.talenttrack.Adapters.jobAdapter;
import com.shubh.talenttrack.FirebaseVar.FirebaseVarClass;
import com.shubh.talenttrack.Jobs.MyJobActivity;
import com.shubh.talenttrack.Models.jobModel;
import com.shubh.talenttrack.R;
import com.shubh.talenttrack.databinding.FragmentJobsBinding;
import com.shubh.talenttrack.databinding.FragmentProfileBinding;

import java.util.ArrayList;

public class JobsFragment extends Fragment {


    public JobsFragment() {
        // Required empty public constructor
    }
    FragmentJobsBinding binding;
    ArrayList<jobModel> list = new ArrayList<>();
    ArrayList<jobModel> listKey = new ArrayList<>();
    FirebaseDatabase mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser mUSer;
    jobAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentJobsBinding.inflate(inflater, container, false);

        //instances
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUSer = mAuth.getCurrentUser();


        //Set the Recyclerview with adapter
        adapter = new jobAdapter(list, listKey, getContext());
        binding.jobRes.setAdapter(adapter);
        binding.jobRes.setLayoutManager(new LinearLayoutManager(getContext()));

        //Fetch the data from database to populate the recycler view via model
        mDatabase.getReference().child(FirebaseVarClass.JOBS).child(FirebaseVarClass.ACTOR).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    jobModel adp = snapshot1.getValue(jobModel.class);
                    list.add(adp);
                }

                listKey.clear();
                for(DataSnapshot snapshot2 : snapshot.getChildren())
                {
                    jobModel adp= new jobModel(snapshot2.getKey());
                    listKey.add(adp);
                }




                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        mDatabase.getReference().child(FirebaseVarClass.PROFILE).child(mUSer.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String img = snapshot.child(FirebaseVarClass.IMG).getValue(String.class);
                if(img !=null){
                    Glide.with(getContext()).load(img).into(binding.jobImg);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.myJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MyJobActivity.class));
            }
        });



        return binding.getRoot();

    }
}