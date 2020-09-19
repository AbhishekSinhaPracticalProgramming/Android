package com.example.mmu_application;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mmu.R;

import java.util.List;

public class Sample_Layout_Adapter extends ArrayAdapter<Add_Alumni_module> {

    private Activity context;
    private List<Add_Alumni_module> alumniList;

    public Sample_Layout_Adapter(Activity context,   List<Add_Alumni_module> alumniList) {
        super(context, R.layout.sample_layout_our_alumni,alumniList);
        this.context = context;
        this.alumniList = alumniList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();

        View view=layoutInflater.inflate(R.layout.sample_layout_our_alumni,null,true);
        Add_Alumni_module add_alumni_module=alumniList.get(position);

        TextView t1=view.findViewById(R.id.sample_name_id);
        TextView t2=view.findViewById(R.id.sample_email_id);
        TextView t3=view.findViewById(R.id.sample_linkedin_id);
        TextView t4=view.findViewById(R.id.sample_phone_id);
        TextView t5=view.findViewById(R.id.sample_session_id);
        TextView t6=view.findViewById(R.id.sample_company_id);
        TextView t7=view.findViewById(R.id.sample_designation_id);
        TextView t8=view.findViewById(R.id.sample_berif_id);

        t1.setText("Name :- "+add_alumni_module.getFullname());
        t2.setText("Email :-    "+add_alumni_module.getEmail());
        t3.setText("LinkedIn :- "+add_alumni_module.getLinkedin());
        t4.setText("Phone :-    "+add_alumni_module.getPhone());
        t5.setText("Session :-  "+add_alumni_module.getSession());
        t6.setText("Company :-  "+add_alumni_module.getCompany());
        t7.setText("Designation :-  "+add_alumni_module.getDesignation());
        t8.setText("Berif Description :-    "+add_alumni_module.getBerif());


        return view;
    }
}

