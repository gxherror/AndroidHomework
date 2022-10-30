package top.xherror.homework.Lab3;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment extends Fragment {

    public BaseFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(getLogTag(), "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(getLogTag(), "onCreate");
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(getLogTag(), "onCreateView");
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(getLogTag(), "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(getLogTag(), "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(getLogTag(), "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(getLogTag(), "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(getLogTag(), "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(getLogTag(), "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(getLogTag(), "onDetach");
    }

    protected abstract String getLogTag();
}
