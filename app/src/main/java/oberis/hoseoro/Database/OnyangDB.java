package oberis.hoseoro.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OnyangDB extends SQLiteOpenHelper {
    final static private String DB_NAME = "Onyang.db";
    final static private int DB_VERSION = 10;

    public OnyangDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Onyang (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "aCam1 TEXT, " +
                "baebang1 TEXT, " +
                "terminal TEXT, " +
                "onyang TEXT, " +
                "baebang2 TEXT," +
                "aCam2 TEXT);");
        // 학기중 평일
        db.execSQL("INSERT INTO Onyang VALUES (null, '8:00', '8:25', '8:35', '8:45', '9:00', '9:10');");
        db.execSQL("INSERT INTO Onyang VALUES (null, '9:20', '9:35', '9:45', '9:55', '10:10', '10:20');");
        db.execSQL("INSERT INTO Onyang VALUES (null, '10:50', '11:05', '11:15', '11:25', '11:40', '11:50');");
        db.execSQL("INSERT INTO Onyang VALUES (null, '13:50', '14:05', '14:15', '14:25', '14:40', '14:50');");
        db.execSQL("INSERT INTO Onyang VALUES (null, '15:00', '15:15', '15:25', '15:35', '15:50', '16:00');");
        db.execSQL("INSERT INTO Onyang VALUES (null, '16:30', '16:45', '16:55', '17:05', '17:20', '17:30');");
        db.execSQL("INSERT INTO Onyang VALUES (null, '17:40', '17:55', '18:05', '18:15', '18:30', '18:40');");
        db.execSQL("INSERT INTO Onyang VALUES (null, '19:30', '19:45', '19:55', '20:05', '20:20', '20:30');");
        // 학기중 주말
        db.execSQL("INSERT INTO Onyang VALUES (null, '8:30', '8:45', '8:55', '9:05', '9:20', '9:30');");
        db.execSQL("INSERT INTO Onyang VALUES (null, '10:30', '10:45', '10:55', '11:05', '11:20', '11:30');");
        db.execSQL("INSERT INTO Onyang VALUES (null, '12:00', '12:15', '12:25', '12:35', '12:50', '13:00');");
        db.execSQL("INSERT INTO Onyang VALUES (null, '13:30', '13:45', '13:55', '14:05', '14:20', '14:30');");
        db.execSQL("INSERT INTO Onyang VALUES (null, '15:30', '15:45', '15:55', '16:05', '16:20', '16:30');");
        db.execSQL("INSERT INTO Onyang VALUES (null, '17:10', '17:25', '17:35', '17:45', '18:00', '18:10');");
        // 방학중
        db.execSQL("INSERT INTO Onyang VALUES (null, '8:30', '8:45', '8:52', '9:05', '9:15', '9:25');");
        db.execSQL("INSERT INTO Onyang VALUES (null, '12:00', '12:15', '12:22', '12:35', '12:45', '12:55');");
        db.execSQL("INSERT INTO Onyang VALUES (null, '17:10', '17:25', '17:32', '17:45', '17:55', '18:05');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Onyang");
        onCreate(db);
    }
}
