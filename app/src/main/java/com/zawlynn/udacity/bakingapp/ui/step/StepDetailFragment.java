package com.zawlynn.udacity.bakingapp.ui.step;

import android.app.Dialog;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.zawlynn.udacity.bakingapp.R;
import com.zawlynn.udacity.bakingapp.constants.Constants;
import com.zawlynn.udacity.bakingapp.databinding.StepFragmentBinding;
import com.zawlynn.udacity.bakingapp.pojo.Step;

import java.util.Objects;

import static android.view.View.GONE;

public class StepDetailFragment extends Fragment {

    private Step step;
    private SimpleExoPlayer player;
    private long playbackPosition;
    private int currentWindow;
    private boolean playWhenReady = true;
    private Dialog fullScreenDialog;
    private boolean isMovieinFullScreen = false;
    private StepFragmentBinding binding;

    public static StepDetailFragment newInstance(Step step) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.DATA, step);
        StepDetailFragment fragment = new StepDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(Constants.DATA)) {
            this.step = getArguments().getParcelable(Constants.DATA);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = StepFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvDesc.setText(step.getDescription());
        initFullscreenDialog();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }


    private void initializePlayer() {

        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getContext()),
                    new DefaultTrackSelector(),
                    new DefaultLoadControl());
            binding.playerView.setPlayer(player);
            player.setPlayWhenReady(playWhenReady);
            player.seekTo(currentWindow, playbackPosition);
        }

        Uri uri = null;

        if (!TextUtils.isEmpty(step.getVideoUrl())) {
            uri = Uri.parse(step.getVideoUrl());
        }

        if (uri == null && !TextUtils.isEmpty(step.getThumbnailUrl())) {
            uri = Uri.parse(step.getThumbnailUrl());
        }

        if (uri != null) {
            MediaSource mediaSource = buildMediaSource(uri);
            player.prepare(mediaSource, true, false);
        } else {
            binding.playerView.setVisibility(GONE);
            binding.videoContainer.setVisibility(GONE);
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("udacity-course")).
                createMediaSource(uri);
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (binding.videoContainer.getVisibility() == View.VISIBLE) {
            int currentOrientation = getResources().getConfiguration().orientation;
            if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                openFullscreenDialog();
            } else {
                closeFullscreenDialog();
            }
        }
    }

    private void initFullscreenDialog() {
        fullScreenDialog = new Dialog(Objects.requireNonNull(getActivity()),
                android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
            public void onBackPressed() {
                if (isMovieinFullScreen)
                    closeFullscreenDialog();
                super.onBackPressed();
            }
        };
    }

    private void openFullscreenDialog() {
        binding.videoContainer.removeView(binding.playerView);
        fullScreenDialog.addContentView(binding.playerView, new ViewGroup
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        isMovieinFullScreen = true;
        fullScreenDialog.show();
    }

    private void closeFullscreenDialog() {
        ((ViewGroup) binding.playerView.getParent()).removeView(binding.playerView);
        binding.videoContainer.addView(binding.playerView);
        isMovieinFullScreen = false;
        fullScreenDialog.dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
