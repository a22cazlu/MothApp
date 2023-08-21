package com.example.mothapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Moth> name;
    private LayoutInflater layoutInflater;
    private OnClickListener onClickListener;

    Adapter(Context context, List<Moth> name, OnClickListener onClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.name = name;
        this.onClickListener = onClickListener;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(name.get(position).getName());
        holder.family.setText("Family: " + name.get(position).getFamily());
        holder.location.setText("Location: " + name.get(position).getLocation());
        holder.size.setText("Size: " + name.get(position).getWings());
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, family, location, size;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title);
            family = itemView.findViewById(R.id.family);
            location = itemView.findViewById(R.id.location);
            size = itemView.findViewById(R.id.size);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(name.get(getAdapterPosition()));
        }
    }

    public interface OnClickListener {
        void onClick(Moth item);
    }
}
