package com.kl.recv.headerfooter.library;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kl on 16/10/12.
 */

public class RecvWithHeaderFooter extends RecyclerView {
    private final String TAG=RecvWithHeaderFooter.class.getSimpleName();

    private List<View> mHeaderViewList;    //cache headerView before setadaper
    private List<View> mFooterViewList;    //cache footerView before setadaper

    public RecvWithHeaderFooter(Context context) {
        super(context);
        mHeaderViewList =new ArrayList<View>();
        mFooterViewList =new ArrayList<View>();
    }

    public RecvWithHeaderFooter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mHeaderViewList =new ArrayList<View>();
        mFooterViewList =new ArrayList<View>();
    }

    public RecvWithHeaderFooter(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mHeaderViewList =new ArrayList<View>();
        mFooterViewList =new ArrayList<View>();
    }


    @Override
    public void setAdapter(Adapter adapter) {
        if(adapter instanceof RecvHeaderFooterAdapter){
            for(int i=0;i<mHeaderViewList.size();i++){
                ((RecvHeaderFooterAdapter)adapter).addHeaderView(mHeaderViewList.get(i));
            }
            for(int i=0;i<mFooterViewList.size();i++){
                ((RecvHeaderFooterAdapter)adapter).addFooterView(mFooterViewList.get(i));
            }
            mHeaderViewList.clear();
            mFooterViewList.clear();
            super.setAdapter(adapter);
        }else {
            throw new RuntimeException("adapter must extands RecvHeaderFooterAdapter");
        }
    }

    public void addHeaderView(int layoutId){
        addHeaderView(LayoutInflater.from(getContext()).inflate(layoutId,null));
    }

    public void addHeaderView(View view){
        if(null!=getAdapter()){
            ((RecvHeaderFooterAdapter)getAdapter()).addHeaderView(view);
        }else {
            mHeaderViewList.add(view);
        }
    }

    public void addFooterView(int layoutId){
       addFooterView(LayoutInflater.from(getContext()).inflate(layoutId,null));
    }

    public void addFooterView(View view){
        if(null!=getAdapter()){
            ((RecvHeaderFooterAdapter)getAdapter()).addFooterView(view);
        }else {
            mFooterViewList.add(view);
        }
    }


    public void removeHeaderView(View view) {
        if(null!=getAdapter()){
            ((RecvHeaderFooterAdapter)getAdapter()).removeHeaderView(view);
        }else {
            Log.e(TAG,"removeHeaderView failure,adapter null ");
        }
    }

    public void removeFooterView(View view) {
        if(null!=getAdapter()){
            ((RecvHeaderFooterAdapter)getAdapter()).removeFooterView(view);
        }else {
            Log.e(TAG,"removeFooterView failure,adapter null ");
        }
    }


    public static abstract class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ExampleViewHolder(View itemView, int viewType) {
            super(itemView);
        }
        abstract public void bindItem(ExampleViewHolder holder, int position);
    }
}
