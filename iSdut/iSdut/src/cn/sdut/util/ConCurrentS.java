package cn.sdut.util;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.sdut.R;
import cn.sdut.dao.Person;

/**
 * 用于当前学期成绩的显示相关
 * 
 */
public class ConCurrentS extends BaseAdapter {
	private List<Person> list;
	private LayoutInflater inflater;

	public ConCurrentS(Context context, List<Person> list) {
		this.list = list; 
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Person person = (Person) this.getItem(position);
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.scorecurrent, null);
			viewHolder.mTextName = (TextView) convertView
					.findViewById(R.id.text_name);
			viewHolder.mTextScore = (TextView) convertView
					.findViewById(R.id.text_score);
			viewHolder.mTextId = (TextView) convertView
					.findViewById(R.id.text_id);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		System.out.println("sad::::" + person.getId() + " " + person.getName()+" "+person.getScore());
		viewHolder.mTextId.setText(person.getId() + "");
		viewHolder.mTextName.setText(person.getName());
		viewHolder.mTextScore.setText(person.getScore());
		return convertView;
	}

	public static class ViewHolder {
		public TextView mTextName;
		public TextView mTextScore;
		public TextView mTextId;

	}

}
