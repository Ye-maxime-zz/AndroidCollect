package com.nf28.collect.appcollect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.nf28.collect.appcollect.gestionViews.ViewAccueil;
import com.nf28.collect.appcollect.gestionViews.ViewAddNewList;
import com.nf28.collect.appcollect.gestionViews.ViewLoginViaEmail;

public class MainActivity extends AppCompatActivity {

    public static boolean isShown = false;
    private static View rootView = null;
    private static ManagerInterface mi_main = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity : ","onCreate");
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        rootView = (View) findViewById(R.id.rl_main);
        ViewGroup viewGroup = (ViewGroup) rootView;
        mi_main = new ManagerInterface(this, this, viewGroup);

        try {
            mi_main.addViewList(ManagerInterface.TShowView.tsvAccueil, View.inflate(this, R.layout.accueil_main, null), new ViewAccueil(mi_main));
            mi_main.addViewList(ManagerInterface.TShowView.tsvLoginViaEmail, View.inflate(this, R.layout.login_via_email, null), new ViewLoginViaEmail(mi_main));
            mi_main.addViewList(ManagerInterface.TShowView.tsvAddNewList, View.inflate(this, R.layout.add_new_list, null), new ViewAddNewList(mi_main));
            if (mi_main.prepareAllInsteractionsViews()){
                mi_main.showView(ManagerInterface.TShowView.tsvAccueil, true);
            }else {
                Log.e("MainActivity : ","prepareAllInsteractionsViews");
            }
        } catch (Exception e) {
            Log.e("MainActivity : ", e.getMessage());
        }


    }

    @Override
    protected void onDestroy() {
        isShown = false;
        Log.d("","onDestroy : ");
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        isShown = true;
        Log.d("","onStart : ");
        super.onStart();
    }
}
