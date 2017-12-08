package oberis.hoseoro.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import oberis.hoseoro.Database.HolidayAcamToCcamDB;
import oberis.hoseoro.Database.HolidayCcamToAcamDB;
import oberis.hoseoro.Database.TermAcamToCcamDB;
import oberis.hoseoro.Database.TermCcamToAcamDB;
import oberis.hoseoro.Database.VacAcamToCcamDB;
import oberis.hoseoro.Database.VacCcamToAcamDB;
import oberis.hoseoro.R;

public class TimetableActivity extends AppCompatActivity {

    TermCcamToAcamDB termCcamToAcamDB;
    TermAcamToCcamDB termAcamToCcamDB;
    VacCcamToAcamDB vacCcamToAcamDB;
    VacAcamToCcamDB vacAcamToCcamDB;
    HolidayCcamToAcamDB holidayCcamToAcamDB;
    HolidayAcamToCcamDB holidayAcamToCcamDB;
    SQLiteDatabase dbCcamToAcam, dbAcamToCcam;

    int index = 0;
    int rowCount = 0;
    int stationColumn = 0;
    boolean isCheck = true; // 하이라이트 시간을 한 번만 표시하기 위한 변수

    String dbNameCcamToAcam;
    String dbNameAcamToCcam;

    String[][] dbArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        Intent intent = getIntent();
        String stationName = intent.getStringExtra("stationName");
        String destination = intent.getStringExtra("destination");
        boolean shuttleMode = intent.getBooleanExtra("shuttleMode", true);
        int whatDay = intent.getIntExtra("whatDay", 1);

        // DB관련 작업
        if (shuttleMode == true) {  // 학기중일 때
            switch (whatDay) {
                case 1:
                    termCcamToAcamDB = new TermCcamToAcamDB(this);
                    termAcamToCcamDB = new TermAcamToCcamDB(this);
                    try {
                        dbCcamToAcam = termCcamToAcamDB.getWritableDatabase();
                        dbAcamToCcam = termAcamToCcamDB.getWritableDatabase();
                        dbNameCcamToAcam = "TermCcamToAcam";
                        dbNameAcamToCcam = "TermAcamToCcam";
                    } catch (SQLException ex) {
                        dbCcamToAcam = termCcamToAcamDB.getReadableDatabase();
                        dbAcamToCcam = termAcamToCcamDB.getReadableDatabase();
                    }
                    if (destination.equals("천캠행"))
                        rowCount = 76; // 맨 아래 데이터 출력 부분에서 쓸 변수 초기화 - 요일에 따라 행의 갯수가 달라짐
                    else if (destination.equals("아캠행"))
                        rowCount = 82;
                    break;

                case 2:
                    holidayCcamToAcamDB = new HolidayCcamToAcamDB(this);
                    holidayAcamToCcamDB = new HolidayAcamToCcamDB(this);
                    try {
                        dbCcamToAcam = holidayCcamToAcamDB.getWritableDatabase();
                        dbAcamToCcam = holidayAcamToCcamDB.getWritableDatabase();
                        dbNameCcamToAcam = "HolidayCcamToAcam";
                        dbNameAcamToCcam = "HolidayAcamToCcam";
                    } catch (SQLException ex) {
                        dbCcamToAcam = holidayCcamToAcamDB.getReadableDatabase();
                        dbAcamToCcam = holidayAcamToCcamDB.getReadableDatabase();
                    }
                    rowCount = 24;
                    break;
            }
        } else {    // 방학줄일 때
            vacCcamToAcamDB = new VacCcamToAcamDB(this);
            vacAcamToCcamDB = new VacAcamToCcamDB(this);
            try {
                dbCcamToAcam = vacCcamToAcamDB.getWritableDatabase();
                dbAcamToCcam = vacAcamToCcamDB.getWritableDatabase();
                dbNameCcamToAcam = "VacCcamToAcam";
                dbNameAcamToCcam = "VacAcamToCcam";
            } catch (SQLException ex) {
                dbCcamToAcam = vacCcamToAcamDB.getReadableDatabase();
                dbAcamToCcam = vacAcamToCcamDB.getReadableDatabase();
            }
            rowCount = 14;
        }
        dbArray = new String[rowCount][7];  // 배열 초기화


        TextView table_Row = (TextView) findViewById(R.id.table_row);
        TextView tableColumn1 = (TextView) findViewById((R.id.table_column1));
        TextView tableColumn2 = (TextView) findViewById((R.id.table_column2));
        TextView tableColumn3 = (TextView) findViewById((R.id.table_column3));
        TextView tableColumn4 = (TextView) findViewById((R.id.table_column4));
        TextView tableColumn5 = (TextView) findViewById((R.id.table_column5));
        TextView tableColumn6 = (TextView) findViewById((R.id.table_column6));
        TextView tableColumn7 = (TextView) findViewById((R.id.table_column7));

        if (destination.equals("아캠행")) {
            table_Row.setText("천안캠퍼스 출발 > 아산캠퍼스 도착");
            tableColumn1.setText("천캠\n출발");
            tableColumn2.setText("천안\n터미널");
            tableColumn3.setText("천안역");
            tableColumn4.setText("충무\n병원");
            tableColumn5.setText("쌍용\n3동");
            tableColumn6.setText("KTX\n천안\n아산역");
            tableColumn7.setText("아캠\n도착");

            // station 행 번호 초기화
            if (stationName.equals(getString(R.string.tab_cCam)))
                stationColumn = 0;
            else if (stationName.equals(getString(R.string.tab_terminal)))
                stationColumn = 1;
            else if (stationName.equals(getString(R.string.tab_station)))
                stationColumn = 2;
            else if (stationName.equals(getString(R.string.tab_hospital)))
                stationColumn = 3;
            else if (stationName.equals(getString(R.string.tab_road)))
                stationColumn = 4;
            else if (stationName.equals(getString(R.string.tab_ktx)))
                stationColumn = 5;
            else if (stationName.equals(getString(R.string.tab_aCam)))
                stationColumn = 6;

            //커서를 이용해 DB 접근
            Cursor cursor = dbCcamToAcam.rawQuery("SELECT * FROM " + dbNameCcamToAcam + ";", null);
            // DB 시간 계산
            if (cursor.moveToFirst()) {
                do {
                    dbArray[index][0] = cursor.getString(cursor.getColumnIndex("cCam"));
                    dbArray[index][1] = cursor.getString(cursor.getColumnIndex("terminal"));
                    dbArray[index][2] = cursor.getString(cursor.getColumnIndex("station"));
                    dbArray[index][3] = cursor.getString(cursor.getColumnIndex("hospital"));
                    dbArray[index][4] = cursor.getString(cursor.getColumnIndex("road"));
                    dbArray[index][5] = cursor.getString(cursor.getColumnIndex("ktx"));
                    dbArray[index][6] = cursor.getString(cursor.getColumnIndex("aCam"));
                    index++;
                } while (cursor.moveToNext());
            }
        } else if (destination.equals("천캠행")) {
            table_Row.setText("아산캠퍼스 출발 > 천안캠퍼스 도착");
            tableColumn1.setText("아캠\n출발");
            tableColumn2.setText("KTX\n천안\n아산역");
            tableColumn3.setText("쌍용\n2동");
            tableColumn4.setText("충무\n병원");
            tableColumn5.setText("천안역");
            tableColumn6.setText("천안\n터미널");
            tableColumn7.setText("천캠\n도착");

            // station 행 번호 초기화
            if (stationName.equals(getString(R.string.tab_aCam)))
                stationColumn = 0;
            else if (stationName.equals(getString(R.string.tab_ktx)))
                stationColumn = 1;
            else if (stationName.equals(getString(R.string.tab_road)))
                stationColumn = 2;
            else if (stationName.equals(getString(R.string.tab_hospital)))
                stationColumn = 3;
            else if (stationName.equals(getString(R.string.tab_station)))
                stationColumn = 4;
            else if (stationName.equals(getString(R.string.tab_terminal)))
                stationColumn = 5;
            else if (stationName.equals(getString(R.string.tab_cCam)))
                stationColumn = 6;

            //커서를 이용해 DB 접근
            Cursor cursor = dbAcamToCcam.rawQuery("SELECT * FROM " + dbNameAcamToCcam + ";", null);
            // DB 시간 계산
            if (cursor.moveToFirst()) {
                do {
                    dbArray[index][0] = cursor.getString(cursor.getColumnIndex("aCam"));
                    dbArray[index][1] = cursor.getString(cursor.getColumnIndex("ktx"));
                    dbArray[index][2] = cursor.getString(cursor.getColumnIndex("road"));
                    dbArray[index][3] = cursor.getString(cursor.getColumnIndex("hospital"));
                    dbArray[index][4] = cursor.getString(cursor.getColumnIndex("station"));
                    dbArray[index][5] = cursor.getString(cursor.getColumnIndex("terminal"));
                    dbArray[index][6] = cursor.getString(cursor.getColumnIndex("cCam"));
                    index++;
                } while (cursor.moveToNext());
            }
        }

        // 데이터 출력
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.table_main); // 테이블 id 명

        // 하이라이트를 주기 위해 현재 시간을 계산
        Date nowTime = calcNowTime();

        for (int i = 0; i < rowCount; i++) {
        // Creation row
            final TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

            for(int j = 0 ; j < 7 ; j++){
                final TextView text = new TextView(this);

                text.setText(dbArray[i][j]);
                text.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                // 현재 정류장에 배경색으로 표시
                if (j == stationColumn ) {
                    text.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                    // 특별히 바로 앞에 오는 시간표에 하이라이트 처리
                    if (isCheck && !dbArray[i][j].equals("-"))
                        isCheck = setHighlight(text, nowTime, dbArray[i][j]);

                } else
                    text.setBackgroundColor(getResources().getColor(R.color.White));

                text.setGravity(Gravity.CENTER);

                tableRow.addView(text);
            }
            tableLayout.addView(tableRow);
        }
    }

    // 현재 시간을 계산하는 메소드
    public Date calcNowTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");   // DateFormat
        Date now = new Date(System.currentTimeMillis());    // 현재 시간 구함
        String nowStr = dateFormat.format(now);
        Date nowTime = null;
        try {   nowTime = dateFormat.parse(nowStr); }
        catch (ParseException e) {    e.printStackTrace();    }
        return nowTime;
    }

    /**
     * 바로 다음에 오는 시간에 하이라이트 처리를 하기 위한 메소드
     * @param textView  // 처리할 텍스트뷰
     * @param nowTime   // 현재 시간 (비교용)
     * @param dbArrayTime   // DB의 시간
     * @return  // 뒤쪽 시간표는 안 건들고 한 번만 처리하기 위해 boolean값을 반환
     */
    public boolean setHighlight(TextView textView, Date nowTime, String dbArrayTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");   // DateFormat

        Date dbTime = null;     // db시간 구함
        try {   dbTime = dateFormat.parse(dbArrayTime); }
        catch (ParseException e) {  e.printStackTrace();    }

        if (dbTime.after(nowTime)) {    // 표시할지 비교 ~ db시간이 더 늦어야 함
            textView.setBackgroundColor(getResources().getColor(R.color.colorMain));
            textView.setPaintFlags(textView.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
            textView.setTextColor(getResources().getColor(R.color.White));
            return false;
        }
        return true;
    }
}
