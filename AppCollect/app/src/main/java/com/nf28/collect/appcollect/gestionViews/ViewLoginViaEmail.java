package com.nf28.collect.appcollect.gestionViews;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nf28.collect.appcollect.InterfaceViews;
import com.nf28.collect.appcollect.ManagerInterface;
import com.nf28.collect.appcollect.R;

public class ViewLoginViaEmail implements InterfaceViews {

    private ManagerInterface mi = null;
    private View viewLoginViaEmail = null;

    private Button btn_next = null;

    public ViewLoginViaEmail(ManagerInterface _mi){
        mi = _mi;
    }

    @Override
    public boolean prepareInteraction() {
        boolean valid = false;
        try {
            viewLoginViaEmail         = mi.getView(ManagerInterface.TShowView.tsvLoginViaEmail);
            btn_next                  = viewLoginViaEmail.findViewById(R.id.btn_next);
            btn_next.setOnClickListener(new BtnClickNext());
            valid = true;
        }catch (Exception e){
            Log.e("ViewLoginViaEmail : ","prepareInteraction");
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


    private  class BtnClickNext implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            try {
                mi.showView(ManagerInterface.TShowView.tsvAddNewList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
