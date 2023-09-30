package com.socketfit.androidapp.moduleOne.dashboard

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.socketfit.androidapp.R
import com.socketfit.androidapp.activity.MainActivity
import com.socketfit.androidapp.databinding.FragmentDashboardBinding
import com.socketfit.androidapp.ui.BottomSheetDialog
import com.socketfit.androidapp.util.SocketFitApplication


/**
 * This fragment is dashboard screen and will show the graphs
 *  1. another graph is shown in BarChartView as a dynamic chart
 *  2. one graph is shown as an image in ShapeableImageView
 *  3. Start activity button and timer is also shown on this screen
 */
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!
    lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //if the start button is clicked then, we will check if timer is running then timer will stop and options in activity will be reset, so that user can choose the new option again
        _binding?.ivBtnStart?.setOnClickListener {

            if (dashboardViewModel.isTimerRunning) {

                dashboardViewModel.stopTimer()
                (activity as MainActivity).resetActivityOptions()

            } else {
                (activity as MainActivity).showBottomSheetLayout()
                (activity as MainActivity).showBottomSheetLayout()
            }
        }

        //observe pattern used to get the count of timer and messages to react on a stop timer operation
        dashboardViewModel.getEstimatedTime().observe(requireActivity(), Observer { estimatedTime ->

            // Update the TextView with the new estimated time value

            if (estimatedTime.equals("stop")) {
                _binding?.tvTimer?.text = ""
                _binding?.tvTimer?.visibility = View.GONE
                updateTimerUI("", false)

            } else {

                //if timer is running show the updates on ui
                if (dashboardViewModel.isTimerRunning) {

                    _binding?.tvTimer?.text = estimatedTime
                    _binding?.tvTimer?.visibility = View.VISIBLE

                } else {
                    updateTimerUI("", false)
                    _binding?.tvTimer?.text = ""
                    _binding?.tvTimer?.visibility = View.GONE
                }
            }
        })

        //this will execute when the view model button is clicked,it will open sensor data screen
        _binding?.llViewModel?.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_navigation_sensor_data)
        }

        SocketFitApplication.getInstance().dashboardFragment = this

        //setup small chart
        setupSmallChart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun receiveOnActivityItemClicked(checkedName: String, checkedId: Boolean) {
        updateTimerUI(checkedName, true)
    }

    //method in which timer is updated on screen and start and stop command for timer is received here
    fun updateTimerUI(checkedName: String, flag: Boolean) {

        if (flag) {

            if (dashboardViewModel.isTimerRunning) {
                dashboardViewModel.stopTimer()
                (activity as MainActivity).resetActivityOptions()
            }

            dashboardViewModel.startTimer()
            _binding?.tvStartActivity?.text = checkedName
            _binding?.tvTimer?.visibility = View.VISIBLE
            _binding?.rlStartActivity?.setBackgroundResource(R.drawable.start_activity_bg_orange)
            _binding?.ivBtnStart?.setImageResource(R.drawable.btn_circle_stop_white)

        } else {

            (activity as MainActivity).resetActivityOptions()
            dashboardViewModel.stopTimer()
            _binding?.tvStartActivity?.text = "Start Activity"
            _binding?.tvTimer?.visibility = View.GONE
            _binding?.rlStartActivity?.setBackgroundResource(R.drawable.start_activity_bg_blue)
            _binding?.ivBtnStart?.setImageResource(R.drawable.btn_circle_play_white)
        }

    }

    private fun setupSmallChart() {

        //4 bars - X axis
        val barSet = listOf(
            "1" to 4F,
            "2" to 7F,
            "3" to 2F,
            "4" to 2.3F,

            )

        _binding?.aaChartView?.animation?.duration = 300
        _binding?.aaChartView?.animate(barSet)

        //4 lines - y Axis
        val lineSet = listOf(
            "4 am" to 5f,
            "8 am" to 2f,
            "12 pm" to 4.7f,
            "4 pm" to 1f,
        )

        //bar colors
        _binding?.lineChart?.gradientFillColors = intArrayOf(
            Color.parseColor("#2C40FC"), Color.parseColor("#00000000")
        )
        _binding?.lineChart?.animation?.duration = 300
        _binding?.lineChart?.animate(lineSet)

    }
}