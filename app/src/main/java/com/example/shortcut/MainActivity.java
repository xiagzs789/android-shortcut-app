package com.example.shortcut;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // 显示"正在执行指令"提示
        Toast.makeText(this, "正在执行指令", Toast.LENGTH_SHORT).show();
        
        // 延迟一点时间确保Toast显示出来
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                executeShortcut();
            }
        }, 500);
    }

    private void executeShortcut() {
        // 读取剪贴板内容
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        String clipboardContent = "";
        
        if (clipboard.hasPrimaryClip()) {
            ClipData clip = clipboard.getPrimaryClip();
            if (clip != null && clip.getItemCount() > 0) {
                CharSequence text = clip.getItemAt(0).getText();
                if (text != null) {
                    clipboardContent = text.toString();
                }
            }
        }
        
        // 在后台线程执行HTTP请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendHttpPostRequest(clipboardContent);
            }
        }).start();
    }

    private void sendHttpPostRequest(String clipboardContent) {
        try {
            URL url = new URL("http://daniu.7766.org:8081/webhook/save_article");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonInput = "{\"url\": \"" + clipboardContent.replace("\\", "\\\\").replace("\"", "\\\"") + "\",\"user\":\"YX\"}";
            
            OutputStream os = connection.getOutputStream();
            os.write(jsonInput.getBytes("UTF-8"));
            os.close();

            int responseCode = connection.getResponseCode();
            
            // 在主线程中显示完成提示
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "完成", Toast.LENGTH_SHORT).show();
                    finish(); // 关闭应用
                }
            });

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            
            // 在主线程中显示错误提示
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    finish(); // 关闭应用
                }
            });
        }
    }
}