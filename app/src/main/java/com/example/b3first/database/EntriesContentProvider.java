package com.example.b3first.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.example.b3first.database.FeedReaderContract.FeedEntry;

public class EntriesContentProvider extends ContentProvider {
    SQLiteDatabase db;
    static final String PROVIDER_NAME = "cognizant.todo";

    // defining content URI
    static final String URL = "content://" + PROVIDER_NAME + "/entry";

    // parsing the content URI
    static final Uri CONTENT_URI = Uri.parse(URL);

    public EntriesContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db.insert(FeedEntry.TABLE_NAME,null,values);
        return null;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        if (db != null) {
            return true;
        }
        return false;    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
       // return db.query(FeedReaderContract.FeedEntry.TABLE_NAME,null,null,null,null,null,null);
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(FeedEntry.TABLE_NAME);


        Cursor c = qb.query(db, projection, selection, selectionArgs, null,
                null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}