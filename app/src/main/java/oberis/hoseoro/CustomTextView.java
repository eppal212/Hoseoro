package oberis.hoseoro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TextView에 테두리를 넣기 위한 사용자 지정 View
 * attrs.xml과 함께 쓰인다.
 * 참고 : http://egloos.zum.com/zerosum30/v/1188818
 */
@SuppressLint("AppCompatCustomView")
public class CustomTextView extends TextView {

    private boolean stroke = false;
    private float strokeWidth = 0.0f;
    private int strokeColor;

    // 기본 생성자를 포함한 3개의 생성자
    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs); initView(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle); initView(context, attrs);
    }

    // 생성자에서 사용되는 변수 초기화 메소드
    private void initView(Context context, AttributeSet attrs) {
        // attrs.xml 사용 준비 & 정의한 속성 할당
        TypedArray aray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        stroke = aray.getBoolean(R.styleable.CustomTextView_textStroke, false);
        strokeWidth = aray.getFloat(R.styleable.CustomTextView_textStrokeWidth, 0.0f);
        strokeColor = aray.getColor(R.styleable.CustomTextView_textStrokeColor, 0xffffffff);
    }

    /**
     * Paint 클래스를 통해 stroke 그리기 가능
     * TextView의 onDraw()를 통해 Paint클래스를 얻을 수 있음.
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        if (stroke) {   // 테두리가 적용될 뷰라면
            ColorStateList states = getTextColors();
            getPaint().setStyle(Style.STROKE);
            getPaint().setStrokeWidth(strokeWidth);
            setTextColor(strokeColor);
            super.onDraw(canvas);
            getPaint().setStyle(Style.FILL);
            setTextColor(states);
        }
        super.onDraw(canvas);
    }
}
