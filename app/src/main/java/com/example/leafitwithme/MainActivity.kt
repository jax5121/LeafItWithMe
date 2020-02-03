package com.example.leafitwithme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import java.text.NumberFormat
import java.util.*

private const val COST_FULL_CHARGE:Double = 668.0

class MainActivity : AppCompatActivity() {

    val percent_price:Double = COST_FULL_CHARGE / 100

    private lateinit var percentInputView:EditText
    private lateinit var overallAnswerView:TextView
    private lateinit var inputErrorView:TextView
    private lateinit var passengerSpinner:Spinner
    private lateinit var chargePerPassenger:TextView
    private lateinit var overallPriceLabel:TextView
    private lateinit var pricePerPassengerLabel:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get views
        percentInputView = findViewById(R.id.percentageInput)
        overallAnswerView = findViewById(R.id.overallChargeLabel)
        inputErrorView = findViewById(R.id.labelInputError)
        passengerSpinner = findViewById(R.id.passengerSpinner)
        chargePerPassenger = findViewById(R.id.chargePerPassengerLabel)
        overallPriceLabel = findViewById(R.id.overallPriceLabel)
        pricePerPassengerLabel = findViewById(R.id.pricePerPassengerLabel)

        //set visibilities
        overallAnswerView.visibility = View.GONE
        inputErrorView.visibility = View.GONE
        chargePerPassenger.visibility = View.GONE
        overallPriceLabel.visibility = View.GONE
        pricePerPassengerLabel.visibility = View.GONE

    }

    fun calculateTrip (view: View) {
        //set formatting up
        var n:NumberFormat = NumberFormat.getCurrencyInstance(Locale.UK)
        inputErrorView.visibility = View.GONE

        var percentText:String = percentInputView.text.toString()
        if (percentText == "") { //if no percentage diff show error
            inputErrorView.visibility = View.VISIBLE
            return
        }
        val percent:Int = percentText.toInt()
        var overallCharge:Double = Math.floor(percent * percent_price)

        var answerString:String = n.format(overallCharge/100)
        overallAnswerView.text = answerString

        //charge per passenger
        val passengers:Int = passengerSpinner.selectedItem.toString().toInt()
        val chargePerPerson:Double = overallCharge/(passengers+1)
        overallCharge -= chargePerPerson //accounts for driver

        var charge:Double = overallCharge/passengers



        var chargePerPassengerStr:String = n.format(charge/100)
        chargePerPassenger.text = chargePerPassengerStr

        //set visibility
        overallAnswerView.visibility = View.VISIBLE
        chargePerPassenger.visibility = View.VISIBLE
        overallPriceLabel.visibility = View.VISIBLE
        pricePerPassengerLabel.visibility = View.VISIBLE
    }
}
