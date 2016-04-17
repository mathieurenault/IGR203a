package igr203a.carteinteractive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import java.lang.Object;import java.lang.Override;import java.lang.String;
import java.lang.reflect.Array;
import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Button;
/**
 * Created by Gautier on 03/03/2015.
 */

public class AdapteurList3 extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> list2 = new ArrayList<String>();
    private Context context;


    public AdapteurList3(ArrayList<String> list, Context context, ArrayList<String> list2) {
        this.list = list;
        this.context = context;
        this.list2=list2;
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_layout_addition, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.nomProduit);
        String split[] = null;
        split = list.get(position).split("/");
        if (split.length > 1) {
            listItemText.setText(split[0] + " (" + split[1] + " €)");
        }
        else {
            listItemText.setText(split[0]);
        }
        TextView edit = (TextView) view.findViewById(R.id.quantiteProduit);
        edit.setText(list2.get(position));






        return view;
    }
}