package com.recoveryenhancementsolutions.volition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;
/**
 * Class for running only create_profile.xml
 */
public class CreateProfile extends AppCompatActivity {
    Spinner spinner, spin2;
    EditText editText, editText2, editText3,editText4;
    Button send;
    RadioGroup rg, rg2;
    RadioButton rb, rb2;
    ArrayAdapter<CharSequence> adapter,adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);
        editText = (EditText) findViewById(R.id.name);
        editText2 = (EditText) findViewById(R.id.date_of_birth);
        editText3 = (EditText) findViewById(R.id.enter_other);
        editText4 = (EditText) findViewById(R.id.clean_date);


        rg = findViewById(R.id.user_type);
        rg2 = findViewById(R.id.drug_selection);

        spinner = (Spinner)findViewById(R.id.gender_spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.create_profile_gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        spin2 = (Spinner)findViewById(R.id.use_type_spinner);
        adapter2 = ArrayAdapter.createFromResource(this,R.array.create_profile_use_type_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter2);
        //spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //@Override
        // public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //  Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)Toast.LENGTH_LONG);
        // }
        //);
        send = (Button) findViewById(R.id.record_button);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stuff to be passed here

                String name = editText.getText().toString();
                String DOB = editText2.getText().toString();
                String Gender = spinner.getSelectedItem().toString();
                String Subdisorder = spin2.getSelectedItem().toString();
                int rID = rg.getCheckedRadioButtonId();
                int rID2 = rg2.getCheckedRadioButtonId();

                rb = findViewById(rID);
                rb2 = findViewById(rID2);

                String whatareyou = rb.getText() + "";
                String whatdrug = rb2.getText()+"";

                if(whatdrug.equals("Other")){
                    whatdrug = editText3.getText().toString();
                }

                String CleanDate = editText3.getText().toString();

                Intent intent = new Intent(CreateProfile.this,DisplayProfile.class);
                //more stuff here
                intent.putExtra("Name", name);
                intent.putExtra("Date of Birth", DOB );
                intent.putExtra("Gender", Gender);
                intent.putExtra("Type of Person",whatareyou);
                intent.putExtra("Drug of Choice",whatdrug);
                intent.putExtra("Disorder", Subdisorder );
                intent.putExtra("CleanDate", CleanDate );
                startActivity(intent);





            }
        });

    }

}

//}
