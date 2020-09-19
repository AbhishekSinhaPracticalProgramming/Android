package com.example.mmu_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mmu.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewAdapter> {


   private Context context;
    private List<PostSubmitModule> postSubmitModules1;

    public PostAdapter(Context context, List<PostSubmitModule> postSubmitModules1) {
        this.context = context;
        this.postSubmitModules1 = postSubmitModules1;
    }

    @NonNull
    @Override
    public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.post_item_layout,viewGroup,false);

        return new PostAdapter.MyViewAdapter(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewAdapter holder, int i) {

        PostSubmitModule postSubmitModule=postSubmitModules1.get(i);

        holder.emailP.setText(postSubmitModule.getEmailM());
        holder.postP.setText(postSubmitModule.getPostM());

    }

    @Override
    public int getItemCount() {
        return postSubmitModules1.size();
    }



    public class MyViewAdapter extends  RecyclerView.ViewHolder {

        TextView emailP,postP;
        public MyViewAdapter(@NonNull View itemView) {

            super(itemView);

            emailP=itemView.findViewById(R.id.postP_email_id);
            postP=itemView.findViewById(R.id.postP_id);


        }
    }
}
