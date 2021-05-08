package com.example.hulaki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hulaki.databinding.ItemReceiveBinding;
import com.example.hulaki.databinding.ItemSentBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter{

    Context context;
    ArrayList<MessageModel> messages;
    final int ITEM_SENT=1;
    final int ITEM_RECEIVE=2;
    public MessageAdapter(Context context, ArrayList<MessageModel> messages){
       this.context=context;
       this.messages=messages;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==ITEM_SENT){
            View view = LayoutInflater.from(context).inflate(R.layout.item_sent,parent,false);
            return new SentViewHolder(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.item_receive,parent,false);
            return new ReceiveViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
           MessageModel messageModel = messages.get(position);
           if(holder.getClass()==SentViewHolder.class){
               SentViewHolder viewHolder = (SentViewHolder)holder;
               viewHolder.binding.message.setText(messageModel.getMessage());
           }else{
               ReceiveViewHolder viewHolder = (ReceiveViewHolder) holder;
               viewHolder.binding.messageReceive.setText(messageModel.getMessage());
           }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        MessageModel messageModel = messages.get(position);
        if(FirebaseAuth.getInstance().getUid().equals(messageModel.getSenderId())){
            return ITEM_SENT;
        }else{
            return ITEM_RECEIVE;
        }
    }

    public class SentViewHolder extends RecyclerView.ViewHolder{

        ItemSentBinding binding;
        public SentViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemSentBinding.bind(itemView);
        }
    }
    public class ReceiveViewHolder extends RecyclerView.ViewHolder{
        ItemReceiveBinding binding;
        public ReceiveViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemReceiveBinding.bind(itemView);
        }
    }
}
