package com.sinothk.manager.video.demo;

import android.app.Application;
import android.os.Environment;

import com.kk.taurus.playerbase.config.PlayerConfig;
import com.kk.taurus.playerbase.config.PlayerLibrary;
import com.kk.taurus.playerbase.entity.DecoderPlan;
import com.kk.taurus.playerbase.log.PLog;
import com.kk.taurus.playerbase.record.PlayRecordManager;

import java.io.File;

/**
 * <pre>
 *  创建:  梁玉涛 2019/3/11 on 15:56
 *  项目:  VideoManagerLib
 *  描述:
 *  更新:
 * <pre>
 */
public class App extends Application {

    public static final int PLAN_ID_IJK = 1;
    public static final int PLAN_ID_EXO = 2;

    private static App instance;

    public static boolean ignoreMobile;

    public static App get(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        PLog.LOG_OPEN = true;

//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
//
//        XLog.init(getApplicationContext(),
//                new XLogConfig()
//                        //loglevel FULL为显示log ，NONE为不显示log
//                        .setLogLevel(LogLevel.FULL)
//                        //文件日志以及崩溃日志文件的目录
//                        .setLogDir(new File(Environment.getExternalStorageDirectory(),"TestXLog"))
//                        //崩溃日志文件标记名称
//                        .setCrashLogTag("CrashLogTag")
//                        //是否云保存文件日志（非crash日志）
//                        .setFileLogAllow(true)
//                        //普通文件日志标记名称
//                        .setNormalLogTag("NormalLogTag")
//                        //日志文件扩展名，默认.txt
//                        .setFileExtensionName(XLogConfig.DEFAULT_FILE_EXTENSION_NAME)
//                        //日志文件定期清理周期（单位毫秒），默认为一周（7*24*60*60*1000）
//                        .setFileClearCycle(XLogConfig.DEFAULT_FILE_CLEAR_CYCLE)
//                        //是否保存崩溃日志
//                        .setSaveCrashLog(true)
//                        //是否为普通日志信息添加消息框
//                        .setMessageTable(true));
//
//        PlayerConfig.addDecoderPlan(new DecoderPlan(PLAN_ID_IJK, IjkPlayer.class.getName(), "IjkPlayer"));
//        PlayerConfig.addDecoderPlan(new DecoderPlan(PLAN_ID_EXO, ExoMediaPlayer.class.getName(), "ExoPlayer"));
//
//        PlayerConfig.setDefaultPlanId(PLAN_ID_IJK);

        //use default NetworkEventProducer.
        PlayerConfig.setUseDefaultNetworkEventProducer(true);

        PlayerConfig.playRecord(true);

        PlayRecordManager.setRecordConfig(
                new PlayRecordManager.RecordConfig.Builder()
                        .setMaxRecordCount(100).build());

        PlayerLibrary.init(this);
    }

}
