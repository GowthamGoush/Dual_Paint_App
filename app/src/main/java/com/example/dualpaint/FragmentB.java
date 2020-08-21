package com.example.dualpaint;


import android.graphics.Path;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class FragmentB extends Fragment {

    private DataViewModel viewModel;
    private PathView pathView2;
    private float X2,Y2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_b, container, false);

        viewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);

        pathView2 = v.findViewById(R.id.pathViewB);

        pathView2.setCoordinatesListener(new PathView.OnCoordinateUpdate() {
            @Override
            public void onUpdate(float X, float Y, boolean isMove) {
                viewModel.setPath(X,Y,isMove);
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getX().observe(getViewLifecycleOwner(), new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                X2 = aFloat;
            }
        });

        viewModel.getY().observe(getViewLifecycleOwner(), new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                Y2 = aFloat;
            }
        });

        viewModel.getMove().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    pathView2.setPathMove(X2,Y2);
                }
                else {
                    pathView2.setPathLine(X2,Y2);
                }
            }
        });
    }



}
