package com.dhq.dialogfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.dhq.dialoglibrary.BaseDialogFragment;
import com.dhq.dialoglibrary.CommonDialog;
import com.dhq.dialoglibrary.ViewConvertListener;
import com.dhq.dialoglibrary.ViewHolder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDialog0(View view) {
        CommonDialog.init()
                .setLayoutId(R.layout.share_layout)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseDialogFragment dialog) {
                        holder.setOnClickListener(R.id.wechat, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setDimAmount(0.5f)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    public void showDialog1(View view) {
        CommonDialog.init()
                .setLayoutId(R.layout.friend_set_layout)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseDialogFragment dialog) {

                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    public void showDialog2(View view) {
        CommonDialog.init()
                .setLayoutId(R.layout.commit_layout)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseDialogFragment dialog) {
                        final EditText editText = holder.getView(R.id.edit_input);
                        editText.post(new Runnable() {
                            @Override
                            public void run() {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(editText, 0);
                            }
                        });
                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    public void showDialog3(View view) {
        CommonDialog.init()
                .setLayoutId(R.layout.ad_layout)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseDialogFragment dialog) {
                        holder.setOnClickListener(R.id.close, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setOutCancel(false)
                .show(getSupportFragmentManager());
    }


    public void showDialog4(View view) {
        CommonDialog.init()
                .setLayoutId(R.layout.loading_layout)
                .setDimAmount(0)
                .show(getSupportFragmentManager());
    }

    public void showDialog5(View view) {
        ConfirmDialog.newInstance("1")
                .setOutCancel(false)
                .show(getSupportFragmentManager());
    }

    public void showDialog6(View view) {
        ConfirmDialog.newInstance("2")
                .setOutCancel(false)
                .show(getSupportFragmentManager());
    }

}
