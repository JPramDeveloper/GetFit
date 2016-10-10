package me.danielhartman.startingstrength.ui.createWorkout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class CreateWorkoutName extends AppCompatActivity {
    private static final String TAG = CreateWorkoutName.class.getSimpleName();
    final int SELECT_PICTURE = 1;
    Uri imageUri;

    @Inject
    CreateWorkoutPresenter presenter;

    @BindView(R.id.selectedImage)
    ImageView selectedImageView;

    @BindView(R.id.workoutNameEditText)
    EditText workoutName;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_workout_name_constraint_layout);
        DaggerHolder.getInstance().component().inject(this);
        ButterKnife.bind(this);
        toolbar.setTitle("Create Workout");
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.findImageButton)
    public void findImageButtonClick() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && null != data) {
            imageUri = data.getData();
            startCroppingImage();
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                presenter.saveUri(imageUri);
                Picasso.with(this).load(imageUri).fit().centerInside().into(selectedImageView);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Log.d(TAG, "onActivityResult: Error Cropping Photo " + error.toString());
                Toast.makeText(this, "There was an error cropping the photo", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void startCroppingImage() {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAutoZoomEnabled(true)
                .setAspectRatio(16, 10)
                .start(this);

    }

    @OnClick(R.id.createWorkoutButton)
    public void onButtonClick() {
        presenter.setWorkout(null);
        presenter.getWorkout().setName(workoutName.getText().toString());
        Log.d(TAG, "onButtonClick: ");
        Intent i = new Intent(this, CreateWorkoutActivity.class);
        startActivity(i);
    }
}
