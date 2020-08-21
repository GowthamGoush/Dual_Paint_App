package com.example.dualpaint;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class FragmentA extends Fragment {

    private DataViewModel viewModel;
    private PathView pathView1;
    private float X1,Y1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_a, container, false);

        pathView1 = v.findViewById(R.id.pathViewA);

        viewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);

        pathView1.setCoordinatesListener(new PathView.OnCoordinateUpdate() {
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
                X1 = aFloat;
            }
        });

        viewModel.getY().observe(getViewLifecycleOwner(), new Observer<Float>() {
            @Override
            public void onChanged(Float aFloat) {
                Y1 = aFloat;
            }
        });

        viewModel.getMove().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                  pathView1.setPathMove(X1,Y1);
                }
                else {
                    pathView1.setPathLine(X1,Y1);
                }
            }
        });



    }




}
