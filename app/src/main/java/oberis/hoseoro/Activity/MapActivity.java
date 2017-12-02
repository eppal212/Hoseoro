package oberis.hoseoro.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import oberis.hoseoro.R;

// 참고자료 http://webnautes.tistory.com/647
public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    public final int MY_PERMISSION_REQUEST_GOOGLEMAP = 2;

    GoogleMap map;

    String stationName;
    String destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setUpMapIfNeeded();

        Intent intent = getIntent();
        stationName = intent.getStringExtra("stationName");
        destination = intent.getStringExtra("destination");
    }

    private void setUpMapIfNeeded() {
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.activity_map);
        mapFragment.getMapAsync(this);
    }

    // getMapAsync()의 콜백 메소드에서 마커 설정
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(final GoogleMap map) {
        this.map = map;

        checkPermission();  // 퍼미션 요청

        LatLng STATION = null;
        MarkerOptions markerOptions = new MarkerOptions();

        if (stationName.equals("cCam")) {
            STATION = new LatLng(36.829926, 127.180610);
            markerOptions.position(STATION);
            markerOptions.title("천안캠퍼스");
            markerOptions.snippet("셔틀버스 정류장");
        } else if (stationName.equals("terminal")) {
            if (destination.equals("천캠행")) {
                STATION = new LatLng(36.818791, 127.156675);
                markerOptions.position(STATION);
                markerOptions.title("천안터미널");
                markerOptions.snippet("건너편 서해약국 앞");
            } else if (destination.equals("아캠행")) {
                STATION = new LatLng(36.819679, 127.159547);
                markerOptions.position(STATION);
                markerOptions.title("천안터미널");
                markerOptions.snippet("허브시티 앞");
            }
        } else if (stationName.equals("station")) {
            STATION = new LatLng(36.809405, 127.147321);
            markerOptions.position(STATION);
            markerOptions.title("천안역");
            markerOptions.snippet("동광장 셔틀버스 승강장");
        } else if (stationName.equals("hospital")) {
            if (destination.equals("천캠행")) {
                STATION = new LatLng(36.798304, 127.133689);
                markerOptions.position(STATION);
                markerOptions.title("충무병원");
                markerOptions.snippet("버스정류장");
            } else if (destination.equals("아캠행")) {
                STATION = new LatLng(36.798166, 1273132494);
                markerOptions.position(STATION);
                markerOptions.title("충무병원");
                markerOptions.snippet("버들약국 앞");
            }
        } else if (stationName.equals("road")) {
            if (destination.equals("천캠행")) {
                STATION = new LatLng(36.800969, 127.118962);
                markerOptions.position(STATION);
                markerOptions.title("쌍용2동");
                markerOptions.snippet("불당대로방향 지하도 출구");
            } else if (destination.equals("아캠행")) {
                STATION = new LatLng(36.801021, 127.119726);
                markerOptions.position(STATION);
                markerOptions.title("쌍용3동");
                markerOptions.snippet("카스바베큐 앞");
            }
        } else if (stationName.equals("ktx")) {
            STATION = new LatLng(36.793785, 127.103591);
            markerOptions.position(STATION);
            markerOptions.title("천안아산역");
            markerOptions.snippet("1층정문 시내버스정류장 앞");
        } else if (stationName.equals("aCam")) {
            STATION = new LatLng(36.738585, 127.076982);
            markerOptions.position(STATION);
            markerOptions.title("아산캠퍼스");
            markerOptions.snippet("셔틀버스 정류장");
        } else {
            STATION = new LatLng(36.738585, 127.076982);
            markerOptions.position(STATION);
            markerOptions.title("오류");
            markerOptions.snippet("오류 발생");
        }
        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(STATION, 18)); // 지도 시작위치 설정


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_GOOGLEMAP);
        } else {
            map.setMyLocationEnabled(true);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_GOOGLEMAP: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {  // 요청 승낙
                    map.setMyLocationEnabled(true);
                } else {
                    Toast.makeText(this, "현재 위치를 표시하지 않고 구글지도를 사용합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
