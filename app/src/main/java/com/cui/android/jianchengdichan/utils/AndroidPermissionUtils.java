package com.cui.android.jianchengdichan.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class AndroidPermissionUtils {
    //系统所需要的全部权限
    private static final String[] permissoins = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.RECEIVE_BOOT_COMPLETED,
            Manifest.permission.VIBRATE,
            Manifest.permission.SYSTEM_ALERT_WINDOW,
            Manifest.permission.GET_TASKS,
            Manifest.permission.ACCOUNT_MANAGER,
//            Manifest.permission.GET_ACCOUNTS,
//            Manifest.permission.BLUETOOTH,
//            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.READ_SYNC_SETTINGS,
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.WRITE_SETTINGS,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CHANGE_CONFIGURATION,
            Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.RESTART_PACKAGES,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_LOGS,
            Manifest.permission.BROADCAST_STICKY,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_MULTICAST_STATE,
            Manifest.permission.WRITE_SYNC_SETTINGS,
            Manifest.permission.CALL_PHONE
    };


    public static String[] checkPermission(Context context){

        List<String> unCheckPermissions = new ArrayList<>();

        for (String permission : permissoins) {
            int checkSelfPermission = ContextCompat.checkSelfPermission(context, permission);
            //如果未申请权限
            if (checkSelfPermission == PackageManager.PERMISSION_DENIED){
                unCheckPermissions.add(permission);
            }
        }

        return unCheckPermissions.toArray(new String[unCheckPermissions.size()]);

    }

}
