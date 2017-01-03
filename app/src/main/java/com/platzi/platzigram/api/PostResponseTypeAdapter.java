package com.platzi.platzigram.api;

import android.util.Log;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.platzi.platzigram.model.Post;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;


public class PostResponseTypeAdapter extends TypeAdapter {
    @Override
    public void write(JsonWriter out, Object value) throws IOException {

    }

    @Override
    public PostResponse read(JsonReader in) throws IOException {

        final PostResponse response = new PostResponse();
        ArrayList<Post> postList = new ArrayList<Post>();
        in.beginObject();

//        if (in.equals("post")) {
//            // read it
//        } else {
//            in.skipValue();
//        }

        while (in.hasNext()){
            Post post = null;
            try {
                post = readPost(in);
            } catch (Exception e) {
                Log.i("ReadPost inicio L", "Error leyendo post");
                e.printStackTrace();
                Log.i("ReadPost", "Error leyendo post");
            }
            postList.add(post);
        }

        in.endObject();
        response.setPostList(postList);
        return response;

    }

    public Post readPost(JsonReader reader) throws Exception{

        Post post = new Post();
        reader.nextName();
        reader.beginObject();

        while (reader.hasNext()){
            String next = reader.nextName();
            switch (next) {
                case "autor":
                    post.setAutor(reader.nextString());
                    break;
                case "imageURL":
                    post.setImageURL(reader.nextString());
                    break;
                case "relativeTimeStamp":
                    post.setRelativeTimeStamp(reader.nextString());
                    break;
                case "timeStampCreated":
                    post.setTimeStampCreated(reader.nextDouble());
                    break;
            }
        }

        reader.endObject();
        return post;
    }
}
