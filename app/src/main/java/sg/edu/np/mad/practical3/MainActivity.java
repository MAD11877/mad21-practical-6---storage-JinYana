package sg.edu.np.mad.practical3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    DBHandler dbHandler = new DBHandler(this, null, null, 1);

    private static final String TAG = "Main Activity";
    private static boolean savefollow;
    private TextView maintext;
    public static User u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Debug", "create");
        Intent receivedData = getIntent();

        maintext = findViewById(R.id.txtName);
        String randomname = receivedData.getStringExtra(("Name"));
        String randomdesc= receivedData.getStringExtra(("Description"));
        int id = receivedData.getIntExtra("id", 0);



        for(int i = 0; i < 20; i++){
            if(ListActivity.userList.get(i).id == id){
               u = ListActivity.userList.get(i);

            }
        }


        TextView name = findViewById(R.id.txtName);
        name.setText(u.name);
        TextView description = findViewById(R.id.txtDescription);
        description.setText(u.description);
        setFollowBtn();

        Button b = findViewById(R.id.btnFollow);

    }

    private void setFollowBtn() {
        Button b = findViewById(R.id.btnFollow);
        if(u.followed) {
            b.setText("Unfollow");
            u.followed = true;




        }
        else {
            b.setText("Follow");
            u.followed = false;


        }

    }
    public void onFollowClick(View v) {
        u.followed = !u.followed;
        setFollowBtn();
        dbHandler.updateUser(u);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Debug", "start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Debug", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Debug", "destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Debug", "pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug", "resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Debug", "restart");
    }
}