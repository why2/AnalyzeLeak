package com.bsd.analyze;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bsd.analyze.binding.DataBindingActivity;
import com.squareup.leakcanary.RefWatcher;

import java.io.IOException;
import java.io.InputStream;

/**
 * 制造内存泄露
 */
public class MainActivity extends AppCompatActivity {
    private static Inner inner;//非静态内部类创建静态实例


    //Handler使用不当
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //...
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inner = new Inner();
        initOpenAssets();
        initHandler();
        initThread();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }


    /**
     * 非静态内部类创建静态实例
     */
    class Inner {

    }


    /**
     * Handler使用不当
     * 初始化
     */
    private void initHandler() {
        Message message = Message.obtain();
        handler.sendMessage(message);
    }


    /**
     * 线程任务为结束
     */
    private void initThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(10000);
            }
        }).start();
    }


    /**
     * 资源没有关闭
     */
    public void initOpenAssets() {
        try {
            InputStream inputStream = getAssets().open("alibaba_java_v1.2.0.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void goDataBinding(View view) {
        startActivity(new Intent(this,DataBindingActivity.class));
    }
}
