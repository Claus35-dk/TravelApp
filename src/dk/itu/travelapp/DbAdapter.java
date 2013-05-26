package dk.itu.travelapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbAdapter {
	private Helper helper;
	private SQLiteDatabase db;

	public DbAdapter(Context context) {
		helper = new Helper(context);
	}

	public void open() {
		db = helper.getWritableDatabase();
	}

	public void close() {
		helper.close();
	}

	public Cursor getLog() {
		if (db == null)
			open();
		return db.rawQuery("SELECT * FROM log ORDER BY _id DESC;", null);
	}

	public void log(String start, String end) {
		if(db == null)
			open();
		db.execSQL("INSERT INTO log(start, end) VALUES('" + start + "', '" + end + "')");
	}
}
