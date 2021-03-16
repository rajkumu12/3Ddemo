package com.survey.human3d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.survey.human3d.Adapters.ShirtsAdapters;
import com.survey.human3d.Interfaces.Pick;
import com.survey.human3d.Model.ShirtsModel;


import java.util.ArrayList;
import java.util.List;

import static com.survey.human3d.GalaXyFragment.body;
import static com.survey.human3d.GalaXyFragment.girl;
import static com.survey.human3d.GalaXyFragment.girl_lowerpart;
import static com.survey.human3d.GalaXyFragment.legs_male;
import static com.survey.human3d.GalaXyFragment.pants_boy;


public class MainActivity extends AppCompatActivity implements Pick {

    List<ShirtsModel> arraylist;
    RecyclerView recyclerView_list_shirts;
    ArrayList<Integer> arr;
    ArrayList<Integer> arr_ladies;
    ArrayList<String> typelit;
    String[] gender = {"Male", "Female"};
    String[] male = {"Height", "Chest", "Hips", "Weist"};
    String[] female = {"Height", "Weist", "Hips", "Breast"};
    String type_body;

    Spinner spnr_gender;
    Spinner spnr_gender_type;
    ImageView aer_up, aero_down;
    public static String gen;
    RelativeLayout r1, r2;

    private Rect outRect = new Rect();
    private int[] location = new int[2];
    private boolean mIsScrolling = false;
    TextView textView_done;
    ImageView minus, plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        recyclerView_list_shirts = findViewById(R.id.recyclerview_shirts);

        spnr_gender = findViewById(R.id.spinner_gender);
        spnr_gender_type = findViewById(R.id.spinner_genr_sbodyparts);
        r1 = findViewById(R.id.rel_root);
        r2 = findViewById(R.id.rel_root2);
        minus = findViewById(R.id.img_minus);
        plus = findViewById(R.id.img_plus);
        r2.setOnTouchListener(new MoveViewTouchListener(r1));

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spnr_gender.setAdapter(aa);

        typelit = new ArrayList<>();
        typelit.add("shirt");
        typelit.add("shirt");
        typelit.add("shirt");
        typelit.add("paint");

        arr = new ArrayList<>();
        arr.add(R.drawable.s_h);
        arr.add(R.drawable.s_t);
        arr.add(R.drawable.sh);
        arr.add(R.drawable.painttexture);


        arr_ladies = new ArrayList<>();
        arr_ladies.add(R.drawable.one_l);
        arr_ladies.add(R.drawable.two);
        arr_ladies.add(R.drawable.femaletex);
        arr_ladies.add(R.drawable.girlpantstex);

        spnr_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gen = gender[position];
                if (gen.equals("Male")) {
                    LoadData(arr);
                    ArrayAdapter aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, male);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    spnr_gender_type.setAdapter(aa);
                } else {
                    LoadData(arr_ladies);
                    ArrayAdapter aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, female);
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
                if (gen.equals("Male")) {
                    type_body = male[position];
                    Log.d("lkjfzdjklfjkjhf", "hhh" + type_body);
                } else {
                    type_body = female[position];
                    Log.d("lkjfzdjklfjkjhf", "hhh" + type_body);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gen.equals("Male")) {
                    if (type_body.equals("Height")) {
                        body.setScale(body.getScaleX() + 0.001, body.getScaleY() + 0.002, body.getScaleZ() + 0);
                        legs_male.setScale(legs_male.getScaleX() + 0.001, legs_male.getScaleY() + 0.001, legs_male.getScaleZ() + 0);
                    } else if (type_body.equals("Chest")) {
                        body.setScale(body.getScaleX() + 0.0015, body.getScaleY() + 0, body.getScaleZ() + 0.004);
                        legs_male.setScale(legs_male.getScaleX() + 0.0015, legs_male.getScaleY() + 0, legs_male.getScaleZ() + 0.002);
                    } else if (type_body.equals("Hips")) {
                        body.setScale(body.getScaleX() + 0.001, body.getScaleY() + 0, body.getScaleZ() + 0.004);
                        legs_male.setScale(legs_male.getScaleX() + 0.001, legs_male.getScaleY() + 0, legs_male.getScaleZ() + 0.004);
                    } else if (type_body.equals("Weist")) {
                        body.setScale(body.getScaleX() + 0.002, body.getScaleY() + 0.000, body.getScaleZ() + 0.0015);
                        legs_male.setScale(legs_male.getScaleX() + 0.002, legs_male.getScaleY() + 0.000, legs_male.getScaleZ() + 0.0015);
                    }
                  /*  for (int i=0;i<GalaXyFragment.legs_male.getNumChildren();i++){
                        Log.d("lkjflkkkfffff","hhh"+GalaXyFragment.legs_male.getChildAt(i).getName());
                    }*/
                  /*  legs_male.setScale(legs_male.getScaleX()+0.005, legs_male.getScaleY()+0, legs_male.getScaleZ()+0.005);
                    body.setScale(body.getScaleX()+0.01, body.getScaleY()+0, body.getScaleZ()+0.01);*/
                    /* pants_boy.setPosition(-12,1,5);*/
                } else {
                    if (type_body.equals("Height")) {
                        girl.setScale(girl.getScaleX() + 0.001, girl.getScaleY() + 0.002, girl.getScaleZ() + 0);
                        girl_lowerpart.setScale(girl_lowerpart.getScaleX() + 0.001, girl_lowerpart.getScaleY() + 0.001, girl_lowerpart.getScaleZ() + 0);
                    } else if (type_body.equals("Weist")) {
                        girl.setScale(girl.getScaleX() + 0.002, girl.getScaleY() + 0.000, girl.getScaleZ() + 0.0015);
                        girl_lowerpart.setScale(girl_lowerpart.getScaleX() + 0.002, girl_lowerpart.getScaleY() + 0.000, girl_lowerpart.getScaleZ() + 0.0015);
                    } else if (type_body.equals("Hips")) {
                        girl.setScale(girl.getScaleX() + 0.001, girl.getScaleY() + 0, girl.getScaleZ() + 0.004);
                        girl_lowerpart.setScale(girl_lowerpart.getScaleX() + 0.001, girl_lowerpart.getScaleY() + 0, girl_lowerpart.getScaleZ() + 0.004);
                    } else if (type_body.equals("Breast")) {
                        girl.setScale(girl.getScaleX() + 0.0015, girl.getScaleY() + 0, girl.getScaleZ() + 0.004);
                        girl_lowerpart.setScale(girl_lowerpart.getScaleX() + 0.0015, girl_lowerpart.getScaleY() + 0, girl_lowerpart.getScaleZ() + 0.001);
                    }/*else if (type_body.equals("Shoulder")){
                        girl.setScale(girl.getScaleX()+0.001, girl.getScaleY()+0.002, girl.getScaleZ()+0);
                        girl_lowerpart.setScale(girl_lowerpart.getScaleX()+0.001, girl_lowerpart.getScaleY()+0.001, girl_lowerpart.getScaleZ()+0);
                    }*/

                  /*  girl.setScale(girl.getScaleX()+0.01, girl.getScaleY()+0, girl.getScaleZ()+0.01);
                    girl_lowerpart.setScale(girl_lowerpart.getScaleX()+0.005, girl_lowerpart.getScaleY()+0, girl_lowerpart.getScaleZ()+0.005);*/
                }
            }
        });


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gen.equals("Male")) {
                    if (type_body.equals("Height")) {
                        body.setScale(body.getScaleX() - 0.001, body.getScaleY() - 0.002, body.getScaleZ() - 0);
                        legs_male.setScale(legs_male.getScaleX() - 0.001, legs_male.getScaleY() - 0.001, legs_male.getScaleZ() - 0);
                    } else if (type_body.equals("Chest")) {
                        body.setScale(body.getScaleX() - 0.0015, body.getScaleY() - 0, body.getScaleZ() - 0.004);
                        legs_male.setScale(legs_male.getScaleX() - 0.0015, legs_male.getScaleY() - 0, legs_male.getScaleZ() - 0.002);
                    } else if (type_body.equals("Hips")) {
                        body.setScale(body.getScaleX() - 0.001, body.getScaleY() - 0, body.getScaleZ() - 0.004);
                        legs_male.setScale(legs_male.getScaleX() - 0.001, legs_male.getScaleY() - 0, legs_male.getScaleZ() - 0.004);
                    } else if (type_body.equals("Weist")) {
                        body.setScale(body.getScaleX() - 0.002, body.getScaleY() - 0.000, body.getScaleZ() - 0.0015);
                        legs_male.setScale(legs_male.getScaleX() - 0.002, legs_male.getScaleY() - 0.000, legs_male.getScaleZ() - 0.0015);
                    }
                  /*  for (int i=0;i<GalaXyFragment.legs_male.getNumChildren();i++){
                        Log.d("lkjflkkkfffff","hhh"+GalaXyFragment.legs_male.getChildAt(i).getName());
                    }*/
                  /*  legs_male.setScale(legs_male.getScaleX()-0.01, legs_male.getScaleY()+0, legs_male.getScaleZ()+0);
                    body.setScale(body.getScaleX()-0.01, body.getScaleY()+0, body.getScaleZ()+0);*/
                    /* pants_boy.setPosition(-12,1,5);*/
                } else {
                    if (type_body.equals("Height")) {
                        girl.setScale(girl.getScaleX() - 0.001, girl.getScaleY() - 0.002, girl.getScaleZ() - 0);
                        girl_lowerpart.setScale(girl_lowerpart.getScaleX() - 0.001, girl_lowerpart.getScaleY() - 0.001, girl_lowerpart.getScaleZ() - 0);
                    } else if (type_body.equals("Weist")) {
                        girl.setScale(girl.getScaleX() - 0.002, girl.getScaleY() - 0.000, girl.getScaleZ() - 0.0015);
                        girl_lowerpart.setScale(girl_lowerpart.getScaleX() - 0.002, girl_lowerpart.getScaleY() - 0.000, girl_lowerpart.getScaleZ() - 0.0015);
                    } else if (type_body.equals("Hips")) {
                        girl.setScale(girl.getScaleX() - 0.001, girl.getScaleY() - 0, girl.getScaleZ() - 0.004);
                        girl_lowerpart.setScale(girl_lowerpart.getScaleX() - 0.001, girl_lowerpart.getScaleY() - 0, girl_lowerpart.getScaleZ() - 0.004);
                    } else if (type_body.equals("Breast")) {
                        girl.setScale(girl.getScaleX() - 0.001, girl.getScaleY() - 0, girl.getScaleZ() - 0.004);
                        girl_lowerpart.setScale(girl_lowerpart.getScaleX() - 0.001, girl_lowerpart.getScaleY() - 0, girl_lowerpart.getScaleZ() - 0.001);
                    }

                    /*girl.setScale(girl.getScaleX()-0.01, girl.getScaleY()+0, girl.getScaleZ()+0);
                    girl_lowerpart.setScale(girl_lowerpart.getScaleX()-0.005, girl_lowerpart.getScaleY()+0, girl_lowerpart.getScaleZ()+0);*/
                }
            }
        });
    }

    private void LoadData(ArrayList<Integer> arr) {
        arraylist = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            ShirtsModel shirtsModel = new ShirtsModel();
            shirtsModel.setImageshirt(arr
                    .get(i));
            shirtsModel.setClothtype(typelit.get(i));
            arraylist.add(shirtsModel);
        }
        ShirtsAdapters topPicksAdapter = new ShirtsAdapters(MainActivity.this, arraylist, this);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_list_shirts.setLayoutManager(layoutManager2);
                            /*  int spacingInPixels = Objects.requireNonNull(getContext()).getResources().getDimensionPixelSize(R.dimen.spacing);
                                recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));*/
        recyclerView_list_shirts.setItemAnimator(new DefaultItemAnimator());
        recyclerView_list_shirts.setAdapter(topPicksAdapter);
        ShirtsModel shirtsModel = arraylist.get(0);
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


    public class MoveViewTouchListener implements View.OnTouchListener {
        private GestureDetector mGestureDetector;
        private View mView;

        public MoveViewTouchListener(View view) {
            mGestureDetector = new GestureDetector(view.getContext(), mGestureListener);
            mView = view;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
           /* if (event.getAction() == MotionEvent.ACTION_UP ) {
                r1.setVisibility(View.GONE);

                mIsScrolling = false;
            }*/
            if (mGestureDetector.onTouchEvent(event)) {
                return true;
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (mIsScrolling) {
                    mIsScrolling = false;

                }
            }
            return false;
        }

        private GestureDetector.OnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {
            private float mMotionDownX, mMotionDownY;

            @Override
            public boolean onDown(MotionEvent e) {
                mMotionDownX = e.getRawX() - mView.getTranslationX();
                mMotionDownY = e.getRawY() - mView.getTranslationY();
                return true;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                mIsScrolling = true;

                mView.setTranslationX(e2.getRawX() - mMotionDownX);
                mView.setTranslationY(e2.getRawY() - mMotionDownY);
                return true;
            }
        };
    }

    private boolean isViewInBounds(View view, int x, int y) {
        view.getDrawingRect(outRect);
        view.getLocationOnScreen(location);
        outRect.offset(location[0], location[1]);
        return outRect.contains(x, y);
    }
}


