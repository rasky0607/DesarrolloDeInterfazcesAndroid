package com.example.staticfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentC extends Fragment {

    private String TAG ="MI FRAGMENT C";
    private WebView wvfrc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentec,container,false);
        //Solo despues de inflar la vista se puede hacer findbyid

        Log.d(TAG,"fragmenteC--> onCreateView()");
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        wvfrc = view.findViewById(R.id.wvfrc);
        String html =getResources().getString(R.string.micdata);
        wvfrc.loadData(html,"text/html","UTF-8");




    }
}
