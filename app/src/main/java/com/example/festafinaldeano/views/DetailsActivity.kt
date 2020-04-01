package com.example.festafinaldeano.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import com.example.festafinaldeano.data.SecurityPreferences
import com.example.festafinaldeano.R
import com.example.festafinaldeano.constant.FimDeAnoConstants

class DetailsActivity : AppCompatActivity(), View.OnClickListener {

    private var _ViewHolder = ViewHolder()
    private lateinit var _securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //Sharede Preferences
        _securityPreferences = SecurityPreferences(this)

        _ViewHolder.checkParticipate = findViewById(R.id.check_participate)
        _ViewHolder.checkParticipate.setOnClickListener(this)

        loadDataFromActivity()
    }

    private class ViewHolder {
        lateinit var checkParticipate: CheckBox
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.check_participate) {
            if (_ViewHolder.checkParticipate.isChecked) {
                //Salvar a presença
                _securityPreferences.storeString(FimDeAnoConstants().PRESENSE_KEY, FimDeAnoConstants().CONFIRMATION_YES)
            } else {
                //Salvar a ausência
                _securityPreferences.storeString(FimDeAnoConstants().PRESENSE_KEY, FimDeAnoConstants().CONFIRMATION_NO)
            }
        }
    }

    fun loadDataFromActivity() {
        var extras: Bundle? = intent.extras
        //Verifica se nao e nullo por questoes de seguranca
        if (extras != null) {
            var presence: String? = extras.getString(FimDeAnoConstants().PRESENSE_KEY)
            //Verifica se existem dados a serem passados para activity details
            if (presence != null && presence.equals(FimDeAnoConstants().CONFIRMATION_YES)) {
                _ViewHolder.checkParticipate.isChecked = true
            } else {
                _ViewHolder.checkParticipate.isChecked = false
            }
        }
    }
}
