package com.oldtree.mywidgetdemo;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Timer mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new MyTimer(context,appWidgetManager),1,60000);
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private class MyTimer extends TimerTask{
        RemoteViews remoteViews;
        AppWidgetManager appWidgetManager;
        ComponentName thisWidget;

        public MyTimer(Context context, AppWidgetManager appWidgetManage){
            this.appWidgetManager = appWidgetManage;
            remoteViews = new RemoteViews(context.getPackageName(),R.layout.activity_main);
            thisWidget=new ComponentName(context,MainActivity.class);

        }

        public void run(){
            Date date = new Date();
            Calendar calendar = new GregorianCalendar(2015,11,8);
            long days = (((calendar.getTimeInMillis()-date.getTime())/1000))/86400;
            remoteViews.setTextViewText(R.id.text, "距iFavine京东众筹还有 " + days+" 天");
            appWidgetManager.updateAppWidget(thisWidget, remoteViews);
        }
    }
}
