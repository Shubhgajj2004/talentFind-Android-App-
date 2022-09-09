package com.shubh.talenttrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.shubh.talenttrack.Dashboard.HomeFragment;
import com.shubh.talenttrack.Dashboard.InboxFragment;
import com.shubh.talenttrack.Dashboard.JobsFragment;
import com.shubh.talenttrack.Dashboard.ProfileFragment;
import com.shubh.talenttrack.databinding.ActivityDashBoardBinding;
import com.shubh.talenttrack.databinding.ActivityMainBinding;

public class DashBoardActivity extends AppCompatActivity {

    ActivityDashBoardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Fragment frag = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_frag, frag).commit();


        //synchronised the fragment with specific button of navigation bar
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            Fragment fragment;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_nav:
                        fragment = new HomeFragment();
                        break;

                    case R.id.job_nav:
                        fragment = new JobsFragment();
                        break;

                    case R.id.inbox_nav:
                        fragment = new InboxFragment();
                        break;

                    case R.id.profile_nav:
                        fragment = new ProfileFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container_frag, fragment).commit();


                return true;
            }
        });


    }
}