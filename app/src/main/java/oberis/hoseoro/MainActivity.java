package oberis.hoseoro;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    boolean shuttleMode = true; // 학기중이냐 방학중이냐를 결정하는 모드. true가 학기중
    int whatDay = 1;    // 무슨 요일을 선택했느냐 하는 변수
    RadioGroup radioGroup;  // 평일 or 토요일 or 일요일 선택하는 라디오 그룹, onCreate에서 사용

    // 네이게이션 드로어 변수들
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    CustomDrawerAdapter adapter;
    List<DrawerItem> dataList;

    long backKeyPressedTime; //백버튼 클릭 시간

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backKeyPressedTime = System.currentTimeMillis();    // 백버튼 클릭 초기화

        // 맨 위에 첫번째 툴바부분 초기화
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        // 앱이 처음 시작됐을 때 뷰페이저 부분을 그려주기 위함
        if (savedInstanceState == null) {
            makeFragment(shuttleMode, whatDay); // makeFragment 메소드가 뷰페이저를 그려주는 역할
        }


        /**
         * 평일/토요일/일요일 라디오버튼 클릭 이벤트 처리
         * 라디오 버튼이 눌렸을 때 뷰페이저를 다시 그려줘야 한다.
         */
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup); // 라디오그룹 리스너
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.button1){
                    whatDay = 1;
                    makeFragment(shuttleMode, whatDay);
                } else if (checkedId == R.id.button2) {
                    whatDay = 2;
                    makeFragment(shuttleMode, whatDay);
                }
            }
        });
        /**
         *  라디오 버튼 이벤트처리 끝
         */


        /**
         * 네비게이션 드로어 부분
         */
        // 초기화 & 목록 설정
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);    // 왼쪽에서 나오는 드로어
        mDrawerList = (ListView) findViewById(R.id.left_drawer);    // 리스트뷰가 드로어로 나오도록
        dataList = new ArrayList<DrawerItem>();
        dataList.add(new DrawerItem(" 학기중 셔틀버스", R.drawable.ic_term));
        dataList.add(new DrawerItem(" 방학중 셔틀버스", R.drawable.ic_vacation));
        dataList.add(new DrawerItem(" 온양순환(학기중)", R.drawable.ic_onyang));

        // 리스트뷰에 어댑터 연결
        adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item, dataList);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setItemChecked(0, true); // 초기값 설정
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());   // 리스트 클릭 리스너 설정

        // 네비게이션 드로어 열기/닫기 감지
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(false); // 네비게이션 드로어 기본아이콘 안 보이기
        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        /**
         * 네비게이션 드로어 부분 끝
         */
    }
    /**
     * onCreate() 메소드 부분 끝
     */


    /**
     * 백버튼 터치로 인한 앱 종료 부분
     */
    @Override
    public void onBackPressed() {
        //1번째 백버튼 클릭
        if(System.currentTimeMillis()>backKeyPressedTime+2000){
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
        //2번째 백버튼 클릭 (종료)
        else{
            finish();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
    /**
     * 앱종료 파트 끝
     */


    /**
     * 네비게이션 드로어 자세한 부분
     */
    // 네비게이션 드로어 온클릭 리스너 구현 부분.. 위쪽 onCreate() 에서는 리스너를 달아주기만 함
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            SelectItem(position);
        }
    }
    // 리스트 골랐을 때 작업
    public void SelectItem(int possition) {
        Bundle args = new Bundle();
        switch (possition) {
            case 0:
                setTermTimeMode();
                break;
            case 1:
                setVacationMode();
                break;
            case 2:
                startActivity(new Intent(MainActivity.this,OnYangActivity.class));
                //finish();
                break;
            default:
                break;
        }

        mDrawerList.setItemChecked(possition, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    // 눌러서 열고 닫기
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns true,
        // then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    // 네비게이션 드로어를 열기 위한 메소드
    public void onClickNavigationDrawer(View v) {
        mDrawerLayout.openDrawer(Gravity.START);
    }

    /**
     * 네비게시션 드로어 부분 끝
     */


    // 정류장 지도보기 버튼 누름
    public void onClickMap(View v) {
        Intent intent = new Intent(MainActivity.this,MapActivity.class);
        startActivity(intent);
    }
/*
    // 전체 시간표보기 버튼 누름
    public void onClickTimetable(View v) {
        Intent intent = new Intent(MainActivity.this,TimetableActivity.class);
        startActivity(intent);
    }
    */

    // 학기중 셔틀 버튼을 눌렀을 때
    private void setTermTimeMode() {
        shuttleMode = true;
        ImageView imageview = (ImageView)findViewById(R.id.title_image);
        imageview.setImageResource(R.drawable.ic_title1);
        makeFragment(shuttleMode, whatDay);
    }

    // 방학중 셔틀 버튼을 눌렀을 때
    private void setVacationMode() {
        shuttleMode = false;
        ImageView imageview = (ImageView)findViewById(R.id.title_image);
        imageview.setImageResource(R.drawable.ic_title2);
        makeFragment(shuttleMode, whatDay);
    }


    /**
     * 뷰페이저 부분을 생성해주는 메소드
     * 프래그먼트 생성하고, 파라미터 주고, 연결까지 해주는 메소드
     * @param shuttleMode   // 학기중/방학중 판단
     * @param whatDay  // 평일/토요일/일요일 판단
     */
    public void makeFragment(boolean shuttleMode, int whatDay) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        SlidingTabsFragment fragment = new SlidingTabsFragment();
        Bundle bundle = new Bundle(2); // 파라미터는 전달할 데이터 개수
        bundle.putBoolean("shuttleMode", shuttleMode);
        bundle.putInt("whatDay", whatDay); // key , value
        fragment.setArguments(bundle);
        transaction.replace(R.id.content_fragment, fragment);
        transaction.commit();
    }
}
