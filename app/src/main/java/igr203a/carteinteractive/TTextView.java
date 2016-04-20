package igr203a.carteinteractive;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Nico on 20/04/2016.
 */
public class TTextView extends TextView {

    public TTextView(Context context, AttributeSet attrs){
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"Fonts/Atelier Omega.ttf"));

    }

    public TTextView(Context context)
    {
        super(context);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"Fonts/Atelier Omega.ttf"));
    }

}