package oberis.hoseoro.SlidingTabView;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
        import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import oberis.hoseoro.Activity.MapActivity;
import oberis.hoseoro.Activity.TimetableActivity;
import oberis.hoseoro.CustomTextView;
import oberis.hoseoro.Database.HolidayAcamToCcamDB;
import oberis.hoseoro.Database.HolidayCcamToAcamDB;
import oberis.hoseoro.Database.TermAcamToCcamDB;
import oberis.hoseoro.Database.TermCcamToAcamDB;
import oberis.hoseoro.Database.VacAcamToCcamDB;
import oberis.hoseoro.Database.VacCcamToAcamDB;
import oberis.hoseoro.R;

// 뷰페이저의 프래그먼트에 내용을 표시하는 역할
public class ContentFragment extends Fragment {

    private static final String KEY_TITLE = "title";
    private static final String KEY_INDICATOR_COLOR = "indicator_color";
    /*private static final String KEY_DIVIDER_COLOR = "divider_color";*/

    Boolean shuttleMode;
    int whatDay;
    String BusstationName;
    String dbNameCcamToAcam, dbNameAcamToCcam;

    TermCcamToAcamDB termCcamToAcamDB;
    TermAcamToCcamDB termAcamToCcamDB;
    VacCcamToAcamDB vacCcamToAcamDB;
    VacAcamToCcamDB vacAcamToCcamDB;
    HolidayCcamToAcamDB holidayCcamToAcamDB;
    HolidayAcamToCcamDB holidayAcamToCcamDB;
    SQLiteDatabase dbCcamToAcam, dbAcamToCcam;

    // 새로운 프래그먼트를 만들어서 반환하는 메소드
    public static ContentFragment newInstance(CharSequence title, int indicatorColor,
                                              /*int dividerColor,*/ boolean shuttleMode, int whatDay) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence(KEY_TITLE, title);
        bundle.putInt(KEY_INDICATOR_COLOR, indicatorColor);
        /*bundle.putInt(KEY_DIVIDER_COLOR, dividerColor);*/
        bundle.putBoolean("shuttleMode", shuttleMode);
        bundle.putInt("whatDay", whatDay);

        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        shuttleMode = getArguments().getBoolean("shuttleMode");
        whatDay = getArguments().getInt("whatDay");

        // DB관련 작업
        if (shuttleMode == true) {  // 학기중일 때
            termCcamToAcamDB = new TermCcamToAcamDB(getActivity());
            termAcamToCcamDB = new TermAcamToCcamDB(getActivity());
            holidayCcamToAcamDB = new HolidayCcamToAcamDB(getActivity());
            holidayAcamToCcamDB = new HolidayAcamToCcamDB(getActivity());

            if (whatDay == 1) {
                try {
                    dbCcamToAcam = termCcamToAcamDB.getWritableDatabase();
                    dbAcamToCcam = termAcamToCcamDB.getWritableDatabase();
                } catch (SQLException ex) {
                    dbCcamToAcam = termCcamToAcamDB.getReadableDatabase();
                    dbAcamToCcam = termAcamToCcamDB.getReadableDatabase();
                }
                dbNameCcamToAcam = "TermCcamToAcam";
                dbNameAcamToCcam = "TermAcamToCcam";
            } else if (whatDay == 2) {
                try {
                    dbCcamToAcam = holidayCcamToAcamDB.getWritableDatabase();
                    dbAcamToCcam = holidayAcamToCcamDB.getWritableDatabase();

                } catch (SQLException ex) {
                    dbCcamToAcam = holidayCcamToAcamDB.getReadableDatabase();
                    dbAcamToCcam = holidayAcamToCcamDB.getReadableDatabase();
                }
                dbNameCcamToAcam = "HolidayCcamToAcam";
                dbNameAcamToCcam = "HolidayAcamToCcam";
            }
        } else {    // 방학중일 때
            vacCcamToAcamDB = new VacCcamToAcamDB(getActivity());
            vacAcamToCcamDB = new VacAcamToCcamDB(getActivity());

            try {
                dbCcamToAcam = vacCcamToAcamDB.getWritableDatabase();
                dbAcamToCcam = vacAcamToCcamDB.getWritableDatabase();
            } catch (SQLException ex) {
                dbCcamToAcam = vacCcamToAcamDB.getReadableDatabase();
                dbAcamToCcam = vacAcamToCcamDB.getReadableDatabase();
            }
            dbNameCcamToAcam = "VacCcamToAcam";
            dbNameAcamToCcam = "VacAcamToCcam";
        }

        Bundle args = getArguments();
        if(args.getCharSequence(KEY_TITLE).equals("천안캠퍼스") ||
                args.getCharSequence(KEY_TITLE).equals("아산캠퍼스")) {
            return inflater.inflate(R.layout.pager_item, container, false);
        } else
            return inflater.inflate(R.layout.pager_item2, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.fragment_layout);
        RelativeLayout layout2 = (RelativeLayout) view.findViewById(R.id.fragment_layout2);

        Bundle args = getArguments();
        BusstationName = args.getCharSequence(KEY_TITLE).toString();

        if (args != null) {
            if(BusstationName.equals("천안캠퍼스")) {
                layout.setBackgroundResource(R.drawable.bg_ccam);   // 배경이미지 설정

                setStationFragment(view, "cCam", 0);    // 프래그먼트의 뷰들 처리

                view.findViewById(R.id.item_timetable).setOnClickListener(onClickListener_TimetableToAcam); // 시간표 버튼 이벤트 처리

            } else if(BusstationName.equals("아산캠퍼스")) {
                layout.setBackgroundResource(R.drawable.bg_acam);

                setStationFragment(view, "aCam", 1);

                view.findViewById(R.id.item_timetable).setOnClickListener(onClickListener_TimetableToCcam);

            } else if(BusstationName.equals("천안터미널")) {
                layout.setBackgroundResource(R.drawable.bg_terminal1);
                layout2.setBackgroundResource(R.drawable.bg_terminal2);

                setStationFragment(view, "terminal", 1);
                setStationFragment(view, "terminal", 2);

                view.findViewById(R.id.item_timetable).setOnClickListener(onClickListener_TimetableToCcam);
                view.findViewById(R.id.item_timetable2).setOnClickListener(onClickListener_TimetableToAcam);

            } else if(BusstationName.equals("천안역")) {
                layout.setBackgroundResource(R.drawable.bg_terminal1);
                layout2.setBackgroundResource(R.drawable.bg_terminal2);

                setStationFragment(view, "station",1);
                setStationFragment(view, "station",2);

                view.findViewById(R.id.item_timetable).setOnClickListener(onClickListener_TimetableToCcam);
                view.findViewById(R.id.item_timetable2).setOnClickListener(onClickListener_TimetableToAcam);

            } else if(BusstationName.equals("충무병원")) {
                layout.setBackgroundResource(R.drawable.bg_terminal1);
                layout2.setBackgroundResource(R.drawable.bg_terminal2);

                setStationFragment(view, "hospital",1);
                setStationFragment(view, "hospital",2);

                view.findViewById(R.id.item_timetable).setOnClickListener(onClickListener_TimetableToCcam);
                view.findViewById(R.id.item_timetable2).setOnClickListener(onClickListener_TimetableToAcam);

            } else if(BusstationName.equals("쌍용동")) {
                layout.setBackgroundResource(R.drawable.bg_terminal1);
                layout2.setBackgroundResource(R.drawable.bg_terminal2);

                setStationFragment(view, "road",1);
                setStationFragment(view, "road",2);

                view.findViewById(R.id.item_timetable).setOnClickListener(onClickListener_TimetableToCcam);
                view.findViewById(R.id.item_timetable2).setOnClickListener(onClickListener_TimetableToAcam);

            } else if(BusstationName.equals("천안아산역")) {
                layout.setBackgroundResource(R.drawable.bg_terminal1);
                layout2.setBackgroundResource(R.drawable.bg_terminal2);

                setStationFragment(view, "ktx",1);
                setStationFragment(view, "ktx",2);

                view.findViewById(R.id.item_timetable).setOnClickListener(onClickListener_TimetableToCcam);
                view.findViewById(R.id.item_timetable2).setOnClickListener(onClickListener_TimetableToAcam);

            }
        }
    }

    /**
     * 뷰페이저에 프래그먼트를 그려주는 메소드 (시간표 아이콘 이벤트 처리는 별도)
     * @param view  onCreateView() 에서 사용되는 view 객체
     * @param Station    정류장 이름
     * @param fragmentNumber    뷰페이저의 위/아래 프래그먼트 어느쪽이냐 선택
     */
    public void setStationFragment(View view, final String Station, int fragmentNumber) {

        TextView busstation = (TextView) view.findViewById(R.id.item_busstation);
        TextView busstation2 = (TextView) view.findViewById(R.id.item_busstation2);
        TextView destination = (TextView) view.findViewById(R.id.item_destination);
        TextView destination2 = (TextView) view.findViewById(R.id.item_destination2);
        TextView time = (TextView) view.findViewById(R.id.item_time);
        TextView time2 = (TextView) view.findViewById(R.id.item_time2);
        TextView timetext = (TextView) view.findViewById(R.id.item_timetext);
        TextView timetext2 = (TextView) view.findViewById(R.id.item_timetext2);

        switch (fragmentNumber) {
            case 0: // 특별히 천안캠퍼스 정류장의 경우
                busstation.setText(BusstationName);  // 프래그먼트 텍스트뷰 설정
                destination.setText("아산캠퍼스행");
                calcTime(dbCcamToAcam, dbNameCcamToAcam, Station, time, timetext, timetext2);    // 시간 계산해서 입력

                // 정류장 지도 보기 버튼 리스너 처리
                view.findViewById(R.id.item_where).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),MapActivity.class);
                                intent.putExtra("stationName", Station);
                                intent.putExtra("destination", Station);
                                startActivity(intent);
                            }
                        }
                );
                break;
            case 1: // 아산캠퍼스를 포함한 모든 천캠행 정류장의 경우
                if (BusstationName.equals("쌍용동"))
                    busstation.setText("쌍용2동");
                else
                    busstation.setText(BusstationName);
                destination.setText("천안캠퍼스행");
                calcTime(dbAcamToCcam, dbNameAcamToCcam, Station, time, timetext, timetext2);

                view.findViewById(R.id.item_where).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), MapActivity.class);
                                intent.putExtra("stationName", Station);
                                intent.putExtra("destination", "천캠행");
                                startActivity(intent);
                            }
                        }
                );
                break;
            case 2: // 뷰페이저 아래쪽에 해당하는 아캠행 정류장의 경우
                if (BusstationName.equals("쌍용동"))
                    busstation2.setText("쌍용3동");
                else
                    busstation2.setText(BusstationName);
                destination2.setText("아산캠퍼스행");
                calcTime(dbCcamToAcam, dbNameCcamToAcam, Station, time2, timetext, timetext2);

                view.findViewById(R.id.item_where2).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), MapActivity.class);
                                intent.putExtra("stationName", Station);
                                intent.putExtra("destination", "아캠행");
                                startActivity(intent);
                            }
                        }
                );
                break;
        }
    }

    // 전체 시간표 보기 버튼 리스너 처리 (천캠행/아캠행)
    Button.OnClickListener onClickListener_TimetableToCcam = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), TimetableActivity.class);
            intent.putExtra("destination", "천캠행");
            intent.putExtra("shuttleMode", shuttleMode);
            intent.putExtra("whatDay", whatDay);
            startActivity(intent);
        }
    };
    Button.OnClickListener onClickListener_TimetableToAcam = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), TimetableActivity.class);
            intent.putExtra("destination", "아캠행");
            intent.putExtra("shuttleMode", shuttleMode);
            intent.putExtra("whatDay", whatDay);
            startActivity(intent);
        }
    };


    /**
     *  남은 시간을 계산해서 주는 메소드
     * @param db    // 시간을 참조할 DB
     * @param tableName // DB의 테이블 이름
     * @param station   // 시간을 표시할 정류장
     * @param time  // 시간을 표시할 텍스트뷰
     * @param timetext
     */
    public void calcTime(SQLiteDatabase db, String tableName, String station, TextView time, TextView timetext, TextView timetext2) {
        //커서를 이용해 DB 접근
        Cursor cursor = db.rawQuery("SELECT " + station + " FROM " + tableName + ";", null);
        // DB 시간 계산
        if (cursor.moveToFirst()) {
            do {
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");   // DateFormat

                Date now = new Date(System.currentTimeMillis());    // 현재 시간 구함
                String nowStr = dateFormat.format(now);
                Date nowTime = null;
                try {   nowTime = dateFormat.parse(nowStr); }
                catch (ParseException e) {    e.printStackTrace();    }

                Date dbTime = null;     // db시간 구함
                try {   dbTime = dateFormat.parse(cursor.getString(cursor.getColumnIndex(station))); }
                catch (ParseException e) {  e.printStackTrace();    }

                if (dbTime.after(nowTime)) {    // 표시할지 비교 ~ db시간이 더 늦어야 함
                    long temp = (dbTime.getTime() - nowTime.getTime()) / (60 * 1000);   // 계산
                    time.setText(String.valueOf(temp));
                    timetext.setText("분 남음");
                    if (timetext2 != null)  // null일때 : 천캠,아캠 페이지
                        timetext2.setText("분 남음");
                    break;
                }

                if (cursor.isLast()) {
                    time.setText("운행종료");
                    timetext.setText(" ");
                    if (timetext2 != null)
                        timetext2.setText(" ");
                    break;
                }
            } while (cursor.moveToNext());
        }
    }

}