package com.jady.replacefragmentdemo;

import android.support.v4.app.Fragment;

/**
 * Created by Jady on 2015/11/27 0027.
 */
public class BaseFragment extends Fragment {

    private String title;
    private int iconId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
