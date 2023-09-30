package com.socketfit.androidapp.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.radiobutton.MaterialRadioButton
import com.socketfit.androidapp.R
import com.socketfit.androidapp.databinding.ActivityMainBinding
import com.socketfit.androidapp.interfaces.CustomBottomSheetCallBacks
import com.socketfit.androidapp.interfaces.CustomOnBackPressed
import com.socketfit.androidapp.util.BottomSheetCalls
import com.socketfit.androidapp.util.SocketFitApplication


/**
 * Main activity
 *
 * @constructor Create empty Main activity
 */
class MainActivity : AppCompatActivity() {

    //this object reference is to connect the behaviour with bottom menu and fragment
    lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayoutCompat>

    lateinit var customBottomSheetCalls: BottomSheetCalls

    //activity binding reference
    private lateinit var binding: ActivityMainBinding

    //custom onBackPressed class reference to handle back button press and execute custom events
    var callback: CustomOnBackPressed? = null

    //activity view model reference
    lateinit var activityViewModel: MainActivityViewModel

    //navigation controller reference to handle navigation of different fragments
    lateinit var navController: NavController

    //oncreate for activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        //assigning the layout with binding to the reference we have declared above
        binding = ActivityMainBinding.inflate(layoutInflater)

        //assigning the layout to the MainActivity
        setContentView(binding.root)

        //initializing the viewModel to MainActivity
        activityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        //asssigning the binding NavigationView to the navView Object
        val navView: BottomNavigationView = binding.navView

        //getting the controller so that we can navigate to any fragment declared in the mobile_navigation.xml
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragment_dashboard,
                R.id.navigation_sensor_data,
                R.id.navigation_share,
                R.id.navigation_settings
            )
        )

        //attaching the navcontroller to the navView so that on clicking the bottom options will navigate to the appropriate fragment
        navView.setupWithNavController(navController)
        setBottomSheet()

        customBottomSheetCalls = BottomSheetCalls(object : CustomBottomSheetCallBacks {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }

            override fun onSlide() {

            }
        })

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetLayout)


        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    binding.navView.visibility = View.VISIBLE
                    binding.viewShadow.visibility = View.GONE
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.e("", "")
            }
        })
    }

    /**
     * Show bottom sheet layout
     *
     */
    var slideOffSet = 0
    fun showBottomSheetLayout() {

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        binding.navView.visibility = View.GONE
        binding.viewShadow.visibility = View.VISIBLE
        //  bottomSheet.show(this@MainActivity.supportFragmentManager, "ModalBottomSheet")

    }

    /**
     * Set on back pressed callback
     *
     * @param callback
     */
    fun setOnBackPressedCallback(callback: CustomOnBackPressed) {
        this.callback = callback
    }

    //this is the last selecte option from activity menu, initially its null and once you click any item in the activity menu it will be assigned to this variable
    var lastSelectedButton: MaterialRadioButton? = null

    /**
     * Reset activity options, when everything is not initialized and no options are selected
     *
     */
    fun resetActivityOptions() {

        binding.llBottomSheet.radioBtnResting.isChecked = false
        binding.llBottomSheet.radioBtnWalking.isChecked = false
        binding.llBottomSheet.radioBtnJogging.isChecked = false
        binding.llBottomSheet.radioBtnRunning.isChecked = false

        binding.llBottomSheet.llResting.setBackgroundResource(R.drawable.radio_button_style_not_selected)
        binding.llBottomSheet.llWalking.setBackgroundResource(R.drawable.radio_button_style_not_selected)
        binding.llBottomSheet.llJogging.setBackgroundResource(R.drawable.radio_button_style_not_selected)
        binding.llBottomSheet.llRunning.setBackgroundResource(R.drawable.radio_button_style_not_selected)

        lastSelectedButton?.isChecked = false

    }

    /**
     * Set bottom sheet
     *
     */
    fun setBottomSheet() {

        val rb1 = binding.llBottomSheet.radioBtnResting
        val rb2 = binding.llBottomSheet.radioBtnWalking
        val rb3 = binding.llBottomSheet.radioBtnJogging
        val rb4 = binding.llBottomSheet.radioBtnRunning

        val ll_1 = binding.llBottomSheet.llResting
        val ll_2 = binding.llBottomSheet.llWalking
        val ll_3 = binding.llBottomSheet.llJogging
        val ll_4 = binding.llBottomSheet.llRunning

        if (SocketFitApplication.getInstance().currentSelectedActivity == "Resting") {
            rb1.isChecked = true
        } else if (SocketFitApplication.getInstance().currentSelectedActivity == "Walking") {
            rb2.isChecked = true
        } else if (SocketFitApplication.getInstance().currentSelectedActivity == "Jogging") {
            rb3.isChecked = true
        } else if (SocketFitApplication.getInstance().currentSelectedActivity == "Running") {
            rb4.isChecked = true
        }

        //Resting button click event
        rb1.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

            lastSelectedButton?.isChecked = false

            if (isChecked) {
                ll_1.setBackgroundResource(R.drawable.radio_button_style_selected)
            } else {
                ll_1.setBackgroundResource(R.drawable.radio_button_style_not_selected)
            }

            lastSelectedButton = rb1
            sendMsgToFragment("Resting", isChecked)

        })

        //Walking button click event
        rb2.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

            lastSelectedButton?.isChecked = false

            if (isChecked) {
                ll_2.setBackgroundResource(R.drawable.radio_button_style_selected)
            } else {
                ll_2.setBackgroundResource(R.drawable.radio_button_style_not_selected)
            }

            lastSelectedButton = rb2
            sendMsgToFragment("Walking", isChecked)

        })

        //Jogging button click event
        rb3.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

            lastSelectedButton?.isChecked = false

            if (isChecked) {
                ll_3.setBackgroundResource(R.drawable.radio_button_style_selected)
            } else {
                ll_3.setBackgroundResource(R.drawable.radio_button_style_not_selected)
            }

            lastSelectedButton = rb3
            sendMsgToFragment("Jogging", isChecked)

        })

        //Running button click event
        rb4.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

            lastSelectedButton?.isChecked = false

            if (isChecked) {
                ll_4.setBackgroundResource(R.drawable.radio_button_style_selected)
            } else {
                ll_4.setBackgroundResource(R.drawable.radio_button_style_not_selected)
            }

            lastSelectedButton = rb4
            sendMsgToFragment("Running", isChecked)

        })


    }

    //whenever any option from activity is selected, a message to fragment is sent so that the fragment will update the ui of start button and timer.
    //start and stop timer message will be sent to fragment in this case or activity change will be handled from this method
    fun sendMsgToFragment(status: String, isChecked: Boolean) {

        SocketFitApplication.getInstance().currentSelectedActivity = status

        val dashboardFragment = SocketFitApplication.getInstance().dashboardFragment
        if (dashboardFragment.isVisible) {
            dashboardFragment.receiveOnActivityItemClicked(status, isChecked)
        }

        binding.navView.visibility = View.VISIBLE
        binding.viewShadow.visibility = View.GONE
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED


    }


}