package com.example.cs160final;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MyLearningsAdapter extends AppCompatActivity {

    // ListView testing
    ListView mListView;

    int[] images ={};
    String[] Names = {};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.listview_adapter);

        // For the list view on the popup window
        mListView = (ListView) findViewById(R.id.listview);
        MyLearningsAdapter.CustomAdaptor mCustomAdaptor = new MyLearningsAdapter.CustomAdaptor();
        mListView.setAdapter(mCustomAdaptor);

//        // Display message when a list item is clicked
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), "You have selected : " + Names[position], Toast.LENGTH_LONG).show();
//            }
//        });
    }

    // For customized list view with image and text. It only works on fragments not in popup windows
    class CustomAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = getLayoutInflater().inflate(R.layout.list_item, null);
            ImageView mImageView = (ImageView) view.findViewById(R.id.imageViewBrand);
            TextView mTextVIew = (TextView) view.findViewById(R.id.textViewBrand);

            mImageView.setImageResource(images[position]);
            mTextVIew.setText(Names[position]);

            return view;
        }
    }
}
