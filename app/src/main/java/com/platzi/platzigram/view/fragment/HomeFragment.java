package com.platzi.platzigram.view.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.platzi.platzigram.R;
import com.platzi.platzigram.adapter.PictureAdapterRecyclerView;
import com.platzi.platzigram.adapter.PostAdapterRecyclerView;
import com.platzi.platzigram.api.PlatzigramCLiente;
import com.platzi.platzigram.api.PlatzigramFirebaseService;
import com.platzi.platzigram.api.PostResponse;
import com.platzi.platzigram.model.Picture;
import com.platzi.platzigram.model.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView pictureRecycler;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Post> posts;
    PostAdapterRecyclerView postAdapterRecyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.tab_home), false,v);

        posts = new ArrayList<>();
        populateDate();

        pictureRecycler=(RecyclerView) v.findViewById(R.id.pictureRecycler);

        pictureRecycler.setHasFixedSize(true);
        pictureRecycler.setItemAnimator(new DefaultItemAnimator());

        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        pictureRecycler.setLayoutManager(linearLayoutManager);

        postAdapterRecyclerView=new
                PostAdapterRecyclerView(posts, R.layout.cardview_picture, getActivity());
        pictureRecycler.setAdapter(postAdapterRecyclerView);



        FloatingActionButton fab=(FloatingActionButton)v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewPostFragment newPostFragment=new NewPostFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, newPostFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;


    }

    @Override
    public void onResume() {
        super.onResume();
        populateDate();
    }

    private void populateDate() {
        PlatzigramFirebaseService service = (new PlatzigramCLiente().getService());
        Call<PostResponse> postListCall = service.getPostList();
        postListCall.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if (response.isSuccessful()){
                    PostResponse result = response.body();

                    posts.clear();
                    posts.addAll(result.getPostList());
                    postAdapterRecyclerView.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {

            }
        });
    }

    public ArrayList<Picture> buidPictures(){
        ArrayList<Picture> list=new ArrayList<>();
        list.add(new Picture("http://i.imgur.com/DvpvklR.png",
                "Pablo Reinoso","4 dias","3 Me Gusta"));
        list.add(new Picture("http://puzzles.tuspuzzles.es/images/original/atardecer-568470fbef6d4.jpg",
                "Uriel Reinoso","3 dias","10 Me Gusta"));
        list.add(new Picture("http://1.bp.blogspot.com/-kjh6bxfAaqU/T7Pjw5hs9lI/AAAAAAAAN14/7x932jj3HU0/s1600/Fondos+de+pantalla+con+bellos+rincones+de+la+naturaleza+(72).jpg",
                "Ramon Reinoso","2 dias","9 Me Gusta"));

        return  list;
    }

    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

}
