package com.pinkyra.pinkyranotes.dependencyinjection.application;

import com.pinkyra.pinkyranotes.api.PinkyraApplication;
import com.pinkyra.pinkyranotes.dependencyinjection.view.ViewComponent;
import com.pinkyra.pinkyranotes.dependencyinjection.view.ViewModule;

import dagger.Component;

/**
 * DI component for {@link ApplicationModule}
 */
@ApplicationScope
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(PinkyraApplication pinkyraApplication);

    ViewComponent buildViewComponent(ViewModule viewModule);
}
