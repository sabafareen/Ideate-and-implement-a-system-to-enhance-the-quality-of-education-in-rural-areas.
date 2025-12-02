package com.example.mytrail1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;

public class Video2Fragment extends Fragment {

    private VideoView videoView;
    private Button startQuizButton;
    private FirebaseAuth firebaseAuth;

    public Video2Fragment() {
        
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    
        View rootView = inflater.inflate(R.layout.fragment_video2, container, false);

        
        firebaseAuth = FirebaseAuth.getInstance();

        videoView = rootView.findViewById(R.id.video);
        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setVideoURI(Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.video2));
        videoView.start();

 
        startQuizButton = rootView.findViewById(R.id.startQuiz);

        startQuizButton.setOnClickListener(view -> {
          
            Intent intent = new Intent(getActivity(), QuizPage.class);
            startActivity(intent);
        });

        return rootView;
    }
}
