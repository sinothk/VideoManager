package com.sinothk.manager.video;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kk.taurus.playerbase.assist.InterEvent;
import com.kk.taurus.playerbase.assist.OnVideoViewEventHandler;
import com.kk.taurus.playerbase.entity.DataSource;
import com.kk.taurus.playerbase.event.OnPlayerEventListener;
import com.kk.taurus.playerbase.player.IPlayer;
import com.kk.taurus.playerbase.receiver.ReceiverGroup;
import com.kk.taurus.playerbase.widget.BaseVideoView;

/**
 * <pre>
 *  创建:  梁玉涛 2019/3/30 on 16:55
 *  项目:  VideoManagerLib
 *  描述: https://github.com/jiajunhui/PlayerBase
 *  更新:
 * <pre>
 */
public abstract class VideoPlayerActivity extends AppCompatActivity implements OnPlayerEventListener {

    public abstract DataSource getDataSource();

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    protected void hideBottomUIMenu2() {
        //隐藏虚拟按键
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View v = getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    /**
     * 显示虚拟按键，并且全屏
     */
    protected void showBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    /**
     * 显示菜单栏
     * 如果底部的bar 隐藏就显示
     */
    protected void showBottomUIMenu2() {
//        /显示虚拟按键
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            //低版本sdk
            View v = getWindow().getDecorView();
            v.setSystemUiVisibility(View.VISIBLE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
    // =======================================================

    private BaseVideoView mVideoView;
    private ReceiverGroup mReceiverGroup;

    private boolean userPause;
    private boolean isLandscape;
    private int margin;

    private boolean hasStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mVideoView = findViewById(R.id.baseVideoView);

        margin = PUtil.dip2px(this, 2);

        updateVideo(false);

        mReceiverGroup = ReceiverGroupManager.get().getReceiverGroup(this);
        mReceiverGroup.getGroupValue().putBoolean(DataInter.Key.KEY_CONTROLLER_TOP_ENABLE, true);
        mVideoView.setReceiverGroup(mReceiverGroup);
        mVideoView.setEventHandler(onVideoViewEventHandler);
        mVideoView.setOnPlayerEventListener(this);
    }

    private int getLayoutId() {
        return R.layout.activity_video_manager_main;
    }

    private void initPlay() {
        if (!hasStart) {
//            DataSource dataSource = new DataSource(DataUtils.VIDEO_URL_08);//DataUtils.VIDEO_URL_08
//            dataSource.setTitle("音乐和艺术如何改变世界");

            mVideoView.setDataSource(getDataSource());
            mVideoView.start();
            hasStart = true;
        }
    }

    @Override
    public void onPlayerEvent(int eventCode, Bundle bundle) {

        if (eventCode == OnPlayerEventListener.PLAYER_EVENT_ON_VIDEO_RENDER_START) {
            //                mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//                SettingAdapter mAdapter = new SettingAdapter(this, SettingItem.initSettingList());
//                mAdapter.setOnItemClickListener(this);
//                mRecycler.setAdapter(mAdapter);
        }

//        switch (eventCode) {
//            case OnPlayerEventListener.PLAYER_EVENT_ON_VIDEO_RENDER_START:
//
//                break;
//        }
    }

    private OnVideoViewEventHandler onVideoViewEventHandler = new OnVideoViewEventHandler() {
        @Override
        public void onAssistHandle(BaseVideoView assist, int eventCode, Bundle bundle) {
            super.onAssistHandle(assist, eventCode, bundle);
            switch (eventCode) {
                case InterEvent.CODE_REQUEST_PAUSE:
                    userPause = true;
                    break;
                case DataInter.Event.EVENT_CODE_REQUEST_BACK:
                    if (isLandscape) {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    } else {
                        finish();
                    }
                    break;
                case DataInter.Event.EVENT_CODE_REQUEST_TOGGLE_SCREEN:
                    setRequestedOrientation(isLandscape ?
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT :
                            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    break;
                case DataInter.Event.EVENT_CODE_ERROR_SHOW:
                    mVideoView.stop();
                    break;
            }
        }

        @Override
        public void requestRetry(BaseVideoView videoView, Bundle bundle) {
            if (PUtil.isTopActivity(VideoPlayerActivity.this)) {
                super.requestRetry(videoView, bundle);
            }
        }
    };

    private void replay() {
        mVideoView.setDataSource(new DataSource(DataUtils.VIDEO_URL_01));
        mVideoView.start();
    }

//    @Override
//    public void onItemClick(SettingAdapter.SettingItemHolder holder, SettingItem item, int position) {
//        int code = item.getCode();
//        switch (code) {
//            case SettingItem.CODE_RENDER_SURFACE_VIEW:
//                mVideoView.setRenderType(IRender.RENDER_TYPE_SURFACE_VIEW);
//                break;
//            case SettingItem.CODE_RENDER_TEXTURE_VIEW:
//                mVideoView.setRenderType(IRender.RENDER_TYPE_TEXTURE_VIEW);
//                break;
//            case SettingItem.CODE_STYLE_ROUND_RECT:
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    mVideoView.setRoundRectShape(PUtil.dip2px(this, 25));
//                } else {
//                    Toast.makeText(this, "not support", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            case SettingItem.CODE_STYLE_OVAL_RECT:
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    mVideoView.setOvalRectShape();
//                } else {
//                    Toast.makeText(this, "not support", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            case SettingItem.CODE_STYLE_RESET:
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    mVideoView.clearShapeStyle();
//                } else {
//                    Toast.makeText(this, "not support", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            case SettingItem.CODE_ASPECT_16_9:
//                mVideoView.setAspectRatio(AspectRatio.AspectRatio_16_9);
//                break;
//            case SettingItem.CODE_ASPECT_4_3:
//                mVideoView.setAspectRatio(AspectRatio.AspectRatio_4_3);
//                break;
//            case SettingItem.CODE_ASPECT_FILL:
//                mVideoView.setAspectRatio(AspectRatio.AspectRatio_FILL_PARENT);
//                break;
//            case SettingItem.CODE_ASPECT_MATCH:
//                mVideoView.setAspectRatio(AspectRatio.AspectRatio_MATCH_PARENT);
//                break;
//            case SettingItem.CODE_ASPECT_FIT:
//                mVideoView.setAspectRatio(AspectRatio.AspectRatio_FIT_PARENT);
//                break;
//            case SettingItem.CODE_ASPECT_ORIGIN:
//                mVideoView.setAspectRatio(AspectRatio.AspectRatio_ORIGIN);
//                break;
//            case SettingItem.CODE_PLAYER_MEDIA_PLAYER:
//                if (mVideoView.switchDecoder(PlayerConfig.DEFAULT_PLAN_ID)) {
//                    replay();
//                }
//                break;
//            case SettingItem.CODE_PLAYER_IJK_PLAYER:
//                if (mVideoView.switchDecoder(App.PLAN_ID_IJK)) {
//                    replay();
//                }
//                break;
//            case SettingItem.CODE_PLAYER_EXO_PLAYER:
//                if (mVideoView.switchDecoder(App.PLAN_ID_EXO)) {
//                    replay();
//                }
//                break;
//            case SettingItem.CODE_SPEED_0_5:
//                mVideoView.setSpeed(0.5f);
//                break;
//            case SettingItem.CODE_SPEED_2:
//                mVideoView.setSpeed(2f);
//                break;
//            case SettingItem.CODE_SPEED_1:
//                mVideoView.setSpeed(1f);
//                break;
//            case SettingItem.CODE_VOLUME_SILENT:
//                mVideoView.setVolume(0f, 0f);
//                break;
//            case SettingItem.CODE_VOLUME_RESET:
//                mVideoView.setVolume(1f, 1f);
//                break;
//            case SettingItem.CODE_CONTROLLER_REMOVE:
//                mReceiverGroup.removeReceiver(DataInter.ReceiverKey.KEY_CONTROLLER_COVER);
//                Toast.makeText(this, "已移除", Toast.LENGTH_SHORT).show();
//                break;
//            case SettingItem.CODE_CONTROLLER_RESET:
//                IReceiver receiver = mReceiverGroup.getReceiver(DataInter.ReceiverKey.KEY_CONTROLLER_COVER);
//                if (receiver == null) {
//                    mReceiverGroup.addReceiver(DataInter.ReceiverKey.KEY_CONTROLLER_COVER, new ControllerCover(this));
//                    Toast.makeText(this, "已添加", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            case SettingItem.CODE_TEST_UPDATE_RENDER:
//                mVideoView.updateRender();
//                break;
//        }
//    }

    private void updateVideo(boolean landscape) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mVideoView.getLayoutParams();
        if (landscape) {
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.setMargins(0, 0, 0, 0);

            hideBottomUIMenu2();
        } else {
            layoutParams.width = PUtil.getScreenW(this);// - (margin * 2)
            layoutParams.height = layoutParams.width * 9 / 16;

            layoutParams.setMargins(0, 0, 0, 0);//(margin, margin, margin, margin)

            showBottomUIMenu2();
        }
        mVideoView.setLayoutParams(layoutParams);
    }

    @Override
    public void onBackPressed() {
        if (isLandscape) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isLandscape = true;
            updateVideo(true);
        } else {
            isLandscape = false;
            updateVideo(false);
        }
        mReceiverGroup.getGroupValue().putBoolean(DataInter.Key.KEY_IS_LANDSCAPE, isLandscape);
    }

    @Override
    protected void onPause() {
        super.onPause();
        int state = mVideoView.getState();
        if (state == IPlayer.STATE_PLAYBACK_COMPLETE)
            return;
        if (mVideoView.isInPlaybackState()) {
            mVideoView.pause();
        } else {
            mVideoView.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int state = mVideoView.getState();
        if (state == IPlayer.STATE_PLAYBACK_COMPLETE)
            return;
        if (mVideoView.isInPlaybackState()) {
            if (!userPause)
                mVideoView.resume();
        } else {
            mVideoView.rePlay(0);
        }
        initPlay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
    }

}
