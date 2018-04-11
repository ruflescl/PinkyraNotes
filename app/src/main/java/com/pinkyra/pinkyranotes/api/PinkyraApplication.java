package com.pinkyra.pinkyranotes.api;

import android.app.Application;
import android.support.annotation.UiThread;

import com.pinkyra.pinkyranotes.dependencyinjection.application.ApplicationComponent;
import com.pinkyra.pinkyranotes.dependencyinjection.application.ApplicationModule;
import com.pinkyra.pinkyranotes.dependencyinjection.application.DaggerApplicationComponent;

import javax.inject.Inject;

/**
 * Custom application class for dependency injection initialization
 */
public class PinkyraApplication extends Application {
    private static final String TAG = "PinkyraApplication";

    @Inject ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        getApplicationComponent().inject(this);
        super.onCreate();
    }

    @UiThread
    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }

        return applicationComponent;
    }
}
