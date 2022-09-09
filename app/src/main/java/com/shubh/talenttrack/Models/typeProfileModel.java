package com.shubh.talenttrack.Models;

import androidx.fragment.app.Fragment;

public class typeProfileModel {

    Fragment fm ;
    String title;

    public typeProfileModel(Fragment fm, String title) {
        this.fm = fm;
        this.title = title;
    }

    public Fragment getFm() {
        return fm;
    }

    public void setFm(Fragment fm) {
        this.fm = fm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
