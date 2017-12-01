/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package oberis.hoseoro.SlidingTabView;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import oberis.hoseoro.Activity.SettingsActivity;
import oberis.hoseoro.R;

// 탭을 구현하는 부분
public class SlidingTabsFragment extends Fragment {

    // 탭 하나하나에 해당하는 객체
    static class PagerItem {
        private final CharSequence mTitle;  // 이름
        private final int mIndicatorColor = Color.parseColor("#1E88E5"); // 선택됐을 때 색깔 : R.color.colorMain
        /*private final int mDividerColor = Color.WHITE;   // 탭끼리 구분선 : 하얗게 해서 구분선 없는것처럼*/
        boolean shuttleMode;    // 학기중/방학중
        int whatDay;    // 평일/토/일

        // 생성자
        PagerItem(CharSequence title, boolean shuttleMode, int whatDay) {
            mTitle = title;
            this.shuttleMode = shuttleMode;
            this.whatDay = whatDay;
        }


        // 뷰페이저를 만들어 반환
        Fragment createFragment() {
            return ContentFragment.newInstance(mTitle, mIndicatorColor, /*mDividerColor,*/ shuttleMode, whatDay);
        }


        CharSequence getTitle() {
            return mTitle;
        }
        int getIndicatorColor() {
            return mIndicatorColor;
        }
        /*int getDividerColor() {
            return mDividerColor;
        }*/
    }


    // VeiwPager과 비슷하지만, 스크롤 할 때 사용자에게 피드백을 주기 위해 개조
    private SlidingTabLayout mSlidingTabLayout;

    // 위의 SlidingTabLayout과 함께 사용되기 위한 ViewPager
    private ViewPager mViewPager;

    // 여러개의 탭들을 만들기 위한 List
    private List<PagerItem> mTabs = new ArrayList<PagerItem>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // MainActivity에서 Bundle 객체로 값을 줬기 때문에
        Boolean shuttleMode = getArguments().getBoolean("shuttleMode");
        int whatDay = getArguments().getInt("whatDay");

        String campusPref_value = PreferenceManager.getDefaultSharedPreferences(getContext()).getString(getString(R.string.campusPref_key), "");
        if (campusPref_value.equals("cCam")) {
            mTabs.add(new PagerItem(getString(R.string.tab_cCam), shuttleMode, whatDay));
            mTabs.add(new PagerItem(getString(R.string.tab_terminal), shuttleMode, whatDay));
            mTabs.add(new PagerItem(getString(R.string.tab_station), shuttleMode, whatDay));
            mTabs.add(new PagerItem(getString(R.string.tab_hospital), shuttleMode, whatDay));
            mTabs.add(new PagerItem(getString(R.string.tab_road), shuttleMode, whatDay));
            mTabs.add(new PagerItem(getString(R.string.tab_ktx), shuttleMode, whatDay));
            mTabs.add(new PagerItem(getString(R.string.tab_aCam), shuttleMode, whatDay));
        } else if (campusPref_value.equals("aCam")) {
            mTabs.add(new PagerItem(getString(R.string.tab_aCam), shuttleMode, whatDay));
            mTabs.add(new PagerItem(getString(R.string.tab_ktx), shuttleMode, whatDay));
            mTabs.add(new PagerItem(getString(R.string.tab_road), shuttleMode, whatDay));
            mTabs.add(new PagerItem(getString(R.string.tab_hospital), shuttleMode, whatDay));
            mTabs.add(new PagerItem(getString(R.string.tab_station), shuttleMode, whatDay));
            mTabs.add(new PagerItem(getString(R.string.tab_terminal), shuttleMode, whatDay));
            mTabs.add(new PagerItem(getString(R.string.tab_cCam), shuttleMode, whatDay));
        }
    }

    // 탭 부분이 보이도록 인플레이트 시킴
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    // fragment_onviewcreated 할 때 시작
    /**
     * onCreateView가 끝나고 시작됨
     * content view로 설정해야할 view를 고름
     * 뷰페이저의 어댑터를 설정
     * @param view {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} 에서 만들어진 View
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SampleFragmentPagerAdapter(getChildFragmentManager()) /*{
            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }
        }*/);   // ViewPager를 표시할 수 있도록 어댑터 연결

        // SlidingTabLayout에 ViewPager를 넘기려면 PagerAdapter가 붙어있어야 함
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);

        mSlidingTabLayout.setSelectedIndicatorColors(Color.parseColor("#1E88E5"));  // 탭이 눌렸을 때 색 변화
        /*mSlidingTabLayout.setDividerColors(Color.WHITE);    // 탭과 탭간의 구분선 색 설정*/
    }


    class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

        // 생성자
        SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // mTab 리스트의 아이템 하나의 프래그먼트를 만들어서 반환
        @Override
        public Fragment getItem(int i) {
            return mTabs.get(i).createFragment();
        }

        // mTab 리스트의 아이템 갯수를 반환
        @Override
        public int getCount() {
            return mTabs.size();
        }

        // 현재 보여주고 있는 페이지의 타이틀을 알려주는 중요한 메소드
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs.get(position).getTitle();
        }
    }
}