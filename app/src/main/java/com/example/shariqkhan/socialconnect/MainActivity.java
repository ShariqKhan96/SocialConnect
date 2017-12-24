package com.example.shariqkhan.socialconnect;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import retrofit2.http.Url;


public class MainActivity extends AppCompatActivity {


    Button bt2;
    Button bt3;
    Button bt4;


    URL url;
    CallbackManager callBackManager;
    ShareDialog dialog;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //    callBackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);

        //    printKeyHash();

        setContentView(R.layout.activity_main);

        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);
        bt4 = (Button) findViewById(R.id.button4);

        FacebookSdk.setApplicationId("334146073720024");
        FacebookSdk.sdkInitialize(getApplicationContext());
        //Init fb


        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent linkedinIntent = new Intent(Intent.ACTION_SEND);
                linkedinIntent.setType("text/plain");
                linkedinIntent.putExtra(Intent.EXTRA_TEXT, "Hello this is plain text!");

                boolean linkedinAppFound = false;
                List<ResolveInfo> matches2 = getPackageManager()
                        .queryIntentActivities(linkedinIntent, 0);

                for (ResolveInfo info : matches2) {
                    if (info.activityInfo.packageName.toLowerCase().startsWith(
                            "com.linkedin")) {
                        linkedinIntent.setPackage(info.activityInfo.packageName);
                        linkedinAppFound = true;
                        break;
                    }
                }

                if (linkedinAppFound) {
                    startActivity(linkedinIntent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"LinkedIn app not Insatlled in your mobile", Toast.LENGTH_SHORT).show();
                }

            }
        });


        CallbackManager.Factory.create();
        dialog = new ShareDialog(this);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ShareLinkContent shareLink = new ShareLinkContent.Builder()
                        .setQuote("Hello here")
                        .setContentUrl(Uri.parse("https://www.youtube.com"))
                        .build();
                if (dialog.canShow(ShareLinkContent.class)) {
                    dialog.show(shareLink);
                }

            }
        });


        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    url = new URL("https://www.youtube.com");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }


                TweetComposer.Builder build = new TweetComposer.Builder(MainActivity.this);
                build.text("Simple first explicit tweet!").
                        url(url);
                build.show();

            }
        });

    }

    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.shariqkhan.socialconnect",
                    PackageManager.GET_SIGNATURES
            );

            for (Signature sign : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(sign.toByteArray());
                Log.e("KehHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));


            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
