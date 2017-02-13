package cn.easec.joyang.edu;

import android.content.res.Configuration;
import org.apache.cordova.Config;
import org.apache.cordova.DroidGap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends DroidGap {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        super.setIntegerProperty("splashscreen",R.drawable.loading);
//        super.loadUrl("http://192.168.1.113:8084/Web/index.jsp",5000);
//        super.setIntegerProperty("loadUrlTimeoutValue", 15000);
        super.loadUrl("file:///android_asset/www/index.html",5000);
        //super.loadUrl(Config.getStartUrl(),5000); //加载网页页面
        //super.setIntegerProperty("loadUrlTimeoutValue", 10000);


    }

    /** 添加的代码开始 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,1,R.string.about);
        menu.add(0,2,2,R.string.exit);
        //menu.add(0,3,3,"test");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==1){
            super.loadUrl("file:///android_asset/www/about.html");
        }
        else if(item.getItemId()==2){
            super.loadUrl("file:///android_asset/www/finish.html");
            //finish();
        }
        else if(item.getItemId()==3){
            super.loadUrl("file:///android_asset/www/test.html");
            //finish();
        }
        return super.onOptionsItemSelected(item);
    }
    /** 添加的代码结束 */
}
