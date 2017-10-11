package mdluex.smartx;


import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;


public class ActivityControlCenter extends AppCompatActivity {

    public int room1_str = 0;
    public int room2_str = 0;
    public int room3_str = 0;
    public int room4_str = 0;
    public int room5_str = 0;
    public int room6_str = 0;
    public static String read_msg;
    public static String cc_degText;
    public boolean smk_stt;
    public boolean mo_stt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_center);

        final RelativeLayout room1_btn = (RelativeLayout) this.findViewById(R.id.room1_btn);
        final TextView room1_st = (TextView) this.findViewById(R.id.room1_st);
        final ImageView room1_img = (ImageView) this.findViewById(R.id.room1_img);
        room1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (room1_str == 0){
                    room1_str = 1;
                    SplashScreen.room1ON();
                    /*room1_btn.setBackgroundResource(R.drawable.btn_grid_nor);
                    room1_st.setText("ON");
                    room1_img.setImageResource(R.drawable.lamp_on);*/
                }
                else {
                    room1_str = 0;
                    SplashScreen.room1OFF();
                    /*room1_btn.setBackgroundResource(R.drawable.btn_grid_off);
                    room1_st.setText("OFF");
                    room1_img.setImageResource(R.drawable.lamp_off);*/
                }
            }
        });

        final RelativeLayout room2_btn = (RelativeLayout) this.findViewById(R.id.room2_btn);
        final TextView room2_st = (TextView) this.findViewById(R.id.room2_st);
        final ImageView room2_img = (ImageView) this.findViewById(R.id.room2_img);
        room2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (room2_str == 0){
                    room2_str = 1;
                    SplashScreen.room2ON();
                    /*room2_btn.setBackgroundResource(R.drawable.btn_grid_nor);
                    room2_st.setText("ON");
                    room2_img.setImageResource(R.drawable.lamp_on);*/
                }
                else {
                    room2_str = 0;
                    SplashScreen.room2OFF();
                    /*room2_btn.setBackgroundResource(R.drawable.btn_grid_off);
                    room2_st.setText("OFF");
                    room2_img.setImageResource(R.drawable.lamp_off);*/
                }
            }
        });

        final RelativeLayout room3_btn = (RelativeLayout) this.findViewById(R.id.room3_btn);
        final TextView room3_st = (TextView) this.findViewById(R.id.room3_st);
        final ImageView room3_img = (ImageView) this.findViewById(R.id.room3_img);
        room3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (room3_str == 0){
                    room3_str = 1;
                    SplashScreen.room3ON();
                    /*room3_btn.setBackgroundResource(R.drawable.btn_grid_nor);
                    room3_st.setText("ON");
                    room3_img.setImageResource(R.drawable.lamp_on);*/
                }
                else {
                    room3_str = 0;
                    SplashScreen.room3OFF();
                    /*room3_btn.setBackgroundResource(R.drawable.btn_grid_off);
                    room3_st.setText("OFF");
                    room3_img.setImageResource(R.drawable.lamp_off);*/
                }
            }
        });

        final RelativeLayout room4_btn = (RelativeLayout) this.findViewById(R.id.room4_btn);
        final TextView room4_st = (TextView) this.findViewById(R.id.room4_st);
        final ImageView room4_img = (ImageView) this.findViewById(R.id.room4_img);
        room4_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (room4_str == 0){
                    room4_str = 1;
                    SplashScreen.room4ON();
                    /*room4_btn.setBackgroundResource(R.drawable.btn_grid_nor);
                    room4_st.setText("ON");
                    room4_img.setImageResource(R.drawable.lamp_on);*/
                }
                else {
                    room4_str = 0;
                    SplashScreen.room4OFF();
                    /*room4_btn.setBackgroundResource(R.drawable.btn_grid_off);
                    room4_st.setText("OFF");
                    room4_img.setImageResource(R.drawable.lamp_off);*/
                }
            }
        });

        final RelativeLayout room5_btn = (RelativeLayout) this.findViewById(R.id.room5_btn);
        final TextView room5_st = (TextView) this.findViewById(R.id.room5_st);
        final ImageView room5_img = (ImageView) this.findViewById(R.id.room5_img);
        room5_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (room5_str == 0){
                    room5_str = 1;
                    SplashScreen.room5ON();
                    /*room5_btn.setBackgroundResource(R.drawable.btn_grid_nor);
                    room5_st.setText("ON");
                    room5_img.setImageResource(R.drawable.lamp_on);*/
                }
                else {
                    room5_str = 0;
                    SplashScreen.room5OFF();
                    /*room5_btn.setBackgroundResource(R.drawable.btn_grid_off);
                    room5_st.setText("OFF");
                    room5_img.setImageResource(R.drawable.lamp_off);*/
                }
            }
        });

        final RelativeLayout room6_btn = (RelativeLayout) this.findViewById(R.id.room6_btn);
        final TextView room6_st = (TextView) this.findViewById(R.id.room6_st);
        final ImageView room6_img = (ImageView) this.findViewById(R.id.room6_img);
        room6_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (room6_str == 0){
                    room6_str = 1;
                    SplashScreen.room6ON();
                    /*room6_btn.setBackgroundResource(R.drawable.btn_grid_nor);
                    room6_st.setText("ON");
                    room6_img.setImageResource(R.drawable.lamp_on);*/
                }
                else {
                    room6_str = 0;
                    SplashScreen.room6OFF();
                    /*room6_btn.setBackgroundResource(R.drawable.btn_grid_off);
                    room6_st.setText("OFF");
                    room6_img.setImageResource(R.drawable.lamp_off);*/
                }
            }
        });

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                final Handler read_handler = new Handler(Looper.getMainLooper());
                read_handler.post(new Runnable() {
                    public void run() {
                        chk_on_off();
                        smk_stt_th();
                        mo_stt_th();
                        deg_text();
                        if (room1_str == 0){
                            room1_btn.setBackgroundResource(R.drawable.btn_grid_off);
                            room1_st.setText("OFF");
                            room1_img.setImageResource(R.drawable.lamp_off);
                        }
                        else {
                            room1_btn.setBackgroundResource(R.drawable.btn_grid_nor);
                            room1_st.setText("ON");
                            room1_img.setImageResource(R.drawable.lamp_on);
                        }

                        if (room2_str == 0){
                            room2_btn.setBackgroundResource(R.drawable.btn_grid_off);
                            room2_st.setText("OFF");
                            room2_img.setImageResource(R.drawable.lamp_off);
                        }
                        else {
                            room2_btn.setBackgroundResource(R.drawable.btn_grid_nor);
                            room2_st.setText("ON");
                            room2_img.setImageResource(R.drawable.lamp_on);
                        }

                        if (room3_str == 0){
                            room3_btn.setBackgroundResource(R.drawable.btn_grid_off);
                            room3_st.setText("OFF");
                            room3_img.setImageResource(R.drawable.lamp_off);
                        }
                        else {
                            room3_btn.setBackgroundResource(R.drawable.btn_grid_nor);
                            room3_st.setText("ON");
                            room3_img.setImageResource(R.drawable.lamp_on);
                        }

                        if (room4_str == 0){
                            room4_btn.setBackgroundResource(R.drawable.btn_grid_off);
                            room4_st.setText("OFF");
                            room4_img.setImageResource(R.drawable.lamp_off);
                        }
                        else {
                            room4_btn.setBackgroundResource(R.drawable.btn_grid_nor);
                            room4_st.setText("ON");
                            room4_img.setImageResource(R.drawable.lamp_on);
                        }

                        if (room5_str == 0){
                            room5_btn.setBackgroundResource(R.drawable.btn_grid_off);
                            room5_st.setText("OFF");
                            room5_img.setImageResource(R.drawable.lamp_off);
                        }
                        else {
                            room5_btn.setBackgroundResource(R.drawable.btn_grid_nor);
                            room5_st.setText("ON");
                            room5_img.setImageResource(R.drawable.lamp_on);
                        }

                        if (room6_str == 0){
                            room6_btn.setBackgroundResource(R.drawable.btn_grid_off);
                            room6_st.setText("OFF");
                            room6_img.setImageResource(R.drawable.lamp_off);
                        }
                        else {
                            room6_btn.setBackgroundResource(R.drawable.btn_grid_nor);
                            room6_st.setText("ON");
                            room6_img.setImageResource(R.drawable.lamp_on);
                        }
                        read_handler.postDelayed(this, 0);
                    }
                });
            }
        });

        /*final Handler deg_handler = new Handler(Looper.getMainLooper());
        deg_handler.post(new Runnable() {
            public void run() {
                deg_text();
                deg_handler.postDelayed(this, 0);
            }
        });

        final Handler smk_mo_handler = new Handler(Looper.getMainLooper());
        smk_mo_handler.post(new Runnable() {
            public void run() {
                smk_stt_th();
                mo_stt_th();
                smk_mo_handler.postDelayed(this, 0);
            }
        });*/

    }
    public void chk_on_off(){
        if (read_msg.matches("V")){
            room1_str = 1;
        }
        else if (read_msg.matches("v")){
            room1_str = 0;
        }
        if (read_msg.matches("K")){
            room2_str = 1;
        }
        else if (read_msg.matches("k")){
            room2_str = 0;
        }
        if (read_msg.matches("D")){
            room3_str = 1;
        }
        else if (read_msg.matches("d")){
            room3_str = 0;
        }
        if (read_msg.matches("F")){
            room4_str = 1;
        }
        else if (read_msg.matches("f")){
            room4_str = 0;
        }
        if (read_msg.matches("G")){
            room5_str = 1;
        }
        else if (read_msg.matches("g")){
            room5_str = 0;
        }
        if (read_msg.matches("H")){
            room6_str = 1;
        }
        else if (read_msg.matches("h")){
            room6_str = 0;
        }
    }
    public void deg_text(){
        cc_degText = read_msg;
        final TextView deg_cc = (TextView) this.findViewById(R.id.cc_temp_st);
        final RelativeLayout heat_stt_sec = (RelativeLayout) this.findViewById(R.id.heat_sec);
        if (cc_degText.matches("z")){
            heat_stt_sec.setBackgroundResource(R.drawable.btn_grid_cold);
            deg_cc.setText("GOOD");
        }
        else if (cc_degText.matches("Z")){
            heat_stt_sec.setBackgroundResource(R.drawable.btn_grid_erg);
            deg_cc.setText("HOT");
        }
        else {
            heat_stt_sec.setBackgroundResource(R.drawable.btn_grid_cold);
            deg_cc.setText("GOOD");
        }
    }
    public void smk_stt_th(){
        if (read_msg.matches("s")){
            smk_stt = false;
        }
        else if (read_msg.matches("S")){
            smk_stt = true;
        }
        /*else {
            smk_stt = false;
        }*/
        final RelativeLayout ket_sec = (RelativeLayout) this.findViewById(R.id.ket_smoke_sec);
        final TextView smk_sttText = (TextView) this.findViewById(R.id.ket_smoke_st);
        if (smk_stt == true){
            smk_sttText.setText("YES");
            ket_sec.setBackgroundResource(R.drawable.btn_grid_erg);
        }
        else if (smk_stt == false){
            smk_sttText.setText("NO");
            ket_sec.setBackgroundResource(R.drawable.btn_grid_off);
        }
    }
    public void mo_stt_th(){
        if (read_msg.matches("w")){
            mo_stt = false;
        }
        else if (read_msg.matches("W")){
            mo_stt = true;
        }
        /*else {
            mo_stt = false;
        }*/
        final RelativeLayout mo_sec = (RelativeLayout) this.findViewById(R.id.room1_mo_sec);
        final TextView mo_sttText = (TextView) this.findViewById(R.id.room1_mo_st);
        if (mo_stt == true){
            mo_sttText.setText("YES");
            mo_sec.setBackgroundResource(R.drawable.btn_grid_nor);
        }
        else if (mo_stt == false){
            mo_sttText.setText("NO");
            mo_sec.setBackgroundResource(R.drawable.btn_grid_off);
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        SplashScreen.appcls();
        System.exit(1);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        SplashScreen.appcls();
        System.exit(1);
    }
}

