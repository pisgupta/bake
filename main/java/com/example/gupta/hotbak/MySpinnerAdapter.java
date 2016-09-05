package com.example.gupta.hotbak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gupta on 9/3/2016.
 */

public class MySpinnerAdapter extends BaseAdapter {

    private List<ProductSelectionModal> list;
    private LayoutInflater mLayoutInflater;

    public MySpinnerAdapter(Context context, List<ProductSelectionModal> list) {
        this.list = list;
        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.spinner_item, parent,false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.chk = (CheckBox) convertView.findViewById(R.id.checkbox);
            holder.chk
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton view,
                                                     boolean isChecked) {
                            int getPosition = (Integer) view.getTag();
                            list.get(getPosition).setSelected(view.isChecked());

                        }
                    });
            convertView.setTag(holder);
            convertView.setTag(R.id.title, holder.title);
            convertView.setTag(R.id.checkbox, holder.chk);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.chk.setTag(position);
        holder.title.setText(list.get(position).getProductName());
        holder.chk.setChecked(list.get(position).isSelected());

        return convertView;
    }

    static class ViewHolder {
        protected TextView title;
        protected CheckBox chk;
    }
}
