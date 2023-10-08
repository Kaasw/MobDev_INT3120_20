package com.example.contentprovider;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button insert = findViewById(R.id.insertButton);
        Button load = findViewById(R.id.loadButton);
        Button update = findViewById(R.id.Update);
        Button delete = findViewById(R.id.deleteButton);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddDetails(v);
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickShowDetails(v);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDelete(v);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUpdate(v);
            }
        });
    }


    public void onClickAddDetails(View view) {

        StringBuilder sb = new StringBuilder();

        ContentValues values = new ContentValues();

;
//        values.put(MyContentProvider.name, ((EditText) findViewById(R.id.editText)).getText().toString());
        sb.append(((EditText) findViewById(R.id.editText)).getText().toString());
        String[] res = sb.toString().split("/");
        if (res.length > 1) {
            for (int i = 0; i < res.length; i++) {
                values.put(MyContentProvider.name, res[i]);
                getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
            }
        }


        Toast.makeText(getBaseContext(), "New Record Inserted", Toast.LENGTH_LONG).show();
    }

    public void onClickShowDetails(View view) {

        TextView resultView= (TextView) findViewById(R.id.res);


        Cursor cursor = getContentResolver().query(Uri.parse("content://com.demo.user.provider/users"), null, null, null, null);


        if(cursor.moveToFirst()) {
            StringBuilder strBuild=new StringBuilder();
            while (!cursor.isAfterLast()) {
                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ "-"+ cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
            resultView.setText(strBuild);
        }
        else {
            resultView.setText("No Records Found");
        }
    }

    public void onClickDelete(View view) {
        getContentResolver().delete(Uri.parse("content://com.demo.user.provider/users"), null, null);
        Toast.makeText(getBaseContext(), "All Records Deleted", Toast.LENGTH_LONG).show();
    }

    public void onClickUpdate(View view) {
        ContentValues values = new ContentValues();
        StringBuilder sb = new StringBuilder();
        sb.append(((EditText) findViewById(R.id.editText)).getText().toString());


        String[] nameAndId = sb.toString().split("&");

        for (int i = 0;i < nameAndId.length;i++) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(nameAndId[i]);
            String[] res = sb2.toString().split("/");
            if (res.length > 1) {
                for (int j = 0; j < res.length; j+=2) {
                    values.put(MyContentProvider.name, res[j+1]);
                    int id = Integer.parseInt(res[j]);
                    getContentResolver().update(Uri.parse("content://com.demo.user.provider/users"), values, "id = " + id, null);

                }
            }
        }





        Toast.makeText(getBaseContext(), "All Records Updated", Toast.LENGTH_LONG).show();
    }
}
