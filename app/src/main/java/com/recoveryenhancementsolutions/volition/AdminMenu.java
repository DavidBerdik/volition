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
import android.widget.Toast;
import java.util.Calendar;
public class AdminMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.edit_profile) {toEditProfile();}

        else if (id == R.id.edit_treatment) {toEditTreatment();}
        else if (id == R.id.ques_class) {toViewQuestionnaire();}
        else if (id == R.id.retake_ques) {toRetakeQuestionnaire();}
        else if (id == R.id.clinical_overview) {toClinicalOverview();}

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void toEditProfile() {

        DemographicDataViewModel demographicDataViewModel = new DemographicDataViewModel(getApplication());//view model instance
        int age;
        try {
            age = Integer.parseInt(demographicDataViewModel.getPatientAge().getValue() + "");
        }
        catch(NumberFormatException e){
            age = 0;
        }

        String name = demographicDataViewModel.getPatientName().getValue();
        Calendar bcalendar = Calendar.getInstance();

        int year;
        int month;
        int day;
        try {
            bcalendar.set(Calendar.YEAR, demographicDataViewModel.getDOB().getValue().getYear());
            bcalendar.set(Calendar.MONTH, demographicDataViewModel.getDOB().getValue().getMonth());
            bcalendar.set(Calendar.DAY_OF_MONTH, demographicDataViewModel.getDOB().getValue().getDay());

            //DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
            year = bcalendar.get((Calendar.YEAR));
            month = bcalendar.get((Calendar.MONTH));
            day = bcalendar.get((Calendar.DAY_OF_MONTH));
        }
        catch(Exception e){
            year = 1950;
            month = 0;
            day = 0;
        }

        boolean heroin = Boolean.parseBoolean(demographicDataViewModel.getUseHeroin().getValue()+"");
        boolean Opiate = Boolean.parseBoolean(demographicDataViewModel.getUseOpiateOrSynth().getValue()+"");
        boolean crackorcocaine = Boolean.parseBoolean(demographicDataViewModel.getUseCrackOrCocaine().getValue()+"");
        boolean marajuana = Boolean.parseBoolean(demographicDataViewModel.getUseMarijuana().getValue()+"");
        boolean meth = Boolean.parseBoolean(demographicDataViewModel.getUseMethamphetamine().getValue()+"");
        boolean benzo = Boolean.parseBoolean(demographicDataViewModel.getUseBenzo().getValue()+"");
        boolean Nonbenzo = Boolean.parseBoolean(demographicDataViewModel.getUseNonBenzo().getValue()+"");
        boolean barb = Boolean.parseBoolean(demographicDataViewModel.getUseBarbitures().getValue()+"");
        boolean inhalant = Boolean.parseBoolean(demographicDataViewModel.getUseInhalants().getValue()+"");
        boolean useother =  !(demographicDataViewModel.getUseOtherDrug().getValue() == null);
        boolean alcoholDisorder = Boolean.parseBoolean(demographicDataViewModel.getAlcoholDisorder().getValue()+"");
        boolean alcohol = Boolean.parseBoolean(demographicDataViewModel.getUseAlcohol().getValue()+"");
        boolean Opioddisorder = Boolean.parseBoolean(demographicDataViewModel.getOpioidDisorder().getValue()+"");
        boolean Support = !Boolean.parseBoolean(demographicDataViewModel.getInRecovery().getValue()+"");
        boolean Client = Boolean.parseBoolean(demographicDataViewModel.getInRecovery().getValue()+"");
        String UseInfo = demographicDataViewModel.getUseOtherDrug().getValue();

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
        String getGender = demographicDataViewModel.getPatientGender().getValue();
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
