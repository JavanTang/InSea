package com.example.tangzhifeng.paperairplane.been;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.tangzhifeng.paperairplane.interfaze.OnStringListener;
import com.example.tangzhifeng.paperairplane.util.VolleySingleton;

/**
 * Created by tangzhifeng on 2017/2/9.
 */

public class StringModelImpl {
    private Context context;

    public StringModelImpl(Context context) {
        this.context = context;
    }

    public void load(String url, final OnStringListener onStringListener){
        StringRequest stringRequest=new StringRequest(url,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                onStringListener.onSuccess(response);
            }
        },null);

        VolleySingleton.getVolleySingleton(context).addToRequestQueue(stringRequest);
    }

}
