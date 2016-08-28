package me.danielhartman.startingstrength.ui.createWorkout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.Interfaces.CreateWorkoutCallback;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.ui.MainActivity;

public class AddImageActivity extends Activity implements CreateWorkoutCallback {
    private static final String TAG = AddImageActivity.class.getSimpleName();
    final int SELECT_PICTURE = 1;
    Uri imageUri;

    @Inject
    CreateWorkoutPresenter presenter;

    @BindView(R.id.selectedImage)
    ImageView selectedImageView;

    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_image_fragment);
        DaggerHolder.getInstance().component().inject(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.findImageButton)
    public void findImageButtonClick(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
    }

    @OnClick(R.id.uploadWorkoutButton)
    public void uploadImageButtonClick()  {
        presenter.commitToFirebase(this,imageUri,this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && null != data) {
            imageUri = data.getData();
            Picasso.with(this).load(imageUri).fit().into(selectedImageView);

        }
    }

    @Override
    public void onComplete() {
        Toast.makeText(this, "Workout Created Successfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "An error occured, workout not created", Toast.LENGTH_LONG).show();
    }
}
