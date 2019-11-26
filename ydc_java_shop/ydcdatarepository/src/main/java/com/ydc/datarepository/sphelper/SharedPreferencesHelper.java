package com.ydc.datarepository.sphelper;

import android.content.Context;
import android.content.SharedPreferences;


import androidx.core.content.SharedPreferencesCompat;

import java.util.Map;

/**
 * @Description 返回接口的基类
 * @Author ydc
 * @CreateDate 2016/11/15
 * @Version 1.0
 */

public class SharedPreferencesHelper {
    private static SharedPreferencesHelper sharedPreferencesHelper;
    private static SharedPreferences sharedPreferences;
    /**
     * 保存在手机里面的名字
     */
    public static final String FILE_NAME = "sp_data";
    private static SharedPreferences.Editor editor;

    /**
     * @param context
     * @return
     */
    public static SharedPreferencesHelper getInstance(Context context) {
        if (null == sharedPreferences) {
            sharedPreferencesHelper = new SharedPreferencesHelper(context);
        }
        return sharedPreferencesHelper;
    }

    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        //editor = sharedPreferences.edit();
    }

    /**
     * 保存数据的方法，拿到数据保存数据的基本类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public static void put(String key, Object object) {
        editor = sharedPreferences.edit();
        if(object==null) {
            object="";
        }
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        //editor.apply();
        editor.commit();
        //SharedPreferencesCompat.apply(editor);
    }

    /**
     * 获取保存数据的方法，我们根据默认值的到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key           键的值
     * @param defaultObject 默认值
     * @return
     */

    public static Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        } else {
            return sharedPreferences.getString(key, null);
        }
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(String key) {
        editor.remove(key);
        //SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有的数据
     */
    public static void clear() {
        editor.clear();
        //SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否存在
     *
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public static Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }

}
