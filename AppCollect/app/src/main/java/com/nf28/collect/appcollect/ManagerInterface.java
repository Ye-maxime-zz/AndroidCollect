package com.nf28.collect.appcollect;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManagerInterface {

    private Context contextInterface;
    private Activity activity;
    private ManagerViews mv;
    private HashMap<TShowView, Object > mapObjetsView;
    private List<InterfaceViews.DatasView[]> listDatasViews = null;


    public enum TShowView {
        tsvMain(0), tsvAccueil(1), tsvLoginViaEmail(2), tsvAddNewList(3);

        private final int value;
        private TShowView(int _value){
            this.value = _value;
        }
    }

    public ManagerInterface(Activity _activity, Context _context, ViewGroup viewGroup){
        activity            = _activity;
        contextInterface    = _context;
        mv                  = new ManagerViews(_context, viewGroup);
        mapObjetsView       = new HashMap<>();
        listDatasViews      = new ArrayList<>();
    }

    public boolean addViewList(TShowView _typeview, View viewAdd, Object _classViewObject) throws Exception{
        Log.d("ManagerInterface : ",  "addViewList " + _typeview);
        boolean validAdd = false;
        if ((_classViewObject instanceof InterfaceViews) && !mapObjetsView.containsKey(_typeview)){
            validAdd = mv.addView(_typeview, viewAdd);
            mapObjetsView.put(_typeview, _classViewObject);
        }
        return validAdd;
    }


    public View getView(TShowView _typeview) throws Exception{
        return mv.getView(_typeview);
    }

    private boolean prepareInteractionView(TShowView _typeview) throws Exception{
        boolean validPrepare = false;
        InterfaceViews viewClass = (InterfaceViews) mapObjetsView.get(_typeview);
        validPrepare = viewClass.prepareInteraction();//prepare le layout de cette vue !!
        return validPrepare;
    }


    public boolean prepareAllInsteractionsViews() throws Exception{
        boolean validPrepare = true;
        for (TShowView tsv : mv.getListTypeViews()){
            validPrepare &= prepareInteractionView(tsv);
            if (!validPrepare)
                break;
        }
        return validPrepare;
    }


    public void showView(ManagerInterface.TShowView _typeviewshow, InterfaceViews.DatasView... _datasView) throws Exception{
        //preparer le prochaine interface avec les fonctions associe a la vue
        showView(_typeviewshow, false, _datasView);
    }

    public void showView(ManagerInterface.TShowView _typeviewshow, boolean _resetListPrevious, InterfaceViews.DatasView... _datasView) throws Exception{
        //preparer le prochaine interface avec les fonctions associes a la vue
        if (mapObjetsView.containsKey(_typeviewshow)) {
            boolean canShow = true;
            listDatasViews.add(_datasView);
//          if ((_datasView != null) && (_datasView.length > 0))
//              canShow = setDataView(_typeviewshow, _datasView);
            Object o = mapObjetsView.get(_typeviewshow);
            InterfaceViews iv = (InterfaceViews) o;
            if (canShow && iv.prepareActionView()) {
                mv.showView(_typeviewshow, _resetListPrevious);
            }
            else {
                Log.d("ManagerInterface : ", "prepareActionView false");
            }
        }else{
                Log.d("ManagerInterface : ", "ManagerInterface showView : ne contient pas la vue demandée");
        }
    }
}
