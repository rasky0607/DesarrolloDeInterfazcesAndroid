package es.adrianmmudarra.toolbarpreference;

import android.os.Bundle;

import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }
}
