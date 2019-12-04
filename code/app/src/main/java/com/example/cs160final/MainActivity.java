package com.example.cs160final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    Button myspendings;
    TextView username;
    Button mylearnings;
    ImageView userpicture;
    TextView termlabel;
    TextView term;
    TextView monthlabel;
    ImageView month;
    TextView weeklabel;
    ImageView week;
    TextView yearofstudy;
    ImageView ivbudget;
    ProgressBar pbbudget;
    ImageView ivacademic;
    ProgressBar pbacademic;
    ImageView ivsocial;
    ProgressBar pbsocial;
    ImageView ivhealth;
    ProgressBar pbhealth;
    ImageView iventertain;
    ProgressBar pbentertain;
    Button performtask;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myspendings = (Button)findViewById(R.id.myspendings);
        username = (TextView)findViewById(R.id.username);
        mylearnings = (Button)findViewById(R.id.mylearnings);
        userpicture = (ImageView)findViewById(R.id.userpicture);
        termlabel = (TextView)findViewById(R.id.termlabel);
        term = (TextView)findViewById(R.id.term);
        monthlabel = (TextView)findViewById(R.id.monthlabel);
        month = (ImageView)findViewById(R.id.month);
        weeklabel = (TextView)findViewById(R.id.weeklabel);
        week = (ImageView) findViewById(R.id.week);
        yearofstudy = (TextView)findViewById(R.id.yearofstudy);
        ivbudget = (ImageView)findViewById(R.id.ivbudget);
        pbbudget = (ProgressBar)findViewById(R.id.pbbudget);
        ivacademic  = (ImageView)findViewById(R.id.ivacademic);
        pbacademic = (ProgressBar)findViewById(R.id.pbacademic);
        ivsocial = (ImageView)findViewById(R.id.ivsocial);
        pbsocial = (ProgressBar)findViewById(R.id.pbsocial);
        ivhealth = (ImageView)findViewById(R.id.ivhealth);
        pbhealth = (ProgressBar)findViewById(R.id.pbhealth);
        iventertain = (ImageView)findViewById(R.id.iventertain);
        pbentertain = (ProgressBar)findViewById(R.id.pbentertain);
        performtask = (Button)findViewById(R.id.performtask);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("user_profile");
        mStorageRef = FirebaseStorage.getInstance().getReference("user_profile");


        mStorageRef.child(userId + "." + "jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(userpicture);
            }
        });

        final DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        username.setText(document.get("fName").toString());
                        term.setText(document.get("fWeek").toString());
                        pbbudget.setProgress(Integer.parseInt(document.get("fBudget").toString()));
                        pbacademic.setProgress(Integer.parseInt(document.get("fAcademics").toString()));
                        pbsocial.setProgress(Integer.parseInt(document.get("fSocial").toString()));
                        pbhealth.setProgress(Integer.parseInt(document.get("fHealth").toString()));
                        pbentertain.setProgress(Integer.parseInt(document.get("fHobbies").toString()));
                    }
                }
            }
        });

    }
    @Override
    protected void onStart()
    {
        super.onStart();

        // When you return from the other activities, you may get a difference to update the five scores.

        // You can use
        // int currentprogress=progressbar.getProgress();
        // progressbar.setProgress(currentprogress+diff);
        // to update the score

        // and
        // progressbar.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
        // to change the color of the bar



        performtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
        myspendings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.cs160final.MySpendingsActivity.class);
                startActivity(intent);

            }
        });
        mylearnings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.cs160final.list.class);
                startActivity(intent);

            }
        });
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), login.class));
        finish();
    }
}
