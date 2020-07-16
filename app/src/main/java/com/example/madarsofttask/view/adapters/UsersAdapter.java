package com.example.madarsofttask.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madarsofttask.R;
import com.example.madarsofttask.databinding.UserItemBinding;
import com.example.madarsofttask.model.UserModel;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ItemViewHolder> {

    private List<UserModel> items;

    public UsersAdapter(List<UserModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public UsersAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserItemBinding userItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.user_item, parent, false);
        return new ItemViewHolder(userItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        UserItemBinding userItemBinding;

        public ItemViewHolder(@NonNull UserItemBinding itemView) {
            super(itemView.getRoot());
            userItemBinding = itemView;
        }

        public void bind(int position) {
            UserModel models = items.get(position);
            userItemBinding.setUser(models);
        }
    }
}
