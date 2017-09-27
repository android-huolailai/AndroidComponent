package com.guiying.module.common.base;

import android.app.Application;

import com.guiying.module.common.utils.Utils;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * 要想使用BaseApplication，必须在组件中实现自己的Application，并且继承BaseApplication；
 * 组件中实现的Application必须在debug包中的AndroidManifest.xml中注册，否则无法使用；
 * 组件的Application需置于java/debug文件夹中，不得放于主代码；
 * 组件中获取Context的方法必须为:Utils.getContext()，不允许其他写法；
 * BaseApplication主要用来管理全局Activity;
 *
 * @author 2016/12/2 17:02
 * @version V1.0.0
 * @name BaseApplication
 */
public class BaseApplication extends Application {

    public static final String ROOT_PACKAGE = "com.guiying.module";

    private static BaseApplication sInstance;

    private List<ApplicationDelegate> delegateList;


    public static BaseApplication getIns() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Utils.init(this);
        delegateList = ClassUtils.getObjectsWithInterface(this, ApplicationDelegate.class, ROOT_PACKAGE);
        for (ApplicationDelegate delegate : delegateList) {
            delegate.onCreate();
        }
        Logger.init("pattern").logLevel(LogLevel.FULL);
    }


}