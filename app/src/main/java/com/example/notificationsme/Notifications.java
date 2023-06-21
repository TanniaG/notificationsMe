package com.example.notificationsme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;

import java.util.Calendar;

public class Notifications extends AppCompatActivity {


    EditText date;
    DatePickerDialog datePickerDialog;

    EditText userTopic;
    EditText userAmount;
    EditText userNote  ;
    EditText userRemind;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        userTopic = findViewById(R.id.topic);
        userAmount = findViewById(R.id.amount);
        userNote = findViewById(R.id.note);
//        userRemind = findViewById(R.id.daypicker);

       // getSupportActionBar().hide();


    /**    TimePicker tp = (TimePicker) this.findViewById(R.id.timePicker);
        tp.setIs24HourView(true);

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                //String minAndsec = hourOfDay + ":" + minute;

                int selectedHour = hourOfDay;
                int selectedMinute = minute;

                // Format the time as a string
                String selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                // display a toast with changed values of time picker
               // Toast.makeText(getApplicationContext(), "time changed ", Toast.LENGTH_SHORT).show();

                // set the current time in text view
               // tp.getText("Time is :: " + hourOfDay + " : " + minute);
            }
        }); **/

        // initiate the date picker and a button
        date = (EditText) findViewById(R.id.simpleDatePicker);
        // perform click event on edit text

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Notifications.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        //Day buttons
        ToggleButton tD;
        ToggleButton tL;
        ToggleButton tM;
        ToggleButton tMi;
        ToggleButton tJ;
        ToggleButton tV;
        ToggleButton tS;

        tD = (ToggleButton) findViewById(R.id.tD);
        tL = (ToggleButton) findViewById(R.id.tL);
        tM = (ToggleButton) findViewById(R.id.tM);
        tMi = (ToggleButton) findViewById(R.id.tMi);
        tJ = (ToggleButton) findViewById(R.id.tJ);
        tV = (ToggleButton) findViewById(R.id.tV);
        tS = (ToggleButton) findViewById(R.id.tS);

        String markedButtons= "";
        //Check individual items.
        if(tD.isChecked()){
            markedButtons +="D,";
        }
        if(tL.isChecked()){
            markedButtons +="L,";
        }
        if(tM.isChecked()){
            markedButtons +="M,";
        }
        if(tMi.isChecked()){
            markedButtons +="Mi,";
        }
        if(tJ.isChecked()){
            markedButtons +="J,";
        }
        if(tV.isChecked()){
            markedButtons +="V,";
        }
        if(tS.isChecked()){
            markedButtons +="S";
        }
        Toast.makeText(this, markedButtons, Toast.LENGTH_SHORT).show();

        databaseReference = FirebaseDatabase.getInstance().getReference(" Bills");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.side_menu, menu);

        MenuItem saveItem = menu.findItem(R.id.done);

        saveItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                // Perform save operation here
                TimePicker tp = findViewById(R.id.timePicker);
                tp.setIs24HourView(true);

                int selectedHour = tp.getHour();
                int selectedMinute = tp.getMinute();

                String selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                String noteTopic = userTopic.getText().toString();
                String noteAmount = userAmount.getText().toString();
                String noteNote = userNote.getText().toString();
                String noteDate = date.getText().toString();

                if(noteTopic.isEmpty()){

                    userTopic.setError("Topic is Required");
                }

                UserDatabase userDatabase = new UserDatabase();
                userDatabase.setTopics(noteTopic);
                userDatabase.setAmounts(noteAmount);
                userDatabase.setNotes(noteNote);
                userDatabase.setDates(noteDate);
                userDatabase.setTimes(selectedTime);
                userDatabase.setTimestamp(Timestamp.now());

                saveToFirebase(userDatabase);
                return true;
            }
        });

        return true;

    }

     void saveToFirebase(UserDatabase userDatabase) {

        DocumentReference documentReference;
        documentReference = Utility.getCollectinReferenceForBills().document();

        documentReference.set(userDatabase).addOnCompleteListener(new OnCompleteListener<Void>(){
             @Override
             public void onComplete(@NonNull Task<Void> task){

                 if(task.isSuccessful()){
                    //note is added
                     Utility.showToast(Notifications.this, "Bill added succesfully");
                     finish();
                 }
                 else{

                     Utility.showToast(Notifications.this, "Not Added. Try Again");
                 }

             }

         });


    }


    /**  @Override
       public boolean onOptionsItemSelected(MenuItem item) {

           switch (item.getItemId()) {
               case R.id.done:
                   String noteTopic = userTopic.getText().toString();
                   String noteAmount = userAmount.getText().toString();
                   String noteNote = userNote.getText().toString();
                   String noteDate = date.getText().toString();
                   //String noteTime = tp.getText().toString();
                   String noteRemind = userRemind.getText().toString();
                   //String selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                   String selectedTime ;

                   if(noteTopic.isEmpty()){

                       userTopic.setError("Topic is Required");
                   }
                   else if (noteAmount.isEmpty()) {
                       userTopic.setError("Please set amount");
                   }

                   UserDatabase userDatabase = new UserDatabase();
                   userDatabase.setTopics(noteTopic);
                   userDatabase.setAmounts(noteAmount);
                   userDatabase.setDates(noteDate);
                   userDatabase.setTimes(selectedTime);


                   break;
           }

           return super.onOptionsItemSelected(item);
       } **/

}
