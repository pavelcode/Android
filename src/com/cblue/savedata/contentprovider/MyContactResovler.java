package com.cblue.savedata.contentprovider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.location.Address;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;

/**
 * 读取通讯录中的信息
 * @author Administrator
 * 需要加上 READ_CONTACT权限
 *
 */
public class MyContactResovler {

	
	
	public static final String TAG = "MyContactResovler";

	private Context context;

	public MyContactResovler(Context context) {
		this.context = context;
	}

	public void displayContact() {
		// 获得ContactResolver
		ContentResolver mContentResolver = context.getContentResolver();

		Cursor contactCursor = mContentResolver.query(Contacts.CONTENT_URI,
				null, null, null, null);

		while (contactCursor.moveToNext()) {
			// 得到联系人ID
			int contact_id = contactCursor.getInt(contactCursor
					.getColumnIndex(Contacts._ID));
			// 得到联系人姓名
			String contact_name = contactCursor.getString(contactCursor
					.getColumnIndex(Contacts.DISPLAY_NAME));

			Log.i(TAG, "contact_id=" + contact_id + ";contact_name="
					+ contact_name);
			// 根据联系人ID得到对应联系人电话
			Cursor phoneCursor = mContentResolver.query(Phone.CONTENT_URI,
					null, Phone.CONTACT_ID + "=" + contact_id, null, null);
			// 电话可能有多个
			while (phoneCursor.moveToNext()) {
				String phone = phoneCursor.getString(phoneCursor
						.getColumnIndex(Phone.NUMBER));
				Log.i(TAG, "phone="+phone);
			}
			phoneCursor.close();
			// 根据联系人ID得到对应联系人邮箱
			Cursor emailCursor = mContentResolver.query(Email.CONTENT_URI,
					null, Email.CONTACT_ID+"="+contact_id, null, null);
			while(emailCursor.moveToNext()){
				String email = emailCursor.getString(emailCursor.getColumnIndex(Email.DATA));
				Log.i(TAG, "email="+email);
			}
			//根据联系人ID得到对应联系人地址????
			emailCursor.close();

		}

		contactCursor.close();
	}

}
