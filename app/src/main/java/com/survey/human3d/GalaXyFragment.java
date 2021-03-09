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
    public static Object3D body,shirt;
    int texturesshirt;
  public static   Material defaultmaterial;

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

            int fogColor = 0xFF3700B3;


            getCurrentScene().setBackgroundColor(fogColor);


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






                /*Material r_hand = new Material();
                r_hand.enableLighting(true);
                r_hand.setDiffuseMethod(new DiffuseMethod.Lambert());
                r_hand.addTexture(new Texture("de", R.drawable.skin));
                r_hand.setColorInfluence(0);
                body.getChildByName("r_hank").setMaterial(r_hand);
                body.setPosition(0,0,0);


                Material l_hand = new Material();
                l_hand.enableLighting(true);
                l_hand.setDiffuseMethod(new DiffuseMethod.Lambert());
                l_hand.addTexture(new Texture("de", R.drawable.skin));
                l_hand.setColorInfluence(0);
                body.getChildByName("l_hand").setMaterial(l_hand);
                body.setPosition(0,0,0);


                Material def = new Material();
                def.enableLighting(true);
                def.setDiffuseMethod(new DiffuseMethod.Lambert());
                def.addTexture(new Texture("de", R.drawable.skin));
                def.setColorInfluence(0);
                body.getChildByName("default").setMaterial(def);
                body.setPosition(0,0,0);

                Material facemath = new Material();
                facemath.enableLighting(true);
                facemath.setDiffuseMethod(new DiffuseMethod.Lambert());
                facemath.addTexture(new Texture("de", R.drawable.skin));
                facemath.setColorInfluence(0);
                body.getChildByName("face").setMaterial(facemath);
                body.setPosition(0,0,0);


                Material chestmat = new Material();
                chestmat.enableLighting(true);
                chestmat.setDiffuseMethod(new DiffuseMethod.Lambert());
                chestmat.addTexture(new Texture("de", R.drawable.skin));
                chestmat.setColorInfluence(0);
                body.getChildByName("chest").setMaterial(chestmat);
                body.getChildByName("chest").setScale(body.getChildByName("chest").getScaleX()+1,body.getChildByName("chest").getScaleY()+1,body.getChildByName("chest").getScaleZ()+1);
                Log.d("lllllllllll","vhest"+body.getChildByName("chest").getScale());


                Material defaultmaterial = new Material();
                defaultmaterial.enableLighting(true);
                defaultmaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
                defaultmaterial.addTexture(new Texture("de", R.drawable.skin));
                defaultmaterial.setColorInfluence(0);
                body.getChildByName("r_L_bow").setMaterial(defaultmaterial);

                Material stomach = new Material();
                stomach.enableLighting(true);
                stomach.setDiffuseMethod(new DiffuseMethod.Lambert());
                stomach.addTexture(new Texture("de", R.drawable.skin));
                stomach.setColorInfluence(0);
                body.getChildByName("stomach").setMaterial(stomach);
                body.setPosition(0,0,0);

                Material ll_bow = new Material();
                ll_bow.enableLighting(true);
                ll_bow.setDiffuseMethod(new DiffuseMethod.Lambert());
                ll_bow.addTexture(new Texture("de", R.drawable.skin));
                ll_bow.setColorInfluence(0);
                body.getChildByName("L_lbow").setMaterial(ll_bow);
                body.setPosition(0,0,0);


                Material L_shoulder = new Material();
                L_shoulder.enableLighting(true);
                L_shoulder.setDiffuseMethod(new DiffuseMethod.Lambert());
                L_shoulder.addTexture(new Texture("de", R.drawable.skin));
                L_shoulder.setColorInfluence(0);
                body.getChildByName("L_shoulder").setMaterial(L_shoulder);
                body.setPosition(0,0,0);*/

                Material body1 = new Material();
                body1.enableLighting(true);
                body1.setDiffuseMethod(new DiffuseMethod.Lambert());
                body1.addTexture(new Texture("de", R.drawable.skin));
                body1.setColorInfluence(0);
                body.getChildByName("body").setMaterial(body1);


                /*body.getChildByName("body").addChild(shirt);*/
                body.setPosition(0,0,0);
                Log.d("kjfdkldjfkldf","fff"+body.getScale());


            } catch (Exception e) {
                e.printStackTrace();
            }

            ArcballCamera arcball = new ArcballCamera(mContext, ((Activity)mContext).findViewById(R.id.lly));
            arcball.setTarget(body);
            arcball.setFieldOfView(30);
            arcball.moveForward(-50);
            arcball.setProjectionMatrix(700,800);
            arcball.setPosition(0, 280, 360);
            arcball.setCameraYaw(0);
            arcball.setFarPlane(12000);
            Log.d("jslkjfdlkfgj","again");
            getCurrentScene().replaceAndSwitchCamera(getCurrentCamera(), arcball);


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

        }
    }



}
