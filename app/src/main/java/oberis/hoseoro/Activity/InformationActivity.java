package oberis.hoseoro.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import oberis.hoseoro.R;

public class InformationActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        printoutTextFile(R.id.txt_privacy, R.raw.privacy);
    }

    /**
     * raw 폴더의 text파일을 읽어와 TextView에 출력하는 메소드
     * @param textView  // 출력대상이 되는 뷰
     * @param textFile  // 출력할 파일
     */
    private void printoutTextFile(int textView, int textFile) {
        String data = null;
        InputStream inputStream = getResources().openRawResource(textFile);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }

            data = new String(byteArrayOutputStream.toByteArray(),"utf-8");
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ((TextView) findViewById(textView)).setText(data);
    }
}
