package com.ydcjavashop.shop.view.recyclerview;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author yinsujun  Created at 2016/5/18 10:03
 * recyclerView item点击事件
 */
public abstract class RecyclerOnItemClickListener implements RecyclerView.OnItemTouchListener{
    private GestureDetectorCompat gestureDetectorCompat;
    private RecyclerView recyclerView;
    public RecyclerOnItemClickListener(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        gestureDetectorCompat=new GestureDetectorCompat(recyclerView.getContext(),
                new ItemTouchHelperGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
    //点击事件
    public abstract void OnItemClickListener(RecyclerView.ViewHolder viewHolder);
    //长按事件
//    public abstract void OnLongClickListener(RecyclerView.ViewHolder viewHolder);



    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View viewChild=recyclerView.findChildViewUnder(e.getX(),e.getY());
            if(viewChild!=null){
                RecyclerView.ViewHolder viewHolder=recyclerView.getChildViewHolder(viewChild);
                OnItemClickListener(viewHolder);
            }
            return true;
        }

//        @Override
//        public void onLongPress(MotionEvent e) {
//            View viewChild=recyclerView.findChildViewUnder(e.getX(),e.getY());
//            if(viewChild!=null){
//                RecyclerView.ViewHolder viewHolder=recyclerView.getChildViewHolder(viewChild);
//                OnLongClickListener(viewHolder);
//            }
//        }
    }
}
