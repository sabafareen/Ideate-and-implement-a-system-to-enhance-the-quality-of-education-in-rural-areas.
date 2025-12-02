package com.example.mytrail1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    TextView logout, emailTextView, nameTextView;

    public ProfileFragment() {
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        
        logout = view.findViewById(R.id.button4); 
        emailTextView = view.findViewById(R.id.pemail); 
        nameTextView = view.findViewById(R.id.pname);   

      
        Bundle bundle = getArguments();
        if (bundle != null) {
            String emailid = bundle.getString("emailid");
            String name = bundle.getString("name");

            emailTextView.setText(emailid);
            nameTextView.setText(name);
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

              
                requireActivity().finish();
            }
        });

        return view;
    }
}
