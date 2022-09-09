package com.shubh.talenttrack.Dashboard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubh.talenttrack.Adapters.typeProfileAdapter;
import com.shubh.talenttrack.DetailProfile.BioProfileFragment;
import com.shubh.talenttrack.DetailProfile.ExpProfileFragment;
import com.shubh.talenttrack.DetailProfile.GalleryProfileFragment;
import com.shubh.talenttrack.Edit.EditProfileActivity;
import com.shubh.talenttrack.FirebaseVar.FirebaseVarClass;
import com.shubh.talenttrack.Models.typeProfileModel;
import com.shubh.talenttrack.R;
import com.shubh.talenttrack.databinding.FragmentProfileBinding;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    public static Uri imgUri;

    public ProfileFragment() {
        // Required empty public constructor
    }

    FragmentProfileBinding binding;
    FirebaseDatabase mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser mUSer;

    private typeProfileAdapter adapter;
    ArrayList<typeProfileModel> fragmentList = new ArrayList<>();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);



        //instances
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUSer = mAuth.getCurrentUser();


        //Fetching the profile data from Database
        mDatabase.getReference().child(FirebaseVarClass.PROFILE).child(mUSer.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.nameProf.setText(snapshot.child(FirebaseVarClass.NAME).getValue(String.class));
                binding.roleProf.setText(snapshot.child(FirebaseVarClass.ROLE).getValue(String.class));
                binding.locationProf.setText(snapshot.child(FirebaseVarClass.LOCATION).getValue(String.class));

                String img = snapshot.child(FirebaseVarClass.IMG).getValue(String.class);
                if(img !=null){

                    if(getActivity() !=null){
                        Glide.with(getContext()).load(img).into(binding.profileImg);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        //To Pick image with some editing features
        binding.profileImg.setOnClickListener(view -> ImagePicker.with(ProfileFragment.this)
                .crop()                     //Crop image(Optional), Check Customization for more option
                .start());




        //Handing Tab layout
        FragmentManager fm = getChildFragmentManager();
        adapter = new typeProfileAdapter(fm, getLifecycle());

        fragmentList.add(new typeProfileModel(new GalleryProfileFragment(), "GALLERY"));
        fragmentList.add(new typeProfileModel(new BioProfileFragment(), "BIO"));
        fragmentList.add(new typeProfileModel(new ExpProfileFragment(), "EXPERIENCE"));

        adapter.Frag(fragmentList);


        binding.Viewpag2.setAdapter(adapter);
        binding.Viewpag2.setOffscreenPageLimit(1);


        new TabLayoutMediator(binding.tabProf, binding.Viewpag2,
                (tab, position) -> {
                    typeProfileModel adp = fragmentList.get(position);
                    tab.setText(adp.getTitle());
                }).attach();



        //Load Edit Profile Activity
        binding.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext() , EditProfileActivity.class));
            }
        });


        return binding.getRoot();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            imgUri = data.getData();
            if (!imgUri.equals(""))
                binding.profileImg.setImageURI(imgUri);
                mDatabase.getReference().child(FirebaseVarClass.PROFILE).child(mUSer.getUid()).child(FirebaseVarClass.IMG).setValue(imgUri.toString());

        } catch (Exception e) {
        }
    }

}