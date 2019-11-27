package com.ydc.mvp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rx.Subscription;
import rx.exceptions.Exceptions;

/**
 * @Description Subscription集合，方便后期引入darger
 * @Author Andy.fang
 * @CreateDate 2016/10/28
 * @Version 1.0
 */
public final class SubscriptionList implements Subscription {
    private Set<Subscription> subscriptions;
    private volatile boolean unsubscribed;

    public SubscriptionList() {
    }

    public SubscriptionList(final Subscription... subscriptions) {
        this.subscriptions = new HashSet<Subscription>(Arrays.asList(subscriptions));
    }

    /**
     * @description 取消本身和所有内部订阅
     * @author Andy.fang
     * @createDate
     * @version 1.0
     */
    @Override
    public void unsubscribe() {
        if (!unsubscribed) {
            Collection<Subscription> unsubscribe = null;
            synchronized (this) {
                if (unsubscribed) {
                    return;
                }
                unsubscribed = true;
                unsubscribe = subscriptions;
                subscriptions = null;
            }
            unsubscribeFromAll(unsubscribe);//只执行一次
        }
    }

    @Override
    public boolean isUnsubscribed() {
        return unsubscribed;
    }

    /**
     * @description 单个订阅加入集合
     * @author Andy.fang
     * @createDate
     * @version 1.0
     */
    public void add(final Subscription s) {
        if (s.isUnsubscribed()) {
            return;
        }
        if (!unsubscribed) {
            synchronized (this) {
                if (!unsubscribed) {
                    if (subscriptions == null) {
                        subscriptions = new HashSet<Subscription>(4);
                    }
                    subscriptions.add(s);
                    return;
                }
            }
        }
        s.unsubscribe();
    }
    
    /**
     * @description 把订阅添加到集合
     * @author Andy.fang
     * @createDate
     * @version 1.0
     */
    public void addAll(final Subscription... subscriptions) {
        if (!unsubscribed) {
            synchronized (this) {
                if (!unsubscribed) {
                    if (this.subscriptions == null) {
                        this.subscriptions = new HashSet<Subscription>(subscriptions.length);
                    }

                    for (Subscription s : subscriptions) {
                        if (!s.isUnsubscribed()) {
                            this.subscriptions.add(s);
                        }
                    }
                    return;
                }
            }
        }

        for (Subscription s : subscriptions) {
            s.unsubscribe();
        }
    }
    
    /**
     * @description 删除一个订阅
     * @author Andy.fang
     * @createDate
     * @version 1.0
     */
    public void remove(final Subscription s) {
        if (!unsubscribed) {
            boolean unsubscribe = false;
            synchronized (this) {
                if (unsubscribed || subscriptions == null) {
                    return;
                }
                unsubscribe = subscriptions.remove(s);
            }
            if (unsubscribe) {
                s.unsubscribe();
            }
        }
    }

    /**
     * @description 取消所有订阅
     * @author Andy.fang
     * @createDate
     * @version 1.0
     */
    public void clear() {
        if (!unsubscribed) {
            Collection<Subscription> unsubscribe = null;
            synchronized (this) {
                if (unsubscribed || subscriptions == null) {
                    return;
                } else {
                    unsubscribe = subscriptions;
                    subscriptions = null;
                }
            }
            unsubscribeFromAll(unsubscribe);
        }
    }


    /**
     * @description 
     * @author Andy.fang
     * @createDate
     * @version 1.0
     */
    private static void unsubscribeFromAll(Collection<Subscription> subscriptions) {
        if (subscriptions == null) {
            return;
        }
        List<Throwable> es = null;
        for (Subscription s : subscriptions) {
            try {
                s.unsubscribe();
            } catch (Throwable e) {
                if (es == null) {
                    es = new ArrayList<Throwable>();
                }
                es.add(e);
            }
        }
        Exceptions.throwIfAny(es);
    }

    /**
     * @description 返回true,如果这个组合不是unsubscribed和subscriptions
     * @author Andy.fang
     * @createDate 
     * @version 1.0
     */
    public boolean hasSubscriptions() {
        if (!unsubscribed) {
            synchronized (this) {
                return !unsubscribed && subscriptions != null && !subscriptions.isEmpty();
            }
        }
        return false;
    }
}
