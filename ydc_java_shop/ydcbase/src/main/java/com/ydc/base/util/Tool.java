package com.ydc.base.util;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@SuppressLint("DefaultLocale")
public class Tool {
    private SimpleDateFormat myFmt;
    private static Toast TOAST = null;

    public Tool() {

    }

    public static boolean CheckSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

    @SuppressLint("ShowToast")
    public static void showTextToast(Context context, String msg) {
        if (TOAST == null) {
            TOAST = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            TOAST.setText(msg);
        }
        TOAST.show();
    }

    /**
     * File转为byte[]
     *
     * @param f
     * @return
     */
    public static byte[] file2Byte(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1024];
            int n;
            while ((n = stream.read(b)) != -1) {
                out.write(b, 0, n);
            }
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return yinsujun 2015-8-20 下午5:34:18
     */
    public static int getWindowWith(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        @SuppressWarnings("deprecation")
        int width = windowManager.getDefaultDisplay().getWidth();
        return width;
    }


    /**
     * 获取屏幕高度
     *
     * @param context
     * @return yinsujun 2015-8-20 下午5:34:18
     */
    public static int getWindowHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        @SuppressWarnings("deprecation")
        int height = windowManager.getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * Bitmap转为byte[]
     *
     * @param bitmap
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 80, baos);
        return baos.toByteArray();
    }

    public static Bitmap getBitmap(Context con, String path) {
        if (path.toLowerCase().startsWith("http")) {
            return getHttpBitmap(path);
        } else {
            return getAssetsBitmap(con, path);
        }
    }

    public static Bitmap getFileBitmap(Context con, String name) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }


    public static Bitmap getAssetsBitmap(Context con, String path) {
        Bitmap image = null;
        try {
            AssetManager am = con.getResources().getAssets();
            try {
                InputStream is = am.open(path);
                image = BitmapFactory.decodeStream(is);
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public static Bitmap getHttpBitmap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.setConnectTimeout(0);
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bitmap != null) {
        }
        return bitmap;
    }

    public static String saveBitmapToFile(String savepath, Bitmap bmp,
                                          String http) {
        if (folderCreate(savepath)) {
            String imgname = System.currentTimeMillis() + ".t";
            String filename = savepath + imgname;
            if (fileCreate(savepath, imgname) != null) {
                try {
                    FileOutputStream out = new FileOutputStream(filename);
                    bmp.compress(CompressFormat.PNG, 90, out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return imgname;
            }
        }
        return null;
    }

    public static boolean saveBitmapToFile(String filepath, String filename,
                                           CompressFormat format, Bitmap bmp) {
        if (folderCreate(filepath)) {
            if (fileCreate(filepath, filename) != null) {
                try {
                    FileOutputStream out = new FileOutputStream(filepath
                            + filename);
                    bmp.compress(format, 90, out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    public static boolean delFolder(String folderpath, String onlyFirst) {
        File folder = new File(folderpath);
        if (!folder.exists()) {
            return false;
        }
        if (!folder.isDirectory()) {
            return false;
        }
        File[] files = folder.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (onlyFirst == null
                        || files[i].getName().indexOf(onlyFirst) == 0) {
                    files[i].delete();
                }
            }
        }
        return true;
    }

    /**
     * 文件是否存在
     *
     * @param filepath 文件路径
     * @param filename 文件名
     */
    public static boolean fileExists(String filepath, String filename) {
        File file = new File(filepath + filename);
        return file.exists();
    }

    /**
     * 创建文件夹
     *
     * @param filepath
     * @return
     */
    public static boolean folderCreate_(String filepath) {
        File folder = new File(filepath);
        if (!folder.exists()) {
            try {
                boolean b = folder.mkdirs();
                return b;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public static boolean folderCreate(String filePath) {
        try {
            File file = null;
            String newPath = null;
            String[] path = filePath.split("/");
            for (int i = 0; i < path.length; i++) {
                if (newPath == null) {
                    newPath = path[i];
                } else {
                    newPath = newPath + "/" + path[i];
                }
                file = new File(newPath);
                if (!file.exists()) {
                    file.mkdir();
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 创建文件
     *
     * @param filepath 文件路径
     * @param filename 文件名
     * @return
     */
    public static File fileCreate(String filepath, String filename) {
        try {
            File file = new File(filepath + filename);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            return file;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean fileRename(String filepath, String filename,
                                     String newname) {
        try {
            File file = new File(filepath + filename);
            String path = file.getParent();
            File newfile = new File(path + newname);
            if (file.exists()) {
                return file.renameTo(newfile);
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean writeFile(String filepath, String filename,
                                    byte[] info) {
        File file = fileCreate(filepath, filename);

        try {
            FileOutputStream op = new FileOutputStream(file);
            op.write(info);
            op.flush();
            op.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 版本号
     *
     * @param con
     * @return
     */
    public static int getAppVersionCode(Context con) {
        try {
            PackageInfo pkgInfo = con.getPackageManager().getPackageInfo(
                    con.getPackageName(), 0);
            if (pkgInfo != null) {
                return pkgInfo.versionCode;
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 版本名称
     *
     * @param con
     * @return
     */
    public static String getAppVersionName(Context con) {
        try {
            PackageInfo pkgInfo = con.getPackageManager().getPackageInfo(
                    con.getPackageName(), 0);
            if (pkgInfo != null) {
                return pkgInfo.versionName;
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 固定电话判断
     *
     * @param telephone
     * @return
     */
    public static boolean isTelephoneNum(String telephone) {
        Pattern p = Pattern.compile("^[\\d|\\D]{0,}\\d{7,11}[\\d|\\D]{0,}$");
        Matcher m = p.matcher(telephone);
        return m.matches();
    }

    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    public static void hideInputMethod(Activity activity) {
        if (null == activity) {
            return;
        }
        if (null != activity.getCurrentFocus() && null != activity.getCurrentFocus().getWindowToken()) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //OOM出现 解决办法
    public static Bitmap createBitmap(int width, int height, Bitmap.Config config) {
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(width, height, config);
        } catch (OutOfMemoryError e) {
            while (bitmap == null) {
                System.gc();
                System.runFinalization();
                bitmap = createBitmap(width, height, config);
            }
        }
        return bitmap;
    }

    /**
     * 获取屏幕分辨率
     *
     * @param context
     * @return
     */
    public static int[] getScreenDispaly(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        @SuppressWarnings("deprecation")
        int width = windowManager.getDefaultDisplay().getWidth();
        @SuppressWarnings("deprecation")
        int height = windowManager.getDefaultDisplay().getHeight();
        int result[] = {width, height};
        return result;
    }

    // 判断字符串对象为null或者""
    public static boolean isBlank(String str) {
        return (str == null || TextUtils.isEmpty(str) || "null".equals(str));
    }

    // 四舍五入(保留两位小数)
    public static double changedoublehalf(double number) {
        BigDecimal b = new BigDecimal(number);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    // 四舍五入(无小数位)
    public static double changedoublenopoint(String string) {
        BigDecimal b = new BigDecimal(string);
        return b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * @param version1
     * @param version2
     * @return if version1 > version2, return 1, if equal, return 0, else return -1
     */
    public static int compare(String version1, String version2) {
        if (version1 == null || version1.length() == 0 || version2 == null || version2.length() == 0)
            throw new IllegalArgumentException("Invalid parameter!");

        int index1 = 0;
        int index2 = 0;
        while (index1 < version1.length() && index2 < version2.length()) {
            int[] number1 = getValue(version1, index1);
            int[] number2 = getValue(version2, index2);

            if (number1[0] < number2[0]) return -1;
            else if (number1[0] > number2[0]) return 1;
            else {
                index1 = number1[1] + 1;
                index2 = number2[1] + 1;
            }
        }
        if (index1 == version1.length() && index2 == version2.length()) return 0;
        if (index1 < version1.length())
            return 1;
        else
            return -1;
    }

    /**
     * @param version
     * @param index   the starting point
     * @return the number between two dots, and the index of the dot
     */
    public static int[] getValue(String version, int index) {
        int[] value_index = new int[2];
        StringBuilder sb = new StringBuilder();
        while (index < version.length() && version.charAt(index) != '.') {
            sb.append(version.charAt(index));
            index++;
        }
        value_index[0] = Integer.parseInt(sb.toString());
        value_index[1] = index;

        return value_index;
    }

    /**
     * utf-8 转unicode
     *
     * @param str
     * @return String
     */
    public static String toUnicode(String str) {
        char[] arChar = str.toCharArray();
        int iValue = 0;
        String uStr = "";
        for (int i = 0; i < arChar.length; i++) {
            iValue = (int) str.charAt(i);
            char sValue = str.charAt(i);
            if (iValue <= 256) {
                // uStr+="& "+Integer.toHexString(iValue)+";";
                // uStr+="\\"+Integer.toHexString(iValue);
                uStr += sValue;
            } else {
                // uStr+="&#x"+Integer.toHexString(iValue)+";";
                uStr += "\\u" + Integer.toHexString(iValue);
            }
        }
        return uStr;
    }

    // 转32位大写MD5
    public final static String get32MD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            Log.e("Tool", "NoSuchAlgorithmException:" + e.toString());
        } catch (UnsupportedEncodingException e) {
            Log.e("Tool", "UnsupportedEncodingException:" + e.toString());
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

    // 判断是否是手机号码
    public static boolean isMobileNO(String mobiles) {
        if (isBlank(mobiles))
            return false;
        Pattern p = Pattern.compile("^1[0-9]{10}$");
        // ^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    // 判断是否是邮箱地址
    public static boolean isEmail(String email) {
        if (isBlank(email))
            return false;
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    // 电话号码部分加*号
    public static String changeMobile(String telephone) {
        if (isBlank(telephone)) {
            return "";
        }
        if (!isMobileNO(telephone))
            return telephone;
        return telephone.substring(0, 3) + "****"
                + telephone.substring(7, telephone.length());
    }

    // 电话号码部分加****号
    public static String changeMobile2(String telephone) {
        if (isBlank(telephone)) {
            return "";
        }
        if (!isMobileNO(telephone))
            return telephone;
        return telephone.substring(0, 2) + "*******"
                + telephone.substring(9, telephone.length());
    }

    // 姓名加*号
    public static String changeName(String name) {
        if (isBlank(name)) {
            return "";
        }
        return "*" + name.substring(1, name.length());
    }

    // 身份证号加*号
    public static String changeIdentity(String identity) {
        if (isBlank(identity)) {
            return "";
        }
        if (identity.length() != 15 && identity.length() != 18) {
            return "身份证号码异常";
        }
        return identity.substring(0, 3) + "************"
                + identity.substring(identity.length() - 3, identity.length());
    }
    // 身份证号加*号
    public static String changeIdentityFive(String identity) {
        if (isBlank(identity)) {
            return "";
        }
        if (identity.length() != 15 && identity.length() != 18) {
            return "身份证号码异常";
        }
        return identity.substring(0, 3) + "************"
                + identity.substring(identity.length() - 4, identity.length());
    }

    // 银行卡加*号
    public static String changeBankAccount(String bankAccount) {
        if (isBlank(bankAccount)) {
            return "";
        }

        return "**** **** **** *** "
                + bankAccount.substring(bankAccount.length() - 4, bankAccount.length());
    }

    //显示银行卡后几位
    public static String BankShow(String bankAccount) {
        if (isBlank(bankAccount)) {
            return "";
        }
        return ""
                + bankAccount.substring(bankAccount.length() - 4, bankAccount.length());
    }

    // 小数进位
    public static double carryAigit(float number) {
        return Math.ceil(number);
    }

    // 判断是否为数字
    public static boolean isNumeric(String str) {
        if (Tool.isBlank(str))
            return false;
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }



    // 名字为汉字
    public static boolean isWord(String str) {
        if (Tool.isBlank(str) || str.trim().length() < 2) {
            return false;
        }
        return true;
    }

    // 检查银行卡号
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId
                .substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null
                || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    // 金额由10000分转为100.00元
    public static String toDeciDouble(String num) {
        if (isBlank(num)) {
            return "0.00";
        }
        if (num.contains(".")) {
            num = num.substring(0, num.indexOf("."));
        }
        int len = num.length();
        if (len == 1) {
            return "0.0" + num;
        } else if (len == 2) {
            return "0." + num;
        } else {
            return num.substring(0, len - 2) + "." + num.substring(len - 2);
        }
    }

    /**
     * 转两位小数 单位：元
     *
     * @param moneyInY
     * @return
     */
    public static String toDeciDouble2(double moneyInY) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(moneyInY);
    }

    // 金额由10000分转为100元
    public static String toIntAccount(String num) {
        if (isBlank(num)) {
            return "0";
        }
        return "" + Long.parseLong(num) / 100;
    }

    // 金额由1000000分转为10,000元
    public static String toDivAccount(String num) {
        if (isBlank(num)) {
            return "0";
        }
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(Long.parseLong(num) / 100);
    }

    // 金额由10000转为10,000元
    public static String toDivAccount1(String num) {
        if (isBlank(num)) {
            return "0";
        }
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(Long.parseLong(num));
    }

    // 金额由10000.00转为10,000.00元
    public static String toDivAccount2(String num) {
        if (isBlank(num) || Double.parseDouble(num) == 0d) {
            return "0.00";
        }
        if (Double.parseDouble(num) < 1d && Double.parseDouble(num) > 0) {
            return num;
        }
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(Double.parseDouble(num));
    }

    //时间比较
    public long getDateShort(String time1, String time2) {
        long dateTime = 0;
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(myFmt.parse(time1));
            long date1 = c.getTimeInMillis();

            Calendar c2 = Calendar.getInstance();
            c2.setTime(myFmt.parse(time2));
            long date2 = c2.getTimeInMillis();
            dateTime = date2 - date1;
            return dateTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    // 利率保留两位小数，要四舍五入
    public static String toFFDoubleForApr3(double num) {
        if (Double.isNaN(num)) {
            return "0.00";
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        BigDecimal bd = new BigDecimal(num);
        num = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return toDeciDouble(nf.format(num * 1000));
    }

    //利率保留两位小数，要四舍五入
    public static String toFFDoubleForApr(double num) {
        if (Double.isNaN(num)) {
            return "0.00";
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        BigDecimal bd = new BigDecimal(num);
        num = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return toDeciDouble(nf.format(num * 100));
    }

    public static String toFFDouble(double num) {
        if (Double.isNaN(num)) {
            return "0.00";
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        BigDecimal bd = new BigDecimal(num);
        num = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return toDeciDouble(nf.format(num * 100));
    }

    // 将输入的数字转为0.00格式的double
    public static Double toForDouble2(String num) {
        if (Tool.isBlank(num)) {
            return 0.00;
        }
        return Double.parseDouble((new DecimalFormat("0.00")).format(Double
                .parseDouble(num)));
    }



    /**
     * java生成随机数字和字母组合
     *
     * @param length [生成随机数的长度]
     * @return
     */
    public static String getCharAndNumr(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }


    private static long lastClickTime;

    /**
     * 判断是否连续点击
     *
     *  对于 startActivity 设置 singletop 无效果
     *  则这样 防止 连续点击跳重复界面
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime <1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 获取assets下面的json文件
     *
     * @param context
     * @param fileName 文件名，需要带上文件夹名称（类似shopping_mall/main.json）
     * @return
     */
    public static String getJsonFromAssets(Context context, String fileName) {
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            inputReader = new InputStreamReader(context.getAssets().open(fileName));
            bufReader = new BufferedReader(inputReader);
            String line;
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line.trim();
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputReader != null) {
                    inputReader.close();
                }
                if (bufReader != null) {
                    bufReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 通过年月日返回当年
     * <p/>
     * 2016-05-04
     *
     * @return 2016
     */
    public static String toYear(String time) {
        if (isBlank(time)) {
            return time;
        }
        return (String) time.subSequence(0, 4);
    }

    /**
     * 通过年月日返回当前月
     * <p/>
     * 2016-05-04
     *
     * @return 05
     */
    public static String toMonth(String time) {
        if (isBlank(time)) {
            return time;
        }
        return (String) time.subSequence(time.length() - 5, time.length() - 3);
    }

    /**
     * 通过年月日返回当前时间
     * <p/>
     * 2016-05-04
     *
     * @return 04
     */
    public static String toDay(String time) {
        if (isBlank(time)) {
            return time;
        }

        String data=(String) time.subSequence(time.length() - 2, time.length());
        String day;
//        String s = “abcat”；
//
//        String s1 = s.replace（‘a’，‘1’）；

        day=data.replace("-","");
        return day;
    }

    /**
     * 字符转换
     * <p/>
     * 05
     *
     * @return 5
     */
    public static String to2num(String num) {
        if (num.contains("0")) {
            return num.replace("0", "");
        }
        return num;
    }
    /**
     * 字符转换
     * <p/>
     * 5
     *
     * @return 05
     */
    public static String toDays(String num) {
        if(num.length()<2){
            num="0"+num;
        }
        return num;
    }

    /**
     * 价格区间格式化
     *
     * @param lowPrice    最低价
     * @param heightPrice 最高价
     * @return String
     */
    public static String formatPriceRange(String lowPrice, String heightPrice) {
        if (!TextUtils.isEmpty(lowPrice) && TextUtils.isEmpty(heightPrice)) {
            return ">" + lowPrice;
        } else if (TextUtils.isEmpty(lowPrice) && !TextUtils.isEmpty(heightPrice)) {
            return "<" + heightPrice;
        } else if (!TextUtils.isEmpty(lowPrice) && !TextUtils.isEmpty(heightPrice)) {
            return lowPrice + "-" + heightPrice;
        } else {
            return "";
        }
    }


    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /*
     * 与当前时间比较返回
     *
     *
     * */
    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年

    /**
     * 返回文字描述的日期
     * <p/>
     * date
     *
     * @return
     */
    public static String getTimeFormatText(String time) {
        Date date = new Date();

        String dateStr = new SimpleDateFormat(time).format(date);

        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    public static boolean toInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean toDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 四舍五入.
     *
     * @param number  原数
     * @param decimal 保留几位小数
     * @return 四舍五入后的值
     */
    public static BigDecimal round(double number, int decimal) {
        return new BigDecimal(number).setScale(decimal, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 金额验证
     */
    public static boolean isAmountText(String str) {
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match = pattern.matcher(str);
        return match.matches();
    }

    /**
     * */

    public static String getTime(String day) {
//      06-16
        //6月16日
        String[] arr=day.split("-");
        String time;
        time=arr[0]+"月"+arr[1]+"日";

        return time;

    }

    public static String getData(String data) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date=sdf.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormat.format(date);
    }

    /**
     * 格式化日期格式
     * @param data
     * @return
     */
    public static String getCouponData(String data) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd");
        Date date=null;
        try {
            date=sdf.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormat.format(date);
    }


    public static float getDisplay(){
        DisplayMetrics dm = new DisplayMetrics();
        float width=dm.widthPixels*dm.density;
        float height=dm.heightPixels*dm.density;
        return width;

    }

    /**
     * 根据图片的url路径获得Bitmap对象
    * @param url
    * @return
     */
    public static Bitmap returnBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }
    /**
     * 时间大小比较
     * */
    public static boolean TimeCompare(String time1,String time2){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date d1=sdf.parse(time1);
            Date d2=sdf.parse(time2);
            if(d1.getTime() - d2.getTime()>0){
             return true;
            }else{
                return false;
            }
        } catch (ParseException e) {
            return false;

        }

    }

    public static boolean isMobileNOS(String mobiles) {

        String telRegex = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)){
            return false;
        }
        else return mobiles.matches(telRegex);
    }

//    public static boolean toInteger(String str) {
//        try {
//            Integer.parseInt(str);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
    /**
     * Log的长度为4k,当打印的数据大于4k则不会打印，该方法可使数据分多次打印
     * @param tag
     * @param msg
     */
    public static void showLog(String tag,String msg) {
        msg = msg.trim();
        int index = 0;
        int maxLength =  4000;
        String finalString;
        while (index < msg.length()) {
            if (msg.length() <= index + maxLength) {
                finalString = msg.substring(index);
            } else {
                finalString = msg.substring(index, maxLength);
            }
            index += maxLength;
            Log.e(tag, finalString.trim());
        }
    }
    // 完整的判断中文汉字和符号
    public static boolean IsChinese(String str){

        char[] chars=str.toCharArray();
        boolean isGB2312=false;
        for(int i=0;i<chars.length;i++){
            byte[] bytes=(""+chars[i]).getBytes();
            if(bytes.length==2){
                int[] ints=new int[2];
                ints[0]=bytes[0]& 0xff;
                ints[1]=bytes[1]& 0xff;
                if(ints[0]>=0x81 && ints[0]<=0xFE && ints[1]>=0x40 && ints[1]<=0xFE){
                    isGB2312=true;
                    break;
                }
            }
        }
        return isGB2312;
    }
}
