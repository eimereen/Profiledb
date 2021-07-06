package com.example.profilecruddb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.profilecruddb.model.SchoolAttended;
import com.example.profilecruddb.model.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText id, name, address, email, contact, saying, workplace, bdate, age, elem, junior, senior, college;
    private Button next, upload, skip;

    private Uri imageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;

    private List<SchoolAttended> schoolAttendedList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStorageRef = FirebaseStorage.getInstance().getReference("users");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("users");
        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        saying = findViewById(R.id.saying);
        workplace = findViewById(R.id.workplace);
        bdate = findViewById(R.id.bdate);
        age = findViewById(R.id.age);
        elem = findViewById(R.id.elem);
        junior = findViewById(R.id.junior);
        senior = findViewById(R.id.senior);
        college = findViewById(R.id.college);

        next = findViewById(R.id.btnNext);
        upload = findViewById(R.id.btnUpload);
        skip = findViewById(R.id.skip);

        skip.setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewActivity.class);
            startActivity(intent);

        });

        next.setVisibility(View.INVISIBLE);
        next.setOnClickListener(v -> {
            uploadFile();
        });

        upload.setOnClickListener(v -> {
            openFileChooser ();
            next.setVisibility(View.VISIBLE);

        });


    }
    private void openFileChooser() {
        Intent intent = new Intent ();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            imageUri = data.getData();
        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        String strId = id.getText().toString();
        String strName = name.getText().toString();
        String strAdd = address.getText().toString();
        String strEmail = email.getText().toString();
        String strContact = contact.getText().toString();
        String strSay = saying.getText().toString();
        String strWork = workplace.getText().toString();
        String strBdate =  bdate.getText().toString();
        String strAge = age.getText().toString();
        String strElem = elem.getText().toString();
        String strJunior = junior.getText().toString();
        String strSenior = senior.getText().toString();
        String strCol = college.getText().toString();
        schoolAttendedList.add(new SchoolAttended(strElem));
        schoolAttendedList.add(new SchoolAttended(strJunior));
        schoolAttendedList.add(new SchoolAttended(strSenior));
        schoolAttendedList.add(new SchoolAttended(strCol));

        if (imageUri != null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(imageUri));

            mUploadTask = fileReference.putFile(imageUri);
            Task<Uri> uriTask = mUploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL

                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    Toast.makeText(MainActivity.this, "Successfully Uploaded!", Toast.LENGTH_SHORT).show();
                    User user = new User();
                    user.setId(Long.parseLong(strId));
                    user.setName(strName);
                    user.setAddress(strAdd);
                    user.setEmail(strEmail);
                    user.setContactNumber(strContact);
                    user.setSaying(strSay);
                    user.setWorkplace(strWork);
                    user.setBirthdate(strBdate);
                    user.setAge(strAge);
                    user.setSchoolAttendedList(schoolAttendedList);
                    user.setImageUri(task.getResult().toString());
                    String dbId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(dbId).setValue(user);



                }
            });
        }
    }
}

