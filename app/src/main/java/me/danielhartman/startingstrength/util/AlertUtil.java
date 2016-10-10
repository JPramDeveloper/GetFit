package me.danielhartman.startingstrength.util;

import android.app.Activity;
import android.support.v7.app.AlertDialog;

public class AlertUtil {
    public AlertDialog showErrorAlert(Activity activity, String message) {
        return new AlertDialog.Builder(activity)
                .setMessage(message)
                .setTitle("An Error Occured")
                .setNegativeButton(android.R.string.ok, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .create();
    }
}
