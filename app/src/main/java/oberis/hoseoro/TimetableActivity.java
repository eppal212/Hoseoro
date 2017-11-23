package oberis.hoseoro;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TimetableActivity extends AppCompatActivity {

    TermCcamToAcamDB termCcamToAcamDB;
    TermAcamToCcamDB termAcamToCcamDB;
    SaturdayCcamToAcamDB saturdayCcamToAcamDB;
    SaturdayAcamToCcamDB saturdayAcamToCcamDB;
    SundayCcamToAcamDB sundayCcamToAcamDB;
    SundayAcamToCcamDB sundayAcamToCcamDB;
    SQLiteDatabase dbCcamToAcam, dbAcamToCcam;

    int index = 0;
    int rowCount = 0;

    String dbNameCcamToAcam;
    String dbNameAcamToCcam;

    String[][] dbArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        Intent intent = getIntent();
        String stationName = intent.getStringExtra("stationName");
        int whatDay = intent.getIntExtra("whatDay", 1);

        // DB관련 작업
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
                rowCount = 73; // 맨 아래 데이터 출력 부분에서 쓸 변수 초기화 - 요일에 따라 행의 갯수가 달라짐
                break;

            case 2:
                saturdayCcamToAcamDB = new SaturdayCcamToAcamDB(this);
                saturdayAcamToCcamDB = new SaturdayAcamToCcamDB(this);
                try {
                    dbCcamToAcam = saturdayCcamToAcamDB.getWritableDatabase();
                    dbAcamToCcam = saturdayAcamToCcamDB.getWritableDatabase();
                    dbNameCcamToAcam = "SaturdayCcamToAcam";
                    dbNameAcamToCcam = "SaturdayAcamToCcam";
                } catch (SQLException ex) {
                    dbCcamToAcam = saturdayCcamToAcamDB.getReadableDatabase();
                    dbAcamToCcam = saturdayAcamToCcamDB.getReadableDatabase();
                }
                rowCount = 16;
                break;

            case 3:
                sundayCcamToAcamDB = new SundayCcamToAcamDB(this);
                sundayAcamToCcamDB = new SundayAcamToCcamDB(this);
                try {
                    dbCcamToAcam = sundayCcamToAcamDB.getWritableDatabase();
                    dbAcamToCcam = sundayAcamToCcamDB.getWritableDatabase();
                    dbNameCcamToAcam = "SundayCcamToAcam";
                    dbNameAcamToCcam = "SundayAcamToCcam";
                } catch (SQLException ex) {
                    dbCcamToAcam = sundayCcamToAcamDB.getReadableDatabase();
                    dbAcamToCcam = sundayAcamToCcamDB.getReadableDatabase();
                }
                rowCount = 22;
                break;
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

        if (stationName.equals("천안캠퍼스") || stationName.equals("천안터미널 아캠행")
                || stationName.equals("천안역 아캠행") || stationName.equals("충무병원 아캠행")
                || stationName.equals("쌍용3동 아캠행") || stationName.equals("천안아산역 아캠행")) {
            table_Row.setText("천안캠퍼스 출발 > 아산캠퍼스 도착");
            tableColumn1.setText("천캠\n출발");
            tableColumn2.setText("천안\n터미널");
            tableColumn3.setText("천안역");
            tableColumn4.setText("충무\n병원");
            tableColumn5.setText("쌍용\n2동");
            tableColumn6.setText("KTX\n천안\n아산역");
            tableColumn7.setText("아캠\n도착");

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
        }
        else if (stationName.equals("아산캠퍼스") || stationName.equals("천안터미널 천캠행")
                || stationName.equals("천안역 천캠행") || stationName.equals("충무병원 천캠행")
                || stationName.equals("쌍용3동 천캠행") || stationName.equals("천안아산역 천캠행")) {
            table_Row.setText("아산캠퍼스 출발 > 천안캠퍼스 도착");
            tableColumn1.setText("아캠\n출발");
            tableColumn2.setText("KTX\n천안\n아산역");
            tableColumn3.setText("쌍용\n2동");
            tableColumn4.setText("충무\n병원");
            tableColumn5.setText("천안역");
            tableColumn6.setText("천안\n터미널");
            tableColumn7.setText("천캠\n도착");

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

        for (int i = 0; i < rowCount; i++) {
        // Creation row
            final TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

            for(int j = 0 ; j < 7 ; j++){
                final TextView text = new TextView(this);

                text.setText(dbArray[i][j]);
                text.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                text.setBackgroundColor(getResources().getColor(R.color.White));
                text.setGravity(Gravity.CENTER);

                tableRow.addView(text);
            }
            tableLayout.addView(tableRow);
        }
    }
}