package com.shubh.talenttrack.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.shubh.talenttrack.Models.typeProfileModel;

import java.util.ArrayList;

public class typeProfileAdapter extends FragmentStateAdapter {


    ArrayList<typeProfileModel> fragmentList ;

    public typeProfileAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void Frag(ArrayList<typeProfileModel> fragmentList )
    {
        this.fragmentList = fragmentList;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        typeProfileModel adp =fragmentList.get(position);

        return adp.getFm();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
