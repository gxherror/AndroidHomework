package top.xherror.homework.Lab3;

import static androidx.viewpager2.widget.ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import top.xherror.homework.R;

public class MainFragment extends BaseFragment {

    private static final String TAG = "MainFragment";
    private static final String TITLE_VIEW_ANIMATION = "视图动画";
    private static final String TITLE_OBJECT_ANIMATION = "属性动画";
    private static final String TITLE_LOTTIE_ANIMATION = "Lottie动画";

    private final String[] tabTitles = new String[HelloFragmentViewPagerAdapter.FRAGMENTS_COUNT];

    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;
    private HelloFragmentViewPagerAdapter mAdapter;
    private MainFragmentListener mListener = null;


    public interface MainFragmentListener {
        void onMultiTabsViewCreated(int tabsCount);
        void onMultiTabsViewDetach();
    }


    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        /*mListener = (MainFragmentListener) context;*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(view);
        initViewPager();
        initTabLayout();
        if (null != mListener) {
            mListener.onMultiTabsViewCreated(mAdapter.getItemCount());
        }

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (null != mListener) {
            mListener.onMultiTabsViewDetach();
        }

    }

    private void initViews(View view) {
        mViewPager = view.findViewById(R.id.view_pager_main);
        mTabLayout = view.findViewById(R.id.tab_layout);
    }

    private void initViewPager() {
        mAdapter = new HelloFragmentViewPagerAdapter(this);
        mViewPager.setPageTransformer(new NGGuidePageTransformer());
        mViewPager.setAdapter(new HelloFragmentViewPagerAdapter(this));
        mViewPager.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT_DEFAULT);

    }

    private void initTabLayout() {
        initTabTitles();
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.setTabIndicatorAnimationMode(TabLayout.INDICATOR_ANIMATION_MODE_ELASTIC);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                mTabLayout,
                mViewPager,
                true,
                true,
                (tab, position) -> tab.setText(tabTitles[position]));
        tabLayoutMediator.attach();
    }

    private void initTabTitles() {
        tabTitles[HelloFragmentViewPagerAdapter.FRAGMENT_VIEW_ANIMATION] = TITLE_VIEW_ANIMATION;
        tabTitles[HelloFragmentViewPagerAdapter.FRAGMENT_OBJECT_ANIMATION] = TITLE_OBJECT_ANIMATION;
        tabTitles[HelloFragmentViewPagerAdapter.FRAGMENT_LOTTIE_ANIMATION] = TITLE_LOTTIE_ANIMATION;
    }

    public class NGGuidePageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_ALPHA = 0.0f;    //最小透明度
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();    //得到view宽
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left. 出了左边屏幕
                view.setAlpha(0);
            } else if (position <= 1) { // [-1,1]
                // Fade the page relative to its size.
                float alphaFactor = Math.max(MIN_ALPHA, 1 - Math.abs(position));
                view.setAlpha(alphaFactor);
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.    出了右边屏幕
                view.setAlpha(0);
            }
        }

    }

}
