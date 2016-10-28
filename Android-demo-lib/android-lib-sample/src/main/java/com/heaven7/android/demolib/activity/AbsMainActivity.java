package com.heaven7.android.demolib.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.heaven7.adapter.ISelectable;
import com.heaven7.adapter.QuickRecycleViewAdapter;
import com.heaven7.android.lib.sample.R;
import com.heaven7.core.util.ViewHelper;

import java.util.ArrayList;
import java.util.List;


public abstract class AbsMainActivity extends AppCompatActivity {

    protected final List<ActivityInfo> mInfos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_list);
        // setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options));
        addDemos(mInfos);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new QuickRecycleViewAdapter<ActivityInfo>(android.R.layout.simple_list_item_1, mInfos) {
            @Override
            protected void onBindData(Context context, int position, final ActivityInfo item,
                                      int itemLayoutId, ViewHelper helper) {
                helper.setText(android.R.id.text1, item.desc)
                        .setRootOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(AbsMainActivity.this, item.clazz));
                            }
                        });
            }
        });
    }

    protected abstract void addDemos(List<ActivityInfo> list);

    public static class ActivityInfo implements ISelectable {
        final String desc;
        final Class<? extends Activity> clazz;

        public ActivityInfo(Class<? extends Activity> clazz, String desc) {
            this.clazz = clazz;
            this.desc = desc;
        }

        public ActivityInfo(Context context, Class<? extends Activity> clazz, int stringResId) {
            this.clazz = clazz;
            this.desc = context.getResources().getString(stringResId);
        }

        @Override
        public void setSelected(boolean selected) {
        }

        @Override
        public boolean isSelected() {
            return false;
        }
    }
}
