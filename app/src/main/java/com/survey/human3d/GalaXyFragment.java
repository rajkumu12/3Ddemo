package com.survey.human3d;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import org.rajawali3d.Object3D;
import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.lights.PointLight;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;

public class GalaXyFragment extends MY3DFragment implements View.OnTouchListener {
    Button mMoveForward;
    Button mMoveRight;
    Button mMoveUp;
    private Vector3 mCameraOffset;
    public GalaXyFragment() {
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
        return false;
    }

  /*  @Override
    protected void onBeforeApplyRenderer() {
        mRajawaliSurface.setAntiAliasingMode(ISurface.ANTI_ALIASING_CONFIG.MULTISAMPLING);
        mRajawaliSurface.setSampleCount(2);
        super.onBeforeApplyRenderer();
    }*/

    private final class FogRenderer extends AExampleRenderer{
        private DirectionalLight mLight;
        PointLight pointLight;
        private Object3D mRoad, earth,mars,mSphere;

        public FogRenderer(Context context) {
            super(context);
        }


        @Override
        protected void initScene() {
            mLight = new DirectionalLight(0, -10, -10);
            mLight.setPower(2.5f);
            getCurrentScene().addLight(mLight);

            int fogColor = 0x000000;

          /*  getCurrentScene().setBackgroundColor(R.drawable.galaxy);*/

            /*getCurrentScene().setFog(new FogMaterialPlugin.FogParams(FogMaterialPlugin.FogType.LINEAR, fogColor, 1, 15));*/

		/*	LoaderOBJ objParser = new LoaderOBJ(mContext.getResources(),
					mTextureManager, R.raw.road);
			try {
				objParser.parse();
				mRoad = objParser.getParsedObject();
				mRoad.setZ(5);
				mRoad.setRotY(180);
				Log.d("hgfdkhfghkdgf","position"+mRoad.getPosition()+"hwp"+mRoad.getWorldPosition()+"Sce"+mRoad.getScenePosition());

				getCurrentScene().addChild(mRoad);

				for (int i=0;i<mRoad.getNumChildren();i++){
					Log.d("jhdjsahfdkjha","jkf"+mRoad.getChildAt(i).getName());
				}

				Material roadMaterial = new Material();
				roadMaterial.enableLighting(true);
				roadMaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
				roadMaterial.addTexture(new Texture("roadTex", R.drawable.road));
				roadMaterial.setColorInfluence(0);
				mRoad.getChildByName("Road").setMaterial(roadMaterial);

				Material signMaterial = new Material();
				signMaterial.enableLighting(true);
				signMaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
				signMaterial.addTexture(new Texture("rajawaliSign", R.drawable.sign));
				signMaterial.setColorInfluence(0);
				mRoad.getChildByName("WarningSign").setMaterial(signMaterial);

				Material warningMaterial = new Material();
				warningMaterial.enableLighting(true);
				warningMaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
				warningMaterial.addTexture(new Texture("warning", R.drawable.warning));
				warningMaterial.setColorInfluence(0);
				mRoad.getChildByName("Warning").setMaterial(warningMaterial);
			} catch (Exception e) {
				e.printStackTrace();
			}*/
         /* mSphere = new Sphere(400, 8, 8);
            Material sphereMaterial = new Material();
            try {
                sphereMaterial.addTexture(new Texture("skySphere", R.drawable.skysphere));
                sphereMaterial.setColorInfluence(0);
            } catch (ATexture.TextureException e1) {
                e1.printStackTrace();
            }
            mSphere.setMaterial(sphereMaterial);
            mSphere.setDoubleSided(true);
            getCurrentScene().addChild(mSphere);*/

            getCurrentScene().setBackgroundColor(fogColor);

            LoaderOBJ objParser1 = new LoaderOBJ(mContext.getResources(),
                    mTextureManager, R.raw.ft_boby);
            try {
                objParser1.parse();
                earth = objParser1.getParsedObject();

                earth.setZ(20);

                earth.setRotY(0);

                Log.d("hgfdkhfghkdgf","position"+earth.getPosition()+"hwp"+earth.getWorldPosition()+"Sce"+earth.getScenePosition());
                getCurrentScene().addChild(earth);

                for (int i=0;i<earth.getNumChildren();i++){
                    Log.d("jhdjsahfdkjha","jkf"+earth.getChildAt(i).getName());
                    Log.d("jhdjsahfdkjha","jkf"+earth.getChildAt(i).getNumChildren());
                    for (int j=0;j<earth.getChildAt(i).getNumChildren();j++){
                        Log.d("jhdjsahfdkjha","jkm"+earth.getChildAt(i).getChildAt(j).getName());
                    }

                }
           Material defaultmaterial = new Material();
                defaultmaterial.enableLighting(true);
                defaultmaterial.setDiffuseMethod(new DiffuseMethod.Toon());
                defaultmaterial.addTexture(new Texture("de", R.drawable.rrr));
                defaultmaterial.setColorInfluence(0);
                earth.getChildByName("Zuccarello:H_M_A_nickZ_L4_Group45_H_M_A_nickZ_L4_polySurface2").setMaterial(defaultmaterial);
                earth.setPosition(4,4,4);


                Material sphere_mat = new Material();
                sphere_mat.enableLighting(true);
                sphere_mat.setDiffuseMethod(new DiffuseMethod.Lambert());
                sphere_mat.addTexture(new Texture("de", R.drawable.rrr));
                sphere_mat.setColorInfluence(0);
                earth.getChildByName("pSphere1").setMaterial(sphere_mat);
               // earth.getChildByName("pSphere1").setScale(0.4,0.4,0.4);


                Material sphere1_mat2 = new Material();
                sphere1_mat2.enableLighting(true);
                sphere1_mat2.setDiffuseMethod(new DiffuseMethod.Lambert());
                sphere1_mat2.addTexture(new Texture("de", R.drawable.rrr));
                sphere1_mat2.setColorInfluence(0);
                earth.getChildByName("Zuccarello:H_M_A_nickZ_L4_Group45_H_M_A_nickZ_L4_polySurface2").setMaterial(sphere1_mat2);
                earth.getChildByName("Zuccarello:H_M_A_nickZ_L4_Group45_H_M_A_nickZ_L4_polySurface2").setScale(1.5,1.5,1.5);



                Material sphere_mat2 = new Material();
                sphere_mat2.enableLighting(true);
                sphere_mat2.setDiffuseMethod(new DiffuseMethod.Lambert());
                sphere_mat2.addTexture(new Texture("de", R.drawable.rrr));
                sphere_mat2.setColorInfluence(0);
                earth.getChildByName("pSphere2").setMaterial(sphere_mat2);
               // earth.getChildByName("pSphere2").setScale(0.4,0.4,0.4);
                earth.setPosition(0,0,0);
                




            } catch (Exception e) {
                e.printStackTrace();
            }






            ArcballCamera arcball = new ArcballCamera(mContext, ((Activity)mContext).findViewById(R.id.lly));
            arcball.setTarget(earth);
            arcball.setFieldOfView(120);
            arcball.moveForward(50);
            arcball.setProjectionMatrix(700,800);
            arcball.setPosition(0, 280, 100);
            arcball.setCameraYaw(0);
            arcball.setFarPlane(10000);
            Log.d("jslkjfdlkfgj","again");
            getCurrentScene().replaceAndSwitchCamera(getCurrentCamera(), arcball);

        }

    }
}
