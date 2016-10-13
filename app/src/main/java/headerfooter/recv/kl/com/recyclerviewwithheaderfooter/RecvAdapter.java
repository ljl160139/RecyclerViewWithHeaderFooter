package headerfooter.recv.kl.com.recyclerviewwithheaderfooter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kl.recv.headerfooter.library.RecvHeaderFooterAdapter;
import com.kl.recv.headerfooter.library.RecvWithHeaderFooter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kl on 16/10/12.
 */

public class RecvAdapter extends RecvHeaderFooterAdapter {

    private List<String> mData;

    private final int TYPE_0=0;
    private final int TYPE_1=1;

    public RecvAdapter() {
        this.mData = new ArrayList<>();
    }

    public void setData(List<String> data){
        mData.clear();
        if(null!=data){
            mData.addAll(data);
        }
        notifyDataSetChanged();;
    }

    public void addData(List<String> data){
        if(null!=data){
            mData.addAll(data);
        }
        notifyDataSetChanged();;
    }

    @Override
    public RecvWithHeaderFooter.ExampleViewHolder createHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_0){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recv_header_footer_item_0,null);
            return new ViewHolder(view,viewType);
        }else if(viewType==TYPE_1){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recv_header_footer_item_1,null);
            return new ViewHolder(view,viewType);
        }
        return null;
    }


    @Override
    public int getViewType(int position) {
        if(position>30){
            return TYPE_1;
        }else {
            return TYPE_0;
        }
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    class ViewHolder extends RecvWithHeaderFooter.ExampleViewHolder{
        private TextView tv;
        public ViewHolder(View itemView,int viewType) {
            super(itemView,viewType);
            tv= (TextView) itemView.findViewById(R.id.tv_content);
        }

        @Override
        public void bindItem(RecvWithHeaderFooter.ExampleViewHolder holder, int position) {
           if(TYPE_0==getViewType(position)){
                tv.setText("type0_"+mData.get(position));
            }else if(TYPE_1==getViewType(position)){
                tv.setText("type1_"+mData.get(position));
            }
        }
    }


}
