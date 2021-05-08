package com.example.hulaki;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.hulaki.databinding.UserRowBinding;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    Context context;
    ArrayList<UsersModel> users;
    public UsersAdapter(Context context,ArrayList<UsersModel> users){
        this.context=context;
        this.users=users;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     final UsersModel usersModel= users.get(position);
     holder.binding.userNameView.setText(usersModel.getUserName());
        Glide.with(context).load(usersModel.getUserProfileImage()).placeholder(R.drawable.avatar).into(holder.binding.userImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ChatActivity.class);
                intent.putExtra("name",usersModel.getUserName());
                intent.putExtra("uid",usersModel.getUserId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       UserRowBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=UserRowBinding.bind(itemView);
        }
    }
}
