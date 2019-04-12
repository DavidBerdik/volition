package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LiveData;
import android.os.Bundle;
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
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class AdminMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Button send;
        final Button send2;
        final Button send3;
        send = findViewById(R.id.button);
        send2 = findViewById(R.id.button2);
        send3 = findViewById(R.id.button3);

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

        if (id == R.id.edit_profile) {toEditProfile();}

        else if (id == R.id.edit_treatment) {toEditTreatment();}
        else if (id == R.id.ques_class) {toViewQuestionnaire();}
        else if (id == R.id.retake_ques) {toRetakeQuestionnaire();}
        else if (id == R.id.clinical_overview) {toClinicalOverview();}

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void toEditProfile() {


        int age = 12;
        String name = "yeet";
        Calendar bcalendar = Calendar.getInstance();
        bcalendar.set(Calendar.YEAR, 2015);
        bcalendar.set(Calendar.MONTH, 11);
        bcalendar.set(Calendar.DAY_OF_MONTH, 28);


        //DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
        int year = bcalendar.get((Calendar.YEAR));
        int month = bcalendar.get((Calendar.MONTH));
        int day = bcalendar.get((Calendar.DAY_OF_MONTH));

        // String test = calendar.set(Calendar.YEAR, 2015);
        //LiveData<Date> CleanDate = demographicDataDao.queryLastCleanDate();
        //boolean recovery = demographicDataDao.queryIsInRecovery();
        //boolean heroin = demographicDataDao.queryIsUsingHeroin();
        //boolean Opiate = demographicDataDao.queryIsUsingOpiateOrSynth();
        //boolean crackorcocaine = demographicDataDao.queryIsUsingCrackOrCo();
        //boolean marajuana = demographicDataDao.queryIsUsingMarijuana();
        //boolean meth = demographicDataDao.queryIsUsingMeth();
        //boolean benzo = demographicDataDao.queryIsUsingBenzo();
        //boolean Nonbenzo = demographicDataDao.queryIsUsingNonBenzoTranq();
        //boolean barb = demographicDataDao.queryIsUsingBarbOrHypno();
        //boolean inhalant = demographicDataDao.queryIsUsingInhalants();
        //String useother = demographicDataDao.queryOtherUsedDrugs();
        //boolean alcoholDisorder = demographicDataDao.queryIsHavingAlcoholDisorder();
        //boolean alcohol = demographicDataDao.queryIsUsingAlcohol();
        //boolean Opioddisorder = demographicDataDao.queryIsHavingAlcoholDisorder();
        boolean heroin = false;
        boolean Opiate = false;
        boolean crackorcocaine = false;
        boolean marajuana = false;
        boolean meth = false;
        boolean benzo = true;
        boolean Nonbenzo = false;
        boolean barb = false;
        boolean inhalant = false;
        boolean useother = false;
        boolean alcoholDisorder = true;
        boolean alcohol = false;
        boolean Opioddisorder = false;
        boolean Support = true;
        boolean Client = false;
        String UseInfo = "REEEE";

        int flag =1;

        String test = "yeeted off roof";
        Intent intent = new Intent(AdminMenu.this, CreateProfileActivity.class);

        intent.putExtra("age", age);
        intent.putExtra("name", name);
       // intent.putExtra("DOB", datebirth);
        intent.putExtra("BYear", year);
        intent.putExtra("BMonth", month);
        intent.putExtra("BDay", day);
        intent.putExtra("heroin", heroin);
        intent.putExtra("flag", flag);

        intent.putExtra("condition", test);


        Calendar Cleancalendar = Calendar.getInstance();

        Cleancalendar.set(Calendar.YEAR, 2016);
        Cleancalendar.set(Calendar.MONTH, 3);
        Cleancalendar.set(Calendar.DAY_OF_MONTH, 29);


        //DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
        int Cleanyear = Cleancalendar.get((Calendar.YEAR));
        int Cleanmonth = Cleancalendar.get((Calendar.MONTH));
        int Cleanday = Cleancalendar.get((Calendar.DAY_OF_MONTH));
        intent.putExtra("CYear", Cleanyear);
        intent.putExtra("CMonth", Cleanmonth);
        intent.putExtra("CDay", Cleanday);

        //intent.putExtra("CleanDate", CleanDate.getValue());
       // intent.putExtra("recovery", recovery);


        intent.putExtra("heroin", heroin);
        intent.putExtra("Opiate", Opiate);
        intent.putExtra("crackorcocaine", crackorcocaine);
        intent.putExtra("marajuana", marajuana);
        intent.putExtra("meth", meth);
        intent.putExtra("benzo", benzo);
        intent.putExtra("Nonbenzo", Nonbenzo);
        intent.putExtra("barb", barb);
        intent.putExtra("inhalant", inhalant);
        intent.putExtra("useother", useother);
        intent.putExtra("alcohol",alcohol);
        intent.putExtra("condition", UseInfo);
        intent.putExtra("family", Support);
        intent.putExtra("recovery", Client);
        String getGender = "Female";
        intent.putExtra("gender", getGender);


        intent.putExtra("alcoholDisorder", alcoholDisorder);
        intent.putExtra("Opioddisorder", Opioddisorder);



      //  Toast.makeText(getApplicationContext(), "vlaue is "+DOB, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "before change"+ year +" " + month + " " + day , Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "benzo is "+ benzo, Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    private void toEditTreatment() {
        Intent intent = new Intent(AdminMenu.this, EditTreatmentActivity.class);
        startActivity(intent);
    }

    private void toViewQuestionnaire() {
        Intent intent = new Intent(AdminMenu.this, ViewSeverityLevelActivity.class);
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
