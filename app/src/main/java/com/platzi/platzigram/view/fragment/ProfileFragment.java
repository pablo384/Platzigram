package com.platzi.platzigram.view.fragment;


import android.os.Bundle;
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
import com.platzi.platzigram.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_profile, container, false);
        showToolbar("",false,v);

        RecyclerView pictureRecycler=(RecyclerView) v.findViewById(R.id.pictureProfileRecycler);

        pictureRecycler.setHasFixedSize(true);
        pictureRecycler.setItemAnimator(new DefaultItemAnimator());

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        pictureRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView=new
                PictureAdapterRecyclerView(buidPictures(), R.layout.cardview_picture, getActivity());
        pictureRecycler.setAdapter(pictureAdapterRecyclerView);

        return v;
    }

    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

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

}
