package yuresko.moapps.auth

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class InformationDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
                .setMessage("Предосмотр приложений, созданных с помощью конструктора MO APPS." +
                        "Для входа введите логин и пароль от личного кабинета на сайте mo-apps.com")
                .setPositiveButton("ОК") { dialog, id ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
