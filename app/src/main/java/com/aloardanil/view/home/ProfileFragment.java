package com.aloardanil.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aloardanil.R;
import com.aloardanil.Session.Session;
import com.aloardanil.view.login.LoginActivity;
import com.aloardanil.viewmodel.ProfileViewModel;
import com.google.android.material.snackbar.Snackbar;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private Button mLogout;
    private LinearLayout linearLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        final TextView mName = root.findViewById(R.id.text_name);
        final TextView mPosition = root.findViewById(R.id.text_position);
        final TextView mPhone = root.findViewById(R.id.text_phone);
        mLogout = root.findViewById(R.id.btn_logout);
        linearLayout = root.findViewById(R.id.linearLayout);

        profileViewModel.getName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mName.setText(s);
            }
        });

        profileViewModel.getPosition().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mPosition.setText(s);
            }
        });

        profileViewModel.getPhone().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mPhone.setText(s);
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Session session = new Session(getContext());
                session.clearSession();
                Snackbar.make(linearLayout, R.string.login_failed, Snackbar.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                getActivity().finish();
            }
        });
    }
}