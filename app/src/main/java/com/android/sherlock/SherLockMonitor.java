package com.android.sherlock;

import android.app.AndroidAppHelper;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.widget.Toast;

import java.net.NetworkInterface;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * 示例：
 * <pre>
 *     XposedHelpers.findAndHookMethod(
 *     "需要hook的方法所在类的完整类名",
 *     lpparam.classLoader,      // 类加载器
 *     "需要hook的方法名",
 *     Class<?>,  // 参数类型
 *     new XC_MethodHook() {
 *
 *         @Override
 *         protected void beforeHookedMethod(MethodHookParam param) {
 *             XposedBridge.log("调用XXX()获取了XXX");
 *         }
 *
 *         @Override
 *         protected void afterHookedMethod(MethodHookParam param) throws Throwable {
 *             XposedBridge.log(getMethodStack());
 *             super.afterHookedMethod(param);
 *         }
 *     }
 * );
 * </pre>
 */
public class SherLockMonitor implements IXposedHookLoadPackage {

    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {

        if (lpparam == null) {
            return;
        }

        final ApplicationInfo appInfo = lpparam.appInfo;


        XposedHelpers.findAndHookMethod(
                android.telephony.TelephonyManager.class.getName(),
                lpparam.classLoader,
                "getDeviceId",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log(lpparam.packageName +"调用getDeviceId()获取了imei");
                        showToast(appInfo, lpparam.packageName +"调用getDeviceId()获取了imei");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        // hook获取设备信息方法
        XposedHelpers.findAndHookMethod(
                android.telephony.TelephonyManager.class.getName(),
                lpparam.classLoader,
                "getDeviceId",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log(lpparam.packageName +"调用getDeviceId(int)获取了imei");
                        showToast(appInfo, lpparam.packageName +"调用getDeviceId(int)获取了imei");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
          android.telephony.TelephonyManager.class.getName(),
          lpparam.classLoader,
          "getImei",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log(lpparam.packageName +"调用getImei()获取了imei");
                        showToast(appInfo, lpparam.packageName +"调用getImei()获取了imei");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }

        );


        XposedHelpers.findAndHookMethod(
                android.telephony.TelephonyManager.class.getName(),
                lpparam.classLoader,
                "getImei",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log(lpparam.packageName +"调用getImei(int)获取了imei");
                        showToast(appInfo, lpparam.packageName +"调用getImei(int)获取了imei");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }

        );

        // hook imsi获取方法
        XposedHelpers.findAndHookMethod(
                android.telephony.TelephonyManager.class.getName(),
                lpparam.classLoader,
                "getSubscriberId",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log(lpparam.packageName +"调用getSubscriberId获取了imsi");
                        showToast(appInfo, lpparam.packageName +"调用getSubscriberId获取了imsi");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );
        // hook低版本系统获取mac地方方法
        XposedHelpers.findAndHookMethod(
                android.net.wifi.WifiInfo.class.getName(),
                lpparam.classLoader,
                "getMacAddress",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log(lpparam.packageName +"调用getMacAddress()获取了mac地址");
                        showToast(appInfo, lpparam.packageName +"调用getMacAddress()获取了mac地址");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );
        // hook获取mac地址方法
        XposedHelpers.findAndHookMethod(
                java.net.NetworkInterface.class.getName(),
                lpparam.classLoader,
                "getHardwareAddress",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log(lpparam.packageName +"调用getHardwareAddress()获取了mac地址");
                        showToast(appInfo, lpparam.packageName +"调用getNetworkInterfaces获取了网络信息");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        // hook获取剪切板方法
        XposedHelpers.findAndHookMethod(
                android.content.ClipboardManager.class.getName(),
                lpparam.classLoader,
                "getPrimaryClip",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log(lpparam.packageName +"调用getPrimaryClip()获取了剪切板");
                        showToast(appInfo, lpparam.packageName +"调用getPrimaryClip获取了剪切板内容");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );


        XposedHelpers.findAndHookMethod(
                android.content.ClipboardManager.class.getName(),
                lpparam.classLoader,
                "getPrimaryClip",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log(lpparam.packageName +"调用getPrimaryClip(int)获取了剪切板");
                        showToast(appInfo, lpparam.packageName +"调用getPrimaryClip(int)获取了剪切板内容");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                Settings.Secure.class.getName(),
                lpparam.classLoader,
                "getString",
                ContentResolver.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log( lpparam.packageName +"调用Settings.Secure.getstring获取了" + param.args[1]);
                        showToast(appInfo,  lpparam.packageName +"调用Settings.Secure.getstring获取了" + param.args[1]);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        // hook定位方法
        XposedHelpers.findAndHookMethod(
                LocationManager.class.getName(),
                lpparam.classLoader,
                "getLastKnownLocation",
                String.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log(lpparam.packageName +"调用getLastKnownLocation获取了GPS地址");
                        showToast(appInfo, lpparam.packageName +"调用getLastKnownLocation获取了GPS地址");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                LocationManager.class.getName(),
                lpparam.classLoader,
                "requestLocationUpdates",
                String.class,
                long.class,
                float.class,
                LocationListener.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log(lpparam.packageName +"调用requestLocationUpdates获取了GPS地址");
                        showToast(appInfo, lpparam.packageName +"调用requestLocationUpdates获取了GPS地址");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );


        XposedHelpers.findAndHookMethod(
                "android.app.ActivityManager",
                lpparam.classLoader,
                "getRunningAppProcesses",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param)  {
                        XposedBridge.log(lpparam.packageName +"调用getRunningAppProcesses()获取了正在运行的App");
                        showToast(appInfo, lpparam.packageName +"调用getRunningAppProcesses()获取了正在运行的App");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );

        XposedHelpers.findAndHookMethod(
                "android.app.ApplicationPackageManager",
                lpparam.classLoader,
                "getInstalledPackages",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param)  {
                        XposedBridge.log(lpparam.packageName +"调用getInstalledPackages()获取了当前用户安装的所有软件包的列表");
                        showToast(appInfo, lpparam.packageName +"调用getInstalledPackages()获取了当前用户安装的所有软件包的列表");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );


        XposedHelpers.findAndHookMethod(
                "android.app.ApplicationPackageManager",
                lpparam.classLoader,
                "getInstalledApplications",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param)  {
                        XposedBridge.log(lpparam.packageName +"调用getInstalledApplications()获取了当前用户安装的所有应用程序包的列表");
                        showToast(appInfo, lpparam.packageName +"调用getInstalledApplications()获取了当前用户安装的所有应用程序包的列表");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );


        // Hook 获取网络接入标识|IP地址等信息
        XposedHelpers.findAndHookMethod(
                NetworkInterface.class.getName(),
                lpparam.classLoader,
                "getNetworkInterfaces",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param)  {
                        XposedBridge.log(lpparam.packageName +"调用getNetworkInterfaces获取了网络信息");
                        showToast(appInfo, lpparam.packageName +"调用getNetworkInterfaces获取了网络信息");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );
        XposedHelpers.findAndHookMethod(
                ConnectivityManager.class.getName(),
                lpparam.classLoader,
                "getActiveNetworkInfo",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        XposedBridge.log(lpparam.packageName +"调用getActiveNetworkInfo获取了网络信息");
                        showToast(appInfo, lpparam.packageName +"调用getActiveNetworkInfo获取了网络信息");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log(getMethodStack());
                        super.afterHookedMethod(param);
                    }
                }
        );
    }

    private void showToast(ApplicationInfo appInfo, String msg) {
        try {
            Context context = AndroidAppHelper.currentApplication().getApplicationContext();
            String label = appInfo.loadLabel(context.getPackageManager()).toString();
            Toast.makeText(context, "[" + label + "]" + msg, Toast.LENGTH_SHORT).show();
        } catch (Throwable ignore) {

        }
    }

    private String getMethodStack() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        StringBuilder stringBuilder = new StringBuilder();

        for (StackTraceElement temp : stackTraceElements) {
            stringBuilder.append(temp.toString()).append("\n");
        }

        return stringBuilder.toString();
    }

}
