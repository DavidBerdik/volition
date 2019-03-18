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
import java.util.List;
import java.util.Date;
import java.util.Calendar;

public class ViewActivitiesActivity extends AppCompatActivity {

    private TextView detailToday, detail1Ago, detail2Ago, detail3Ago, detail4Ago, detail5Ago,
                     detail6Ago, labelToday, label1Ago, label2Ago, label3Ago, label4Ago, label5Ago,
                     label6Ago; //Might be reduced in the future
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
        mTextMessage = (TextView) findViewById(R.id.message);
        labelToday = (TextView) findViewById(R.id.labelToday);
        label1Ago = (TextView) findViewById(R.id.labelYesterday);
        label2Ago = (TextView) findViewById(R.id.label2DaysAgo);
        label3Ago = (TextView) findViewById(R.id.label3DaysAgo);
        label4Ago = (TextView) findViewById(R.id.label4DaysAgo);
        label5Ago = (TextView) findViewById(R.id.label5DaysAgo);
        label6Ago = (TextView) findViewById(R.id.label6DaysAgo);

        detailToday = (TextView) findViewById(R.id.detailsToday);
        detail1Ago = (TextView) findViewById(R.id.detailsYesterday);
        detail2Ago = (TextView) findViewById(R.id.details2DaysAgo);
        detail3Ago = (TextView) findViewById(R.id.details3DaysAgo);
        detail4Ago = (TextView) findViewById(R.id.details4DaysAgo);
        detail5Ago = (TextView) findViewById(R.id.details5DaysAgo);
        detail6Ago = (TextView) findViewById(R.id.details6DaysAgo);

        //Initializing ViewModel
        actViewModel = ViewModelProviders.of(this).get(UserActivityHistoryViewModel.class);

        subscribeUIActivities();
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void subscribeUIActivities() {
        final ArrayList<String> activityList = new ArrayList<String>();
        Date date = new Date();
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_WEEK);

        for (int i = 0; i < 7; i++) {
            actViewModel.getActivitiesByDate(year, month, day).observe(this, new Observer<List<UserActivityEntity>>() {
                @Override
                public void onChanged(@NonNull final List<UserActivityEntity> activities) {
                    for (UserActivityEntity activity : activities)
                        activityList.add(activity.getDesc());
                    updateDayActivities(cal, activityList);
                }
            });
            activityList.clear();
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
    }

    private void updateDayActivities(final Calendar day, ArrayList<String> descs) {
        //Noah's code
    }
}