package com.blasco991.simpleelections;

import com.blasco991.simpleelections.model.Model;
import com.blasco991.simpleelections.view.NumericElectionsFrame;
import com.blasco991.simpleelections.controller.Controller;
import com.blasco991.simpleelections.view.HistogramElectionsFrame;

import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			MVC mvc = new MVC(new Model(), new Controller());

			new NumericElectionsFrame(mvc).setVisible(true);
			new NumericElectionsFrame(mvc).setVisible(true);
			new HistogramElectionsFrame(mvc).setVisible(true);
		});
	}
}