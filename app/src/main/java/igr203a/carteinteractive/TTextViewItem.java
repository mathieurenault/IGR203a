package igr203a.carteinteractive;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Nico on 20/04/2016.
 */
public class TTextViewItem extends TextView {

    public TTextViewItem(Context context, AttributeSet attrs){
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"Fonts/Champagne & Limousines.ttf"));

    }

    public TTextViewItem(Context context)
    {
        super(context);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"Fonts/Champagne & Limousines.ttf"));
    }

}