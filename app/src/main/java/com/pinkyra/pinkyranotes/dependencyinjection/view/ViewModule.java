package com.pinkyra.pinkyranotes.dependencyinjection.view;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Base dependencies for all activities / fragments
 */
@Module
public class ViewModule {
    private final FragmentActivity activity;

    public ViewModule(FragmentActivity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return this.activity;
    }

    @Provides
    public Context provideContext() {
        return this.activity;
    }

    @Provides
    public FragmentManager provideFragmentManager() {
        return this.activity.getSupportFragmentManager();
    }
}
