package harish.hibare.my1application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

public class MainActivity extends AppCompatActivity {
ImageButton imageButton;
Intent invokecamera;
Bitmap bitmap;
Button button;

final static  int picbycamera=007;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton=findViewById(R.id.imageButton);
        imageView=findViewById(R.id.imageView);
        button=findViewById(R.id.button);
        @SuppressLint("ResourceType") InputStream inputStream=getResources().openRawResource(R.drawable.image1);
        bitmap= BitmapFactory.decodeStream(inputStream);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    getApplicationContext().setWallpaper(bitmap);
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invokecamera=new Intent(ACTION_IMAGE_CAPTURE);
                startActivityForResult(invokecamera,picbycamera);
               //startActivity(invokecamera);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            Bundle bundle=data.getExtras();
            bitmap= (Bitmap) bundle.get("data");
            imageView.setImageBitmap(bitmap);

            //Toast.makeText(this, "I can see U but U can't!!!!!", Toast.LENGTH_SHORT).show();
        }
    }
}