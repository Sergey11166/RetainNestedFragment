package com.example.vs.retainnestedfragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NestedFragment extends Fragment {

    private static final String TAG_TEMPLATE = NestedFragment.class.getSimpleName();
    public static final String ARGS_KEY = NestedFragment.class.getName() + "ARGS_KEY";

    @BindView(R.id.text) TextView textView;
    private Unbinder unbinder;
    private Timer timer;
    private Handler handler;
    private int count;
    private String TAG;

    public NestedFragment(){}

    public static NestedFragment newInstance(int position) {
        NestedFragment instance = new NestedFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_KEY, position);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = TAG_TEMPLATE + getArguments().getInt(ARGS_KEY);
        Log.d(TAG, "onCreate(Bundle " + (savedInstanceState != null ? savedInstanceState.hashCode() : "null") + ")");
        handler = new Handler();
        initTimer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
        if (timer != null) timer.cancel();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttache(Context " + (context != null ? context.hashCode() : "null") + " )");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated(Bundle " + (savedInstanceState != null ? savedInstanceState.hashCode() : "null") + " )");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState(Bundle " + (outState != null ? outState.hashCode() : "null") + " )");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(TAG, "onViewStateRestored(Bundle " + (savedInstanceState != null ? savedInstanceState.hashCode() : "null") + " )");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged(Configuration " + (newConfig != null ? newConfig.hashCode() : "null") + " )");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView(Bundle " + (savedInstanceState != null ? savedInstanceState.hashCode() : "null") + " )");
        View view = inflater.inflate(R.layout.nestewd_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated(Bundle " + (savedInstanceState != null ? savedInstanceState.hashCode() : "null") + " )");
        if (timer == null) initTimer();
        updateTextView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView()");
        unbinder.unbind();
    }

    private void updateTextView() {
        if (textView == null) return;
        String text = String.valueOf(getArguments().getInt(ARGS_KEY)) + " - " + count;
        handler.post(() -> textView.setText(text));
    }

    private void initTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count++;
                updateTextView();
            }
        }, 1000, 1000);
    }
}
