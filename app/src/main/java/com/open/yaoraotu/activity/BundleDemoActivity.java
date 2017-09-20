package com.open.yaoraotu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.open.yaoraotu.R;
/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fengguangjing
 * @createTime: 17/8/31
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class BundleDemoActivity extends AppCompatActivity {
    Button btnBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundle_demo);
        btnBundle = (Button) findViewById(R.id.btnBundle);
        btnBundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BundleDemoActivity.this,"单bundle调试"+
                        "1、安装设备且连接电脑成功\n\n"+
                                "2、修改一个bundle工程的代码或者自由（设置生效的标识）\n\n"+
                                "3、bundle工程的目录下执行 ../gradlew clean assemblePatchDebug,然后等应用重启或者应用关闭后点击重启",Toast.LENGTH_LONG).show();

            }
        });
    }
}
