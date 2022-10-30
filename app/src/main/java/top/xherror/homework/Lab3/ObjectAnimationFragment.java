package top.xherror.homework.Lab3;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;


import com.airbnb.lottie.LottieAnimationView;

import top.xherror.homework.R;

public class ObjectAnimationFragment extends BaseFragment {

    private static final String TAG = "StaticFragment";
    private static final long ANIMATION_DURATION = 3000;
    private static final float ROTATE_START_DEGREE = 0f;
    private static final float ROTATE_END_DEGREE = 360f;
    private static final float ROTATE_PIVOT = 0.5f;
    private static final float ALPHA_START = 0f;
    private static final float ALPHA_END = 1f;
    private static final float FROM_X_DELTA = -300f;
    private static final float TO_X_DELTA = 0f;
    private static final float FROM_X = 0.5f;
    private static final float TO_X = 1f;

    private ImageView mRobot;
    private ObjectAnimator mAnimator,translateAnimator,alphaAnimator,scaleAnimator,rotateAnimator;
    private LottieAnimationView lottieAnimationView ;
    ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(5000);

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
        final int[] clickCount = {0};
        mRobot.setOnClickListener(v -> {
            switch (clickCount[0] % 4){
                case 0 :
                    mAnimator = translateAnimator;
                    break;
                case 1 :
                    mAnimator = alphaAnimator;
                    break;
                case 2 :
                    mAnimator = scaleAnimator;
                    break;
                case 3 :
                    mAnimator = rotateAnimator;
                    break;
            }
            mAnimator.setDuration(ANIMATION_DURATION);
            mAnimator.start();
            clickCount[0] = clickCount[0] +1;

        });
        mRobot.setVisibility(View.INVISIBLE);
        lottieAnimationView=view.findViewById(R.id.animation_view);
        lottieAnimationView.setAnimation(R.raw.lottie_check_animation);
        animator.addUpdateListener(animation -> {
            lottieAnimationView.setProgress((Float) animation.getAnimatedValue());
        });
        lottieAnimationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (valueAnimator.getAnimatedFraction() == 0) {

                } else if (valueAnimator.getAnimatedFraction() == 1f) {
                    mRobot.setVisibility(View.VISIBLE);
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                    }
                };
            });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        animator.start();
        mRobot.setVisibility(View.INVISIBLE);
        lottieAnimationView.setVisibility(View.VISIBLE);
        if (null != mRobot) {
                translateAnimator = ObjectAnimator.ofFloat(mRobot, "translationX",FROM_X_DELTA,TO_X_DELTA);
                alphaAnimator = ObjectAnimator.ofFloat(mRobot, "alpha", ALPHA_START, ALPHA_END);
                scaleAnimator = ObjectAnimator.ofFloat(mRobot,"scaleX",FROM_X,TO_X);
                rotateAnimator = ObjectAnimator.ofFloat(mRobot,"rotation",ROTATE_START_DEGREE,ROTATE_END_DEGREE,ROTATE_PIVOT);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != mAnimator && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
        lottieAnimationView.cancelAnimation();
        mRobot.setVisibility(View.INVISIBLE);
    }


}
