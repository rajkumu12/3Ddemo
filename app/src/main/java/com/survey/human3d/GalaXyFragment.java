package com.survey.human3d;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.survey.human3d.Interfaces.Pick;

import org.rajawali3d.Object3D;
import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.lights.PointLight;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.util.OnObjectPickedListener;

public class GalaXyFragment extends MY3DFragment implements View.OnTouchListener {
    Button mMoveForward;
    Button mMoveRight;
    Button mMoveUp;
    private Vector3 mCameraOffset;
    public static Object3D body,shirt,legs_male,pants_boy,girl,shirt_girl,girl_lowerpart,girl_trouser;
    int texturesshirt;
  public static   Material defaultmaterial;
  public static   Material defaultmaterial_shirt_g;
  public static   Material defaultmaterial_pant;
  public static   Material lady_pant;

    public GalaXyFragment(int imageshirt) {
        this.texturesshirt=imageshirt;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ((View) mRajawaliSurface).setOnTouchListener(this);


        return mLayout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mCameraOffset = new Vector3();
        super.onCreate(savedInstanceState);
    }
    @Override
    public MY3DFragment.AExampleRenderer createRenderer() {
        return new FogRenderer(getActivity());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            Log.d("hjdfjfhdf","fffff"+((FogRenderer) mRenderer).getCurrentScene().getCamera().getScale());
        }


        return false;
    }


  /*  @Override
    protected void onBeforeApplyRenderer() {
        mRajawaliSurface.setAntiAliasingMode(ISurface.ANTI_ALIASING_CONFIG.MULTISAMPLING);
        mRajawaliSurface.setSampleCount(2);
        super.onBeforeApplyRenderer();
    }*/

    private final class FogRenderer extends AExampleRenderer implements OnObjectPickedListener, View.OnTouchListener{
        private DirectionalLight mLight;
        PointLight pointLight;


        public FogRenderer(Context context) {
            super(context);
        }


        @Override
        protected void initScene() {
            mLight = new DirectionalLight(0, -10, -10);
            mLight.setPower(1.5f);
            getCurrentScene().addLight(mLight);

            Log.d("kjdgglkfjlkjjj","okkk"+MainActivity.gen);
            int fogColor = 0xFFFFFF;

            getCurrentScene().setBackgroundColor(fogColor);


            if (MainActivity.gen.equals("Male")){
                LoaderOBJ objParser2 = new LoaderOBJ(mContext.getResources(),
                        mTextureManager, R.raw.shirt_six);
                try {
                    objParser2.parse();
                    shirt = objParser2.getParsedObject();

                    shirt.setZ(20);
                    shirt.setRotY(0);
                    shirt.setTransparent(false);

                    Log.d("hgfdkhfghkdgf","position"+shirt.getPosition()+"hwp"+shirt.getWorldPosition()+"Sce"+shirt.getScenePosition());
                    getCurrentScene().addChild(shirt);

                    for (int i=0;i<shirt.getNumChildren();i++){
                        Log.d("jhdjsahfdkjha","jkf"+shirt.getChildAt(i).getName());
                        Log.d("jhdjsahfdkjha","jkf"+shirt.getChildAt(i).getNumChildren());
                        for (int j=0;j<shirt.getChildAt(i).getNumChildren();j++){
                            Log.d("jhdjsahfdkjha","jkm"+shirt.getChildAt(i).getChildAt(j).getName());
                        }

                    }
                    defaultmaterial = new Material();
                    defaultmaterial.enableLighting(true);
                    defaultmaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
                    defaultmaterial.addTexture(new Texture("de",texturesshirt));
                    Log.d("gfgfcgfgsgg",""+texturesshirt);
                    defaultmaterial.setColorInfluence(0);
                    shirt.getChildByName("shirt").setMaterial(defaultmaterial);
                    shirt.setPosition(0,0,0);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                LoaderOBJ objParser_pant = new LoaderOBJ(mContext.getResources(),
                        mTextureManager, R.raw.pant);
                try {
                    objParser_pant.parse();
                    pants_boy = objParser_pant.getParsedObject();

                    pants_boy.setZ(20);
                    pants_boy.setRotY(0);
                    pants_boy.setTransparent(false);

                    Log.d("hgfdkhfghkdgf","position"+pants_boy.getPosition()+"hwp"+pants_boy.getWorldPosition()+"Sce"+pants_boy.getScenePosition());
                    getCurrentScene().addChild(pants_boy);

                    for (int i=0;i<pants_boy.getNumChildren();i++){
                        Log.d("jhdjsahfdkjha","jkf"+pants_boy.getChildAt(i).getName());
                        Log.d("jhdjsahfdkjha","jkf"+pants_boy.getChildAt(i).getNumChildren());
                        for (int j=0;j<pants_boy.getChildAt(i).getNumChildren();j++){
                            Log.d("jhdjsahfdkjha","jkm"+pants_boy.getChildAt(i).getChildAt(j).getName());
                        }

                    }
                    defaultmaterial_pant = new Material();
                    defaultmaterial_pant.enableLighting(true);
                    defaultmaterial_pant.setDiffuseMethod(new DiffuseMethod.Lambert());
                    defaultmaterial_pant.addTexture(new Texture("de",R.drawable.painttexture));
                    /* Log.d("gfgfcgfgsgg",""+texturesshirt);*/
                    defaultmaterial_pant.setColorInfluence(0);
                    pants_boy.getChildByName("pant").setMaterial(defaultmaterial_pant);
                    pants_boy.setPosition(-12,1,5);
                    pants_boy.setScale(1,0.9,1.1);
                    /* pants_boy.setPosition(0,0,0);*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LoaderOBJ objParser3 = new LoaderOBJ(mContext.getResources(),
                        mTextureManager, R.raw.male_legs);
                try {
                    objParser3.parse();
                    legs_male = objParser3.getParsedObject();

                    legs_male.setZ(-20);
                    legs_male.setRotY(0);

                    getCurrentScene().addChild(legs_male);

                    for (int i=0;i<legs_male.getNumChildren();i++){
                        Log.d("jhdjsahfdkjha","jkf"+legs_male.getChildAt(i).getName());
                        Log.d("jhdjsahfdkjha","jkf"+legs_male.getChildAt(i).getNumChildren());
                        for (int j=0;j<legs_male.getChildAt(i).getNumChildren();j++){
                            Log.d("jhdjsahfdkjha","jkm"+legs_male.getChildAt(i).getChildAt(j).getName());
                        }

                    }
                    Material leg_mat = new Material();
                    leg_mat.enableLighting(true);
                    leg_mat.setDiffuseMethod(new DiffuseMethod.Lambert());
                    leg_mat.addTexture(new Texture("de", R.drawable.skin_fair));
                    leg_mat.setColorInfluence(0);
                    legs_male.getChildByName("legs").setMaterial(leg_mat);


                    legs_male.setPosition(15,-80,-24);
                    legs_male.addChild(pants_boy);
                    legs_male.setScale(1.2,1,1);




                } catch (Exception e) {
                    e.printStackTrace();
                }

                LoaderOBJ objParser1 = new LoaderOBJ(mContext.getResources(),
                        mTextureManager, R.raw.body);
                try {
                    objParser1.parse();
                    body = objParser1.getParsedObject();

                    body.setZ(-20);
                    body.setRotY(0);


                    Log.d("hgfdkhfghkdgf","position"+body.getPosition()+"hwp"+body.getWorldPosition()+"Sce"+body.getScenePosition());
                    getCurrentScene().addChild(body);

                    for (int i=0;i<body.getNumChildren();i++){
                        Log.d("jhdjsahfdkjha","jkf"+body.getChildAt(i).getName());
                        Log.d("jhdjsahfdkjha","jkf"+body.getChildAt(i).getNumChildren());
                        for (int j=0;j<body.getChildAt(i).getNumChildren();j++){
                            Log.d("jhdjsahfdkjha","jkm"+body.getChildAt(i).getChildAt(j).getName());
                        }

                    }
                    Material body1 = new Material();
                    body1.enableLighting(true);
                    body1.setDiffuseMethod(new DiffuseMethod.Lambert());
                    body1.addTexture(new Texture("de", R.drawable.skin));
                    body1.setColorInfluence(0);
                    body.getChildByName("body").setMaterial(body1);
                    body.addChild(shirt);

                    Log.d("kjfdkldjfkldf","fff"+body.getScale());


                } catch (Exception e) {
                    e.printStackTrace();
                }





                ArcballCamera arcball = new ArcballCamera(mContext, ((Activity)mContext).findViewById(R.id.lly));
                arcball.setTarget(shirt);
                arcball.setFieldOfView(30);
                arcball.moveForward(-50);
                arcball.setProjectionMatrix(700,800);
                arcball.setPosition(0, 280, 360);
                arcball.setCameraYaw(0);
                arcball.setFarPlane(12000);
                Log.d("jslkjfdlkfgj","again");
                getCurrentScene().replaceAndSwitchCamera(getCurrentCamera(), arcball);

            }else {
                Log.d("lkjdlskjkdlkjdjdjdjd","lol"+MainActivity.gen);


                LoaderOBJ objParser_pant_fem = new LoaderOBJ(mContext.getResources(),
                        mTextureManager, R.raw.girls_pant);
                try {
                    objParser_pant_fem.parse();
                    girl_trouser = objParser_pant_fem.getParsedObject();

                    girl_trouser.setZ(20);
                    girl_trouser.setRotY(0);
                    girl_trouser.setTransparent(false);

                    // Log.d("hgfdkhfghkdgf","position"+shirt.getPosition()+"hwp"+shirt.getWorldPosition()+"Sce"+shirt.getScenePosition());
                    getCurrentScene().addChild(girl_trouser);

                    for (int i=0;i<girl_trouser.getNumChildren();i++){
                        Log.d("jhdjsahfdkjha","jkf"+girl_trouser.getChildAt(i).getName());
                        Log.d("jhdjsahfdkjha","jkf"+girl_trouser.getChildAt(i).getNumChildren());
                        for (int j=0;j<girl_trouser.getChildAt(i).getNumChildren();j++){
                            Log.d("jhdjsahfdkjha","jkm"+girl_trouser.getChildAt(i).getChildAt(j).getName());
                        }
                    }
                   lady_pant= new Material();
                    lady_pant.enableLighting(true);
                    lady_pant.setDiffuseMethod(new DiffuseMethod.Lambert());
                    lady_pant.addTexture(new Texture("de",R.drawable.girlpantstex));
                    Log.d("gfgfcgfgsgg",""+texturesshirt);
                    lady_pant.setColorInfluence(0);
                    girl_trouser.getChildByName("pant").setMaterial(lady_pant);
                    girl_trouser.setPosition(0,0,0);
                    girl_trouser.setScale(1,1,1);
                } catch (Exception e) {
                    e.printStackTrace();
                }



                LoaderOBJ objParser_lowerfem = new LoaderOBJ(mContext.getResources(),
                        mTextureManager, R.raw.lower_female);
                try {
                    objParser_lowerfem.parse();
                    girl_lowerpart = objParser_lowerfem.getParsedObject();

                    girl_lowerpart.setZ(20);
                    girl_lowerpart.setRotY(0);
                    girl_lowerpart.setTransparent(false);

                    // Log.d("hgfdkhfghkdgf","position"+shirt.getPosition()+"hwp"+shirt.getWorldPosition()+"Sce"+shirt.getScenePosition());
                    getCurrentScene().addChild(girl_lowerpart);

                    for (int i=0;i<girl_lowerpart.getNumChildren();i++){
                        Log.d("jhdjsahfdkjha","jkf"+girl_lowerpart.getChildAt(i).getName());
                        Log.d("jhdjsahfdkjha","jkf"+girl_lowerpart.getChildAt(i).getNumChildren());
                        for (int j=0;j<girl_lowerpart.getChildAt(i).getNumChildren();j++){
                            Log.d("jhdjsahfdkjha","jkm"+girl_lowerpart.getChildAt(i).getChildAt(j).getName());
                        }
                    }
                    Material lowerlady = new Material();
                    lowerlady.enableLighting(true);
                    lowerlady.setDiffuseMethod(new DiffuseMethod.Lambert());
                    lowerlady.addTexture(new Texture("de",R.drawable.skin));
                    Log.d("gfgfcgfgsgg",""+texturesshirt);
                    lowerlady.setColorInfluence(0);
                    girl_lowerpart.getChildByName("legs").setMaterial(lowerlady);
                    girl_lowerpart.setPosition(0,-90,-2);
                    girl_lowerpart.addChild(girl_trouser);
                } catch (Exception e) {
                    e.printStackTrace();
                }





                LoaderOBJ objParser_dress = new LoaderOBJ(mContext.getResources(),
                        mTextureManager, R.raw.female_shirts);
                try {
                    objParser_dress.parse();
                    shirt_girl = objParser_dress.getParsedObject();

                    shirt_girl.setZ(20);
                    shirt_girl.setRotY(0);
                    shirt_girl.setTransparent(false);

                   // Log.d("hgfdkhfghkdgf","position"+shirt.getPosition()+"hwp"+shirt.getWorldPosition()+"Sce"+shirt.getScenePosition());
                    getCurrentScene().addChild(shirt_girl);

                    for (int i=0;i<shirt_girl.getNumChildren();i++){
                        Log.d("jhdjsahfdkjha","jkf"+shirt_girl.getChildAt(i).getName());
                        Log.d("jhdjsahfdkjha","jkf"+shirt_girl.getChildAt(i).getNumChildren());
                        for (int j=0;j<shirt_girl.getChildAt(i).getNumChildren();j++){
                            Log.d("jhdjsahfdkjha","jkm"+shirt_girl.getChildAt(i).getChildAt(j).getName());
                        }

                    }
                    defaultmaterial_shirt_g = new Material();
                    defaultmaterial_shirt_g.enableLighting(true);
                    defaultmaterial_shirt_g.setDiffuseMethod(new DiffuseMethod.Lambert());
                    defaultmaterial_shirt_g.addTexture(new Texture("de",texturesshirt));
                    Log.d("gfgfcgfgsgg",""+texturesshirt);
                    defaultmaterial_shirt_g.setColorInfluence(0);
                    shirt_girl.getChildByName("shirt").setMaterial(defaultmaterial_shirt_g);
                    shirt_girl.setPosition(0,0,0);
                } catch (Exception e) {
                    e.printStackTrace();
                }



                LoaderOBJ objParser_girls = new LoaderOBJ(mContext.getResources(),
                        mTextureManager, R.raw.gillllllllll);
                try {
                    objParser_girls.parse();
                    girl = objParser_girls.getParsedObject();

                    girl.setZ(20);
                    girl.setRotY(0);
                    girl.setTransparent(false);

                    Log.d("hgfdkhfghkdgf","position"+girl.getPosition()+"hwp"+girl.getWorldPosition()+"Sce"+girl.getScenePosition());
                    getCurrentScene().addChild(girl);

                    for (int i=0;i<girl.getNumChildren();i++){
                        Log.d("jhdjsahfdkjha","jkf"+girl.getChildAt(i).getName());
                        Log.d("jhdjsahfdkjha","jkf"+girl.getChildAt(i).getNumChildren());
                        for (int j=0;j<girl.getChildAt(i).getNumChildren();j++){
                            Log.d("jhdjsahfdkjha","jkm"+girl.getChildAt(i).getChildAt(j).getName());
                        }
                    }


                    Material girlmat8 = new Material();
                    girlmat8.enableLighting(true);
                    girlmat8.setDiffuseMethod(new DiffuseMethod.Lambert());
                    girlmat8.addTexture(new Texture("de",R.drawable.skin));
                    /*Log.d("gfgfcgfgsgg",""+texturesshirt);*/
                    girlmat8.setColorInfluence(0);
                    girl.getChildByName("female").setMaterial(girlmat8);
                    girl.addChild(shirt_girl);
                    girl.addChild(girl_lowerpart);

                    girl.setPosition(0,4,4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ArcballCamera arcball = new ArcballCamera(mContext, ((Activity)mContext).findViewById(R.id.lly));
                arcball.setTarget(girl);
                arcball.setFieldOfView(30);
                arcball.moveForward(-50);
                arcball.setProjectionMatrix(700,800);
                arcball.setPosition(0, 280, 360);
                arcball.setCameraYaw(0);
                arcball.setFarPlane(12000);
                Log.d("jslkjfdlkfgj","again");
                getCurrentScene().replaceAndSwitchCamera(getCurrentCamera(), arcball);


            }
        }


        public void ApplyTexture() throws ATexture.TextureException {
            Material defaultmaterial = new Material();
            defaultmaterial.enableLighting(true);
            defaultmaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
            defaultmaterial.addTexture(new Texture("de", R.drawable.s_th));
            defaultmaterial.setColorInfluence(0);
            shirt.getChildByName("shirt1").setMaterial(defaultmaterial);

        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }

        @Override
        public void onObjectPicked(Object3D object) {

            Log.d(";kjfdfjkfjfjf","ol"+object.getName());
        }
    }



}
