package top.xherror.homework.Lab3;

import static android.view.animation.Animation.INFINITE;
import static android.view.animation.Animation.RESTART;
import static android.view.animation.Animation.REVERSE;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.AttributeSet;
import android.view.animation.AnimationSet;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import top.xherror.homework.R;


public class ViewAnimationFragment extends BaseFragment {

    private static final String TAG = "ViewAnimationFragment";
    private static final String PARAM_COLOR = "param_color";
    private static final long DURATION = 2000;
    private static final float ROTATE_START_DEGREE = 0f;
    private static final float ROTATE_END_DEGREE = 360f;
    private static final float ROTATE_PIVOT = 0.5f;
    private static final float ALPHA_START = 0f;
    private static final float ALPHA_END = 1f;
    private static final float FROM_X_DELTA = 0f;
    private static final float TO_X_DELTA = 300f;
    private static final float FROM_Y_DELTA = 0f;
    private static final float TO_Y_DELTA = 300f;
    private static final float FROM_X = 1f;
    private static final float TO_X = 0.5f;
    private static final float FROM_Y = 1f;
    private static final float TO_Y = 0.5f;



    private int mColor = Color.WHITE;
    private ImageView mRobot;
    private RotateAnimation mRotateAnimation;
    private AlphaAnimation mAlphaAnimation;
    private TranslateAnimation mTranslateAnimation;
    private ScaleAnimation mScaleAnimation;
    private AnimationSet setAnimation = new AnimationSet(true);

    public ViewAnimationFragment() {
        // Required empty public constructor
    }

    public static ViewAnimationFragment newInstance(int color) {
        ViewAnimationFragment fragment = new ViewAnimationFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (null != args) {
            int givenColor = args.getInt(PARAM_COLOR);
            mColor = (0 != givenColor) ? givenColor : mColor;
        }
        initAnimation();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_animation, container, false);
        view.setBackgroundColor(mColor);
        mRobot = view.findViewById(R.id.iv_robot);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mRobot) {
            mRobot.startAnimation(setAnimation);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
       if(setAnimation.hasStarted()){
           setAnimation.cancel();
       }
        /*
        if(null != mRotateAnimation && mRotateAnimation.hasStarted()) {
            mRotateAnimation.cancel();
        }

         */
    }



    private void initAnimation() {
        mAlphaAnimation = new AlphaAnimation(ALPHA_START,ALPHA_END);

        mRotateAnimation = new RotateAnimation(
                ROTATE_START_DEGREE, ROTATE_END_DEGREE,
                Animation.RELATIVE_TO_SELF, ROTATE_PIVOT,
                Animation.RELATIVE_TO_SELF, ROTATE_PIVOT
        );
        mRotateAnimation.setStartOffset(DURATION);

        mScaleAnimation = new ScaleAnimation(FROM_X,TO_X,FROM_Y,TO_Y);
        mScaleAnimation.setStartOffset(2*DURATION);
        mScaleAnimation.setFillEnabled(true);
        mScaleAnimation.setFillBefore(true);

        mTranslateAnimation = new TranslateAnimation(FROM_X_DELTA,TO_X_DELTA,FROM_Y_DELTA,TO_Y_DELTA);
        mTranslateAnimation.setStartOffset(3*DURATION);




        setAnimation.addAnimation(mAlphaAnimation);
        setAnimation.addAnimation(mRotateAnimation);
        setAnimation.addAnimation(mScaleAnimation);
        setAnimation.addAnimation(mTranslateAnimation);
        setAnimation.setDuration(DURATION);
        //https://stackoverflow.com/questions/30732557/how-to-repeat-an-animationset-with-sequentially-added-animations
        //https://stackoverflow.com/questions/7609974/android-animation-listener
        //https://stackoverflow.com/questions/5474923/onanimationend-is-not-getting-called-onanimationstart-works-fine

        setAnimation.start();
        /*
        mRotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(getLogTag(), "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(getLogTag(), "onAnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i(getLogTag(), "onAnimationRepeat");
            }
        });

         */
    }
}