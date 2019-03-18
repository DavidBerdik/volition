package com.recoveryenhancementsolutions.volition;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class ViewActivitiesActivity extends AppCompatActivity {
    /**
     * Structure for storing a date's corresponding TextViews for the title and descriptions.
     */
    private class DateView {
        public TextView title;
        public TextView content;

        public DateView(TextView title, TextView content) {
            this.title   = title;
            this.content = content;
        }
    }

    /**
     * When the activity starts, the calendar displays only display the past
     * dates and "today".  In the event that "today" changes while the activity is running,
     * pushActivity would not know which View to update.
     */
    private Calendar today;
    private ArrayList<DateView> dateViews = new ArrayList<DateView>();

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_activities);

        mTextMessage = (TextView) findViewById(R.id.message);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        dateViews.add(new DateView(
                (TextView)findViewById(R.id.labelToday),
                (TextView)findViewById(R.id.detailsToday)));
        dateViews.add(new DateView(
                (TextView)findViewById(R.id.labelYesterday),
                (TextView)findViewById(R.id.detailsYesterday)));
        dateViews.add(new DateView(
                (TextView)findViewById(R.id.label2DaysAgo),
                (TextView)findViewById(R.id.details2DaysAgo)));
        dateViews.add(new DateView(
                (TextView)findViewById(R.id.label3DaysAgo),
                (TextView)findViewById(R.id.details3DaysAgo)));
        dateViews.add(new DateView(
                (TextView)findViewById(R.id.label4DaysAgo),
                (TextView)findViewById(R.id.details4DaysAgo)));
        dateViews.add(new DateView(
                (TextView)findViewById(R.id.label5DaysAgo),
                (TextView)findViewById(R.id.details5DaysAgo)));
        dateViews.add(new DateView(
                (TextView)findViewById(R.id.label6DaysAgo),
                (TextView)findViewById(R.id.details6DaysAgo)));
    }

    private void updateDayActivities(final Calendar day, ArrayList<String> descs) {
        Calendar dayNoTime; // Activity day with the time set to 0 for accurate millisecond difference.
        int dist; // How many days ago these activities occured.
        StringBuilder activityBuffer = new StringBuilder();

        dayNoTime = (Calendar)day.clone();
        dayNoTime.set(Calendar.HOUR_OF_DAY, 0);
        dayNoTime.set(Calendar.MINUTE, 0);
        dayNoTime.set(Calendar.SECOND, 0);
        dayNoTime.set(Calendar.MILLISECOND, 0);
        dist = (int)TimeUnit.MILLISECONDS.toDays(today.getTimeInMillis() - dayNoTime.getTimeInMillis());

        if (dist < 0 || dist >= dateViews.size()) return;

        for (int i = 0; i < descs.size(); ++i) {
            activityBuffer.append(descs.get(i));
            if (i < descs.size() - 1) activityBuffer.append('\n');
        }
        dateViews.get(dist).content.setText(activityBuffer);
    }
}