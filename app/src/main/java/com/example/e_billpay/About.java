package com.example.e_billpay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
//import android.provider.CalendarContract;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;
import android.os.Bundle;


public class About extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("About Me");
        Element adsElement = new Element();
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.ujjal)
                .setDescription(" \uD83D\uDC4B Hi, I’m Ujjal\n" +
                        "\uD83D\uDC40 I’m interested in coding. when i get free time that time i will try to learn code.\n" +
                        "\uD83C\uDF31 I’m currently learning Javascript. we al know javascript is most powerful language.\n" +
                        "\uD83D\uDC9E️ I’m looking to collaborate on the coding related academy.\n" +
                        "\uD83D\uDCEB How to reach me just know on my linkedin or you can also touch in email. i frequently check my email")
                .addItem(new Element().setTitle("Version 1.0"))
                .addGroup("Connect with Me!")
                .addEmail("https://mail.google.com/mail/u/0/#inbox")
                .addWebsite("https://developer.android.com/guide/topics/ui/menus")
                .addYoutube("https://www.youtube.com/channel/UC-ZcLYvf4qdPSAWWyFJf_6A")   //Enter your youtube link here (replace with my channel link)
                .addPlayStore("com.example.e_billpay")   //Replace all this with your package name
                .addFacebook("https://www.facebook.com/ujjal.roy.7862/")
                .addInstagram("https://www.instagram.com/ujjal_roy7862/")    //Your instagram id
                .addGitHub("https://github.com/Ujjal-ENG")
                .addItem(createCopyright())
                .create();
        setContentView(aboutPage);
    }
    private Element createCopyright()
    {
        Element copyright = new Element();
        @SuppressLint("DefaultLocale") final String copyrightString = String.format("Copyright %d by Ujjal Kumar Roy", Calendar.getInstance().get(Calendar.YEAR));
        copyright.setTitle(copyrightString);
        // copyright.setIcon(R.mipmap.ic_launcher);
        copyright.setGravity(Gravity.CENTER);
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(About.this,copyrightString,Toast.LENGTH_SHORT).show();
            }
        });
        return copyright;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.exit){
            AlertDialog.Builder builder = new AlertDialog.Builder(About.this);
            builder.setMessage("Do you Want to Exit?");
            builder.setCancelable(true);

            builder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            });
            builder.setPositiveButton("No",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return true;
    }
}