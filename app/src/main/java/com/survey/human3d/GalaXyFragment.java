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

    int texturesshirt;

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
        private Object3D body,shirt;

        public FogRenderer(Context context) {
            super(context);
        }


        @Override
        protected void initScene() {
            mLight = new DirectionalLight(0, -10, -10);
            mLight.setPower(2f);
            getCurrentScene().addLight(mLight);

            int fogColor = 0xFF3700B3;


            getCurrentScene().setBackgroundColor(fogColor);


            LoaderOBJ objParser2 = new LoaderOBJ(mContext.getResources(),
                    mTextureManager, R.raw.shhh);
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
              Material defaultmaterial = new Material();
                defaultmaterial.enableLighting(true);
                defaultmaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
                defaultmaterial.addTexture(new Texture("de",texturesshirt));
                Log.d("gfgfcgfgsgg",""+texturesshirt);
               defaultmaterial.setColorInfluence(0);
                shirt.getChildByName("shirt1").setMaterial(defaultmaterial);
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

                Material defaultmaterial = new Material();
                defaultmaterial.enableLighting(true);
                defaultmaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
                defaultmaterial.addTexture(new Texture("de", R.drawable.skin));
                defaultmaterial.setColorInfluence(0);
                body.getChildByName("body").setMaterial(defaultmaterial);
                body.getChildByName("body").addChild(shirt);
                body.setPosition(0,0,0);


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
