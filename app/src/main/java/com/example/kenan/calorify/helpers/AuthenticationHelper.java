package com.example.kenan.calorify.helpers;

import android.content.Context;
import android.content.Intent;

import com.example.kenan.calorify.MainActivity;
import com.example.kenan.calorify.MenuActivity;
import com.example.kenan.calorify.dal.repos.UserRepository;

/**
 * Created by Kenan on 12/10/2017.
 */

public class AuthenticationHelper {
    private static boolean isActiveUserPresent() {
        UserRepository repository = new UserRepository();
        return repository.getActiveUser() != null;
    }

    public static void continueToMenu(Context context){
        if (isActiveUserPresent()){
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClass(context, MenuActivity.class);
            context.startActivity(intent);
        }
    }

    public static void continueToMain(Context context){
        if (!isActiveUserPresent()){
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClass(context, MainActivity.class);
            context.startActivity(intent);
        }
    }
}
