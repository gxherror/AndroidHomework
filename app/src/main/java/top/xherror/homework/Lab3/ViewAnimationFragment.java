package top.xherror.homework.Lab3;

import static android.view.animation.Animation.INFINITE;
import static android.view.animation.Animation.REVERSE;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import top.xherror.homework.R;


public class ViewAnimationFragment extends BaseFragment {

    private static final String TAG = "ViewAnimationFragment";
    private static final String PARAM_COLOR = "param_color";
    private static final long ROTATE_DURATION = 2000;
    private static final float ROTATE_START_DEGREE = 0f;
    private static final float ROTATE_END_DEGREE = 360f;
    private static final float ROTATE_PIVOT = 0.5f;


    private int mColor = Color.WHITE;
    private ImageView mRobot;
    private RotateAnimation mRotateAnimation;

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
        initAnimation();
        if (null != mRobot) {
            mRobot.startAnimation(mRotateAnimation);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(null != mRotateAnimation && mRotateAnimation.hasStarted()) {
            mRotateAnimation.cancel();
        }
    }

    private void initAnimation() {
        mRotateAnimation = new RotateAnimation(
                ROTATE_START_DEGREE, ROTATE_END_DEGREE,
                Animation.RELATIVE_TO_SELF, ROTATE_PIVOT,
                Animation.RELATIVE_TO_SELF, ROTATE_PIVOT
        );
        mRotateAnimation.setDuration(ROTATE_DURATION);
        mRotateAnimation.setRepeatCount(INFINITE);
        mRotateAnimation.setRepeatMode(REVERSE);
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
    }
}