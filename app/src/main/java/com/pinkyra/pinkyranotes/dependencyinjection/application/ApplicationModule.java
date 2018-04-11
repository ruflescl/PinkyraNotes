package com.pinkyra.pinkyranotes.dependencyinjection.application;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * The dependency graph - Services available for all the application scope
 */
@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application provideApplication() {
        return this.application;
    }
}
