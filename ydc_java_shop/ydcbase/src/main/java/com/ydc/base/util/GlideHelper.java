package com.ydc.base.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.ydc.base.R;

import java.io.File;
import java.math.BigDecimal;



/**
 * Glide图片请求工具类
 *
 * @author LZRUI
 */
public class GlideHelper {


    /**
     * 显示圆形图片
     *
     * @param context    上下文
     * @param url        图片路径
     * @param errorImage 加载失败显示的图片
     * @param imageView  显示的View
     */
    @SuppressWarnings("unchecked")
    public static void showCircleImage(Context context, String url, int errorImage, ImageView imageView) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                //  .placeholder(R.mipmap.default_head_portrait) // 加载中显示的图片
                .error(errorImage) // 加载失败显示的图片
                .crossFade() // 显示动画
                .bitmapTransform(new CropCircleTransformation(context))
                .into(imageView);
    }

    /**
     * 显示圆形图片
     *
     * @param context    上下文
     * @param url        图片路径
     * @param errorImage 加载失败显示的图片
     * @param imageView  显示的View
     */
    @SuppressWarnings("unchecked")
    public static void showCircleImageSkipMemoryCache(Context context, String url, int errorImage, ImageView imageView) {
        if (context == null) {
            return;
        }

        // 比如可以将最新一次的更新保存在SharedPreferences中，每次加载时跟Preferences中保存的数值进行对比
        String updateTime = String.valueOf(System.currentTimeMillis()); // 在需要重新获取更新的图片时调用
        Glide.with(context)
                .load(url)
                //.signature(new StringSignature(updateTime))// 在需要重新获取更新的图片时调用，不进行缓存

                //  .placeholder(R.mipmap.default_head_portrait) // 加载中显示的图片
                .error(errorImage) // 加载失败显示的图片
                .dontAnimate()//.crossFade() // 显示动画
                .bitmapTransform(new CropCircleTransformation(context))
                .into(imageView);
    }


    /**
     * 显示圆形图片
     *
     * @param context   上下文
     * @param url       图片路径
     * @param imageView 显示的View
     */
    @SuppressWarnings("unchecked")
    public static void showCircleImages(Context context, String url, ImageView imageView) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                //  .placeholder(R.mipmap.default_head_portrait) // 加载中显示的图片
                .crossFade() // 显示动画
                .bitmapTransform(new CropCircleTransformation(context))
                .into(imageView);
    }


    /**
     * 显示自定义尺寸的圆形图片
     *
     * @param context    上下文
     * @param url        图片路径
     * @param errorImage 加载失败显示的图片
     * @param width      宽度大小
     * @param height     高度大小
     * @param imageView  显示的View
     */
    @SuppressWarnings("unchecked")
    public static void showCircleImageCustomSize(Context context, String url, int errorImage, int width, int height, ImageView imageView) {
        if (context == null) {
            return;
        }
        // 当你用了转换后你就不能使用 .centerCrop() 或 .fitCenter() 了
        Glide.with(context)
                .load(url)
                .error(errorImage)
                .crossFade()
                .bitmapTransform(new CropCircleTransformation(context))
                .override(width, height)
                .into(imageView);
    }

    /**
     * 显示自定义角度图片
     *
     * @param context    上下文
     * @param url        路径
     * @param errorImage 加载失败图片
     * @param round      圆角度数
     * @param imageView  显示的View
     */
    @SuppressWarnings("unchecked")
    public static void showRoundImage(Context context, String url, int errorImage, int round, ImageView imageView) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .placeholder(errorImage)
                .error(errorImage)
                .crossFade()
                .bitmapTransform(new RoundedCornersTransformation(context, round))
                .into(imageView);
    }

    /**
     * 显示自定义角度以及图片尺寸的图片
     *
     * @param context    上下文
     * @param round      圆角度数
     * @param url        路径
     * @param errorImage 加载失败图片
     * @param imageView  显示的View
     */
    @SuppressWarnings("unchecked")
    public static void showRoundImageCustomSize(Context context, int round, String url, int errorImage, int width, int height, ImageView imageView) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .error(errorImage)
                .crossFade()
                .bitmapTransform(new RoundedCornersTransformation(context, round))
                .override(width, height)
                .into(imageView);
    }

    /**
     * 显示自定义角度图片
     *
     * @param context    上下文
     * @param url        路径
     * @param errorImage 加载失败图片
     * @param imageView  显示的View
     */

    public static void showDefaultImage(Context context, String url, int With, int height, Drawable errorImage, ImageView imageView) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .listener(requestListener)
                .override(With, height)
                .error(errorImage)
                .into(imageView);

    }

    /**
     * 设置错误图片监听
     */
    public static void showErrorImage() {

    }
//    Glide
//            .with( context )
//            .load(UsageExampleListViewAdapter.eatFoodyImages[0])
//    .listener( requestListener )
//    .error( R.drawable.cupcake )
//    .into( imageViewPlaceholder );

    /**
     * 监听事件
     * <p/>
     * onException 如果返回为false 加载错误图片 如果返回为true 不设置默认图片
     */

    public static RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            // todo log exception

//            Log.e("LOG", "lw  log==" + e.toString());
            // important to return false so the error placeholder can be placed

            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            return false;
        }
    };

    /**
     * 显示自定义角度图片
     *
     * @param context    上下文
     * @param url        路径
     * @param errorImage 加载失败图片
     * @param imageView  显示的View
     */
    public static void showImage(Context context, String url, int errorImage, ImageView imageView) {
        if (context == null) {
            return;
        }

        Glide.with(context)
                .load(url)
                .centerCrop()
                .error(errorImage)
                .crossFade()
                .into(imageView);
    }

    /**
     * 显示自定义角度图片
     *
     * @param context      上下文
     * @param url          路径
     * @param imageView    显示的View
     */
    public static void showImageNoError(Context context, String url, ImageView imageView) {
        if (context == null) {
            return;
        }

        Glide.with(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }

    /**
     * 显示自定义角度图片
     *
     * @param context    上下文
     * @param url        路径
     * @param errorImage 加载失败图片
     * @param imageView  显示的View
     */
    public static void showAutoImage(Context context, String url, Drawable errorImage, ImageView imageView) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .listener(requestListener)
                .override(1000, 300)
                .error(errorImage)
                .crossFade()
                .into(imageView);
    }

    public static void showBitmap(Context context, String url, final int with, final int height, Drawable errorImage, final ImageView imageView) {
        Log.e("TAG", "得到返回图片的宽高==" + with + "-高度-=" + height);
        Glide.with(context)
                .load(url)
                .asBitmap()
                .fitCenter()
                .error(errorImage)
                .into(new SimpleTarget<Bitmap>(with, height) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                        bitmap.getWidth();
                        Log.e("TAG", "返回当前bitmap宽度==" + bitmap.getWidth());

                        int mwidth = bitmap.getWidth();
                        int mheight = bitmap.getHeight();

                        int newWidth = mwidth;
                        int newHeight = height;

                        float scaleWidth = ((float) newWidth / mwidth);
                        float scaleHeight = ((float) newHeight) / mheight;

                        // 取得想要缩放的matrix参数

                        Matrix matrix = new Matrix();
                        matrix.postScale(scaleWidth, scaleHeight);
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, mwidth, mheight, matrix,
                                true);

                        imageView.setImageBitmap(bitmap);
                    }

                });

    }

    /**
     * 显示自定义角度图片
     *
     * @param context   上下文
     * @param url       路径
     * @param imageView 显示的View
     */
    public static void showImage(Context context, String url, ImageView imageView, int errorImage) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .fitCenter()
                .crossFade()
                .dontAnimate()
                .placeholder(R.drawable.default_place_holder)
                .error(errorImage)
                .into(imageView);
    }
    /**
     * 显示自定义角度图片
     *
     * @param context   上下文
     * @param url       路径
     * @param imageView 显示的View
     */
    public static void showImage(Context context, String url, ImageView imageView) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .fitCenter()
                .crossFade()
                .placeholder(R.drawable.default_place_holder)
                .error(R.drawable.image_default_white_bg)
                .into(imageView);
    }

    /**
     * 显示自定义角度图片
     *
     * @param context   上下文
     * @param url       路径
     * @param imageView 显示的View
     */
    public static void showImageSkipMemoryCache(Context context, String url, ImageView imageView) {
        if (context == null) {
            return;
        }
        // 比如可以将最新一次的更新保存在SharedPreferences中，每次加载时跟Preferences中保存的数值进行对比
        String updateTime = String.valueOf(System.currentTimeMillis()); // 在需要重新获取更新的图片时调用
        Glide.with(context)
                .load(url)
                //.signature(new StringSignature(updateTime))
                //.fitCenter()
                //.crossFade()
                .dontAnimate()
                .error(R.drawable.image_default_white_bg)
                .into(imageView);
    }

    /**
     * 显示自定义角度图片
     *
     * @param context   上下文
     * @param url       路径
     * @param imageView 显示的View
     */
    public static void showImages(Context context, String url, ImageView imageView) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .fitCenter()
                .crossFade()
                .into(imageView);
    }

    /**
     * 自定义图片显示尺寸
     *
     * @param context    上下文
     * @param url        路径
     * @param errorImage 加载失败图片
     * @param width      宽度
     * @param height     高度
     * @param imageView  显示的view
     */
    public static void showImageCustomSize(Context context, String url, int errorImage, int width, int height, ImageView imageView) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .error(errorImage)
                .crossFade()
                .override(width, height)
                .into(imageView);
    }

    /**
     * 自定义图片显示尺寸  不要加载失败的背景图片
     *
     * @param context   上下文
     * @param url       路径
     * @param width     宽度
     * @param height    高度
     * @param imageView 显示的view
     */
    public static void showImageCustomSizEerrorImage(Context context, String url, int width, int height, ImageView imageView) {
        if (context == null) {
            return;
        }
        //缩略图请求
        DrawableRequestBuilder thumbnailRequest = Glide.with(context).load(url);
        Glide.with(context)
                .load(url)
                .crossFade(1000)//淡入淡出,注意:如果设置了这个,则必须要去掉asBitmap
                .override(width, height)
                .centerCrop()//中心裁剪,缩放填充至整个ImageView
                .skipMemoryCache(true)///跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//保存最终图片
                //  .thumbnail(thumbnailRequest)//设置缩略图
                .into(imageView);
    }

    /**
     * 图片加载的时候显示缩略图
     *
     * @param context      上下文
     * @param thumbnailUrl 缩略图路径
     * @param url          图片路径
     * @param errorImage   错误图片
     * @param imageView    显示的view
     */
    public static void showImageIncludeThumbnail(Context context, String thumbnailUrl, String url, int errorImage, ImageView imageView) {
        if (context == null) {
            return;
        }
        DrawableRequestBuilder<String> thumbnailRequest = Glide
                .with(context)
                .load(thumbnailUrl);

        Glide.with(context)
                .load(url)
                .centerCrop()
                .error(errorImage)
                .crossFade()
                .thumbnail(thumbnailRequest)
                .into(imageView);
    }


    /**
     * ydc 20161114 加载本地图片
     *
     * @param context     上下文
     * @param ResourcesId 路径
     * @param errorImage  加载失败图片
     * @param imageView   显示的View
     */
    public static void showImage(Context context, int ResourcesId, int errorImage, ImageView imageView) {
        if (context == null) {
            return;
        }

        Glide.with(context)
                .load(ResourcesId)
                .centerCrop()
                .error(errorImage)
                .crossFade()
                .into(imageView);
    }

    /**
     * Glide自带清除缓存的功能,分别对应Glide.get(context).clearDiskCache();(清除磁盘缓存)与Glide.get(context).clearMemory();(清除内存缓存)
     * 两个方法.其中clearDiskCache()方法必须运行在子线程,clearMemory()方法必须运行在主线程,这是这两个方法所强制要求的,详见源码.
     * 清除图片磁盘缓存
     */
    public void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context).clearDiskCache();
// BusUtil.getBus().post(new GlideCacheClearSuccessEvent());
                    }
                }).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除图片内存缓存
     */
    public void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除图片所有缓存
     */
    public void clearImageAllCache(Context context) {
        clearImageDiskCache(context);
        clearImageMemoryCache(context);
        String ImageExternalCatchDir = context.getExternalCacheDir() + ExternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR;
        deleteFolderFile(ImageExternalCatchDir, true);
    }

    /**
     * 获取Glide造成的缓存大小
     *
     * @return CacheSize
     */
    public String getCacheSize(Context context) {
        try {
            return getFormatSize(getFolderSize(new File(context.getCacheDir() + "/" + InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取指定文件夹内所有文件大小的和
     *
     * @param file file
     * @return size
     * @throws Exception
     */
    private long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 删除指定目录下的文件，这里用于缓存的删除
     *
     * @param filePath       filePath
     * @param deleteThisPath deleteThisPath
     */
    private void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {
                    File files[] = file.listFiles();
                    for (File file1 : files) {
                        deleteFolderFile(file1.getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {
                        file.delete();
                    } else {
                        if (file.listFiles().length == 0) {
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化单位
     *
     * @param size size
     * @return size
     */
    private static String getFormatSize(double size) {

        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }


}
