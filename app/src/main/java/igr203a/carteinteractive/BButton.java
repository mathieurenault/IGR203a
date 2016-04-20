package igr203a.carteinteractive;

/**
 * Created by Nico on 20/04/2016.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class BButton extends Button {

    public BButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"Fonts/green avocado.ttf"));
    }

    public BButton(Context context)
    {
        super(context);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"Fonts/green avocado.ttf"));
    }

}

