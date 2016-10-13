package com.kl.recv.headerfooter.library;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kl on 16/10/12.
 */

public abstract class RecvHeaderFooterAdapter extends RecyclerView.Adapter<RecvWithHeaderFooter.ExampleViewHolder> {

    private final int HEADER_VIEW_TYPE = 579999999;
    private final int FOOTER_VIEW_TYPE = 579999998;
    private List<View> mHeaderViewList;
    private List<View> mFooterViewList;

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public RecvHeaderFooterAdapter() {
        mHeaderViewList = new ArrayList<View>();
        mFooterViewList = new ArrayList<View>();
    }

    @Override
    public RecvWithHeaderFooter.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_VIEW_TYPE) {
            LinearLayout linearLayout = new LinearLayout(parent.getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            return new HeaderViewHolder(linearLayout, viewType);
        } else if (viewType == FOOTER_VIEW_TYPE) {
            LinearLayout linearLayout = new LinearLayout(parent.getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(linearLayout, viewType);
        } else {
            return createHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(final RecvWithHeaderFooter.ExampleViewHolder holder, final int position) {
        if(HEADER_VIEW_TYPE==getItemViewType(position)){
            holder.bindItem(holder, position);
        }else if(FOOTER_VIEW_TYPE==getItemViewType(position)){
            holder.bindItem(holder, position-mHeaderViewList.size()-getCount());
        }else {
            final int norPossition=position-mHeaderViewList.size();
            holder.bindItem(holder, norPossition);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(norPossition,holder.itemView);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return onItemLongClick(norPossition,holder.itemView);
                }
            });
        }

    }

    class HeaderViewHolder extends RecvWithHeaderFooter.ExampleViewHolder {
        public HeaderViewHolder(View itemView, int viewType) {
            super(itemView, viewType);
        }

        @Override
        public void bindItem(RecvWithHeaderFooter.ExampleViewHolder holder, int position) {
            LinearLayout container = (LinearLayout) holder.itemView;
            container.removeAllViews();
            if (null != mHeaderViewList.get(position).getParent()) {
                LinearLayout parent = (LinearLayout) mHeaderViewList.get(position).getParent();
                parent.removeAllViews();
            }
            container.addView(mHeaderViewList.get(position));
        }
    }

    class FooterViewHolder extends RecvWithHeaderFooter.ExampleViewHolder {
        public FooterViewHolder(View itemView, int viewType) {
            super(itemView, viewType);
        }

        @Override
        public void bindItem(RecvWithHeaderFooter.ExampleViewHolder holder, int position) {
            LinearLayout container = (LinearLayout) holder.itemView;
            container.removeAllViews();
            if (null != mFooterViewList.get(position).getParent()) {
                LinearLayout parent = (LinearLayout) mFooterViewList.get(position).getParent();
                parent.removeAllViews();
            }
            container.addView(mFooterViewList.get(position));
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position < mHeaderViewList.size()) {
            return HEADER_VIEW_TYPE;
        } else if (position >= mHeaderViewList.size() + getCount()) {
            return FOOTER_VIEW_TYPE;
        } else {
            return getViewType(position - mHeaderViewList.size());
        }
    }

    @Override
    public int getItemCount() {
        return getCount() + mHeaderViewList.size() + mFooterViewList.size();
    }


    public void addHeaderView(View view) {
        mHeaderViewList.add(view);
        notifyDataSetChanged();
    }

    public void addFooterView(View view) {
        mFooterViewList.add(view);
        notifyDataSetChanged();
    }

    public void removeHeaderView(View view) {
        for(int i=0;i<mHeaderViewList.size();i++){
            if (view==mHeaderViewList.get(i)) {
                mHeaderViewList.remove(i);
                i--;
            }
        }
        notifyDataSetChanged();
    }

    public void removeFooterView(View view) {
        for(int i=0;i<mFooterViewList.size();i++){
            if (view==mFooterViewList.get(i)) {
                mFooterViewList.remove(i);
                i--;
            }
        }
        notifyDataSetChanged();
    }

    public int getViewType(int position) {
        return 0;
    }

    public abstract RecvWithHeaderFooter.ExampleViewHolder createHolder(ViewGroup parent, int viewType);

    public abstract int getCount();


    /**
     * holder item click event
     * @param position
     * @param convertView
     */
    protected void onItemClick(int position, View convertView) {
        if (null != mOnItemClickListener) {
            mOnItemClickListener.itemClick(position, convertView);
        }
    }

    /**
     * holder item longclick event
     * @param position
     * @param convertView
     * @return
     */
    protected boolean onItemLongClick(int position, View convertView) {
        boolean result=false;
        if (null != mOnItemLongClickListener) {
            mOnItemLongClickListener.itemlongClick(position, convertView);
            result=true;
        }
        return result;
    }

    public interface OnItemClickListener {
        public void itemClick(int position, View contentView);
    }

    public interface OnItemLongClickListener {
        public void itemlongClick(int position, View contentView);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemlongClickListener(OnItemLongClickListener onItemlongClickListener) {
        this.mOnItemLongClickListener = onItemlongClickListener;
    }


}
