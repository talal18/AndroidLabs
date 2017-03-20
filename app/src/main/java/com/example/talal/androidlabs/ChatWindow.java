package com.example.talal.androidlabs;


import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
    public static final String ACTIVITY_NAME = "Query";
    public static final String SQL_MESSAGE = "SQL MESSAGE: ";
    public static final String COLUMN_COUNT = "Cursor column count = ";
    public Cursor cursor;
    private ArrayList<String> chatArray = new ArrayList<>();

    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        ChatDatabaseHelper helper = new ChatDatabaseHelper(this);
        database = helper.getWritableDatabase();
        Toast.makeText(this, "made it", Toast.LENGTH_SHORT).show();

        Resources resources = getResources();
        final ListView listViewChat = (ListView) findViewById(R.id.listViewChat);
        final ChatAdapter chatAdapter = new ChatAdapter(this);
        listViewChat.setAdapter(chatAdapter);
        final EditText editTextChat = (EditText) findViewById(R.id.textChat);
        Button buttonSend = (Button) findViewById(R.id.sendChat);


        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chatString = editTextChat.getText().toString();
                chatArray.add(chatString);


                ContentValues contentValues = new ContentValues();
                contentValues.put(ChatDatabaseHelper.KEY_MESSAGE, editTextChat.getText().toString());
                database.insert(ChatDatabaseHelper.TABLE_NAME, "null", contentValues);
                editTextChat.setText("");
                editTextChat.setHint("So far " + getChatArray().size() + " messages");
                chatAdapter.notifyDataSetChanged();

            }
        });

        String[] allColumns = {ChatDatabaseHelper.KEY_ID, ChatDatabaseHelper.KEY_MESSAGE};

        cursor = database.query(ChatDatabaseHelper.TABLE_NAME, allColumns,null,null,null,null,null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {

            String newMessage = cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE));

            chatArray.add(newMessage);

            Log.i(ACTIVITY_NAME, SQL_MESSAGE + cursor.getString(
                    cursor.getColumnIndex( ChatDatabaseHelper.KEY_MESSAGE)));

            cursor.moveToNext();

        }

        TableStat();
    }

    private void TableStat() {
        for (int x = 0; x < cursor.getColumnCount(); x++) {
            Log.i("Cursor Column name ", cursor.getColumnName(x));
        }
        Log.i(ACTIVITY_NAME, COLUMN_COUNT + cursor.getColumnCount());
    }

    public ArrayList<String> getChatArray() {
        return chatArray;
    }

    public void setChatArray(ArrayList<String> msgs) {
        this.chatArray = msgs;
    }

    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context context) {
            super(context, 0);
        }

        public int getCount() {
            return getChatArray().size();
        }

        public String getItem(int position) {
            return getChatArray().get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if (position%2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            } else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }

            TextView message = (TextView) result.findViewById((R.id.textChat));
            message.setText(getItem(position));
            return result;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        cursor.close();
        database.close();
    }

    @Override
    protected void onDestroy() {
        Log.i(ACTIVITY_NAME, "onDestroy()");
        super.onDestroy();
        cursor.close();
        database.close();
    }

}