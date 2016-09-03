package me.danielhartman.startingstrength.ui.createWorkout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;

public class CreateWorkoutName extends Activity {
    private static final String TAG = CreateWorkoutName.class.getSimpleName();
    final int SELECT_PICTURE = 1;
    Uri imageUri;

    @Inject
    CreateWorkoutPresenter presenter;

    @BindView(R.id.selectedImage)
    ImageView selectedImageView;

    @BindView(R.id.workoutNameEditText)
    EditText workoutName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_image_fragment);
        DaggerHolder.getInstance().component().inject(this);
        ButterKnife.bind(this);
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
            Picasso.with(this).load(imageUri).fit().centerInside().into(selectedImageView);
            presenter.saveUri(imageUri);
        }
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
