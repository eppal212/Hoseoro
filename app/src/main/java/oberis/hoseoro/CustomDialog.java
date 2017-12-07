package oberis.hoseoro;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class CustomDialog extends Dialog {

    private Button mCancelButton;
    private Button mFinishButton;

    private View.OnClickListener mFinishClickListener;

    public CustomDialog(Context context, View.OnClickListener finishListener) {
        super(context/*, android.R.style.Theme_Translucent_NoTitleBar*/);
        this.mFinishClickListener = finishListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        /*// 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);*/

        setCancelable(false);
        setCanceledOnTouchOutside(true);    // dialog 밖에 터치했을 때 사라지기

        mCancelButton = (Button) findViewById(R.id.btn_dialogCancel);
        mFinishButton = (Button) findViewById(R.id.btn_dialogFinish);

        // 클릭 이벤트 셋팅
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        mFinishButton.setOnClickListener(mFinishClickListener);

        setAds();
    }

    private void setAds() {

        AdView adView = (AdView) findViewById(R.id.dialog_adView);

        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)/*.addTestDevice("00271FE205DD4D8EF5307165794013EB")*/  //  테스트 라인
                .build();
        adView.loadAd(adRequest);
    }
}