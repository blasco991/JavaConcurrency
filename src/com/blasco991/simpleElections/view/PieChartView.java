package com.blasco991.simpleElections.view;

import com.blasco991.annotations.UiThread;
import com.blasco991.simpleElections.MVC;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.PieChart;
import net.jcip.annotations.ThreadSafe;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by blasco991 on 06/04/17.
 */

@ThreadSafe
public class PieChartView extends JFrame implements View {

    // list that holds the values you want to display on the chart
    private static ObservableList<PieChart.Data> list = FXCollections.observableList(new ArrayList<PieChart.Data>());
    private final MVC mvc;
    private final PieChart pieChart = new PieChart();


    @UiThread
    public PieChartView(MVC mvc) {
        this.mvc = mvc;
        mvc.register(this);

        JFXPanel fxPanel = buildWidgets();

        setTitle("Pie Chart Elections");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        onModelChanged();
    }

    private JFXPanel buildWidgets() {
        JFXPanel fxPanel = new JFXPanel();
        Platform.runLater(() -> {
            Group root = new Group();
            Scene scene = new Scene(root);
            root.getChildren().add(pieChart);
            fxPanel.setScene(scene);
        });
        pieChart.setLegendSide(Side.RIGHT);
        pieChart.setTitle("Torta della Nonna");
        add(fxPanel);
        return fxPanel;
    }

    @Override
    public void askForNewParty() {
    }

    @Override
    public void reportSaved() {
    }

    @Override
    @UiThread
    public void onModelChanged() {
        Platform.runLater(() -> {
            list.clear();
            mvc.model.getParties().forEach(party -> list.add(new PieChart.Data(party, mvc.model.getVotesFor(party))));
            /*for (String entry : mvc.model.getParties())
                if (list.stream().noneMatch(element -> Objects.equals(element.getName(), entry)))
                    list.add(new PieChart.Data(entry, mvc.model.getVotesFor(entry)));
                else
                    list.set(list.indexOf(list.stream().filter(
                            element -> Objects.equals(element.getName(), entry)).findFirst().get()),
                            new PieChart.Data(entry, mvc.model.getVotesFor(entry))
                    );*/
            pieChart.setData(list);
        });
        pack();
    }
}