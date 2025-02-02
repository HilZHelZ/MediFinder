package com.example.hospitalfindertest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.OptionViewHolder> {

    private ArrayList<String> optionsList;

    public OptionsAdapter(ArrayList<String> optionsList) {
        this.optionsList = optionsList;
    }

    @Override
    public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OptionViewHolder holder, int position) {
        holder.optionTextView.setText(optionsList.get(position));
    }

    @Override
    public int getItemCount() {
        return optionsList.size();
    }

    public static class OptionViewHolder extends RecyclerView.ViewHolder {
        TextView optionTextView;

        public OptionViewHolder(View itemView) {
            super(itemView);
            optionTextView = itemView.findViewById(android.R.id.text1);
        }
    }
}
