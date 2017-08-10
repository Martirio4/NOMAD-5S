
package com.nomad.auditoria5s.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.nomad.auditoria5s.R;
import com.nomad.auditoria5s.RadarMarkerView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RadarChartActivitry extends AppCompatActivity {

    private RadarChart mChart;
    public static final String  PUNJTAJE1="PUNTAJE1";
    public static final String  PUNJTAJE2="PUNTAJE2";
    public static final String  PUNJTAJE3="PUNTAJE3";
    public static final String  PUNJTAJE4="PUNTAJE4";
    public static final String  PROMEDIO="PROMEDIO";
    public static final String  USUARIO="USUARIO";
    public static final String  AREA="AREA";

    private Integer punt1;
    private Integer punt2;
    private Integer punt3;
    private Integer punt4;
    private Float puntpro;

    private FloatingActionMenu fab_menu;

    private TextView texto1;
    private TextView texto2;
    private TextView texto3;
    private TextView texto4;
    private TextView texto5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_radarchart_noseekbar);
        Intent unIntent= getIntent();
        Bundle unBundle=unIntent.getExtras();

        texto1=(TextView)findViewById(R.id.texto1);
        texto2=(TextView)findViewById(R.id.texto2);
        texto3=(TextView)findViewById(R.id.texto3);
        texto4=(TextView)findViewById(R.id.texto4);
        fab_menu=(FloatingActionMenu)findViewById(R.id.fab_chart);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date=sdf.format(cal.getTime());



        punt1=unBundle.getInt(PUNJTAJE1);
        punt2=unBundle.getInt(PUNJTAJE2);
        punt3=unBundle.getInt(PUNJTAJE3);
        punt4=unBundle.getInt(PUNJTAJE4);
        puntpro=unBundle.getFloat(PROMEDIO);

        texto1.setText(puntpro.toString());
        texto2.setText(date);
        texto3.setText("Rojas Hernan");
        texto4.setText("Bandas Angostas");

        /*TextView tv = (TextView) findViewById(R.id.textView);
        tv.setTextColor(Color.WHITE);
        tv.setBackgroundColor(Color.rgb(60, 65, 82));
*/
        mChart = (RadarChart) findViewById(R.id.chart1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mChart.setBackgroundColor(getColor(R.color.fondoOscuro));

        }
        else {
            mChart.setBackgroundColor(getResources().getColor(R.color.fondoOscuro));

        }
        mChart.getDescription().setEnabled(false);

        mChart.setWebLineWidth(1f);
        mChart.setWebColor(Color.LTGRAY);
        mChart.setWebLineWidthInner(1f);
        mChart.setWebColorInner(Color.LTGRAY);
        mChart.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MarkerView mv = new RadarMarkerView(this, R.layout.radar_markerview);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart

        setData();

        mChart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] mActivities = new String[]{"1S", "2S", "3S", "4S", "5S"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(20f);

        YAxis yAxis = mChart.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.WHITE);
        l.setTextSize(20f);
    }


    public void setData() {

        float mult = 80;
        float min = 20;
        int cnt = 5;

        ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();
        ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();

        entries1.add(new RadarEntry(punt1*20));
        entries1.add(new RadarEntry(punt2*20));
        entries1.add(new RadarEntry(punt3*20));
        entries1.add(new RadarEntry(punt4*20));
        entries1.add(new RadarEntry(puntpro*20));

        entries2.add(new RadarEntry(3.5f*20));
        entries2.add(new RadarEntry(3.5f*20));
        entries2.add(new RadarEntry(3.5f*20));
        entries2.add(new RadarEntry(3.5f*20));
        entries2.add(new RadarEntry(3.5f*20));

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
       /* for (int i = 0; i < cnt; i++) {
            float val1 = (float) (Math.random() * mult) + min;
            entries1.add(new RadarEntry(val1));

            float val2 = (float) (Math.random() * mult) + min;
            entries2.add(new RadarEntry(val2));
        }
        */

        RadarDataSet set1 = new RadarDataSet(entries1, "Auditoria");
        set1.setColor(Color.rgb(103, 110, 129));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarDataSet set2 = new RadarDataSet(entries2, "Objetivo");
        set2.setColor(Color.rgb(121, 162, 175));
        set2.setFillColor(Color.rgb(121, 162, 175));
        set2.setDrawFilled(true);
        set2.setFillAlpha(90);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);
        mChart.invalidate();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
