package com.example.helloworldjava.model;

import android.app.Activity;

public class ProfileFeature {
    private int ImageResourceId;
    private String name;
    private Class<? extends Activity> activityClass;

    public ProfileFeature(int imageResourceId, String name) {
        this(imageResourceId, name, null);
    }

    public ProfileFeature(int imageResourceId, String name, Class<? extends Activity> activityClass) {
        ImageResourceId = imageResourceId;
        this.name = name;
        this.activityClass = activityClass;
    }

    public int getImageResourceId() {
        return ImageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        ImageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends Activity> getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class<? extends Activity> activityClass) {
        this.activityClass = activityClass;
    }
}
