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