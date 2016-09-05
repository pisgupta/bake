package com.example.gupta.hotbak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ScrollingTabContainerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner mSpinner;
    Adapter adapter;
    List<ProductSelectionModal> modals;
    ProductSelectionModal modal;
    EditText  editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modals = new ArrayList<>();
        mSpinner = (Spinner) findViewById(R.id.myspinner);
        editText = (EditText)findViewById(R.id.display_selectedData);
        String[] data = getResources().getStringArray(R.array.item_list);
        for (String s : data) {
            modal = new ProductSelectionModal();
            modal.setProductName(s);
            modals.add(modal);
        }

        mSpinner.setAdapter(new CustomSpinnerAdapter(this,modals,editText));
        //adapter = new MySpinnerAdapter(this, modals);
        //mSpinner.setAdapter((SpinnerAdapter) adapter);
        //setAdapterToListview();
    }

    public void setAdapterToListview() {

        // Sort Data Alphabatical order
        Collections.sort(modals, new Comparator<ProductSelectionModal>() {

            @Override
            public int compare(ProductSelectionModal lhs, ProductSelectionModal rhs) {
                return lhs.getProductName().compareTo(rhs.getProductName());
            }
        });

        adapter = new MySpinnerAdapter(MainActivity.this, modals);
        //mSpinner.setAdapter((SpinnerAdapter) adapter);

        //mSpinner.setAdapter(new CustomSpinnerAdapter(this,modals));



//        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                CheckBox chk = (CheckBox) view.findViewById(R.id.checkbox);
//                ProductSelectionModal bean = modals.get(position);
//                if (bean.isSelected()) {
//                    bean.setSelected(false);
//                    chk.setChecked(false);
//                } else {
//                    bean.setSelected(true);
//                    chk.setChecked(true);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

    }
}
