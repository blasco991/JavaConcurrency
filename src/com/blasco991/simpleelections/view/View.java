package com.blasco991.simpleelections.view;


import com.blasco991.annotations.UiThread;

public interface View {
    // 3: change your display
    @UiThread
    void askForNewParty();

    // 4: I've changed
    @UiThread
    void onModelChanged();
}