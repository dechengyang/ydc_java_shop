package com.ydcjavashop.shop.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kuanyoupeng on 16/11/8.
 */

public class PermissionHelper {
    public static final int REQ_PERMISSION_CODE = 0x12;
    /***
     * 动态添加权限
     */
    private static final int REQUEST_CODE_ONE = 100;
    private static final int REQUEST_CODE_TWO = 101;
    private static final int REQUEST_CODE_THREE = 102;

    /**
     * 动态申请权限
     *
     * @param context     上下文
     * @param permissions 需要申请的权限
     * @return 是不是需要申请
     */
    public static boolean checkPermission(Activity context, String[] permissions) {
        //6.0以上
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            //没有权限需要申请时
            if (permissions == null || permissions.length <= 0)
                return true;
            //检查权限是不是已经授予
            List<String> noOkPermissions = new ArrayList();
            for (String permission : permissions) {
                if (context.checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED) {
                    noOkPermissions.add(permission);
                }
            }
            //该权限已经授予，不再申请
            if (noOkPermissions.size() <= 0)
                return true;
            //6.0以上需要申请权限
            context.requestPermissions(noOkPermissions.toArray(new String[noOkPermissions.size()]), REQ_PERMISSION_CODE);
            return false;
        }
        //6.0以下下不需要申请
        return true;
    }

    //检查权限是不是已经授予
    private static List<String> noOkPermissions = new ArrayList();

    /**
     * ydc 20180307
     *
     * @param context
     * @param permissions
     * @return
     */
    public static boolean checkingPermission(Activity context, String[] permissions) {
        if (noOkPermissions != null) {
            if (noOkPermissions.size() > 0) {
                noOkPermissions.clear();
            }
        }
        //6.0以上
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            //没有权限需要申请时
            if (permissions == null || permissions.length <= 0)
                return true;

            for (String permission : permissions) {
                if (context.checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED) {
                    noOkPermissions.add(permission);
                }
            }
            //该权限已经授予，不再申请
            if (noOkPermissions.size() <= 0) {
                return true;
            } else {
                return false;
            }
        }
        //6.0以下下不需要申请
        return true;
    }

    /**
     * ydc 20180307 6.0以上需要申请权限
     *
     * @param context
     */
    public static void requestPermissions(Activity context) {
        //6.0以上需要申请权限
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            context.requestPermissions(noOkPermissions.toArray(new String[noOkPermissions.size()]), REQ_PERMISSION_CODE);
        }

    }

    /**
     * ydc 20180307 启动应用的设置
     *
     * @param mContext
     */
    public static void startAppSettings(Context mContext) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + mContext.getPackageName()));
        mContext.startActivity(intent);
    }


    /**
     * 处理权限申请的结果，返回结构化的数据
     *
     * @param requestCode  请求码
     * @param permissions  被请求的权限
     * @param grantResults 请求结果
     * @param listener     监听
     */
    public static void onRequestPermissionsResult(int requestCode,
                                                  String[] permissions,
                                                  int[] grantResults,
                                                  OnPermissionHandleOverListener listener) {
        if (requestCode != REQ_PERMISSION_CODE)
            return;
        Map<String, Integer> result = new HashMap<>();
        boolean isHavePermissionNotOk = false;
        for (int i = 0; i < Math.min(permissions.length, grantResults.length); i++) {
            result.put(permissions[i], grantResults[i]);
            //有权限没有同意
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                isHavePermissionNotOk = true;
            }
        }
        //如果权限全部同意，继续执行
        if (listener != null)
            listener.onHandleOver(!isHavePermissionNotOk, result);
    }

    public interface OnPermissionHandleOverListener {
        void onHandleOver(boolean isOkExactly, Map<String, Integer> result);
    }


    /**
     * 查看权限
     */
    public static boolean verifyStoragePermissions(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA) && ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {//是否请求过该权限
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_THREE);
                    //ToastUtils.shows("您拒绝了获取到相机权限,请手动获取或重装软件");
                } else {//没有则请求获取权限
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_THREE);
                }
            } else if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {//是否请求过该权限
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_TWO);
                    //ToastUtils.shows("您拒绝了获取读取存储图片权限,请手动获取或重装软件");
                } else {//没有则请求获取权限
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_TWO);
                }
            } else if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {//是否请求过该权限
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_ONE);
                    //ToastUtils.shows("您拒绝了获取到相机权限,请手动获取或重装软件");
                } else {//没有则请求获取权限
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_ONE);
                }
            } else {        //如果已经获取到了权限则直接进行下一步操作
                //Intent intent = new Intent(AddInquiryBillActivity.this, CaptureActivity.class);
                //startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                //addImage();
                return true;
            }
            return false;
        } else {
            return true;
        }
    }
}
/***
 @Override public void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 mSaveBundle = savedInstanceState;
 mActivity = this;
 mContext = getApplicationContext();
 createViewShow();
 //如果不需要监测，会立刻执行invokeCommonMethod()开始Activity操作，如果需要就会发起申请
 if (PermissionHelper.checkPermission(mActivity, getPermission2Check()))
 invokeCommonMethod(mSaveBundle);
 }


 //抽象方法，由子类来决定哪些权限需要申请
 protected abstract String[] getPermission2Check();

 //子类实现决定处理结果
 protected boolean handlePermissionResult(Map<String, Integer> resultNotOk) {
 return true;
 }


 //处理返回结果
 @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
 super.onRequestPermissionsResult(requestCode, permissions, grantResults);
 PermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults, new PermissionHelper.OnPermissionHandleOverListener() {
 @Override public void onHandleOver(boolean isOkExactly, Map<String, Integer> result) {
 //权限ok或者子类要求直接执行
 if (isOkExactly || handlePermissionResult(result))
 invokeCommonMethod(mSaveBundle);
 }
 });
 }**/