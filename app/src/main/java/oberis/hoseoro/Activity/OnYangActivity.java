package oberis.hoseoro.Activity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import oberis.hoseoro.Database.OnyangDB;
import oberis.hoseoro.R;

public class OnYangActivity extends AppCompatActivity {

    OnyangDB onyangDB;
    SQLiteDatabase dbOnyang;
    String dbNameOnyang;
    String[][] dbArray;

    int index = 0;  // 배열에서 사용할 변수
    final int table1_rowCount = 8;  // 각 시간표마다 운행(줄)수
    final int table2_rowCount = 6;
    final int table3_rowCount = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onyang);

        // DB관련 작업
        onyangDB = new OnyangDB(this);
        try {
            dbOnyang = onyangDB.getWritableDatabase();
            dbNameOnyang = "Onyang";
        } catch (SQLException ex) {
            dbOnyang = onyangDB.getReadableDatabase();
        }

        dbArray = new String[table1_rowCount+table2_rowCount+table3_rowCount][6];  // 배열 초기화

        // 표 제목 적어주기
        TextView table1_Row = (TextView) findViewById(R.id.onyang_table1_row);
        TextView table2_Row = (TextView) findViewById(R.id.onyang_table2_row);
        TextView table3_Row = (TextView) findViewById(R.id.onyang_table3_row);
        table1_Row.setText("학기중 평일 온양순환");
        table2_Row.setText("학기중 주말 온양순환");
        table3_Row.setText("방학중 온양순환");

        //커서를 이용해 DB 접근 - 배열에 DB의 모든 데이터 입력
        Cursor cursor = dbOnyang.rawQuery("SELECT * FROM " + dbNameOnyang + ";", null);
        if (cursor.moveToFirst()) {
            do {
                dbArray[index][0] = cursor.getString(cursor.getColumnIndex("aCam1"));
                dbArray[index][1] = cursor.getString(cursor.getColumnIndex("baebang1"));
                dbArray[index][2] = cursor.getString(cursor.getColumnIndex("terminal"));
                dbArray[index][3] = cursor.getString(cursor.getColumnIndex("onyang"));
                dbArray[index][4] = cursor.getString(cursor.getColumnIndex("baebang2"));
                dbArray[index][5] = cursor.getString(cursor.getColumnIndex("aCam2"));
                index++;
            } while (cursor.moveToNext());
        }

        // table 객체 연결
        final TableLayout tableLayout1 = (TableLayout) findViewById(R.id.onyang_table1); // 테이블 id 명
        final TableLayout tableLayout2 = (TableLayout) findViewById(R.id.onyang_table2); // 테이블 id 명
        final TableLayout tableLayout3 = (TableLayout) findViewById(R.id.onyang_table3); // 테이블 id 명

        // 표 출력
        addTableRow(tableLayout1, 0, table1_rowCount, dbArray);
        addTableRow(tableLayout2, table1_rowCount, table1_rowCount+table2_rowCount, dbArray);
        addTableRow(tableLayout3, table1_rowCount+table2_rowCount, table1_rowCount+table2_rowCount+table3_rowCount, dbArray);
    }

    /**
     * 데이터가 들어있는 배열을 이용해 표를 그려주는 메소드
     * @param tableLayout   // 어느 표에 그려줄건지
     * @param start_rowCount    // DB에서 출력할 데이터의 몇번째 줄부터~
     * @param end_rowCount      // ~몇번째 줄까지
     * @param dbArray   // 데이터가 들어있는 배열
     */
    private void addTableRow(TableLayout tableLayout, int start_rowCount, int end_rowCount, String[][] dbArray) {
        for (int i = start_rowCount; i < end_rowCount; i++) {
            // 행을 만듦
            final TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

            for(int j = 0 ; j < 6 ; j++){
                // TextView를 만들어서 데이터를 넣고 행에 넣음
                final TextView text = new TextView(this);

                text.setText(dbArray[i][j]);
                text.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                text.setBackgroundColor(getResources().getColor(R.color.White));
                text.setGravity(Gravity.CENTER);

                tableRow.addView(text);
            }
            tableLayout.addView(tableRow);  // table에 행 추가
        }
    }
}
