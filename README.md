# BrainJD
模仿JD


- TextView 设置横线

```
textView.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线

textView.getPaint().setAntiAlias(true);//抗锯齿

textview.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线

setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰

textView.getPaint().setFlags(0);  // 取消设置的的划线
```



- 对于ScrollView嵌套ListView/GridView解决滑动冲突的方式

    - 上面的数据  + ListView的高度计算死
    - 上面的数据作为ListView的头布局
