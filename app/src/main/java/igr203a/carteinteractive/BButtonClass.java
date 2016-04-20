package igr203a.carteinteractive;

/**
 * Created by Nico on 20/04/2016.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class BButtonClass extends Button {

    public BButtonClass(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"Fonts/Champagne & Limousines Bold.ttf"));
    }

    public BButtonClass(Context context)
    {
        super(context);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"Fonts/Champagne & Limousines Bold.ttf"));
    }

}
