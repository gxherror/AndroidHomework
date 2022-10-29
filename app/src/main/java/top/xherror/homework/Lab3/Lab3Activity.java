package top.xherror.homework.Lab3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import top.xherror.homework.R;

public class Lab3Activity extends AppCompatActivity implements MainFragment.MainFragmentListener{

    private static final String TAG = "MainActivity";

    private Button mReplaceButton;
    @Override
    public void onMultiTabsViewCreated(int tabsCount) {
        /*
        TextView tv = findViewById(R.id.tv_tabs_count);
        tv.setText(tabsCount + " tabs created");
        tv.setVisibility(View.VISIBLE);
         */
    }

    @Override
    public void onMultiTabsViewDetach() {
        mReplaceButton.setVisibility(View.VISIBLE);
        /*
        TextView tv = findViewById(R.id.tv_tabs_count);
        tv.setVisibility(View.GONE);

         */
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate --- Start ---");
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate --- End   ---");
        setContentView(R.layout.activity_lab3);

        mReplaceButton = findViewById(R.id.btn_replace);
        mReplaceButton.setOnClickListener(v -> {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .add(R.id.fragment_container, new MainFragment())
                    .addToBackStack(null)
                    .commit();
            mReplaceButton.setVisibility(View.GONE);
        });
    }
}