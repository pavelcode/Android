package com.cblue.ui.listview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cblue.android.R;

/**
 * 自定义的ArrayAdapter
 * @author Administrator
 *
 */
public class ListView03PersonActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);

		// 创建数据源.
		Person[] data = new Person[] {
				new Person("蔡志坤", 25, "ffczk86@gmail.com", "厦门市"),
				new Person("李杰华", 25, "aa@bb.com", "漳州市"),
				new Person("张亮", 25, "cc@gmail.com", "厦门市"),
				new Person("陈旭", 25, "ccadd@gmail.com", "厦门市"),
				new Person("刘玄德", 25, "ffczk86@gmail.com", "福州市") };

		ListView lv = (ListView) findViewById(R.id.listView1);
		PersonAdapter adapter = new PersonAdapter(this,R.layout.listviewperson, data);
		lv.setAdapter(adapter);
	}
}

class PersonAdapter extends ArrayAdapter<Person> {

	LayoutInflater mLayoutInflater;
	int resourceId;
	Context mContext;

	public PersonAdapter(Context context, int resourceId, Person[] objects) {
		super(context, resourceId, objects);
		// 获取LayoutInflater 服务,用来从预定义的xml布局创建view对象.
		this.resourceId = resourceId;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			// 创建新的view视图.
			convertView = mLayoutInflater.inflate(resourceId, null);
		}
		// 获取当前要显示的数据
		Person person = getItem(position);

		TextView name = (TextView) convertView.findViewById(R.id.person_name);
		TextView age = (TextView) convertView.findViewById(R.id.person_age);

		name.setText(person.name);
		age.setText(String.valueOf(person.age));

		return convertView;
	}
}

class Person {
	public String name;
	public int age;
	public String email;
	public String address;

	public Person(String name, int age, String email, String address) {
		super();
		this.name = name;
		this.age = age;
		this.email = email;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", email=" + email
				+ ", address=" + address + "]";
	}
}

