package com.myworld.android.mpchart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
    }

    private void findView() {
        BarChart barChart = (BarChart) findViewById(R.id.barChart);
        List<List<Float>>list = new ArrayList<>();
        for (int i = 0;i<4;i++){
            List<Float>values = new ArrayList<>();
            for (int j = 0;j<4;j++){
                values.add((float) (Math.random()*20));
            }
          list.add(values);
        }
        String[]str = {"语文","数学","英语","物理"};
        int[]color = {Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN};
        BarData data = new BarData();
        for (int n = 0;n<list.size();n++){
            List<BarEntry> entry = new ArrayList<>();
            for (int s = 0;s<list.get(n).size();s++){
                entry.add(new BarEntry(s,list.get(n).get(s)));
            }
            BarDataSet dataSet = new BarDataSet(entry,str[n]);
            dataSet.setColor(color[n]);
            data.addDataSet(dataSet);
            data.setBarWidth(0.5f);
        }
        //设置一组状图的起始位置,组与组之间的间隔,柱与柱之间的间隔
        data.groupBars(0,0.22f,0.11f);
        barChart.setData(data);
        XAxis xaxis = barChart.getXAxis();
        xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置间隔
        xaxis.setGranularity(1);
        //如果为true，根据需要显示
        //false强制显示我们需要的标识
        xaxis.setLabelCount(10,false);
        //设置最大值
        xaxis.setAxisMaximum(11);
        //设置最小值
        xaxis.setAxisMinimum(0);
        xaxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return ((int)value)+"时";
            }
        });
    }
}
