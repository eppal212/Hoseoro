package oberis.hoseoro;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple Fragment used to display some meaningful content for each page in the sample's
 * {@link android.support.v4.view.ViewPager}.
 */
public class ContentFragment extends Fragment {

    private static final String KEY_TITLE = "title";
    private static final String KEY_INDICATOR_COLOR = "indicator_color";
    private static final String KEY_DIVIDER_COLOR = "divider_color";

    Boolean shuttleMode;
    int whatDay;
    String dbNameCcamToAcam, dbNameAcamToCcam;

    TermCcamToAcamDB termCcamToAcamDB;
    TermAcamToCcamDB termAcamToCcamDB;
    SaturdayCcamToAcamDB saturdayCcamToAcamDB;
    SaturdayAcamToCcamDB saturdayAcamToCcamDB;
    SundayCcamToAcamDB sundayCcamToAcamDB;
    SundayAcamToCcamDB sundayAcamToCcamDB;
    SQLiteDatabase dbCcamToAcam, dbAcamToCcam;

    /**
     * @return a new instance of {@link ContentFragment}, adding the parameters into a bundle and
     * setting them as arguments.
     */
    public static ContentFragment newInstance(CharSequence title, int indicatorColor,
                                              int dividerColor, boolean shuttleMode, int whatDay) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence(KEY_TITLE, title);
        bundle.putInt(KEY_INDICATOR_COLOR, indicatorColor);
        bundle.putInt(KEY_DIVIDER_COLOR, dividerColor);
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
        termCcamToAcamDB = new TermCcamToAcamDB(getActivity());
        termAcamToCcamDB = new TermAcamToCcamDB(getActivity());
        saturdayCcamToAcamDB = new SaturdayCcamToAcamDB(getActivity());
        saturdayAcamToCcamDB = new SaturdayAcamToCcamDB(getActivity());
        sundayCcamToAcamDB = new SundayCcamToAcamDB(getActivity());
        sundayAcamToCcamDB = new SundayAcamToCcamDB(getActivity());

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
                dbCcamToAcam = saturdayCcamToAcamDB.getWritableDatabase();
                dbAcamToCcam = saturdayAcamToCcamDB.getWritableDatabase();

            } catch (SQLException ex) {
                dbCcamToAcam = saturdayCcamToAcamDB.getReadableDatabase();
                dbAcamToCcam = saturdayAcamToCcamDB.getReadableDatabase();
            }
            dbNameCcamToAcam = "SaturdayCcamToAcam";
            dbNameAcamToCcam = "SaturdayAcamToCcam";
        } else if (whatDay == 3) {
            try {
                dbCcamToAcam = sundayCcamToAcamDB.getWritableDatabase();
                dbAcamToCcam = sundayAcamToCcamDB.getWritableDatabase();

            } catch (SQLException ex) {
                dbCcamToAcam = sundayCcamToAcamDB.getReadableDatabase();
                dbAcamToCcam = sundayAcamToCcamDB.getReadableDatabase();
            }
            dbNameCcamToAcam = "SundayCcamToAcam";
            dbNameAcamToCcam = "SundayAcamToCcam";
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

        if (args != null) {
            // 학기중 셔틀
            if(args.getCharSequence(KEY_TITLE).equals("천안캠퍼스")) {
                layout.setBackgroundResource(R.drawable.bg_ccam);

                TextView destination = (TextView) view.findViewById(R.id.item_destination);
                destination.setText("아산캠퍼스행");
                TextView time = (TextView) view.findViewById(R.id.item_time);
                calcTime(dbCcamToAcam, dbNameCcamToAcam, "cCam", time);

                // 전체 시간표 보기 버튼 리스너 처리
                view.findViewById(R.id.item_timetable).setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(),TimetableActivity.class);
                            intent.putExtra("stationName", "천안캠퍼스");
                            intent.putExtra("whatDay", whatDay);
                            startActivity(intent);
                        }
                    }
                );
                view.findViewById(R.id.item_where).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),MapActivity.class);
                                intent.putExtra("stationName", "천안캠퍼스");
                                intent.putExtra("destination", "아캠행");
                                startActivity(intent);
                            }
                        }
                );

            } else if(args.getCharSequence(KEY_TITLE).equals("아산캠퍼스")) {
                layout.setBackgroundResource(R.drawable.bg_acam);

                TextView destination = (TextView) view.findViewById(R.id.item_destination);
                destination.setText("천안캠퍼스행");
                TextView time = (TextView) view.findViewById(R.id.item_time);
                calcTime(dbAcamToCcam, dbNameAcamToCcam, "aCam", time);

                // 전체 시간표 보기 버튼 리스너 처리
                view.findViewById(R.id.item_timetable).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),TimetableActivity.class);
                                intent.putExtra("stationName", "아산캠퍼스");
                                intent.putExtra("whatDay", whatDay);
                                startActivity(intent);
                            }
                        }
                );
                view.findViewById(R.id.item_where).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),MapActivity.class);
                                intent.putExtra("stationName", "아산캠퍼스");
                                intent.putExtra("destination", "천캠행");
                                startActivity(intent);
                            }
                        }
                );

            } else if(args.getCharSequence(KEY_TITLE).equals("천안터미널")) {
                layout.setBackgroundResource(R.drawable.bg_terminal1);

                TextView destination = (TextView) view.findViewById(R.id.item_destination);
                destination.setText("천안캠퍼스행");
                TextView time = (TextView) view.findViewById(R.id.item_time);
                calcTime(dbAcamToCcam, dbNameAcamToCcam, "terminal", time);

                layout2.setBackgroundResource(R.drawable.bg_terminal2);

                TextView destination2 = (TextView) view.findViewById(R.id.item_destination2);
                destination2.setText("아산캠퍼스행");
                TextView time2 = (TextView) view.findViewById(R.id.item_time2);
                calcTime(dbCcamToAcam, dbNameCcamToAcam, "terminal", time2);

                // 전체 시간표 보기 버튼 리스너 처리                                                                              //////// 이거 나중에 리스터 따로 하나 만들어서 파라미터 넘기는 식으로
                view.findViewById(R.id.item_timetable).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),TimetableActivity.class);
                                intent.putExtra("stationName", "천안터미널 천캠행");
                                intent.putExtra("whatDay", whatDay);
                                startActivity(intent);
                            }
                        }
                );
                view.findViewById(R.id.item_timetable2).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),TimetableActivity.class);
                                intent.putExtra("stationName", "천안터미널 아캠행");
                                intent.putExtra("whatDay", whatDay);
                                startActivity(intent);
                            }
                        }
                );
                view.findViewById(R.id.item_where).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),MapActivity.class);
                                intent.putExtra("stationName", "천안터미널");
                                intent.putExtra("destination", "천캠행");
                                startActivity(intent);
                            }
                        }
                );
                view.findViewById(R.id.item_where2).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),MapActivity.class);
                                intent.putExtra("stationName", "천안터미널");
                                intent.putExtra("destination", "아캠행");
                                startActivity(intent);
                            }
                        }
                );

            } else if(args.getCharSequence(KEY_TITLE).equals("천안역")) {
                layout.setBackgroundResource(R.drawable.bg_station1);

                TextView destination = (TextView) view.findViewById(R.id.item_destination);
                destination.setText("천안캠퍼스행");
                TextView time = (TextView) view.findViewById(R.id.item_time);
                calcTime(dbAcamToCcam, dbNameAcamToCcam, "station", time);

                layout2.setBackgroundResource(R.drawable.bg_station2);

                TextView destination2 = (TextView) view.findViewById(R.id.item_destination2);
                destination2.setText("아산캠퍼스행");
                TextView time2 = (TextView) view.findViewById(R.id.item_time2);
                calcTime(dbCcamToAcam, dbNameCcamToAcam, "station", time2);

                // 전체 시간표 보기 버튼 리스너 처리
                view.findViewById(R.id.item_timetable).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),TimetableActivity.class);
                                intent.putExtra("stationName", "천안역 천캠행");
                                intent.putExtra("whatDay", whatDay);
                                startActivity(intent);
                            }
                        }
                );
                view.findViewById(R.id.item_timetable2).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),TimetableActivity.class);
                                intent.putExtra("stationName", "천안역 아캠행");
                                intent.putExtra("whatDay", whatDay);
                                startActivity(intent);
                            }
                        }
                );
                view.findViewById(R.id.item_where).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),MapActivity.class);
                                intent.putExtra("stationName", "천안역");
                                intent.putExtra("destination", "천캠행");
                                startActivity(intent);
                            }
                        }
                );
                view.findViewById(R.id.item_where2).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),MapActivity.class);
                                intent.putExtra("stationName", "천안역");
                                intent.putExtra("destination", "아캠행");
                                startActivity(intent);
                            }
                        }
                );

            } else if(args.getCharSequence(KEY_TITLE).equals("충무병원")) {
                layout.setBackgroundResource(R.drawable.bg_acam);

                TextView destination = (TextView) view.findViewById(R.id.item_destination);
                destination.setText("천안캠퍼스행");
                TextView time = (TextView) view.findViewById(R.id.item_time);
                calcTime(dbAcamToCcam, dbNameAcamToCcam, "hospital", time);

                layout2.setBackgroundResource(R.drawable.bg_acam);

                TextView destination2 = (TextView) view.findViewById(R.id.item_destination2);
                destination2.setText("아산캠퍼스행");
                TextView time2 = (TextView) view.findViewById(R.id.item_time2);
                calcTime(dbCcamToAcam, dbNameCcamToAcam, "hospital", time2);

                // 전체 시간표 보기 버튼 리스너 처리
                view.findViewById(R.id.item_timetable).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),TimetableActivity.class);
                                intent.putExtra("stationName", "충무병원 천캠행");
                                intent.putExtra("whatDay", whatDay);
                                startActivity(intent);
                            }
                        }
                );
                view.findViewById(R.id.item_timetable2).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),TimetableActivity.class);
                                intent.putExtra("stationName", "충무병원 아캠행");
                                intent.putExtra("whatDay", whatDay);
                                startActivity(intent);
                            }
                        }
                );
                view.findViewById(R.id.item_where).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),MapActivity.class);
                                intent.putExtra("stationName", "충무병원");
                                intent.putExtra("destination", "천캠행");
                                startActivity(intent);
                            }
                        }
                );
                view.findViewById(R.id.item_where2).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),MapActivity.class);
                                intent.putExtra("stationName", "충무병원");
                                intent.putExtra("destination", "아캠행");
                                startActivity(intent);
                            }
                        }
                );

            } else if(args.getCharSequence(KEY_TITLE).equals("쌍용3동")) {
                layout.setBackgroundResource(R.drawable.bg_acam);

                TextView destination = (TextView) view.findViewById(R.id.item_destination);
                destination.setText("천안캠퍼스행");
                TextView time = (TextView) view.findViewById(R.id.item_time);
                calcTime(dbAcamToCcam, dbNameAcamToCcam, "road", time);

                layout2.setBackgroundResource(R.drawable.bg_acam);

                TextView destination2 = (TextView) view.findViewById(R.id.item_destination2);
                destination2.setText("아산캠퍼스행");
                TextView time2 = (TextView) view.findViewById(R.id.item_time2);
                calcTime(dbCcamToAcam, dbNameCcamToAcam, "road", time2);

                // 전체 시간표 보기 버튼 리스너 처리
                view.findViewById(R.id.item_timetable).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),TimetableActivity.class);
                                intent.putExtra("stationName", "쌍용3동 천캠행");
                                intent.putExtra("whatDay", whatDay);
                                startActivity(intent);
                            }
                        }
                );
                view.findViewById(R.id.item_timetable2).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),TimetableActivity.class);
                                intent.putExtra("stationName", "쌍용3동 아캠행");
                                intent.putExtra("whatDay", whatDay);
                                startActivity(intent);
                            }
                        }
                );
                view.findViewById(R.id.item_where).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),MapActivity.class);
                                intent.putExtra("stationName", "쌍용3동");
                                intent.putExtra("destination", "천캠행");
                                startActivity(intent);
                            }
                        }
                );
                view.findViewById(R.id.item_where2).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),MapActivity.class);
                                intent.putExtra("stationName", "쌍용3동");
                                intent.putExtra("destination", "아캠행");
                                startActivity(intent);
                            }
                        }
                );

            } else if(args.getCharSequence(KEY_TITLE).equals("천안아산역")) {
                layout.setBackgroundResource(R.drawable.bg_acam);

                TextView destination = (TextView) view.findViewById(R.id.item_destination);
                destination.setText("천안캠퍼스행");
                TextView time = (TextView) view.findViewById(R.id.item_time);
                calcTime(dbAcamToCcam, dbNameAcamToCcam, "ktx", time);

                layout2.setBackgroundResource(R.drawable.bg_acam);

                TextView destination2 = (TextView) view.findViewById(R.id.item_destination2);
                destination2.setText("아산캠퍼스행");
                TextView time2 = (TextView) view.findViewById(R.id.item_time2);
                calcTime(dbCcamToAcam, dbNameCcamToAcam, "ktx", time2);

                // 전체 시간표 보기 버튼 리스너 처리
                view.findViewById(R.id.item_timetable).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),TimetableActivity.class);
                                intent.putExtra("stationName", "천안아산역 천캠행");
                                intent.putExtra("whatDay", whatDay);
                                startActivity(intent);
                            }
                        }
                );
                view.findViewById(R.id.item_timetable2).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),TimetableActivity.class);
                                intent.putExtra("stationName", "천안아산역 아캠행");
                                intent.putExtra("whatDay", whatDay);
                                startActivity(intent);
                            }
                        }
                );
                view.findViewById(R.id.item_where).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),MapActivity.class);
                                intent.putExtra("stationName", "천안아산역");
                                intent.putExtra("destination", "천캠행");
                                startActivity(intent);
                            }
                        }
                );
                view.findViewById(R.id.item_where2).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),MapActivity.class);
                                intent.putExtra("stationName", "천안아산역");
                                intent.putExtra("destination", "아캠행");
                                startActivity(intent);
                            }
                        }
                );

            } /*else {
                layout.setBackgroundResource(R.drawable.bg_acam);

                TextView destination = (TextView) view.findViewById(R.id.item_destination);
                destination.setText("천안캠퍼스행");
                TextView time = (TextView) view.findViewById(R.id.item_time);
                time.setText("N/A");

                layout2.setBackgroundResource(R.drawable.bg_acam);

                TextView destination2 = (TextView) view.findViewById(R.id.item_destination2);
                destination2.setText("아산캠퍼스행");
                TextView time2 = (TextView) view.findViewById(R.id.item_time2);
                time2.setText("N/A");


                int dividerColor = args.getInt(KEY_DIVIDER_COLOR);
                TextView dividerColorView = (TextView) view.findViewById(R.id.item_divider_color);
                dividerColorView.setText("Divider: #" + Integer.toHexString(dividerColor));
                dividerColorView.setTextColor(dividerColor);

            }
            */
        }
    }

    public void calcTime(SQLiteDatabase db, String tableName, String station, TextView time) {
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
                    break;
                }

                if (cursor.isLast()) {
                    time.setText("운행종료");
                    break;
                }
            } while (cursor.moveToNext());
        }
    }

}