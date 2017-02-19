package com.example.tangzhifeng.paperairplane.util;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tangzhifeng.paperairplane.data.guoke.GuoKe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class LoadNetUtil {
    private static final String TGA = "wkl";


    public static List<GuoKe> GuokeList  = new LinkedList<GuoKe>();
    public static final String GUOKE_URL = "http://apis.guokr.com/handpick/article.json?retrieve_type=by_since&category=all&limit=25&ad=1";

    public List<GuoKe> StartAsyncTask(){
        MyAsyncTask mAsyncTask = new MyAsyncTask();
        mAsyncTask.execute(GUOKE_URL);
//        Log.i("wkl", "StartAsyncTask: "+mAsyncTask.list.size());
        return GuokeList;
    }

    public static class MyAsyncTask extends AsyncTask<String, Void, List<GuoKe>> {
        public static List<GuoKe> GuokeList1  = new LinkedList<GuoKe>();


        @Override
        protected List<GuoKe> doInBackground(String... strings) {
            String InReader = ReaderUrlData(strings[0]);
            return GetJsonData(InReader);
        }

        @Override
        protected void onPostExecute(List<GuoKe> guoKes) {
            super.onPostExecute(guoKes);
            GuokeList1 = guoKes;
            Log.i("TAG", "onPostExecute: " + GuokeList1.size() + GuokeList.get(0).getResult().get(0).getSummary());
        }

    }
    public static List<GuoKe> GetJsonData(String StreamReader) {
        try {
            JSONObject object = new JSONObject(StreamReader);
            JSONArray JsonArray = object.getJSONArray("result");
            for (int i = 0; i < JsonArray.length(); i++) {
                GuoKe guoke = getGuoKe(JsonArray, i);
                GuokeList.add(guoke);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return GuokeList;
    }

    @NonNull
    private static GuoKe getGuoKe(JSONArray jsonArray, int i) throws JSONException {
        GuoKe guoke = new GuoKe();
        GuoKe.ResultBean resultBean = new GuoKe.ResultBean();
        JSONObject jsonData = jsonArray.getJSONObject(i);
        JSONObject jsondataforSourceBean = jsonData.getJSONObject("source_data");
//                获取果壳小组头像
        resultBean.image = jsondataforSourceBean.getString("image");
//               获取果壳小组名称
        resultBean.group_name = jsondataforSourceBean.getString("title");
//               item概要  content
        resultBean.summary = jsonData.getString("summary");
//               item Icon Bitmap
        resultBean.headline_img = jsonData.getString("headline_img");
//               获取作者
        resultBean.author = jsonData.getString("author");
        //        item的title
        resultBean.itemtitle = jsonData.getString("title");
//        获取喜欢的数量
        resultBean.likings_count =jsonData.getInt("likings_count");
//        获取评论数

       if (jsonData.getString("replies_count").equals("null")||jsonData.getString("replies_count") == null){
           resultBean.replies_count = 0+"";
       }
        else{
           resultBean.replies_count = jsonData.getString("replies_count");
       }

//        获取创建时间
        Log.i("wkl", "getGuoKe: "+jsonData.getString("replies_count"));
        resultBean.setDate_created(jsonData.getDouble("date_created"));
        guoke.result = new ArrayList<>();
        guoke.result.add(resultBean);
        return guoke;
    }

    public static String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }

    public static String ReaderUrlData(String url) {
        StringBuilder sb = null;
        try {
            URLConnection connection = new URL(url).openConnection();
            InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "utf-8");
            BufferedReader bir = new BufferedReader(isr);
            sb = new StringBuilder();
            String values;
            while ((values = bir.readLine()) != null) {
                sb.append(values);
            }
            if (sb.toString() != null)
                return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
