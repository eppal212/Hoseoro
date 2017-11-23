package oberis.hoseoro;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

// 참고자료 http://webnautes.tistory.com/647


public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

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
        MapFragment mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.activity_map);
        mapFragment.getMapAsync(this);
    }

    // getMapAsync()의 콜백 메소드에서 마커 설정
    @Override
    public void onMapReady(final GoogleMap map) {
        LatLng STATION;
        MarkerOptions markerOptions;

        if (stationName.equals("천안캠퍼스")) {
            STATION = new LatLng(36.829926, 127.180610);
            markerOptions = new MarkerOptions();
            markerOptions.position(STATION);
            markerOptions.title("천안캠퍼스");
            markerOptions.snippet("호서대 천안캠퍼스");
        } else if (stationName.equals("천안터미널")){
            if (destination.equals("천캠행")) {
                STATION = new LatLng(36.818760, 127.155420);
            } else {
                STATION = new LatLng(36.819693, 127.159560);
            }
            markerOptions = new MarkerOptions();
            markerOptions.position(STATION);
            markerOptions.title("천안터미널");
            markerOptions.snippet("야우리");
        } else if (stationName.equals("천안역")){
            STATION = new LatLng(36.809528, 127.147378);
            markerOptions = new MarkerOptions();
            markerOptions.position(STATION);
            markerOptions.title("천안역");
            markerOptions.snippet("전철/기차 승강장");
        } else if (stationName.equals("충무병원")){
            STATION = new LatLng(37.56, 126.97);
            markerOptions = new MarkerOptions();
            markerOptions.position(STATION);
            markerOptions.title("충무병원");
            markerOptions.snippet("약 엄청 많이 줌");
        } else if (stationName.equals("쌍용3동")){
            STATION = new LatLng(37.56, 126.97);
            markerOptions = new MarkerOptions();
            markerOptions.position(STATION);
            markerOptions.title("쌍용3동");
            markerOptions.snippet("길바닥");
        } else if (stationName.equals("천안아산역")){
            STATION = new LatLng(37.56, 126.97);
            markerOptions = new MarkerOptions();
            markerOptions.position(STATION);
            markerOptions.title("천안아산역");
            markerOptions.snippet("KTX개꿀");
        } else if (stationName.equals("아산캠퍼스")){
            STATION = new LatLng(36.738585, 127.076982);
            markerOptions = new MarkerOptions();
            markerOptions.position(STATION);
            markerOptions.title("아산캠퍼스");
            markerOptions.snippet("집");
        } else {
            STATION = new LatLng(36.738585, 127.076982);
            markerOptions = new MarkerOptions();
            markerOptions.position(STATION);
            markerOptions.title("오류");
            markerOptions.snippet("오류 발생");
        }

        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(STATION, 18)); // 지도 시작위치 설정
    }

}
