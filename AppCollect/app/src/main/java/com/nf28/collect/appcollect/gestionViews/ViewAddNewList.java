package com.nf28.collect.appcollect.gestionViews;

import android.util.Log;
import android.view.View;

import com.nf28.collect.appcollect.InterfaceViews;
import com.nf28.collect.appcollect.ManagerInterface;

public class ViewAddNewList implements InterfaceViews {

    private ManagerInterface mi = null;
    private View viewAddNewList = null;

    public ViewAddNewList(ManagerInterface _mi){
        mi = _mi;
    }

    @Override
    public boolean prepareInteraction() {
        boolean valid = false;
        try {
            viewAddNewList         = mi.getView(ManagerInterface.TShowView.tsvAddNewList);

            valid = true;
        }catch (Exception e){
            Log.e("ViewAddNewList : ","prepareInteraction");
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
}
