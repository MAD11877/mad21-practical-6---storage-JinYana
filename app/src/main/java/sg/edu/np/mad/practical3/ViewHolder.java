package sg.edu.np.mad.practical3;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder  {
    TextView name;
    TextView desc;
    ImageView image;

    public ViewHolder(View itemview){
        super(itemview);
        name = itemview.findViewById((R.id.textView4));
        desc = itemview.findViewById((R.id.textView5));
        image = itemview.findViewById(R.id.imageView3);








    }



}
