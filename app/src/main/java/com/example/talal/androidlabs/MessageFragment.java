package com.example.talal.androidlabs;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MessageFragment extends Fragment {

    private int deviceType;
    public MessageFragment() {
        deviceType = 0;
    }

    public MessageFragment(int d) {
        deviceType = d;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_fragment, container, false);

        final Bundle data = this.getArguments();

        TextView messageText = (TextView)v.findViewById(R.id.messageText);
        final TextView messageID = (TextView)v.findViewById(R.id.messageID);

        final int id = Integer.parseInt(data.getString("messageID"));
        final long dataid = Long.parseLong(data.getString("dataID"));

        messageText.setText(data.getString("messageText"));
        messageID.setText(data.getString("dataID"));

        Button deleteMessage = (Button)v.findViewById(R.id.deleteMessage);
        deleteMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(deviceType == 0) {
                    Intent i = new Intent();
                    i.putExtra("id", data.getString("messageID"));
                    i.putExtra("dataID", data.getString("dataID"));
                    getActivity().setResult(5, i);
                    getActivity().finish();
                } else {
                    ((ChatWindow)getActivity()).deleteMessage(id, dataid);
                }
            }
        });


        return v;
    }
}
