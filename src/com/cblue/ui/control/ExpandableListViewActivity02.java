package com.cblue.ui.control;

import java.util.ArrayList;
import java.util.List;

import com.cblue.android.R;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridLayout;
import android.widget.TextView;

public class ExpandableListViewActivity02 extends Activity {
	
	
	private ExpandableListView mExpandableListView;
	private List<String> groupArray;
	private List<List<String>> childArray;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_expandablelistview);
        initData();		
		mExpandableListView = (ExpandableListView) findViewById(R.id.expandableList);
		mExpandableListView.setAdapter(new ExpandableListAdapter() {
				

			@Override
			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				return getContent(groupArray.get(groupPosition));
			}
			
			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				return groupArray.size();
			}
			
			
			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				return childArray.get(groupPosition).size();
			}
			
			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				return getContent(childArray.get(groupPosition).get(childPosition));
			}
				
				
				@Override
				public long getGroupId(int groupPosition) {
					// TODO Auto-generated method stub
					return 0;
				}
				
				
				
				@Override
				public Object getGroup(int groupPosition) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public long getCombinedGroupId(long groupId) {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public long getCombinedChildId(long groupId, long childId) {
					// TODO Auto-generated method stub
					return 0;
				}
				
				
				
				@Override
				public long getChildId(int groupPosition, int childPosition) {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public Object getChild(int groupPosition, int childPosition) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public boolean areAllItemsEnabled() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean hasStableIds() {
					// TODO Auto-generated method stub
					return false;
				}


				@Override
				public boolean isEmpty() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void onGroupCollapsed(int groupPosition) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onGroupExpanded(int groupPosition) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void registerDataSetObserver(DataSetObserver observer) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void unregisterDataSetObserver(DataSetObserver observer) {
					// TODO Auto-generated method stub
					
				}
			});
			
	}

	
	private TextView getContent(String str){
		AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		TextView textView = new TextView(getApplicationContext());
		textView.setLayoutParams(layoutParams);
		textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
		textView.setPadding(40, 0, 0, 0);
		textView.setText(str);
		return textView;
	}
	
	
	private void initData(){
		groupArray = new ArrayList<String>();
		childArray = new ArrayList<List<String>>();
		addInfo("河南", new String[]{"郑州","洛阳"});
		addInfo("山西", new String[]{"临汾","太原"});
		
		
	}
	private void addInfo(String group,String[]child){
		groupArray.add(group);
		List<String> childItem = new ArrayList<String>();
		for(int i=0;i<child.length;i++){
			childItem.add(child[i]);
		}
		childArray.add(childItem);
		
	}
	
   
}
