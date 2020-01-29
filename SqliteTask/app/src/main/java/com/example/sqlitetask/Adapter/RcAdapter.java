package com.example.sqlitetask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitetask.Model.Model;
import com.example.sqlitetask.R;

import java.util.ArrayList;
import java.util.List;

public class RcAdapter extends RecyclerView.Adapter<RcAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Model> dataSet;

    public RcAdapter(Context context,ArrayList<Model> dataSet) {
       this.context=context;
       this.dataSet=dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.fName.setText(dataSet.get(position).getfName());
        holder.lName.setText(dataSet.get(position).getlName());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView fName;
        TextView lName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fName=itemView.findViewById(R.id.fName);
            lName=itemView.findViewById(R.id.lName);

        }
    }
}
