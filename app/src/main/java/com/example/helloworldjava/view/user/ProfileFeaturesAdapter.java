package com.example.helloworldjava.view.user;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.helloworldjava.R;
import com.example.helloworldjava.model.ProfileFeature;
import com.example.helloworldjava.presenter.LoginPresenter;
import com.example.helloworldjava.view.Account_Login;

import java.util.List;

public class ProfileFeaturesAdapter extends RecyclerView.Adapter<ProfileFeaturesAdapter.ProfileFeaturesViewHolder> {
    private Context mContext;
    private List<ProfileFeature> mProfileFeatures;

    public ProfileFeaturesAdapter(Context context, List<ProfileFeature> profileFeatures) {
        this.mProfileFeatures = profileFeatures;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ProfileFeaturesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfileFeaturesViewHolder(mContext, LayoutInflater.from(mContext).
                inflate(R.layout.user_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileFeaturesViewHolder holder, int position) {
        //Get the current sport
        ProfileFeature currentProfileFeature = mProfileFeatures.get(position);

        //Bind the data to the views
        holder.bindTo(currentProfileFeature);
    }

    @Override
    public int getItemCount() {
        return mProfileFeatures.size();
    }

    public static class ProfileFeaturesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context mContext;
        private ImageView mImage;
        private TextView mName;
        private ProfileFeature profileFeature;

        public ProfileFeaturesViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            mContext = context;
            mImage = itemView.findViewById(R.id.profileFeatureIconImage);
            mName = itemView.findViewById(R.id.profileFeatureTextName);
            itemView.setOnClickListener(this);
        }

        public void bindTo(ProfileFeature profileFeature) {
            this.profileFeature = profileFeature;
            mName.setText(profileFeature.getName());

            if (profileFeature.getImageResourceId() == R.drawable.logout) {
                mName.setTextColor(mContext.getResources().getColor(R.color.colorError));
            }

            Glide.with(mContext).load(profileFeature.getImageResourceId())
                    .circleCrop()
                    .into(mImage);
        }

        @Override
        public void onClick(View v) {
            if (profileFeature.getActivityClass() != null) {
                Intent intent = new Intent(mContext, profileFeature.getActivityClass());
                mContext.startActivity(intent);
            }

            if (profileFeature.getImageResourceId() == R.drawable.logout) {
                LoginPresenter.logoutNguoiDungFromSharedFref(mContext);
                Intent intent = new Intent(mContext, Account_Login.class);
                mContext.startActivity(intent);
            }
        }
    }
}
