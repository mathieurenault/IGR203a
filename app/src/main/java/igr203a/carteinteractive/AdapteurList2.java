package igr203a.carteinteractive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

public class AdapteurList2 extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> list2 = new ArrayList<String>();
    private Context context;


    public AdapteurList2(ArrayList<String> list, Context context, ArrayList<String> list2) {
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
            view = inflater.inflate(R.layout.custom_layout_commande, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string2);
        String split[] = null;
        split = list.get(position).split("/");
        listItemText.setText(split[0] + " (" + split[1] + " €)");
        TextView edit = (TextView) view.findViewById(R.id.edit_nombre);
        edit.setText(list2.get(position));

        //Handle buttons and add onClickListeners
        Button plusButton = (Button)view.findViewById(R.id.plus_btn);
        //Button addBtn = (Button)view.findViewById(R.id.add_btn);


        plusButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something

                System.out.println("!!!!!!!!!!!!!!!!!!!!!Number"+list2.get(position));
                int number = Integer.parseInt(list2.get(position));
                number=number+1;
                list2.set(position,Integer.toString(number));
                notifyDataSetChanged();

            }
        });

        Button moinsButton = (Button)view.findViewById(R.id.moins_btn);
        //Button addBtn = (Button)view.findViewById(R.id.add_btn);

        moinsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                int number = Integer.parseInt(list2.get(position));
                if(number>1){number=number-1;list2.set(position,Integer.toString(number));}
                else if(number==1){
                    list2.remove(position);
                    list.remove(position);
                    SharedPreferences recupTableau = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor recupTableauEditor = recupTableau.edit();
                    recupTableauEditor.remove("Status_" + position);
                    ArrayList<String> saveTab = new ArrayList<String>();
                    for(int i=position;i<recupTableau.getInt("Status_size",0);i++){
                        saveTab.add(recupTableau.getString("Status_" + (i+1),null));
                    }
                    for(int i=position;i<recupTableau.getInt("Status_size",0);i++){
                        recupTableauEditor.putString("Status_" + i, saveTab.get(i-position));
                    }
                    recupTableauEditor.putInt("Status_size", recupTableau.getInt("Status_size",0)-1);
                    recupTableauEditor.commit();

                }
                notifyDataSetChanged();

            }
        });


        return view;
    }
}
