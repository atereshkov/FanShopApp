package com.github.handioq.fanshop.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;

import com.github.handioq.R;

public class BadgeDrawable extends Drawable {

    private static final int BADGE_BACKGROUND_COLOR = Color.RED;
    private static final int TEXT_COLOR = Color.WHITE;

    private Paint badgePaint;
    private Paint badgePaint1;
    private Paint textPaint;
    private Rect txtRec = new Rect();

    private String count = "";
    private boolean willDraw;

    public BadgeDrawable(Context context) {
        float mTextSize = context.getResources().getDimension(R.dimen.text_caption);

        int badgeBackgroundColor = ContextCompat.getColor(context.getApplicationContext(), R.color.colorAccent);

        badgePaint = new Paint();
        badgePaint.setColor(BADGE_BACKGROUND_COLOR);
        badgePaint.setAntiAlias(true);
        badgePaint.setStyle(Paint.Style.FILL);
        badgePaint1 = new Paint();
        badgePaint1.setColor(ContextCompat.getColor(context.getApplicationContext(), R.color.colorPrimary));
        badgePaint1.setAntiAlias(true);
        badgePaint1.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setColor(TEXT_COLOR);
        textPaint.setTypeface(Typeface.DEFAULT);
        textPaint.setTextSize(mTextSize);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public void draw(Canvas canvas) {

        if (!willDraw) {
            return;
        }
        Rect bounds = getBounds();
        float width = bounds.right - bounds.left;
        float height = bounds.bottom - bounds.top;

        // Position the badge in the top-right quadrant of the icon.

	    /*Using Math.max rather than Math.min */

        float radius = ((Math.max(width, height) / 2)) / 2;
        float centerX = (width - radius - 1) +5;
        float centerY = radius -5;
        if(count.length() <= 2){
            // Draw badge circle.
            canvas.drawCircle(centerX, centerY, (int)(radius+7.5), badgePaint1);
            canvas.drawCircle(centerX, centerY, (int)(radius+5.5), badgePaint);
        }
        else{
            canvas.drawCircle(centerX, centerY, (int)(radius+8.5), badgePaint1);
            canvas.drawCircle(centerX, centerY, (int)(radius+6.5), badgePaint);
            //canvas.drawRoundRect(radius, radius, radius, radius, 10, 10, badgePaint);
        }
        // Draw badge count text inside the circle.
        textPaint.getTextBounds(count, 0, count.length(), txtRec);
        float textHeight = txtRec.bottom - txtRec.top;
        float textY = centerY + (textHeight / 2f);
        if(count.length() > 2)
            canvas.drawText("99+", centerX, textY, textPaint);
        else
            canvas.drawText(count, centerX, textY, textPaint);
    }

    /*
    Sets the count (i.e notifications) to display.
     */
    public void setCount(String count) {
        this.count = count;

        // Only draw a badge if there are notifications.
        willDraw = !count.equalsIgnoreCase("0");
        invalidateSelf();
    }

    @Override
    public void setAlpha(int alpha) {
        // do nothing
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        // do nothing
    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    public static void setCartBadgeCount(Context context, LayerDrawable icon, String count) {
        BadgeDrawable badge;

        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);

        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }
}