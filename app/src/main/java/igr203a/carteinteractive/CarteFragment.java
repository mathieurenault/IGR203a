package igr203a.carteinteractive;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import java.util.Arrays;
import android.widget.Toast;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Mathieu on 04/04/2016.
 */


public class CarteFragment extends Fragment implements AdapteurList.AdapterInterface {

    public CarteFragment() {
        // Required empty public constructor
    }

    private Button entreeButton = null;
    private Button platButton = null;
    private Button dessertButton = null;
    private Button boissonButton = null;
    private AdapteurList adapter = null;
    private ArrayList<String> test2 = null;
    FragmentInterface mCallback;


    // Container Activity must implement this interface
    public interface FragmentInterface {
        public void updateOtherFragment();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.carte_fragment, container, false);
        test2 = new ArrayList<>();


        adapter = new AdapteurList(test2, this.getActivity(),this);



        //handle listview and assign adapter
        ListView lView = (ListView)rootView.findViewById(R.id.listView2);
        if (lView==null){System.out.println("null");}

        lView.setAdapter(adapter);


        entreeButton = (Button) rootView.findViewById(R.id.entreeButton);
        platButton = (Button) rootView.findViewById(R.id.platButton);
        dessertButton = (Button) rootView.findViewById(R.id.dessertButton);
        boissonButton = (Button) rootView.findViewById(R.id.boissonButton);

        entreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Le premier paramètre est le nom de l'activité actuelle
                // Le second est le nom de l'activité de destination
                ArrayList<String> test3 = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.entreeList)));
                test2.clear();
                String[] split = null;
                for(int i=0;i<test3.size();i++){
                    System.out.println(test3.get(i));
                    split = test3.get(i).split("/");
                    //test2.add(i,split[0] + " (" + split[1] + " €)");
                    test2.add(i,test3.get(i));
                }

                adapter.notifyDataSetChanged();


            }
        });

        platButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Le premier paramètre est le nom de l'activité actuelle
                // Le second est le nom de l'activité de destination
                ArrayList<String> test3 = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.platList)));
                test2.clear();
                String[] split = null;
                for(int i=0;i<test3.size();i++){
                    System.out.println(test3.get(i));
                    split = test3.get(i).split("/");
                    //test2.add(i,split[0] + " (" + split[1] + " €)");
                    test2.add(i,test3.get(i));
                }

                adapter.notifyDataSetChanged();







            }
        });

        dessertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Le premier paramètre est le nom de l'activité actuelle
                // Le second est le nom de l'activité de destination
                ArrayList<String> test3 = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.dessertList)));
                String[] split = null;
                for(int i=0;i<test3.size();i++){
                    System.out.println(test3.get(i));
                    split = test3.get(i).split("/");
                    //test2.add(i,split[0] + " (" + split[1] + " €)");
                    test2.add(i,test3.get(i));
                }

                adapter.notifyDataSetChanged();

            }
        });

        boissonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Le premier paramètre est le nom de l'activité actuelle
                // Le second est le nom de l'activité de destination
                ArrayList<String> test3 = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.boissonList)));
                String[] split = null;
                for(int i=0;i<test3.size();i++){
                    System.out.println(test3.get(i));
                    split = test3.get(i).split("/");
                    //test2.add(i,split[0] + " (" + split[1] + " €)");
                    test2.add(i,test3.get(i));
                }

                adapter.notifyDataSetChanged();







            }
        });

        return rootView;

    }

    public void updateCommande() {
        // some action
        System.out.println("LA CA FONCTIONNE");
        mCallback.updateOtherFragment();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a = null;

        if (context instanceof Activity){
            a=(Activity) context;
        }

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (FragmentInterface) a;
        } catch (ClassCastException e) {
            throw new ClassCastException(a.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }









}

