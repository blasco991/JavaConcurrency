package com.blasco991.simpleElections.view;

import com.blasco991.annotations.UiThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public interface View {
	public final ScheduledExecutorService exec
		= Executors.newScheduledThreadPool(2);

	// 3: change your display
	@UiThread
	void askForNewParty();

	@UiThread 
	void reportSaved();

	// 4: I've changed
	@UiThread
	void onModelChanged();
}