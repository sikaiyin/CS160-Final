package com.example.cs160final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class registeration extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mName, mEmail, mPassword, mRePassword, mGrade;
    Button mRegisterBtn;
    ImageButton mEditBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;


    ImageView mProfile;
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    byte[] imagedata;


    public static final int GET_FROM_GALLERY = 3;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            mImageUri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageUri);
                mProfile.setImageBitmap(bitmap);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bs);
                imagedata = bs.toByteArray();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        mName = findViewById(R.id.editTextName);
        mEmail = findViewById(R.id.editTextEmail);
        mPassword = findViewById(R.id.editTextPassword);
        mRePassword = findViewById(R.id.editTextRepa);
        mGrade = findViewById(R.id.editTextGrade);
        mRegisterBtn = findViewById(R.id.button_register);
        mEditBtn = findViewById(R.id.editButton);
        mProfile = findViewById(R.id.imageView_profile);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference("user_profile");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("user_profile");


        mEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (shouldShowRequestPermissionRationale(
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        // Explain to the user why we need to read the contacts
                    }

                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                    // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                    // app-defined int constant that should be quite unique

                    return;
                }
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            }
        });



        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String reenter = mRePassword.getText().toString().trim();
                final String name = mName.getText().toString();
                final String grade = mGrade.getText().toString();


                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required for registeration");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required for registeration");
                    return;
                }
                if(TextUtils.isEmpty(reenter)){
                    mPassword.setError("Please re-enter password for registeration");
                    return;
                }
                if(!password.equals(reenter)){
                    mRePassword.setError("Password Unmatch");
                    return;
                }
                if(password.length() < 6){
                    mPassword.setError("Password must be at least 6 characters");
                    return;
                }


                // register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(registeration.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("fName", name);
                            user.put("fEmail", email);
                            user.put("fGrade", grade);
                            if(mUploadTask != null && mUploadTask.isInProgress()){
                                Toast.makeText(registeration.this, "Upload In Progress", Toast.LENGTH_SHORT).show();
                            }else{
                                uploadFile();
                            }
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "user profile created for " + userID);
                                }
                            });
                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(registeration.this, "Registeration Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show(); }
                    }
                });

            }
        });
    }

    private String getFileExtension(Uri uri){
        // get extension of selected file
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        if(mImageUri != null){
            StorageReference fileReference = mStorageRef.child(userID.trim() + "." + getFileExtension(mImageUri));
            mUploadTask = fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(registeration.this, "User Image Uploaded", Toast.LENGTH_SHORT).show();
                    Upload upload = new Upload(userID.trim(), mStorageRef.getDownloadUrl().toString());
                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(upload);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(registeration.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this, "No Image selected", Toast.LENGTH_SHORT).show();
        }

    }

}
