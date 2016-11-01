package me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutName;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.databinding.CreateWorkoutNameConstraintLayoutBinding;
import me.danielhartman.startingstrength.ui.base.BaseFragment;
import me.danielhartman.startingstrength.ui.base.BasePresenter;

import static android.app.Activity.RESULT_OK;


public class CWNFragment extends BaseFragment<CWNContract.View> implements CWNContract.View {
    final int SELECT_PICTURE = 1;
    Uri imageUri;
    CWNPresenter presenter;
    CreateWorkoutNameConstraintLayoutBinding binding;

    @Override
    public void initDagger() {
        presenter = DaggerHolder.getInstance().component().getCWNPresenter();
    }

    @Override
    public BasePresenter<CWNContract.View> getPresenter() {
        return presenter;
    }

    @Override
    public CWNContract.View getContractView() {
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.create_workout_name_constraint_layout, container, false);
        binding.workoutNameEditText.requestFocus();
        fabOnClick();
        return binding.getRoot();
    }

    public void fabOnClick() {
        binding.findImageButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,
                    "Select Picture"), SELECT_PICTURE);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        CropImage.ActivityResult result;
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && null != data) {
            startCroppingImage(data.getData());
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            result = CropImage.getActivityResult(data);
            presenter.saveUri(imageUri);
            Picasso.with(getContext()).load(result.getUri()).fit().centerInside().into(binding.selectedImage);
        } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
            result = CropImage.getActivityResult(data);
            Exception error = result.getError();
            Toast.makeText(getContext(), "There was an error cropping the photo", Toast.LENGTH_LONG).show();
        }
    }

    public void startCroppingImage(Uri uri) {
        CropImage.activity(uri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAutoZoomEnabled(true)
                .setAspectRatio(16, 10)
                .start(getActivity());

    }
}
