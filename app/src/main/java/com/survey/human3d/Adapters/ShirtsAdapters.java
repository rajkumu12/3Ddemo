package com.survey.human3d.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.survey.human3d.Interfaces.BaseDelegates;
import com.survey.human3d.Interfaces.Pick;
import com.survey.human3d.Model.ShirtsModel;
import com.survey.human3d.R;

import java.util.List;

public  class ShirtsAdapters extends  RecyclerView.Adapter<ShirtsAdapters.ViewHolder> {
    BaseDelegates cb;
    private Context context;
    private List<ShirtsModel> arrayList;

    public ShirtsAdapters(Context context, List<ShirtsModel> arrayList,BaseDelegates cb) {
        this.context = context;
        this.arrayList = arrayList;
        this.cb=cb;
    }

    @NonNull
    @Override
    public ShirtsAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsview_image, parent, false);
        return new ShirtsAdapters.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShirtsAdapters.ViewHolder holder, final int position) {

        ShirtsModel reportData=arrayList.get(position);
        holder.imageView.setImageResource(reportData.getImageshirt());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pick) cb).onSucess(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.images_1);


        }
    }
}


