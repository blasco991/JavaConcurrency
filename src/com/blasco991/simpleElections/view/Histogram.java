package com.blasco991.simpleElections.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Histogram extends JComponent {
	private final float percent;

	/**
	 * Builds a component that represents a histogram.
	 * The size is given as a percent (0..1) of the total width of 200 pixels.
	 * 
	 * @param percent
	 */

	public Histogram(float percent) {
		this.percent = percent;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// this is true in Java 1.2 and later
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.fillRect(4, 4, (int) (200 * percent), 16);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(208, 24);
	}
}
