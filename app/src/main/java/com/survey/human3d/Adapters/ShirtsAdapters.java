package com.survey.human3d.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.survey.human3d.MainActivity;
import com.survey.human3d.Model.ShirtsModel;
import com.survey.human3d.R;

import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;

import java.util.List;

import static com.survey.human3d.GalaXyFragment.defaultmaterial;
import static com.survey.human3d.GalaXyFragment.defaultmaterial_pant;
import static com.survey.human3d.GalaXyFragment.defaultmaterial_shirt_g;
import static com.survey.human3d.GalaXyFragment.girl_trouser;
import static com.survey.human3d.GalaXyFragment.lady_pant;
import static com.survey.human3d.GalaXyFragment.pants_boy;
import static com.survey.human3d.GalaXyFragment.shirt;
import static com.survey.human3d.GalaXyFragment.shirt_girl;

public class ShirtsAdapters extends RecyclerView.Adapter<ShirtsAdapters.ViewHolder> {
    BaseDelegates cb;
    private Context context;
    private List<ShirtsModel> arrayList;

    public ShirtsAdapters(Context context, List<ShirtsModel> arrayList, BaseDelegates cb) {
        this.context = context;
        this.arrayList = arrayList;
        this.cb = cb;
    }
    @NonNull
    @Override
    public ShirtsAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsview_image, parent, false);
        return new ShirtsAdapters.ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull final ShirtsAdapters.ViewHolder holder, final int position) {
        ShirtsModel reportData = arrayList.get(position);
        holder.imageView.setImageResource(reportData.getImageshirt());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*((Pick) cb).onSucess(position);*/

                if (MainActivity.gen.equals("Male")){
                    if (reportData.getClothtype().equals("shirt")) {
                        for (ATexture texture : defaultmaterial.getTextureList()) {
                            defaultmaterial.removeTexture(texture);
                            shirt.getChildByName("shirt").getMaterial().removeTexture(texture);// Does not free GLES memory.
                            // Frees GLES memory
                            texture = null;                             // Does not free GLES memory.
                        }
                        Material defaultmaterial = new Material();
                        defaultmaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
                        try {
                            defaultmaterial.addTexture(new Texture("de", reportData.getImageshirt()));
                            defaultmaterial.setColorInfluence(0);

                            shirt.getChildByName("shirt").setMaterial(defaultmaterial);
                        } catch (ATexture.TextureException e) {
                            e.printStackTrace();
                        }
                    }else {
                        for (ATexture texture : defaultmaterial_pant.getTextureList()) {
                            defaultmaterial_pant.removeTexture(texture);
                            pants_boy.getChildByName("pant").getMaterial().removeTexture(texture);// Does not free GLES memory.
                            // Frees GLES memory
                            texture = null;                             // Does not free GLES memory.
                        }
                        Material defaultmaterial = new Material();
                        defaultmaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
                        try {
                            defaultmaterial.addTexture(new Texture("de", reportData.getImageshirt()));
                            defaultmaterial.setColorInfluence(0);

                            pants_boy.getChildByName("pant").setMaterial(defaultmaterial);
                        } catch (ATexture.TextureException e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    if (reportData.getClothtype().equals("shirt"))
                    {
                        for (ATexture texture : defaultmaterial_shirt_g.getTextureList()) {
                            defaultmaterial_shirt_g.removeTexture(texture);
                            shirt_girl.getChildByName("shirt").getMaterial().removeTexture(texture);// Does not free GLES memory.
                            // Frees GLES memory
                            texture = null;                             // Does not free GLES memory.
                        }
                        Material defaultmaterial = new Material();
                        defaultmaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
                        try {
                            defaultmaterial.addTexture(new Texture("de", reportData.getImageshirt()));
                            defaultmaterial.setColorInfluence(0);

                            shirt_girl.getChildByName("shirt").setMaterial(defaultmaterial);
                        } catch (ATexture.TextureException e) {
                            e.printStackTrace();
                        }
                    }else {
                        for (ATexture texture : lady_pant.getTextureList()) {
                            lady_pant.removeTexture(texture);
                            girl_trouser.getChildByName("pant").getMaterial().removeTexture(texture);// Does not free GLES memory.
                            // Frees GLES memory
                            texture = null;                             // Does not free GLES memory.
                        }
                        Material defaultmaterial = new Material();
                        defaultmaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
                        try {
                            defaultmaterial.addTexture(new Texture("de", reportData.getImageshirt()));
                            defaultmaterial.setColorInfluence(0);

                            girl_trouser.getChildByName("pant").setMaterial(defaultmaterial);
                        } catch (ATexture.TextureException e) {
                            e.printStackTrace();
                        }
                    }

                }

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
            imageView = itemView.findViewById(R.id.images_1);
        }
    }
}


