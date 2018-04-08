package com.nf28.collect.appcollect.gestionViews;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nf28.collect.appcollect.InterfaceViews;
import com.nf28.collect.appcollect.ManagerInterface;
import com.nf28.collect.appcollect.R;

public class ViewAccueil implements InterfaceViews {

    private ManagerInterface mi = null;
    private View viewAccueil = null;

    private Button btn_facebook = null;
    private Button btn_telephone = null;

    public ViewAccueil(ManagerInterface _mi){
        mi = _mi;
    }

    @Override
    public boolean prepareInteraction() {
        boolean valid = false;
        try {
            viewAccueil         = mi.getView(ManagerInterface.TShowView.tsvAccueil);
            btn_facebook        = (Button) viewAccueil.findViewById(R.id.btn_facebook);
            btn_telephone       = (Button) viewAccueil.findViewById(R.id.btn_telephone);
            btn_facebook.setOnClickListener(new BtnClickFacebook());
            valid = true;
        }catch (Exception e){
            Log.e("ViewAccueil : ","prepareInteraction");
        }
        return valid;
    }

    @Override
    public boolean prepareActionView() {
        return true;
    }

    @Override
    public boolean setDataView(DatasView... _datasView) {
        return true;
    }


    private class BtnClickFacebook implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            try {
                mi.showView(ManagerInterface.TShowView.tsvLoginViaEmail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
