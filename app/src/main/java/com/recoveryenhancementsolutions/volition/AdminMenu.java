package com.recoveryenhancementsolutions.volition;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AdminMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.edit_profile) {
            toEditProfile();
        } else if (id == R.id.edit_treatment) {
            toEditTreatment();
        } else if (id == R.id.ques_class) {
            toViewQuestionnaire();
        } else if (id == R.id.retake_ques) {
            toRetakeQuestionnaire();
        } else if (id == R.id.clinical_overview) {
            toClinicalOverview();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void toEditProfile() {
        final Intent intent = new Intent(AdminMenu.this, CreateProfileActivity.class);
/*
        DemographicDataViewModel demographicDataViewModel = new DemographicDataViewModel(
            getApplication());//view model instance
        int age;
        try {
            age = Integer.parseInt(demographicDataViewModel.getPatientAge().getValue() + "");
        } catch (NumberFormatException e) {
            age = 0;
        }
        String name = demographicDataViewModel.getPatientName().getValue();
        Calendar bcalendar = Calendar.getInstance();
        int year;
        int month;
        int day;
        try {
            Date date = demographicDataViewModel.getDOB().getValue();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DAY_OF_MONTH);
            bcalendar.set(Calendar.YEAR, year);
            bcalendar.set(Calendar.MONTH, month);
            bcalendar.set(Calendar.DAY_OF_MONTH, day);
            year = bcalendar.get((Calendar.YEAR));
            month = bcalendar.get((Calendar.MONTH));
            day = bcalendar.get((Calendar.DAY_OF_MONTH));
        } catch (Exception e) {
            year = 1950;
            month = 0;
            day = 0;
        }
        final boolean heroin = Boolean
            .parseBoolean(demographicDataViewModel.getUseHeroin().getValue() + "");
        final boolean opiate = Boolean
            .parseBoolean(demographicDataViewModel.getUseOpiateOrSynth().getValue() + "");
        final boolean crackOrCocaine = Boolean
            .parseBoolean(demographicDataViewModel.getUseCrackOrCocaine().getValue() + "");
        final boolean marajuana = Boolean
            .parseBoolean(demographicDataViewModel.getUseMarijuana().getValue() + "");
        final boolean meth = Boolean
            .parseBoolean(demographicDataViewModel.getUseMethamphetamine().getValue() + "");
        final boolean benzo = Boolean
            .parseBoolean(demographicDataViewModel.getUseBenzo().getValue() + "");
        final boolean nonbenzo = Boolean
            .parseBoolean(demographicDataViewModel.getUseNonBenzo().getValue() + "");
        final boolean barb = Boolean
            .parseBoolean(demographicDataViewModel.getUseBarbitures().getValue() + "");
        final boolean inhalant = Boolean
            .parseBoolean(demographicDataViewModel.getUseInhalants().getValue() + "");
        final boolean useother = !(demographicDataViewModel.getUseOtherDrug().getValue() == null);
        final boolean alcoholDisorder = Boolean
            .parseBoolean(demographicDataViewModel.getAlcoholDisorder().getValue() + "");
        final boolean alcohol = Boolean
            .parseBoolean(demographicDataViewModel.getUseAlcohol().getValue() + "");
        final boolean opiodDisorder = Boolean
            .parseBoolean(demographicDataViewModel.getOpioidDisorder().getValue() + "");
        final boolean support = !Boolean
            .parseBoolean(demographicDataViewModel.getInRecovery().getValue() + "");
        final boolean client = Boolean
            .parseBoolean(demographicDataViewModel.getInRecovery().getValue() + "");
        final String useInfo = demographicDataViewModel.getUseOtherDrug().getValue();

        final int flag = 1;
        final Intent intent = new Intent(AdminMenu.this, CreateProfileActivity.class);

        intent.putExtra("age", age);
        intent.putExtra("name", name);
        intent.putExtra("bYear", year);
        intent.putExtra("bMonth", month);
        intent.putExtra("bDay", day);
        intent.putExtra("heroin", heroin);
        intent.putExtra("flag", flag);
        Calendar Cleancalendar = Calendar.getInstance();
        Cleancalendar.set(Calendar.YEAR, 2016);
        Cleancalendar.set(Calendar.MONTH, 3);
        Cleancalendar.set(Calendar.DAY_OF_MONTH, 29);
        final int cleanYear = Cleancalendar.get((Calendar.YEAR));
        final int cleanMonth = Cleancalendar.get((Calendar.MONTH));
        final int cleanDay = Cleancalendar.get((Calendar.DAY_OF_MONTH));
        intent.putExtra("cYear", cleanYear);
        intent.putExtra("cMonth", cleanMonth);
        intent.putExtra("cDay", cleanDay);
        intent.putExtra("heroin", heroin);
        intent.putExtra("opiate", opiate);
        intent.putExtra("crackorcocaine", crackOrCocaine);
        intent.putExtra("marajuana", marajuana);
        intent.putExtra("meth", meth);
        intent.putExtra("benzo", benzo);
        intent.putExtra("nonbenzo", nonbenzo);
        intent.putExtra("barb", barb);
        intent.putExtra("inhalant", inhalant);
        intent.putExtra("useother", useother);
        intent.putExtra("alcohol", alcohol);
        intent.putExtra("condition", useInfo);
        intent.putExtra("family", support);
        intent.putExtra("recovery", client);
        String getGender = demographicDataViewModel.getPatientGender().getValue();
        intent.putExtra("gender", getGender);
        intent.putExtra("alcoholDisorder", alcoholDisorder);
        intent.putExtra("opiodDisorder", opiodDisorder);
        */
        startActivity(intent);
    }
    private void toEditTreatment() {
        Intent intent = new Intent(AdminMenu.this, TreatmentPlanActivity.class);
        startActivity(intent);
    }

    private void toViewQuestionnaire() {
        Intent intent = new Intent(AdminMenu.this, QuestionnaireConfirmActivity.class);
        startActivity(intent);
    }

    private void toRetakeQuestionnaire() {
        Intent intent = new Intent(AdminMenu.this, QuestionnaireActivity.class);
        startActivity(intent);
    }

    private void toClinicalOverview() {
        Intent intent = new Intent(AdminMenu.this, ClinicalOverviewActivity.class);
        startActivity(intent);
    }


}
