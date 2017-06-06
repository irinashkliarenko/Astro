package astroweather.com.astro;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

/**
 * Created by shkliare on 02-Jun-17.
 */

public class NewSettingsActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);

        bindPreferenceSummaryToValue(findPreference("savedLongitude"));
    }

    private void bindPreferenceSummaryToValue(Preference preference) {
        if (preference != null) {        // Set the listener to watch for value changes.
            preference.setOnPreferenceChangeListener(this);

            // Trigger the listener immediately with the preference's
            // current value.
            onPreferenceChange(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.getContext())
                            .getString(preference.getKey(), ""));
        }
    }



//    override fun onPreferenceChange(preference: Preference?, value: Any?): Boolean {
//        val stringValue = value.toString()
//
//        if (preference is ListPreference) {
//            // For list preferences, look up the correct display value in
//            // the preference's 'entries' list (since they have separate labels/values).
//            val listPreference = preference
//            val prefIndex = listPreference.findIndexOfValue(stringValue)
//            if (prefIndex >= 0) {
//                preference.setSummary(listPreference.entries[prefIndex])
//            }
//        } else {
//            // For other preferences, set the summary to the value's simple string representation.
//            preference?.summary = stringValue
//        }
//        return true
//    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String stringValue = newValue.toString();

        preference.setSummary(stringValue);
        return true;
    }
}
