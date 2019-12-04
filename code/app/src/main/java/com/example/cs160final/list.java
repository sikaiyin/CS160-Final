package com.example.cs160final;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class list extends AppCompatActivity {

    // ListView testing
    ListView mListView;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    List<String> images;
    List<String> Names;
    String mTitle[] = {"Facebook", "Whatsapp", "Twitter", "Instagram", "Youtube"};
    int Images[] = {R.drawable.facebook, R.drawable.whatsapp, R.drawable.twitter, R.drawable.instagram, R.drawable.youtube};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        // For the list view on the popup window
        mListView = (ListView) findViewById(R.id.listView);

        userID = fAuth.getCurrentUser().getUid();
        final DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    final DocumentSnapshot document = task.getResult();
                    if(document.exists()) {
                        Names = (List<String>) document.get("fLearning");
                        images = (List<String>) document.get("fGraph");

                        MyAdapter adapter = new MyAdapter(list.this, Names, images);
                        //MyLearningsAdapter.CustomAdaptor mCustomAdaptor = new MyLearningsAdapter.CustomAdaptor();
                        mListView.setAdapter(adapter);

                    }}}});


    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        List<String> rTitle;
        List<String> rImgs;

        MyAdapter (Context c, List<String> title, List<String> imgs) {
            super(c, R.layout.row, R.id.textViewBrand, title);
            this.context = c;
            this.rTitle = title;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView image = row.findViewById(R.id.imageViewBrand);
            TextView myTitle = row.findViewById(R.id.textViewBrand);

            // now set our resources on views
            //images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle.get(position));
            int id = getResources().getIdentifier("com.example.cs160final:drawable/" + images.get(position), null, null);
            image.setImageResource(id);

            return row;
        }
    }
}
