
package com.nomad.auditoria5s;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;

/**
 * Custom implementation of the MarkerView.
 * 
 * @author Philipp Jahoda
 */
public class RadarMarkerView extends MarkerView {

    private TextView tvContent;
    private DecimalFormat format = new DecimalFormat("##0");

    public RadarMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

        tvContent = (TextView) findViewById(R.id.tvContent);

    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(format.format(e.getY()/20) );

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight() - 10);
    }
}
