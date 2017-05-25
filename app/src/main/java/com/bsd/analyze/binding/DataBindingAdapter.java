package com.bsd.analyze.binding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bsd.analyze.BR;
import com.bsd.analyze.R;
import com.bsd.analyze.databinding.ListItemLayoutBinding;

import java.util.ArrayList;

/**
 * @author ShiDa.Bian
 * @date 2017/5/25.
 * listView Databinding adapter
 */
public class DataBindingAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<User> userList;
    private ListItemLayoutBinding binding;

    public DataBindingAdapter(Context context) {
        userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            userList.add(new User("firstName" + i, "lastName" + i));
        }
        this.context = context;
    }

    @Override
    public int getCount() {
        return userList != null ? userList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_item_layout, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ListItemLayoutBinding) convertView.getTag();
        }
       /* *
         * 设置给item的数据
         * 这里的BR.user 指的是给item设置数据的标识
         */
        binding.setVariable(BR.user, userList.get(position));
        binding.setAdapter(this);
        return convertView;
    }
}
