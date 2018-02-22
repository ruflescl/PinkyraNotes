package com.pinkyra.pinkyranotes.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;

import com.pinkyra.pinkyranotes.R;

/**
 * Builder class for building app standard {@link android.support.v4.widget.SwipeRefreshLayout}
 */
public class SwipeRefreshLayoutBuilder {
    public static void setup(@NonNull Context actContext,
                                           SwipeRefreshLayout view,
                                           SwipeRefreshLayout.OnRefreshListener refreshListener) {
        view.setColorSchemeColors(
                ContextCompat.getColor(actContext, R.color.colorPrimary),
                ContextCompat.getColor(actContext, R.color.colorAccent),
                ContextCompat.getColor(actContext, R.color.colorPrimaryDark)
        );

        view.setOnRefreshListener(refreshListener);
    }
}
