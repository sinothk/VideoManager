package com.sinothk.manager.video.bean;

import java.io.Serializable;

/**
 * <pre>
 *  创建:  梁玉涛 2019/4/18 on 15:49
 *  项目:  VideoManagerLib
 *  描述:
 *  更新:
 * <pre>
 */
public class VideoSourceEntity implements Serializable {
    /**
     * Usually it's a video url.
     */
    private String url;

    /**
     * you can set video name to it.
     */
    private String title;

    public VideoSourceEntity() {
    }

    public VideoSourceEntity(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
