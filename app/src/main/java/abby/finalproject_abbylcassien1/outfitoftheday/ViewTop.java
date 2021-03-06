package abby.finalproject_abbylcassien1.outfitoftheday;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Base64;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;

import abby.finalproject_abbylcassien1.Load.Clothing;
import abby.finalproject_abbylcassien1.R;

/**
 * Created by cassondranealon on 3/30/16.
 */
public class ViewTop extends FrameLayout {

    private Firebase rootRef = new Firebase("https://abbyandcassie.firebaseio.com/");

    private TextView textView;
    private TextView textViewInfo;
    private ImageView imageView;

    public ViewTop(Context context) {
        super(context);
    }

    public ViewTop(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewTop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public ViewTop(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(Clothing clothing) {
        inflate(getContext(), R.layout.randomtop, this);
        textView = (TextView) findViewById(R.id.text_top);
        textViewInfo = (TextView) findViewById(R.id.top_info);
        imageView = (ImageView) findViewById(R.id.imageTop);

        imageView.setImageBitmap(byteStringToBitmap(clothing.photo));
        textView.setText(clothing.name);
        textViewInfo.setText(clothing.info);


    }

    private Bitmap byteStringToBitmap(String byteString) {
        byte[] imageAsBytes = Base64.decode(byteString.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }
}
