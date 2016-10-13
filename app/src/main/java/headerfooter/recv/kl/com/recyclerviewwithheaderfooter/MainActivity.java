package headerfooter.recv.kl.com.recyclerviewwithheaderfooter;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.kl.recv.headerfooter.library.RecvHeaderFooterAdapter;
import com.kl.recv.headerfooter.library.RecvWithHeaderFooter;

import java.util.ArrayList;
import java.util.List;

/**
 * RecvWithHeaderFooter must set up LinearLayoutManager.VERTICAL
 */
public class MainActivity extends AppCompatActivity {

    private RecvWithHeaderFooter mRecvWithHeaderFooter;
    private RecvAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recv_header_footer_activity);
        mRecvWithHeaderFooter = (RecvWithHeaderFooter) findViewById(R.id.rcv_content);
        mRecvWithHeaderFooter.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecvWithHeaderFooter.addHeaderView(R.layout.recv_header);
        final View headerView=LayoutInflater.from(this).inflate(R.layout.recv_header,null);
        mRecvWithHeaderFooter.addHeaderView(headerView);
        mRecvWithHeaderFooter.addHeaderView(R.layout.recv_header);

        mRecvWithHeaderFooter.addFooterView(R.layout.recv_footer);
        mRecvWithHeaderFooter.addFooterView(R.layout.recv_footer);
        final View footerView=LayoutInflater.from(this).inflate(R.layout.recv_footer,null);
        mRecvWithHeaderFooter.addFooterView(footerView);

        mAdapter = new RecvAdapter();
        mRecvWithHeaderFooter.setAdapter(mAdapter);
        bindView();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecvWithHeaderFooter.addHeaderView(headerView);
            }
        },1000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecvWithHeaderFooter.removeHeaderView(headerView);
            }
        },4000);
    }


    protected void bindView() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            data.add("recv_header_footer_item_" + i);
        }
        mAdapter.setData(data);
        mAdapter.setOnItemClickListener(new RecvHeaderFooterAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, View contentView) {
                Toast.makeText(MainActivity.this,"click position " + position,Toast.LENGTH_SHORT).show();
            }
        });
        mAdapter.setOnItemlongClickListener(new RecvHeaderFooterAdapter.OnItemLongClickListener() {
            @Override
            public void itemlongClick(int position, View contentView) {
                Toast.makeText(MainActivity.this,"long_click position " + position,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
