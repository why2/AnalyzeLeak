package com.bsd.analyze.binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.bsd.analyze.R;
import com.bsd.analyze.databinding.DataBindingLayoutBinding;

/**
 * @author ShiDa.Bian
 * @date 2017/5/25.
 */
public class DataBindingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingSingleVeiw();
        bindingMultiView();
    }


    /**
     * bingding ViewGroup---listView
     */
    private void bindingMultiView() {
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new DataBindingAdapter(this));
    }


    /**
     * 绑定单个view
     */
    private void bindingSingleVeiw() {
//        这里的DataBindingLayoutBinding 是根据data_binding_layout而来
        DataBindingLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.data_binding_layout);
        User user = new User("Test", "User");
        binding.setUser(user);
    }
}
