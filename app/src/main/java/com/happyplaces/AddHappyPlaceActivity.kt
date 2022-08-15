package com.happyplaces

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_happy_place.*
import java.text.SimpleDateFormat
import java.util.*

class AddHappyPlaceActivity : AppCompatActivity(),View.OnClickListener {

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    // TODO(Step 2 : Creating the variables of Calender Instance and DatePickerDialog listener to use it for date selection.)
    // START
    /**
     * An variable to get an instance calendar using the default time zone and locale.
     */
    private var cal = Calendar.getInstance()

    /**
     * A variable for DatePickerDialog OnDateSetListener.
     * The listener used to indicate the user has finished selecting a date. Which we will be initialize later on.
     */
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    // END

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)

        // This is used to align the xml view to this class
        setContentView(R.layout.activity_add_happy_place)

        // TODO (Step 2: Setting up the action bar using the toolbar and making enable the home back button and also adding the click of it.)
        // START
        setSupportActionBar(toolbar_add_place) // Use the toolbar to set the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // This is to use the home back button.
        // Setting the click event to the back button
        toolbar_add_place.setNavigationOnClickListener {
            onBackPressed()
        }
        // END
        // TODO(Step 3 : Initializing the dateSetListener.)
        // START
        // https://www.tutorialkart.com/kotlin-android/android-datepicker-kotlin-example/
        // create an OnDateSetListener
        dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                // TODO(Step 9 : Called a function as updateDateInView where after selecting a date from date picker is populated in the UI component.)
                // START
                updateDateInView()
                // END
            }
        // END

        // TODO(Step 6 : We have extended the onClickListener above and the override method as onClick added and here we are setting a listener to date edittext.)
        // START
        et_date.setOnClickListener(this)
        // END
    }
    // TODO(Step 5 : This is a override method after extending the onclick listener interface.)
    // START
    override fun onClick(v: View?) {
        when (v!!.id) {
            // TODO(Step 7 : Launching the datepicker dialog on click of date edittext.)
            // START
            R.id.et_date -> {
                DatePickerDialog(
                    this@AddHappyPlaceActivity,
                    dateSetListener, // This is the variable which have created globally and initialized in setupUI method.
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR), // Here the cal instance is created globally and used everywhere in the class where it is required.
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            // END
        }
    }
    // END

    // TODO(Step 8 : Created a function as updateDateInView where after selecting a date from date picker is populated in the UI component.)
    // START
    /**
     * A function to update the selected date in the UI with selected format.
     * This function is created because every time we don't need to add format which we have added here to show it in the UI.
     */
    private fun updateDateInView() {
        val myFormat = "dd.MM.yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault()) // A date format
        et_date.setText(sdf.format(cal.time).toString()) // A selected date using format which we have used is set to the UI.
    }
    // END
}