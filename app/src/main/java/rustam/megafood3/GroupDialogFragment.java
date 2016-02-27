package rustam.megafood3;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.List;

/**
 * Created by Rustam on 2/27/2016.
 */
public class GroupDialogFragment extends DialogFragment {

    private String title;
    private List<String> items;
    public final static String DIALOG_TITLE = "dialog_title";
    public final static String DIALOG_ITEMS = "dialog_items";


    public interface GroupDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
    }

    GroupDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (GroupDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        title = getArguments().getString(DIALOG_TITLE);
        items = getArguments().getStringArrayList(DIALOG_ITEMS);

        builder.setMessage(title)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                });


        return builder.create();
    }
}
