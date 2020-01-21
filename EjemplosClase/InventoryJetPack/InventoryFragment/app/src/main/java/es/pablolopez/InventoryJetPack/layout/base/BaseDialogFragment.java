package es.pablolopez.InventoryJetPack.layout.base;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import es.pablolopez.InventoryJetPack.R;

public class BaseDialogFragment extends DialogFragment {
    public static final String TITLE = "title";
    public static  final String MESSAGE= "message";
    public static final String TAG = "BaseDialogFragment";

    private OnBaseDialogListener fragmentListener;

    public static BaseDialogFragment getInstance(Bundle b){
        BaseDialogFragment baseDialogFragment = new BaseDialogFragment();
        if (b != null){
            baseDialogFragment.setArguments(b);
        }
        return baseDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        fragmentListener = (OnBaseDialogListener) getTargetFragment();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(getArguments().getString(TITLE));
        alertDialog.setMessage(getArguments().getString(MESSAGE));
        alertDialog.setPositiveButton(getString(android.R.string.yes), (dialog, which) -> fragmentListener.onAccept());
        alertDialog.setNegativeButton(getString(android.R.string.no), (dialog, which) -> fragmentListener.onCancel());
        alertDialog.setIcon(R.drawable.ic_action_warning);
        return alertDialog.create();

    }

    public interface OnBaseDialogListener{
        void onAccept();
        void onCancel();
    }
}
