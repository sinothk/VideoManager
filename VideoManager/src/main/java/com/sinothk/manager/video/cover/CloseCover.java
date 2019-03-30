package com.sinothk.manager.video.cover;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kk.taurus.playerbase.receiver.BaseCover;
import com.sinothk.manager.video.DataInter;
import com.sinothk.manager.video.R;

public class CloseCover extends BaseCover implements View.OnClickListener {

    ImageView mCloseIcon;

    public CloseCover(Context context) {
        super(context);
    }

    @Override
    public void onReceiverBind() {
        super.onReceiverBind();

        mCloseIcon = findViewById(R.id.iv_close);
        mCloseIcon.setOnClickListener(this);

    }

    @Override
    public void onReceiverUnBind() {
        super.onReceiverUnBind();
    }

    @Override
    public View onCreateCoverView(Context context) {
        return View.inflate(context, R.layout.layout_close_cover, null);
    }

    @Override
    public void onPlayerEvent(int eventCode, Bundle bundle) {

    }

    @Override
    public void onErrorEvent(int eventCode, Bundle bundle) {

    }

    @Override
    public void onReceiverEvent(int eventCode, Bundle bundle) {

    }

    @Override
    public int getCoverLevel() {
        return levelMedium(10);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_close) {
            notifyReceiverEvent(DataInter.Event.EVENT_CODE_REQUEST_CLOSE, null);
        }
    }
}
