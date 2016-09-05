package com.example.gupta.hotbak;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gupta on 9/4/2016.
 */

public class CustomSpinnerAdapter implements SpinnerAdapter {
    Context mContext;
    List<ProductSelectionModal> modals;
    LayoutInflater mLayoutInflater;
    private static final String TAG = "CustomSpinnerAdapter";
    EditText editText;

    public CustomSpinnerAdapter(Context mContext, List<ProductSelectionModal> modals, EditText editText) {
        this.modals = modals;
        this.editText = editText;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Log.e(TAG, "getDropDownView: ");
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.spinner_item, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.chk = (CheckBox) convertView.findViewById(R.id.checkbox);
            holder.chk
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton view,
                                                     boolean isChecked) {
                            int getPosition = (Integer) view.getTag();
                            modals.get(getPosition).setSelected(view.isChecked());

                        }
                    });
            convertView.setTag(holder);
            convertView.setTag(R.id.title, holder.title);
            convertView.setTag(R.id.checkbox, holder.chk);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.chk.setTag(position);
        holder.title.setText(modals.get(position).getProductName());
        holder.chk.setChecked(modals.get(position).isSelected());
        holder.chk.setVisibility(View.VISIBLE);

        return convertView;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        Log.e(TAG, "registerDataSetObserver: ");
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        Log.e(TAG, "unregisterDataSetObserver: ");
        editText.setText("");
        for (ProductSelectionModal modal : modals) {
            if (modal.isSelected()) {
                String name = modal.getProductName();
                editText.append(name + "\n");
            }
        }

    }

    @Override
    public int getCount() {
        Log.e(TAG, "getCount: ");
        return modals.size();
    }

    @Override
    public Object getItem(int position) {
        Log.e(TAG, "getItem: ");
        return modals.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.e(TAG, "getItemId: ");
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        Log.e(TAG, "hasStableIds: ");
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e(TAG, "getView: ");
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.spinner_item, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.chk = (CheckBox) convertView.findViewById(R.id.checkbox);
            holder.chk
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton view,
                                                     boolean isChecked) {
                            int getPosition = (Integer) view.getTag();
                            modals.get(getPosition).setSelected(view.isChecked());

                        }
                    });
            convertView.setTag(holder);
            convertView.setTag(R.id.title, holder.title);
            convertView.setTag(R.id.checkbox, holder.chk);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.chk.setTag(position);
        holder.title.setText(modals.get(position).getProductName());
        holder.chk.setChecked(modals.get(position).isSelected());
        if (holder.chk.isChecked()) {
            holder.chk.setVisibility(View.VISIBLE);
        }

        return convertView;

    }


    @Override
    public int getItemViewType(int position) {
        Log.e(TAG, "getItemViewType: ");
        return modals.size();
    }

    @Override
    public int getViewTypeCount() {
        Log.e(TAG, "getViewTypeCount: ");
        return 1;
    }

    @Override
    public boolean isEmpty() {
        Log.e(TAG, "isEmpty: ");
        if (modals.size() > 0)
            return true;
        else
            return false;
    }

    static class ViewHolder {

        protected TextView title;
        protected CheckBox chk;
    }
}
