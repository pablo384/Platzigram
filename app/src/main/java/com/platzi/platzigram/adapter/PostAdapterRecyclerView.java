package com.platzi.platzigram.adapter;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.platzi.platzigram.R;
import com.platzi.platzigram.model.Picture;
import com.platzi.platzigram.model.Post;
import com.platzi.platzigram.view.PictureDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//paso 1                                paso 5
public class PostAdapterRecyclerView extends RecyclerView.Adapter<PostAdapterRecyclerView.PictureiewHolder>{

    //paso 10
    private ArrayList<Post> posts; //lista de objetos que recibiremos y estaremos manejando
    private int resourse; //sera nuestro layout(cardview)
    private Activity activity;//actividad desde donde se esta lllamdo esta clase

    public PostAdapterRecyclerView(ArrayList<Post> posts, int resourse, Activity activity) {
        this.posts = posts;
        this.resourse = resourse;
        this.activity = activity;
    }

    @Override
    public PictureiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //paso 12 tomar elemento y mostrarlo en la pantalla
        View v = LayoutInflater.from(parent.getContext()).inflate(resourse, parent, false);
        //paso 13
        return new PictureiewHolder(v);
    }

    @Override //paso 14
    public void onBindViewHolder(PictureiewHolder holder, int position) {
        //obtenemos la posicion de nuestra lista (cachear nuestra lista)
        Post post = posts.get(position);
        //le asignamos datos correspondientes
        holder.usernameCard.setText(post.getAutor());
        holder.timecard.setText(post.getRelativeTimeStamp());
        holder.likenumbercard.setText("1");
        //holder.picturecard.setImageURI(picture.getPicture());
        Picasso.with(activity).load(post.getImageURL()).fit().into(holder.picturecard);

        holder.picturecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(activity, PictureDetailActivity.class);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                    Explode explode=new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(i,
                            ActivityOptionsCompat
                                    .makeSceneTransitionAnimation
                                            (activity,view,activity.getString(R.string.transicionName_picture))
                                    .toBundle());
                }else{

                    activity.startActivity(i);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        //paso 15
        return posts.size();
    }

    //paso 2
    public class PictureiewHolder extends RecyclerView.ViewHolder{
        //paso 3
        private ImageView picturecard;
        private TextView usernameCard;
        private TextView timecard;
        private TextView likenumbercard;



        public PictureiewHolder(View itemView) {
            super(itemView);
            //paso 4
            picturecard=(ImageView) itemView.findViewById(R.id.pictureCard);
            usernameCard=(TextView)itemView.findViewById(R.id.userNameCard);
            timecard=(TextView)itemView.findViewById(R.id.timecard);
            likenumbercard=(TextView)itemView.findViewById(R.id.likeNumberCard);
        }
    }
}
/*
1- creamos adaptador
2- creamos una clase dentro Clase(viewHolder) que extienda de RecyclerView.ViewHolder
3- dentro de la Clase(viewHolder) declaramos todos los elementos de nuestra CardView
4- instanciar los elementos de nuestra CardView dentro del constructor de la Clase(viewHolder)
5- a la clase principal heredar de RecyclerView.Adapter< "una coleccion/arraylis">
6- creamos un model/POJO de nuestra coleccion/arrailist con sus respectivos atributos constructor/seter/getter
7- el nombre de la Clase(viewHolder) lo ponemos como parametro de tipo en el RecyclerView.Adapter<Clase ViewHolder>
8- alt enter y pondra el nombre de la clase princimal segudi de un punto nuestra Clase ViewHolder
9- alt enter importamos los metodos correspondientes del adaptador
10- declaramos lo que queremos recibir en nuestro adaptador en este caso es:
    private ArrayList<Picture> posts; //lista de objetos que recibiremos y estaremos manejando
    private int resourse; //sera nuestro layout(cardview)
    private Activity activity;//actividad desde donde se esta lllamdo esta clase
11- generamos el constructor con los elementos que declaramos.
12- vamos al metodo onCreateViewHolder y creamos un View para inflar nuestro recurso(layout xml) y
    parent(CardView/ViewGrup) y false.
    (tomar elemento y mostrarlo en la pantalla)
13- en el return instanciamos nuestra clase ViewHolder y le pasamos nuestro View inflado
14- vamos al onBinViewHolder
15- camos al metodo getItemCount y devolvemos el tamano de nuestra lista

***********adapter listo******************************
 */
