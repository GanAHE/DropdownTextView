## 一、Android Spinner
Spinner是Android开发常见的一个下拉选项菜单控件，但该控件定制化较为麻烦，经验不足时想要达到需要的效果可能需要半天时间慢慢摸索。

基于实际的需要，故开发一款可轻松灵活定制的下来选项==DropdownTextView==，能够像Spinner一样实现下拉选择功能，同时该控件采用RecycleView实现。

## 二、DropdownTextView
利用RecycleView仿制Spinner实现.
当前版本： [![](https://jitpack.io/v/GanAHE/DropdownTextViewDemo.svg)](https://jitpack.io/#GanAHE/DropdownTextViewDemo)

![dropdownTextViewDemo_zip.gif](https://s2.loli.net/2022/01/16/aSyHCcuM6de1Viq.gif)

### 2.1 获取与配置使用
项目Demo演示地址如下：
1.Github：https://github.com/GanAHE/DropdownTextViewDemo
2.Gitee：[https://gitee.com/ganahe/DropdownTextViewDemo](https://gitee.com/ganahe/DropdownTextViewDemo "https://gitee.com/ganahe/DropdownTextViewDemo")

#### 2.1.1 Gradle方式

在项目build.gradle中加入：
 1. 将JitPack存储库添加到构建文件中


```xml
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
2. 添加依赖关系
```xml
	dependencies {
	        implementation 'com.github.GanAHE:DropdownTextView:1.0.0'
	}
```

#### 2.1.2 Maven方式
1. 将JitPack存储库添加到构建文件中

```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

2.  添加依赖关系

```xml
	<dependency>
	    <groupId>com.github.GanAHE</groupId>
	    <artifactId>DropdownTextView</artifactId>
	    <version>1.0.0</version>
	</dependency>
```

### 2.2 使用参数
|  属性值 | 说明  |  属性值 | 说明  |
| :------------: | :------------: | :------------: | :------------: |
| arrays  | 弹出框的可选项  | zoneText  | 文本框文本  |
|  textviewWidth | 文本框宽度  |  textviewHeight |  文本框高度 |
| zoneTextUnderLineEnable  | 是否显示下划线  | zoneTextUnderLineColor  |  下划线颜色 |
|  zoneTextSize | 文本框内文字大小，输入整数值不带单位，默认dp属性  | zoneTextColor  | 文本框中文字颜色  |
|  iconWidth | 下拉提示图标宽度  | iconHeight  |  下拉提示图标高度 |
|  iconSrc |  图标资源 | iconBackground | 图标背景  |
|  popWindowWidth | 弹出选项框宽度  | popWindowHeight | 弹出选项框高度  |
|  popWindowBackGroundColor |  弹出选项框背景色 | textviewBackground | 弹出选项框背景  |
|  popWindowTextGravity |  弹出选项框选项文本对齐样式：左对齐left；居中center；右对齐right | popWindowTextColor | 弹出选项框选项文本颜色  |

### 2.3 使用布局Demo

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <LinearLayout
        android:id="@+id/linearlayout1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="5dp"
        android:layout_marginEnd="8dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent">

        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Demo1"/>

        <com.ganahe.dropdowntextview.DropdownTextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            app:zoneText="GPS_Mode"
            app:arrays="@array/GPS_MODE"
            app:iconSrc="@mipmap/select_more_down"
            app:popWindowTextGravity="left"
            app:zoneTextSize="12"
            android:layout_weight="1"/>


    </LinearLayout>


    <LinearLayout
        android:id="@+id/linearlayout2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="5dp"
        android:layout_marginStart="5dp"
        android:layout_marginEnd="8dp"
        app:layout_constraintTop_toBottomOf="@+id/linearlayout1">

        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Demo2"/>


        <com.ganahe.dropdowntextview.DropdownTextView
            android:layout_width="0dp"
            android:layout_height="20dp"
            android:layout_weight="1"
            app:zoneTextSize="12"
            app:zoneText="GanAHE_WebType"
            app:arrays="@array/ganahe_webType"
            app:popWindowHeight="120dp"
            app:popWindowWidth="wrap_content"
            app:popWindowBackGroundColor="#C8000000"/>

    </LinearLayout>


    <LinearLayout
        android:id="@+id/linearlayout3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="5dp"
        android:layout_marginStart="5dp"
        android:layout_marginEnd="8dp"
        app:layout_constraintTop_toBottomOf="@+id/linearlayout2">

        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Demo3"/>


        <com.ganahe.dropdowntextview.DropdownTextView
            android:id="@+id/customDropDownPopWindow_rional_speed"
            android:layout_width="0dp"
            android:layout_height="20dp"
            android:layout_weight="1"
            app:zoneTextSize="12"
            app:zoneText="@string/app_name"
            app:zoneTextColor="@color/alis_blue"
            app:popWindowHeight="120dp"
            app:popWindowWidth="wrap_content"
            app:zoneTextUnderLineEnable="false"
            app:popWindowTextGravity="left"
            app:popWindowBackGroundColor="#C88CDD6E"
            app:popWindowTextColor="@color/red_orange"/>

    </LinearLayout>





    <LinearLayout
        android:id="@+id/linearlayout4"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="5dp"
        android:layout_marginStart="5dp"
        android:layout_marginEnd="8dp"
        app:layout_constraintTop_toBottomOf="@+id/linearlayout3">

        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Demo4"/>


        <com.ganahe.dropdowntextview.DropdownTextView
            android:id="@+id/dropdownTextview_demo_4"
            android:layout_width="0dp"
            android:layout_height="20dp"
            android:layout_weight="1"
            app:zoneTextSize="12"
            app:zoneText="GanAHE"
            app:popWindowHeight="wrap_content"
            app:popWindowWidth="wrap_content"
            app:popWindowTextGravity="center"
            app:iconWidth="12dp"
            app:iconHeight="12dp"
            app:popWindowBackGroundColor="@color/purple_200"/>

    </LinearLayout>

    <LinearLayout
        android:id="@+id/linearlayout5"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="5dp"
        android:layout_marginStart="5dp"
        android:layout_marginEnd="8dp"
        app:layout_constraintTop_toBottomOf="@+id/linearlayout4">

        <TextView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Demo5"/>


        <com.ganahe.dropdowntextview.DropdownTextView
            android:id="@+id/dropdownTextview_demo_5"
            android:layout_width="wrap_content"
            android:layout_height="20dp"
            app:zoneTextSize="12"
            app:zoneTextUnderLineColor="#FF9800"
            app:zoneText="GanAHE"
            app:popWindowHeight="120dp"
            app:popWindowWidth="wrap_content"
            app:popWindowTextGravity="right"
            app:popWindowBackGroundColor="#D0499EE1" />

    </LinearLayout>



    <TextView
        android:id="@+id/textview_online_help"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_marginTop="20dp"
        android:text="@string/online_help"
        android:textColor="@color/green_yellow"
        android:gravity="center"
        android:textSize="15sp"
        app:layout_constraintTop_toBottomOf="@+id/linearlayout5" />

    <View
        android:layout_width="match_parent"
        android:layout_height="1dp"
        android:layerType="software"
        android:background="@color/gray_light"
        app:layout_constraintBottom_toTopOf="@+id/textview_copyright" />

    <TextView
        android:id="@+id/textview_copyright"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Copyright @ GanAHE"
        android:layout_gravity="bottom"
        android:textSize="18sp"
        android:textColor="@color/gray_light"
        android:gravity="center"
        android:layout_marginTop="3dp"
        android:layout_marginBottom="5dp"
        android:paddingBottom="10dp"
        app:layout_constraintBottom_toBottomOf="parent"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```
#### 2.4 代码使用Demo
```java
package com.ganahe.dropdowntextviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.ganahe.dropdowntextview.DropdownTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<CharSequence> demo4_items = new ArrayList<>();
    private final List<CharSequence> demo5_items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.textview_online_help).setOnClickListener(view->{
            Uri uri = Uri.parse("http://dgzc.ganahe.top");
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(uri);
            startActivity(intent);
        });

        //手动从array中获取List
        List<CharSequence> demo3List = new ArrayList<>();
        String[] a = getResources().getStringArray(R.array.WIFI_KEYS);
        for(String str:a){
            demo3List.add((CharSequence) str);
        }
        ((DropdownTextView) findViewById(R.id.customDropDownPopWindow_rional_speed)).setArrayItems(demo3List);


        demo4_items.add("GNSSAMS");
        demo4_items.add("DGZC.ganahe.top");
        demo4_items.add("Test for ong");

        ((DropdownTextView) findViewById(R.id.dropdownTextview_demo_4)).setArrayItems(demo4_items);

        demo5_items.add("TianqueROS");
        demo5_items.add("Software");
        demo5_items.add("GanAHE");
        ((DropdownTextView) findViewById(R.id.dropdownTextview_demo_5)).setArrayItems(demo5_items);
    }
}
```


### 2.5 其他

暂无
