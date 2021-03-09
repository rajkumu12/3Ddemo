package com.survey.human3d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.survey.human3d.Adapters.ShirtsAdapters;
import com.survey.human3d.Interfaces.Pick;
import com.survey.human3d.Model.ShirtsModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Pick {

    List<ShirtsModel>arraylist;
    RecyclerView recyclerView_list_shirts;
    ArrayList<Integer>arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        recyclerView_list_shirts=findViewById(R.id.recyclerview_shirts);



        arr=new ArrayList<>();
        arr.add(R.drawable.s_h);
        arr.add(R.drawable.s_t);
        arr.add(R.drawable.sh);
        LoadData();
    }

    private void LoadData() {
        arraylist=new ArrayList<>();
        for (int i=0;i<arr.size();i++){
            ShirtsModel shirtsModel=new ShirtsModel();
            shirtsModel.setImageshirt(arr.get(i));
            arraylist.add(shirtsModel);
        }
        ShirtsAdapters topPicksAdapter = new ShirtsAdapters(MainActivity.this,arraylist,this);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL, true);
        recyclerView_list_shirts.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_list_shirts.setItemAnimator(new DefaultItemAnimator());
        recyclerView_list_shirts.setAdapter(topPicksAdapter);
        ShirtsModel shirtsModel=arraylist.get(0);
        loadFragment(new GalaXyFragment(shirtsModel.getImageshirt()));
    }


    public boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onSucess(int position) {
      /* ShirtsModel shirtsModel=arraylist.get(position);
        loadFragment(new GalaXyFragment(shirtsModel.getImageshirt()));
        Log.d("kjlkdsajflkdjfldjflkdf","ro"+position);*/
    }
}