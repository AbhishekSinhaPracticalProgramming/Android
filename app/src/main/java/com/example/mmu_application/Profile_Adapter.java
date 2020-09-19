package com.example.mmu_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mmu.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_Adapter extends RecyclerView.Adapter<Profile_Adapter.MyViewHolder> {


   private Context context;
   private List<Uplaod> uplaodList1;


    public Profile_Adapter(Context context, List<Uplaod> uplaodList1) {
        this.context = context;
        this.uplaodList1 = uplaodList1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
       View view= layoutInflater.inflate(R.layout.profile_item_layout,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        Uplaod uplaod=  uplaodList1.get(i);

        holder.p_name.setText("Name :- "+uplaod.getName());
        holder.p_email.setText("Email :- "+uplaod.getEmail());
        holder.p_phone.setText("Phone :- "+uplaod.getPhone());
        holder.p_session.setText("Session :- "+uplaod.getSession());
        Picasso.with(context)
                .load(uplaod.getImageUrl())
                .placeholder(R.drawable.ic_person_black_24dp)
                .fit()
                .centerCrop()
                .into(holder.p_img);

    }

    @Override
    public int getItemCount()
    {
        return uplaodList1.size();
    }






    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView p_name,p_email,p_phone,p_session;
       ImageView p_img;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            p_name=itemView.findViewById(R.id.profile_name_id);
            p_email=itemView.findViewById(R.id.profile_email_id);
            p_phone=itemView.findViewById(R.id.profile_phone_id);
            p_session=itemView.findViewById(R.id.profile_session_id);
            p_img=itemView.findViewById(R.id.profile_pic_id);
        }
    }
}
