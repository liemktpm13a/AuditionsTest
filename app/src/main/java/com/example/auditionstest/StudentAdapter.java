package com.example.auditionstest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ThingViewHolder>{
    private Context ctx;
    private LayoutInflater inflater;
    private ArrayList<Student> students;

    public StudentAdapter(Context ctx, ArrayList<Student> students) {
        inflater=LayoutInflater.from(ctx);
        this.students = students;
    }

    @NonNull
    @Override
    public StudentAdapter.ThingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = inflater.inflate(R.layout.item_view,parent,false);
        return new ThingViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ThingViewHolder holder, int position) {
        Student student = students.get(position);
        holder.tvName.setText(student.getName());

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ThingViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        StudentAdapter studentAdapter;
        public ThingViewHolder(@NonNull View itemView, StudentAdapter studentAdapter) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameStd);
        }
    }
}
