package sg.edu.np.mad.practical3;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Recycleradapter extends RecyclerView.Adapter<ViewHolder>  {
    AlertDialog.Builder builder;
    Random random = new Random();




    ArrayList<User> data;


    public Recycleradapter(ArrayList<User> input){

        data = input;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        ViewHolder vh;

        User info = data.get(viewType);
        char last = info.getName().charAt(info.getName().length() - 1);




            if (Character.getNumericValue(last) == 7) {
                View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.extrarecyclerview, parent, false);
                 vh = new ViewHolder(item);


                return vh;



            }
            else {
                View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, parent, false);
                 vh = new ViewHolder(item);

                return vh;


            }












    }

    public void onBindViewHolder(ViewHolder holder, int position){
        User info = data.get(position);
        holder.name.setText(info.getName());
        holder.desc.setText(info.getDescription());


        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                builder = new AlertDialog.Builder(context);
                builder.setTitle("Profile");
                builder.setMessage(info.getName());
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("Name", info.getName());
                        intent.putExtra("Description", info.getDescription());
                        intent.putExtra("id", info.getId());


                        context.startActivity(intent);







                    }
                });
                builder.show();

            }
        });


    }





    public int getItemCount(){
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position++;
    }
}
