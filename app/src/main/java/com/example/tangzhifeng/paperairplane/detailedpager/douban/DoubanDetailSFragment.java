package com.example.tangzhifeng.paperairplane.detailedpager.douban;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tangzhifeng.paperairplane.R;

/**
 * Created by Administrator on 2017/2/27.
 */

public class DoubanDetailSFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.douban_details_fragment,container,false);
        return view;
    }
}
