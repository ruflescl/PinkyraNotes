package com.pinkyra.pinkyranotes.dependencyinjection.application;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Singleton scope for entire application lifecycle
 *
 * '@ApplicationScope' = '@Singleton' (with a better suggestive name)
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScope {
}
