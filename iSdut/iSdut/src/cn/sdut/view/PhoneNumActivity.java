package cn.sdut.view;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;

/**
 * œ‘ æµÁª∞∫≈¬Î-Activity
 *
 */
public class PhoneNumActivity extends ExpandableListActivity {

	private ExpandableListAdapter mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setTitle("ExpandableList");
		mAdapter = new PhoneNumAdapter(this);
		setListAdapter(mAdapter);
		registerForContextMenu(this.getExpandableListView());
	}

}