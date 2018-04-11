package com.pinkyra.pinkyranotes.dependencyinjection.view;

import com.pinkyra.pinkyranotes.notesoverview.NotesOverviewActivity;

import dagger.Subcomponent;

/**
 * Sub-component for {@link ViewModule} instatiantion
 */
@Subcomponent(modules = {ViewModule.class})
public interface ViewComponent {
    void inject(NotesOverviewActivity notesOverviewActivity);
}
