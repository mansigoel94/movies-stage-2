package com.example.mansi.movies;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import static com.example.mansi.movies.R.xml.preference;

public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(preference);
        Preference sortPreference = findPreference(getString(R.string.list_preference_key));
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String selectedValue = sharedPreferences.getString(getString(R.string.list_preference_key), "");
        bindSummaryToValue(sortPreference, selectedValue);

        Preference.OnPreferenceChangeListener mOnPreferenceChangeListener = new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object selectedSort) {
                bindSummaryToValue(preference, selectedSort);
                return true;
            }
        };
        sortPreference.setOnPreferenceChangeListener(mOnPreferenceChangeListener);
    }

    public void bindSummaryToValue(Preference preference, Object selectedSort) {
        if (preference instanceof ListPreference) {
            int selectedItemIndex = ((ListPreference) preference).findIndexOfValue(String.valueOf(selectedSort));
            CharSequence entries[] = ((ListPreference) preference).getEntries();
            preference.setSummary(entries[selectedItemIndex]);
        }
    }
}
