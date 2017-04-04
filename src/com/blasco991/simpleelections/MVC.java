package com.blasco991.simpleelections;

import com.blasco991.simpleelections.controller.Controller;
import com.blasco991.simpleelections.model.Model;
import com.blasco991.simpleelections.view.View;

import java.util.concurrent.CopyOnWriteArrayList;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class MVC {
	public final Model model;
	public final Controller controller;
	private final CopyOnWriteArrayList<View> views = new CopyOnWriteArrayList<>();

	public MVC(Model model,Controller controller) {
		this.model = model;
		this.controller = controller;
		
		model.setMVC(this);
		controller.setMVC(this);
	}

	public void register(View view) {
		this.views.add(view);
	}

	public void unregister(View view) {
		this.views.remove(view);
	}

	/**
     * A task that can be executed on all views currently registered.
     */
    public interface ViewTask {

        /**
         * Applies the task to the given view.
         *
         * @param view
         */
        void process(View view);
    }

    /**
     * Applies the given task to all views currently registered.
     *
     * @param task
     */
    public void forEachView(ViewTask task) {
        // Internal iteration, preferred since we do not need
        // to expose the modifiable set of views
        for (View view: views)
            task.process(view);
    }
}