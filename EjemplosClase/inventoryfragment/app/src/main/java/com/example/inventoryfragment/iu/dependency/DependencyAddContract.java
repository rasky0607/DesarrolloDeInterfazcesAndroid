package com.example.inventoryfragment.iu.dependency;

import com.example.inventoryfragment.data.model.Dependency;

public interface DependencyAddContract {

    interface View{
        void showError(String message);
        void onSuccess();

    }

    interface Presenter{
        void add(Dependency dependency);
    }

}
