# VideoManager

    VideoManager（https://github.com/jiajunhui/PlayerBase）

## Step 1. Add the JitPack repository to your build file
    
    allprojects {
      repositories {
        ...
        maven { url 'https://jitpack.io' }
      }
    }

## Step 2. Add the dependency
	
  	dependencies {
	        implementation 'com.github.sinothk:VideoManager:1.0.0425'
	}


# 使用
 	a. 继承：VideoApplication
	
	b. 
	import android.app.Activity;
	import android.content.Intent;
	import android.os.Bundle;
	import android.text.TextUtils;
	import android.widget.Toast;

	import com.kk.taurus.playerbase.entity.DataSource;
	import com.sinothk.manager.video.VideoPlayerActivity;
	import com.sinothk.manager.video.bean.VideoSourceEntity;

	public class VideoManagerPlayActivity extends VideoPlayerActivity {

	    DataSource dataSource = new DataSource();
	    VideoSourceEntity videoSource;

	    public static void start(Activity mActivity, VideoSourceEntity videoSource) {
		Intent intent = new Intent(mActivity, VideoManagerPlayActivity.class);
	//        DataSource dataSource = new DataSource(DataUtils.VIDEO_URL_08);//DataUtils.VIDEO_URL_08
	//        dataSource.setTitle("音乐和艺术如何改变世界");

		Bundle bundle = new Bundle();
		bundle.putSerializable("DataSource", videoSource);
		intent.putExtras(bundle);

		mActivity.startActivity(intent);
	    }

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
		videoSource = (VideoSourceEntity) getIntent().getSerializableExtra("DataSource");
		super.onCreate(savedInstanceState);
	    }

	    @Override
	    public DataSource getDataSource() {
		if (videoSource == null || TextUtils.isEmpty(videoSource.getTitle()) || TextUtils.isEmpty(videoSource.getUrl())) {
		    finish();
		    Toast.makeText(this, "标题、地址不能为空", Toast.LENGTH_SHORT).show();
		    return null;
		}

		dataSource.setTitle(videoSource.getTitle());
		dataSource.setData(videoSource.getUrl());
		return dataSource;
	    }
	}

