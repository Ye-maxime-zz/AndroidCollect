package com.nf28.collect.appcollect;

public interface InterfaceViews {

    class DatasView{
        Object typeDatas    = null;
        Object[] datas      = null;

        public DatasView(Object _typeDatas, Object... _datas){
            typeDatas   = _typeDatas;
            datas       = _datas;
        }
        public Object getTypeDatas(){
            return typeDatas;
        }
        public Object[] getDatas(){
            return datas;
        }
    }

    boolean prepareInteraction();

    boolean prepareActionView();

    boolean setDataView(DatasView... _datasView);
}
