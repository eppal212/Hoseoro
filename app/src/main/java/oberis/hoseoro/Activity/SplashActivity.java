package oberis.hoseoro.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SplashActivity extends Activity {

    public final int MY_PERMISSION_REQUEST_STORAGE = 1; // 저장장치 읽기/쓰기 권한 확인용 변수

    /*@TargetApi(Build.VERSION_CODES.M)*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) // 디바이스 버전이 마시멜로 이상인 경우
            checkPermission();
        else {  // 마시멜로 이하 버전이면 퍼미션 검사 안 함
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(this, MainActivity.class));    // 메인액티비티 이동
            finish();
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) { // 거절된 경우
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSION_REQUEST_STORAGE); // 퍼미션 요청
        } else {    // 이미 승낙된 경우
            try{    // 0.5초 스플래시아트 표시
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(this, MainActivity.class));    // 메인액티비티 이동
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.i("tag", "리스너 처리");
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_STORAGE: {   // 외부 저장장치 권한요청 결과
                // 요청이 취소되면 result 배열은 비어있음
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {  // 요청 승낙
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {    // 요청 거부
                    Toast.makeText(this, "앱 실행을 위해서는 DB접근권한을 승낙해야 합니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}