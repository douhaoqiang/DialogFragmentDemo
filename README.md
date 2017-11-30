# DialogFragment 打造通用dialog弹框
## 使用
    ···java
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
                .setShowBottom(true)
                .show(getSupportFragmentManager());
     ···
## 方法说明
    init() 初始化dialog
    setLayoutId(resId) //设置弹框布局文件
    setShowBottom(Boolean) //设置是否靠底部显示（默认显示中间）
    setOutCancel(Boolean) //设置是否可以点击外部或点击返回键消失
    setDimAmount(float) //设置背景透明度（默认0.5半透明 取值范围0 - 1）

