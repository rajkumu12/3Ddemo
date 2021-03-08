package com.survey.human3d;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.survey.human3d.Interfaces.Pick;

import org.rajawali3d.renderer.ISurfaceRenderer;
import org.rajawali3d.renderer.Renderer;
import org.rajawali3d.view.IDisplay;
import org.rajawali3d.view.ISurface;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * A simple {@link Fragment} subclass.
o
 * create an instance of this fragment.
 */
public class MY3DFragment extends Fragment  implements IDisplay, View.OnClickListener {
    public static final String BUNDLE_EXAMPLE_URL = "BUNDLE_EXAMPLE_URL";
    public static final String BUNDLE_EXAMPLE_TITLE = "BUNDLE_EXAMPLE_TITLE";

    /* protected ProgressBar mProgressBarLoader;*/
    RelativeLayout progress;
    protected String           mExampleUrl;
    protected String           mExampleTitle;
    protected FrameLayout mLayout;
    protected ISurface mRajawaliSurface;
    protected ISurfaceRenderer mRenderer;
    FrameLayout frameLayout;


    public MY3DFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

   /*     final Bundle bundle = getArguments();
        if (bundle == null || !bundle.containsKey(BUNDLE_EXAMPLE_URL)) {
            throw new IllegalArgumentException(getClass().getSimpleName()
                    + " requires " + BUNDLE_EXAMPLE_URL
                    + " argument at runtime!");
        }

        mExampleUrl = bundle.getString(BUNDLE_EXAMPLE_URL);
        mExampleTitle = bundle.getString(BUNDLE_EXAMPLE_TITLE);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mLayout = (FrameLayout) inflater.inflate(getLayoutID(), container, false);

        /* progress.findViewById(R.id.relative_layout_loader_container).bringToFront();*/

        // Find the TextureView
        mRajawaliSurface = (ISurface) mLayout.findViewById(R.id.rajwali_surface);

        // Create the loader
        progress =  mLayout.findViewById(R.id.relative_lay);
        progress.setVisibility(View.GONE);

        // Set the example link

        // Create the renderer
        mRenderer = createRenderer();
        onBeforeApplyRenderer();
        applyRenderer();


        return mLayout;
    }


    protected void onBeforeApplyRenderer() {

    }

    protected void applyRenderer() {
        mRajawaliSurface.setSurfaceRenderer(mRenderer);
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public ISurfaceRenderer createRenderer() {
        return null;
    }
    protected void hideLoader() {
        progress.post(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
            }
        });
    }

    protected void showLoader() {
        progress.post(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.VISIBLE);
            }
        });
    }
    @Override
    public int getLayoutID() {
        return R.layout.fragment_m_y3_d;
    }

    protected boolean onTouchEvent(MotionEvent event) {
        return false;
    }



    protected abstract class AExampleRenderer extends Renderer {

        public AExampleRenderer(Context context) {
            super(context);
        }

        @Override
        public void onOffsetsChanged(float v, float v2, float v3, float v4, int i, int i2) {

        }

        @Override
        public void onTouchEvent(MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    getCurrentCamera().moveRight(200);
                case MotionEvent.ACTION_UP:
                    getCurrentCamera().moveUp(100);
            }
        }
        @Override
        public void onRenderSurfaceCreated(EGLConfig config, GL10 gl, int width, int height) {
            showLoader();
            super.onRenderSurfaceCreated(config, gl, width, height);
            hideLoader();
        }
        @Override
        protected void onRender(long ellapsedRealtime, double deltaTime) {
            super.onRender(ellapsedRealtime, deltaTime);
        }
    }
}