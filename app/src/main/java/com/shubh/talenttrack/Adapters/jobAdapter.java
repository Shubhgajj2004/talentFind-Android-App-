package com.shubh.talenttrack.Adapters;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shubh.talenttrack.DetailJobActivity;
import com.shubh.talenttrack.FirebaseVar.FirebaseVarClass;
import com.shubh.talenttrack.Models.jobModel;
import com.shubh.talenttrack.R;

import java.util.ArrayList;

public class jobAdapter extends RecyclerView.Adapter<jobAdapter.MyHolder> {

    ArrayList<jobModel> list;
    ArrayList<jobModel> listKey;
    Context context;

    public jobAdapter(ArrayList<jobModel> list, ArrayList<jobModel> listKey, Context context) {
        this.list = list;
        this.listKey = listKey;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        jobModel adp= list.get(position);
        jobModel adp2= listKey.get(position);

        holder.title.setText(adp.getTitle());
        holder.recruiter.setText(adp.getRecruiter());
        holder.location.setText(adp.getLocation());
        Glide.with(context).load(adp.getImg()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , DetailJobActivity.class);
                intent.putExtra(FirebaseVarClass.TITLE , adp.getTitle());
                intent.putExtra(FirebaseVarClass.IMG , adp.getImg());
                intent.putExtra(FirebaseVarClass.ROLE , adp.getRole());
                intent.putExtra(FirebaseVarClass.DES , adp.getDes());
                intent.putExtra(FirebaseVarClass.RECRUITER , adp.getRecruiter());
                intent.putExtra(FirebaseVarClass.POSTEDDATE , adp.getPostedDate());
                intent.putExtra(FirebaseVarClass.LOCATION , adp.getLocation());
                intent.putExtra(FirebaseVarClass.GENDER , adp.getGender());
                intent.putExtra(FirebaseVarClass.EXPIRESDATE , adp.getExpiresDate());
                intent.putExtra(FirebaseVarClass.APPLICANTS , adp.getApplicants());
                intent.putExtra(FirebaseVarClass.HIGHAGE , adp.getHighAge());
                intent.putExtra(FirebaseVarClass.HIGHPAY , adp.getHighPay());
                intent.putExtra(FirebaseVarClass.LOWAGE , adp.getLowAge());
                intent.putExtra(FirebaseVarClass.LOWPAY , adp.getLowPay());
                intent.putExtra(FirebaseVarClass.KEY,adp2.getKey());



                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

        public static class MyHolder extends RecyclerView.ViewHolder
        {
            ImageView img;
            TextView title, recruiter, location;

            public MyHolder(@NonNull View itemView) {
                super(itemView);

                img =itemView.findViewById(R.id.logo_job);
                title = itemView.findViewById(R.id.title_job);
                recruiter = itemView.findViewById(R.id.recruiter_job);
                location = itemView.findViewById(R.id.location_job);
            }
        }
}
