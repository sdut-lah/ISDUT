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
 * 用于所有成绩的显示相关
 * 
 */
public class ConAllS extends BaseAdapter {
	private List<Person> list;
	private LayoutInflater inflater;
	private int type;

	public ConAllS(Context context, List<Person> list2, int type) {
		this.list = list2;
		inflater = LayoutInflater.from(context);
		this.type = type;
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
			convertView = inflater.inflate(R.layout.scorealllist, null);

			if (type == 2) {
				TextView text_id2 = (TextView) convertView
						.findViewById(R.id.text_id2);
				text_id2.setText("时间");
			}

			viewHolder.mTextName = (TextView) convertView
					.findViewById(R.id.text_name2);
			viewHolder.mTextScore = (TextView) convertView
					.findViewById(R.id.text_score2);
			viewHolder.mTextId = (TextView) convertView
					.findViewById(R.id.text_id2);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
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
