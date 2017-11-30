package oberis.hoseoro.Activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import oberis.hoseoro.SettingsFragment;

public class SettingsActivity extends PreferenceActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 설정 프래그먼트의 내용을 보여줌
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }
}