package androiddeveloper.eder.padilla.bignotificationsample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.image_view)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                getImage();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getImage(){
        Log.e("get","image");
        String url = "http://vignette3.wikia.nocookie.net/dragonball/images/2/29/6846_dragon_ball_z_hd_wallpapers.jpg/revision/latest?cb=20140822015018";
        Glide.with(this)
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(200, 200) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                        // Do something with bitmap here.
                        Log.e("nidfs","sdfsdf");
                            sendNotification(bitmap);}

                });
    }

   public void sendNotification(Bitmap bitmap){
       long []patron = new long[]{500,2200,800,900};
       Intent intent = new Intent(this, MainActivity.class);
       PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 100, intent, 0);
       NotificationManager mNotifM = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
       NotificationCompat.Builder mBuilder =
               new NotificationCompat.Builder(getApplicationContext())
                       .setSmallIcon(R.drawable.android_symbol)
                       .setSubText("Hola mundo")
                       .setContentTitle("¡Notificacion de prueba")
                       .setContentText("Soy el campeon de fifa")
                       .setLargeIcon(bitmap).setStyle(new NotificationCompat.BigTextStyle().bigText("Dragon ball"))
                       //.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                       .setStyle(new NotificationCompat.BigTextStyle().bigText("08-11 14:20:15.310 15072-15095/? W/OpenGLRenderer: Points are too far apart 4.00238708-11 14:20:15.310 15072-15095/? W/OpenGLRenderer: Points are too far apart 4.00238708-11 14:20:15.310 15072-15095/? W/OpenGLRenderer: Points are too far apart 4.002387"))
                       .addAction(R.drawable.android_symbol,"Hola mundo",contentIntent)
                       .setPriority(Notification.PRIORITY_LOW)
                       .setVibrate(patron)
                       .setAutoCancel(true)
                       .setContentIntent(contentIntent);
       mNotifM.notify(1000, mBuilder.build());
   }

}
