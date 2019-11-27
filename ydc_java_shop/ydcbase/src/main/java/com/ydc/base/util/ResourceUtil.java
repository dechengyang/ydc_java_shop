package com.ydc.base.util;

import android.content.Context;
import android.util.TypedValue;

public class ResourceUtil {

    private static TypedValue mTmpValue = new TypedValue();
    
    private ResourceUtil(){}
    
    public static int getXmlDef(Context context, int id) {
        synchronized (mTmpValue) {
	        TypedValue value = mTmpValue;
	        context.getResources().getValue(id, value, true);
	        return (int)TypedValue.complexToFloat(value.data);
        }
    }
}