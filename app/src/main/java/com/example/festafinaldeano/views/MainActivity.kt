package com.example.festafinaldeano.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.festafinaldeano.data.SecurityPreferences
import com.example.festafinaldeano.R
import com.example.festafinaldeano.constant.FimDeAnoConstants
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var _ViewHolder = ViewHolder()
    private var SIMPLE_DATE_FORMAT = SimpleDateFormat("dd/MM/yyyy")
    private lateinit var _securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _ViewHolder.textToday = findViewById(R.id.text_today)
        _ViewHolder.textDaysLeft = findViewById(R.id.text_days_left)
        _ViewHolder.buttonConfirm = findViewById(R.id.button_confirm)

        //Sharede Preferences
        _securityPreferences = SecurityPreferences(this)

        // Datas
        _ViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().time))
        _ViewHolder.textDaysLeft.setText("${getDaysLeft()} dias")

        _ViewHolder.buttonConfirm.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        verifyPresence()
    }

    //Método responsável por calcular quantos dias faltam para o final do ano
    fun getDaysLeft(): Int {
        //Data de Hoje
        var calendarToday = Calendar.getInstance()
        var today: Int = calendarToday.get(Calendar.DAY_OF_YEAR)

        //Dia Máximo do ano
        // [1-365]
        var calendarLastDay = Calendar.getInstance()
        var dayMax: Int = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR)

        return dayMax - today
    }

    //Padrão ViewHolder
    private class ViewHolder {
        lateinit var textToday: TextView
        lateinit var textDaysLeft: TextView
        lateinit var buttonConfirm: Button
    }

    // OnClick
    override fun onClick(v: View?) {
        if (v?.id == R.id.button_confirm) {
            var presence: String = _securityPreferences.getStoreString(FimDeAnoConstants().PRESENSE_KEY)

            var intent = Intent(applicationContext, DetailsActivity::class.java)
            intent.putExtra(FimDeAnoConstants().PRESENSE_KEY, presence)
            startActivity(intent)
        }
    }

    //Método responsável por verificar a presença
    private fun verifyPresence() {
        var presence: String = _securityPreferences.getStoreString(FimDeAnoConstants().PRESENSE_KEY)
        if (presence.equals("")) {
            _ViewHolder.buttonConfirm.setText("Não Confirmado")
        } else if (presence.equals(FimDeAnoConstants().CONFIRMATION_YES)) {
            _ViewHolder.buttonConfirm.setText("Sim")
        }
        else {
            _ViewHolder.buttonConfirm.setText("Não")
        }
    }
}