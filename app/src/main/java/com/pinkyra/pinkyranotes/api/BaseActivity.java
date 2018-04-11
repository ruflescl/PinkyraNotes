package com.pinkyra.pinkyranotes.api;

import android.support.v7.app.AppCompatActivity;

import com.pinkyra.pinkyranotes.dependencyinjection.view.ViewComponent;
import com.pinkyra.pinkyranotes.dependencyinjection.view.ViewModule;


public class BaseActivity extends AppCompatActivity {
    protected ViewComponent getViewComponent() {
        return ((PinkyraApplication) getApplication())
                .getApplicationComponent()
                .buildViewComponent(new ViewModule(this));
    }
}
