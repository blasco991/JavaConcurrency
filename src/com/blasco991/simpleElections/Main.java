package com.blasco991.simpleElections;

import com.blasco991.simpleElections.model.Model;
import com.blasco991.simpleElections.view.NumericElectionsFrame;
import com.blasco991.simpleElections.controller.Controller;
import com.blasco991.simpleElections.view.HistogramElectionsFrame;
import com.blasco991.simpleElections.view.PieChartView;
import com.sun.javafx.application.PlatformImpl;

import javax.swing.*;
import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            PlatformImpl.startup(() -> {
            });
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            } finally {
                MVC mvc = new MVC(new Model(), new Controller());
                new NumericElectionsFrame(mvc).setVisible(true);
                new HistogramElectionsFrame(mvc).setVisible(true);
                new NumericElectionsFrame(mvc).setVisible(true);
                new PieChartView(mvc).setVisible(true);
            }
        });
    }
}