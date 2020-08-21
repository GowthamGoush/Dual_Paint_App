package com.example.dualpaint;

import android.graphics.Path;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {

    private MutableLiveData<Float> pointX = new MutableLiveData<>();
    private MutableLiveData<Float> pointY = new MutableLiveData<>();
    private MutableLiveData<Boolean> isMove = new MutableLiveData<>();

    public void setPath(float inputX,float inputY,boolean inputMove) {
        pointX.setValue(inputX);
        pointY.setValue(inputY);
        isMove.setValue(inputMove);
    }

    public LiveData<Float> getX() {
        return pointX;
    }

    public LiveData<Float> getY() {
        return pointY;
    }

    public LiveData<Boolean> getMove() {
        return isMove;
    }


}
