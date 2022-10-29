package top.xherror.homework.Lab3;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import top.xherror.homework.R;

public class ObjectAnimationFragment extends BaseFragment {

    private static final String TAG = "StaticFragment";
    private static final long ANIMATION_DURATION = 3000;
    private static final float ALPHA_START = 0f;
    private static final float ALPHA_END = 1f;

    private ImageView mRobot;
    private ObjectAnimator mAnimator;

    public ObjectAnimationFragment() {

    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_object_animation, container, false);
        mRobot = view.findViewById(R.id.iv_robot);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mRobot) {
            mAnimator = ObjectAnimator.ofFloat(mRobot, "alpha", ALPHA_START, ALPHA_END, ALPHA_START);
            mAnimator.setDuration(ANIMATION_DURATION);
            mAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mAnimator.setRepeatMode(ValueAnimator.RESTART);
            mAnimator.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != mAnimator && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
    }

}
