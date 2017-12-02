package oberis.hoseoro;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import oberis.hoseoro.Activity.MainActivity;

// 실질적으로 설정창을 구현하는 곳
// 참고 : http://blog.naver.com/PostView.nhn?blogId=hellocatgu&logNo=220833553648&redirect=Dlog&widgetTypeCall=true
public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

    ListPreference campusPref, termPref;    // 캠퍼스,학기 설정 프리퍼런스

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // xml로부터 설정창 불러옴
        addPreferencesFromResource(R.xml.preferences);

        campusPref = (ListPreference) findPreference(getString(R.string.campusPref_key));
        campusPref.setOnPreferenceChangeListener(this);   // 리스너 연결
        termPref = (ListPreference) findPreference(getString(R.string.termPref_key));
        termPref.setOnPreferenceChangeListener(this);   // 리스너 연결
    }

    @Override
    public void onResume() {
        super.onResume();

        updateSummury();    // 앱 시작시 캠퍼스 설정 프리퍼런스의 summary 갱신
    }

    /**
     * Preference.OnPreferenceChangeListener 인터페이스를 구현하면서 재정의해야하는 리스터 실행부분
     * 설정값이 변경되었을때 이루어지는 처리를 담당
     * @param preference    // 설정값이 바뀐 프리퍼런스
     * @param o // 바뀐 값
     * @return
     */
    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {

        if (preference == campusPref) {   // 바뀐 프리퍼런스가 캠퍼스 설정 이라면
            // 바뀐 값으로 summary 수정
            ListPreference campusPreference = (ListPreference) preference;
            String value = o.toString();
            int index = campusPreference.findIndexOfValue(value);
            campusPref.setSummary(index >= 0 ? campusPreference.getEntries()[index] : null);
            Toast.makeText(getActivity(), "캠퍼스 변경 완료", Toast.LENGTH_SHORT).show();
            ((MainActivity)MainActivity.mMainActivity).doMakeFragment = true;
        } else if (preference == termPref) {   // 바뀐 프리퍼런스가 학기 설정 이라면
            ListPreference termPreference = (ListPreference) preference;
            String value = o.toString();
            int index = termPreference.findIndexOfValue(value);
            termPref.setSummary(index >= 0 ? termPreference.getEntries()[index] : null);
            Toast.makeText(getActivity(), "시간표 변경 완료", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void updateSummury() {
        // 설정 값을 불러와서 summary값을 바꿔줌줌
       campusPref.setSummary(campusPref.getEntry());
       termPref.setSummary(termPref.getEntry());
    }
}