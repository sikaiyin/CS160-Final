package com.example.cs160final;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class transaction extends AppCompatActivity {

    // ListView testing
    ListView mListView;
    ImageView mImage;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    private StorageReference mStorageRef;


    List<String> Names;
    List<String> Balances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        // For the list view on the popup window
        mListView = (ListView) findViewById(R.id.listView);
        mImage = (ImageView) findViewById(R.id.imageView11);

        userID = fAuth.getCurrentUser().getUid();

        mStorageRef = FirebaseStorage.getInstance().getReference("user_profile");


        mStorageRef.child(userID + "." + "jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(mImage);
            }
        });

        final DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    final DocumentSnapshot document = task.getResult();
                    if(document.exists()) {
                        Names = (List<String>) document.get("fOption");
                        Balances = (List<String>) document.get("fCurrentBalance");

                        transaction.MyAdapter adapter = new transaction.MyAdapter(transaction.this, Names, Balances);
                        //MyLearningsAdapter.CustomAdaptor mCustomAdaptor = new MyLearningsAdapter.CustomAdaptor();
                        mListView.setAdapter(adapter);

                    }}}});


    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        List<String> rTitle;
        List<String> rImgs;

        MyAdapter (Context c, List<String> title, List<String> imgs) {
            super(c, R.layout.transaction, R.id.textViewOption, title);
            this.context = c;
            this.rTitle = title;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.transaction, parent, false);

            TextView myTitle = row.findViewById(R.id.textViewOption);
            TextView myBlance = row.findViewById(R.id.textViewCurrent);

            // now set our resources on views
            //images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle.get(position));
            myBlance.setText("Current Balance : $" + rImgs.get(position));

            return row;
        }
    }
}
