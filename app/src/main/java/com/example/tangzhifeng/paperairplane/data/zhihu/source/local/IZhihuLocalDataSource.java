package com.example.tangzhifeng.paperairplane.data.zhihu.source.local;

import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHu;
import com.example.tangzhifeng.paperairplane.data.zhihu.ZhiHuList;
import com.example.tangzhifeng.paperairplane.data.zhihu.source.ZhihuDateSource;

import java.util.List;

/**
 * 作者: tangzhifeng on 2017/6/23.
 * 邮箱: tzfjobmail@gmail.com
 */

public interface IZhihuLocalDataSource extends ZhihuDateSource{

    //检查是否存在  true为存在  false为不存在
    boolean isLocalCheckId(String id);
    //保存知乎详细信息
    void saveZhihu(ZhiHu zhiHu);

    void saveZhiHuList(List<ZhiHuList> zhiHuLists);
}
