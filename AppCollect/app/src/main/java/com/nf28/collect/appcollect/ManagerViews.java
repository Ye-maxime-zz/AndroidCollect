package com.nf28.collect.appcollect;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ManagerViews {

    private Context contextManager;
    private ViewGroup viewMain;
    private ManagerInterface.TShowView screenCurrent = null;
    private HashMap<ManagerInterface.TShowView, View> mapViews;
    private List<ManagerInterface.TShowView> listScreenPrevious;

    public ManagerViews(Context _context, ViewGroup _viewMain){
        contextManager      = _context;
        mapViews            = new HashMap<>();
        listScreenPrevious  = new ArrayList<>();
        viewMain            = _viewMain;
    }

    public boolean addView(ManagerInterface.TShowView _typeView, View view) throws Exception{
        boolean validAdd = false;
        try {
            if (!mapViews.containsKey(_typeView)) {
                mapViews.put(_typeView, view);
                validAdd = true;
            }
        }catch (Exception e){
            throw e;
        }
        return validAdd;
    }


    public View getView(ManagerInterface.TShowView _typeview) throws Exception{
        View view = null;
        if (mapViews.containsKey(_typeview)){
            view = mapViews.get(_typeview);
        }
        return view;
    }


    public boolean showView(ManagerInterface.TShowView _typeviewshow){
        return showView(_typeviewshow, false);
    }

    public boolean showView(ManagerInterface.TShowView _typeviewshow, boolean _resetListPrevious){
        Log.d("ManagerViews : ", "showView : " + _typeviewshow);
        boolean validShow = false;
        try {
            if (_resetListPrevious){
                listScreenPrevious.clear();
            }else if (screenCurrent != null){
                listScreenPrevious.add(screenCurrent);
            }
            screenCurrent = _typeviewshow;//mettre a jour la vue actuelle !!
            viewMain.removeAllViews();
            viewMain.addView(getView(_typeviewshow)); //declencehr la methode requestLayout de ViewRoot pour redessiner l'arbre de vue
            validShow = true;
        }catch (Exception e){
            Log.e("ManagerViews : ",  " showView : " + e.getMessage());
        }
        return validShow;
    }


    public Set<ManagerInterface.TShowView> getListTypeViews(){
        return mapViews.keySet();
    }


}
