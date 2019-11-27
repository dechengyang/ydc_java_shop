package com.ydc.mvp;


import com.ydc.networkservice.bean.Feed;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * @Description 数据仓库中心
 * @Author ydc
 * @CreateDate 2016/10/30
 * @Version 1.0
 */
public class DataRepository implements IDataSource {
    private static DataRepository INSTANCE = null;
    private IDataSource mRemoteDataSource;//远程数据源
    private IDataSource mLocalDataSource;//本地数据源
    Map<String, Feed> mCaches;
    boolean mCacheIsDirty = false;//緩存是否有效，默认无效

//    private DataRepository(IDataSource remoteDataSource,IDataSource localDataSource){
//        mRemoteDataSource = remoteDataSource;//远程数据源
//        mLocalDataSource = localDataSource;//本地数据源
//    }

    /**
     * @description 单例数据仓库（引入Dar）
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public synchronized static DataRepository getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new DataRepository();
        }
        return INSTANCE;
    }
    /**
     * @description 单例数据仓库（引入Dar）
     * @author ydc
     * @createDate
     * @version 1.0
     */
//    public static DataRepository getInstance(IDataSource remoteDataSource,IDataSource localDataSource) {
//        if(INSTANCE == null){
//            INSTANCE = new DataRepository(remoteDataSource,localDataSource);
//        }
//        return INSTANCE;
//    }

    /**
     * @description 清理数据仓库
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public static void destroyInstance(){
        INSTANCE = null;
    }


    @Override
    public Observable<List<Feed>> getFeeds() {
        return null;
    }

    @Override
    public Observable<Feed> getFeed(Feed feed) {
        return null;
    }

    @Override
    public void onInvalidData() {

    }

    @Override
    public void saveData(String id) {

    }

    @Override
    public void delData(String id) {

    }

    @Override
    public void delAllData() {

    }

    @Override
    public void refreshData() {

    }
}
