package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LiveData;
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
import java.util.List;

public class PlanActivity extends AppCompatActivity {

    /**
     * Structure for storing a date's corresponding TextViews for the title and descriptions.
     */
    private class DateView implements Observer<List<UserActivityEntity>> {

        public final Calendar day;
        public final TextView title;
        public final TextView content;
        LiveData<List<UserActivityEntity>> data;

        public DateView(Calendar day, TextView title, TextView content) {
            this.day = (Calendar) day.clone();
            this.title = title;
            this.content = content;

            title.setText(day.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, getResources().getConfiguration().locale));
        }

        @Override
        public void onChanged(@NonNull final List<UserActivityEntity> activities) {
            final StringBuilder activityBuffer = new StringBuilder();

            //Compiles a list of activity descriptions for a specific date
            for (int i = 0; i < activities.size(); ++i) {
                activityBuffer.append(activities.get(i).getDesc());
                if (i < activities.size() - 1) {
                    activityBuffer.append('\n');
                }
            }

            content.setText(activityBuffer);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        //Initializing TextViews
        mTextMessage = (TextView) findViewById(R.id.message);

        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Store today's date with a time of 0 for relative date calculation.
        final Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        // Load the views of of the currently displayed dates.
        dateViews.add(new DateView(
            today,
            (TextView) findViewById(R.id.day_of_week_1),
            (TextView) findViewById(R.id.textview_day_1)));
        today.add(Calendar.DAY_OF_MONTH, -1);
        dateViews.add(new DateView(
            today,
            (TextView) findViewById(R.id.day_of_week_2),
            (TextView) findViewById(R.id.textview_day_2)));
        today.add(Calendar.DAY_OF_MONTH, -1);
        dateViews.add(new DateView(
            today,
            (TextView) findViewById(R.id.day_of_week_3),
            (TextView) findViewById(R.id.textview_day_3)));
        today.add(Calendar.DAY_OF_MONTH, -1);
        dateViews.add(new DateView(
            today,
            (TextView) findViewById(R.id.day_of_week_4),
            (TextView) findViewById(R.id.textview_day_4)));
        today.add(Calendar.DAY_OF_MONTH, -1);

        //Initializing ViewModel
        actViewModel = ViewModelProviders.of(this).get(UserActivityViewModel.class);
        DELETEME();
        subscribeUIActivities();
    }

    /**
     * Retrieves activity descriptions for the current and previous dates
     */
    private void subscribeUIActivities() {
        for (DateView dv : dateViews) {
            actViewModel.getActivitiesByDate(dv.day.getTime()).observe(this, dv);
        }
    }

    private void cycle() {
        for (DateView dv : dateViews) {
            dv.data.removeObservers(this);
        }
    }

    private void DELETEME() {
        Thread t = new Thread() {
            public void run() {
                actViewModel.db.clearAllTables();

                // Create 5 User Activity Dates
                final int[] userActivityYear = {2019, 2019, 2019, 2019, 2019};
                final int[] userActivityMonth = {3, 3, 3, 3, 3};
                final int[] userActivityDay = {20, 24, 24, 23, 22};

                // Create 5 User Activity Descriptions
                final String[] userActivityDesc = {"This is a", "test of the", "emergency", "broadcast",
                    "system."};

                // Insert the entities.
                for (int x = 0; x < 5; x++) {
                    actViewModel.insertActivity(userActivityYear[x], userActivityMonth[x], userActivityDay[x],
                        userActivityDesc[x]);
                }

                try {
                    Thread.sleep(1500);
                } catch (Exception e) { e.printStackTrace(); }
            }
        };
        t.start();
        try {
            t.wait();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
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

    private final ArrayList<DateView> dateViews = new ArrayList<DateView>();
    private UserActivityViewModel actViewModel;

    private TextView mTextMessage;
}