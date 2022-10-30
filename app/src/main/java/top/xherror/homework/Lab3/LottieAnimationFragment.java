package top.xherror.homework.Lab3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;

import top.xherror.homework.R;

public class LottieAnimationFragment extends BaseFragment {

    public static final String TAG = "LottieAnimationFragment";


    public LottieAnimationFragment() {

    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lottie_animation, container, false);
    }
}
