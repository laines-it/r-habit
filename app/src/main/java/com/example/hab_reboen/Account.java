package com.example.hab_reboen;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import org.w3c.dom.Text;

import java.io.IOException;

public class Account extends AppCompatActivity{
    private AppBarConfiguration mAppBarConfiguration;
    public SQLiteDatabase mDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_page);

        DataBaseHelper mDBHelper = new DataBaseHelper(this);
        try {
            mDBHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("UnableToUpdateDatabase");
        }
        try{
            mDBHelper.openDataBase();
        }catch (SQLException sqle){
            throw sqle;
        }
        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        Cursor query = mDb.rawQuery("SELECT * FROM accounts;", null);
        TextView text_name = findViewById(R.id.profile_name);
        TextView text_city = findViewById(R.id.city);
        TextView text_id = findViewById(R.id.self_id);
        query.moveToFirst();
        String name = "noname";
        String city = "nocity";
        String id = "noid";
        while(!query.isAfterLast()){
            id = query.getString(0);
            name = query.getString(1);
            city = query.getString(2);
            query.moveToNext();
        }
        query.close();
        text_id.setText(id);
        text_name.setText(name);
        text_city.setText(city);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        if (getSupportFragmentManager().popBackStackImmediate()) {
            return false;
        }
        Intent intent = new Intent(Account.this, MainActivity.class);
        startActivity(intent);
        return false;
    }
}
