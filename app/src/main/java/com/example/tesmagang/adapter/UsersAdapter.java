package com.example.tesmagang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tesmagang.R;
import com.example.tesmagang.model.Users;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private Context context;
    private List<Users> usersList;

    public UsersAdapter(Context context, List<Users> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public UsersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_model, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.MyViewHolder holder, int position) {

        Users users = usersList.get(position);
        holder.textView1.setText(users.getFirst_name());
        holder.textView2.setText(users.getLast_name());
        holder.textView3.setText(users.getEmail());

        Glide.with(context)
                .load(users.getAvatar())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imageView;
        private TextView textView1, textView2, textView3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.action_image);
            textView1 = itemView.findViewById(R.id.textFistUser);
            textView2 = itemView.findViewById(R.id.textLastUser);
            textView3 = itemView.findViewById(R.id.textEmailUser);
        }
    }
}
