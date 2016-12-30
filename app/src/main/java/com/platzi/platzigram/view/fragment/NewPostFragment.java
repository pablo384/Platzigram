package com.platzi.platzigram.view.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.platzi.platzigram.PlatzigramApplication;
import com.platzi.platzigram.R;
import com.platzi.platzigram.model.Post;
import com.platzi.platzigram.utils.Constants;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPostFragment extends Fragment {

    ImageView imgPicture;
    Button btnTakePicture;

    static final int REQUEST_IMAGE_CAPTURE=1;
    String mCurrentPhotoPath;

    String mCurrentAbsolutePhotoPath;
    PlatzigramApplication app;
    StorageReference storageReference;
    DatabaseReference postReference;


    public NewPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_new_post, container, false);
        showToolbar(getResources().getString(R.string.new_post_title), false, v);

        imgPicture=(ImageView)v.findViewById(R.id.imageViewPicture);
        btnTakePicture=(Button) v.findViewById(R.id.btnTakePicture);

        app = (PlatzigramApplication) getActivity().getApplicationContext();
        storageReference = app.getStorageReference();
        postReference = app.getPostReference();

        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });


        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_IMAGE_CAPTURE && resultCode==getActivity().RESULT_OK){

            Picasso.with(getActivity()).load(mCurrentPhotoPath).into(imgPicture);
            addPicturetoGallery();

//            Toast.makeText(getActivity(), mCurrentPhotoPath, Toast.LENGTH_LONG).show();

            uploadFIle();
        }
    }

    private void uploadFIle() {
        File newFile = new File(mCurrentAbsolutePhotoPath);
        Uri contentUri = Uri.fromFile(newFile);

        StorageReference imagesReference = storageReference.child(Constants.FIREBASE_STORAGE_IMAGES+contentUri.getLastPathSegment());

        UploadTask uploadTask = imagesReference.putFile(contentUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Error subiendo la imagen", Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getActivity(), taskSnapshot.getDownloadUrl().toString(), Toast.LENGTH_LONG).show();

                String imageURL = taskSnapshot.getDownloadUrl().toString();

                createNewpost(imageURL);
            }
        });
    }

    private void createNewpost(String imageURL) {

        SharedPreferences pref = getActivity().getSharedPreferences("USER", getActivity().MODE_PRIVATE);
        String email= pref.getString("email","");
        String enCodedEmail = email.replace(".",",");



        Post post = new Post(enCodedEmail, imageURL, (double)new Date().getTime());
        postReference.push().setValue(post);

        getFragmentManager().popBackStackImmediate();


    }

    private void takePicture() {

        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File photoFile=null;
        try {
            photoFile=createImageFIle();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (photoFile != null){
            Uri photoURI= FileProvider.getUriForFile(getActivity(),
                    "com.platzi.platzigram",
                    photoFile);

            i.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(i, REQUEST_IMAGE_CAPTURE);

        }

    }

    private File createImageFIle() throws IOException{

        String timeStamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFIlename="JPEG_"+timeStamp+"_";

        File storageDir=getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image=File.createTempFile(
          imageFIlename,
                ".jpg",
                storageDir
        );

        mCurrentPhotoPath="file:"+image.getAbsolutePath();
        mCurrentAbsolutePhotoPath= image.getAbsolutePath();


        return image;
    }

    private void addPicturetoGallery(){
        Intent mediaScanIntent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File Newfile=new File(mCurrentPhotoPath);

        Uri contentURI= Uri.fromFile(Newfile);
        mediaScanIntent.setData(contentURI);
        getActivity().sendBroadcast(mediaScanIntent);
    }
    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }


}
