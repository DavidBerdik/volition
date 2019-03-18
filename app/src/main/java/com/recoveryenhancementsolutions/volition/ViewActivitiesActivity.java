package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

    private UserActivityHistoryViewModel actViewModel;

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

        //Initializing TextViews
        mTextMessage = (TextView)findViewById(R.id.message);

        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Store today's date with a time of 0 for relative date calculation.
        today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        // Load the views of of the currently displayed dates.
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

        //Initializing ViewModel
        actViewModel = ViewModelProviders.of(this).get(UserActivityHistoryViewModel.class);
        subscribeUIActivities();
    }

    /**
     * Sets the activity text for the corresponding views on the calendar.
     * @param day The day on the calendar to update.  Should be before or on the date the activity loaded.
     * @param descs All the activities that were done on the given day.
     */
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

    /**
     * Retrieves activity descriptions for the current and previous dates
     */
    private void subscribeUIActivities() {
        Date date = new Date();
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_WEEK);

        //Separate Observers for each date
        for (int i = 0; i < 7; i++) {
            actViewModel.getActivitiesByDate(year, month, day).observe(this, new Observer<List<UserActivityEntity>>() {
                @Override
                public void onChanged(@NonNull final List<UserActivityEntity> activities) {
                    final ArrayList<String> activityList = new ArrayList<String>();
                    //Compiles a list of activity descriptions for a specific date
                    for (UserActivityEntity activity : activities)
                        activityList.add(activity.getDesc());
                    updateDayActivities(cal, activityList);
                }
            });
            //Decrements the current date and updates year, month, and day (for later Observers)
            cal.add(Calendar.DAY_OF_MONTH, -1);
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DAY_OF_WEEK);
        }
    }
}