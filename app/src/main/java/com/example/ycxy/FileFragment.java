package com.example.ycxy;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1031.R;

import java.util.ArrayList;
import java.util.List;


public class FileFragment extends Fragment {
    private ExpandableListView expandableListView;

    /**
     * 每个分组的名字的集合
     */
    private List<String> groupList;

    /**
     * 每个分组下的每个子项的 GridView 数据集合
     */
    private List<String> itemGridList;

    /**
     * 所有分组的所有子项的 GridView 数据集合
     */
    private List<List<String>> itemList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_file, container, false);

        expandableListView = view.findViewById(R.id.expandableList);

        // 分组
        groupList = new ArrayList<>();
        groupList.add("公共服务");
        groupList.add("办公服务");

        // 每个分组下的每个子项的 GridView 数据集合
        itemGridList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            itemGridList.add("电脑" + (i + 1));
        }

        // 所有分组的所有子项的 GridView 数据集合
        itemList = new ArrayList<>();
        itemList.add(itemGridList);
        itemList.add(itemGridList);
        // 创建适配器
        MyExpandableListViewAdapter adapter = new MyExpandableListViewAdapter(getActivity(),
                groupList, itemList);
        expandableListView.setAdapter(adapter);
        // 隐藏分组指示器
        expandableListView.setGroupIndicator(null);
        // 默认展开第一组
        expandableListView.expandGroup(0);
        return view;
    }
    class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

        private Context mContext;

        /**
         * 每个分组的名字的集合
         */
        private List<String> groupList;

        /**
         * 所有分组的所有子项的 GridView 数据集合
         */
        private List<List<String>> itemList;

        private GridView gridView;

        public MyExpandableListViewAdapter(Context context, List<String> groupList,
                                           List<List<String>> itemList) {
            mContext = context;
            this.groupList = groupList;
            this.itemList = itemList;
        }

        @Override
        public int getGroupCount() {
            return groupList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 1;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupList.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return itemList.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup
                parent) {
            if (null == convertView) {
                convertView = View.inflate(mContext, R.layout.expandablelist_group, null);
            }
            ImageView ivGroup = (ImageView) convertView.findViewById(R.id.iv_group);
            TextView tvGroup = (TextView) convertView.findViewById(R.id.tv_group);
            // 如果是展开状态，就显示展开的箭头，否则，显示折叠的箭头
            if (isExpanded) {
                ivGroup.setImageResource(R.drawable.ic_open);
            } else {
                ivGroup.setImageResource(R.drawable.ic_close);
            }
            // 设置分组组名
            tvGroup.setText(groupList.get(groupPosition));
            return convertView;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View
                convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = View.inflate(mContext, R.layout.expandablelist_item, null);
            }
            // 因为 convertView 的布局就是一个 GridView，
            // 所以可以向下转型为 GridView
            gridView = (GridView) convertView;
            // 创建 GridView 适配器
            MyGridViewAdapter gridViewAdapter = new MyGridViewAdapter(mContext, itemList.get
                    (groupPosition));
            gridView.setAdapter(gridViewAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "点击了第" + (groupPosition + 1) + "组，第" +
                            (position + 1) + "项", Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
    class MyGridViewAdapter extends BaseAdapter {

        private Context mContext;

        /**
         * 每个分组下的每个子项的 GridView 数据集合
         */
        private List<String> itemGridList;

        public MyGridViewAdapter(Context mContext, List<String> itemGridList) {
            this.mContext = mContext;
            this.itemGridList = itemGridList;
        }

        @Override
        public int getCount() {
            return itemGridList.size();
        }

        @Override
        public Object getItem(int position) {
            return itemGridList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = View.inflate(mContext, R.layout.gridview_item, null);
            }
            TextView tvGridView = (TextView) convertView.findViewById(R.id.tv_gridview);
            tvGridView.setText(itemGridList.get(position));
            return convertView;
        }
    }
}