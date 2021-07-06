package com.example.profilecruddb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.profilecruddb.R;
import com.example.profilecruddb.model.User;

import java.util.ArrayList;
import java.util.List;

public class ViewUserAdapter extends RecyclerView.Adapter<ViewUserAdapter.ViewHolder>{
private List<User> users = new ArrayList<>();
private Context context;

public ViewUserAdapter(Context context){
    this.context = context;
}


    @NonNull
    @Override
    public ViewUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user_adapter, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewUserAdapter.ViewHolder holder, int position) {
        Glide.with(context).asBitmap().load(users.get(position).getImageUri()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public Context getContext() {
        return context;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image_userprofile);
        }
    }
}
