package ru.History;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import jBerry.MySugar.R;

/*
    This class gives a good example how charts of blood sugar in the past would look like.
    Note: This is a demo.
 */
public class HistoryActivity extends ActionBarActivity {

    GraphViewSeries demoData1 = new GraphViewSeries(new GraphView.GraphViewData[]{
            new GraphView.GraphViewData(1, 2.0d)
            , new GraphView.GraphViewData(2, 1.5d)
            , new GraphView.GraphViewData(3, 2.5d)
            , new GraphView.GraphViewData(4, 1.0d)
    });
    GraphViewSeries demoData2 = new GraphViewSeries(new GraphView.GraphViewData[]{
            new GraphView.GraphViewData(1, 1.0d)
            , new GraphView.GraphViewData(2, 1.2d)
            , new GraphView.GraphViewData(3, 1.0d)
            , new GraphView.GraphViewData(4, 1.3d)
            , new GraphView.GraphViewData(5, 1.2d)
            , new GraphView.GraphViewData(6, 1.8d)
            , new GraphView.GraphViewData(7, 1.6d)
            , new GraphView.GraphViewData(8, 2.0d)
            , new GraphView.GraphViewData(9, 1.9d)
    });
    private String[] dates = {
            "8", "10", "12", "14", "16", "18", "20", "22", "24"
    };
    private String[] insulin = {
            "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1", "0"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_history);

        GraphView graphView = new LineGraphView(this, "Statistic");
        graphView.addSeries(demoData1);
        graphView.addSeries(demoData2);
        graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.GRAY);
        graphView.getGraphViewStyle().setVerticalLabelsColor(Color.GRAY);

        graphView.getGraphViewStyle().setGridColor(Color.GREEN);
        graphView.getGraphViewStyle().setTextSize(60);

        graphView.setHorizontalLabels(dates);
        graphView.setVerticalLabels(insulin);

        graphView.setScrollable(true);
        LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
        layout.addView(graphView);
    }
}
