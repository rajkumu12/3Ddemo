package com.survey.human3d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.survey.human3d.Adapters.ShirtsAdapters;
import com.survey.human3d.Interfaces.Pick;
import com.survey.human3d.Model.ShirtsModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Pick {

    List<ShirtsModel>arraylist;
    RecyclerView recyclerView_list_shirts;
    ArrayList<Integer>arr;
    ArrayList<Integer>arr_ladies;
    ArrayList<String>typelit;
    String[] gender = { "Male", "Female"};
    String[] male = { "Chest", "Shoulder"};
    String[] female = { "Breast", "Female"};

    Spinner spnr_gender;
    Spinner spnr_gender_type;
    public static String gen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        recyclerView_list_shirts=findViewById(R.id.recyclerview_shirts);

        spnr_gender=findViewById(R.id.spinner_gender);
        spnr_gender_type=findViewById(R.id.spinner_genr_sbodyparts);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spnr_gender.setAdapter(aa);


        typelit=new ArrayList<>();
        typelit.add("shirt");
        typelit.add("shirt");
        typelit.add("shirt");
        typelit.add("paint");

        arr=new ArrayList<>();
        arr.add(R.drawable.s_h);
        arr.add(R.drawable.s_t);
        arr.add(R.drawable.sh);
        arr.add(R.drawable.painttexture);


        arr_ladies=new ArrayList<>();
        arr_ladies.add(R.drawable.one_l);
        arr_ladies.add(R.drawable.two);
        arr_ladies.add(R.drawable.femaletex);
        arr_ladies.add(R.drawable.girlpantstex);

        spnr_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gen=gender[position];
                if(gen.equals("Male")){
                    LoadData(arr);
                    ArrayAdapter aa = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,male);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    spnr_gender_type.setAdapter(aa);
                }else {
                    LoadData(arr_ladies);
                    ArrayAdapter aa = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,female);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    spnr_gender_type.setAdapter(aa);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnr_gender_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (gen.equals("Male")){
                    Log.d("lfjhgkfsjgfkjg","ffffff"+male[position]);

                }else {
                    Log.d("lfjhgkfsjgfkjg","ffffff"+female[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void LoadData(ArrayList<Integer> arr) {
        arraylist=new ArrayList<>();
        for (int i=0;i<arr.size();i++){
            ShirtsModel shirtsModel=new ShirtsModel();
            shirtsModel.setImageshirt(arr
                    .get(i));
            shirtsModel.setClothtype(typelit.get(i));
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