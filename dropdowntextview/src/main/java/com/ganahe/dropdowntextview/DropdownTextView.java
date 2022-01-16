package com.ganahe.dropdowntextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: GanAHE
 * @date: 2021/12/20
 * @description: CustomDropDownPopWindow 通用弹窗选择框
 *
 *
 * @Tip 2021年12月21日
 * 由于RecycleView的特性，Warp—Content 属性绘制的宽度将以可视区域最长Item绘制
 * 如果列表中非可视区域有Item长度长于可视区域的Item，则滑动到该Item时会发现，该Item显示不全
 * 解决措施：
 *  法1.调整高度或Item顺序，让Item最长的条目为首次打开可视；
 *  法2.手动设定宽度待遇大于最大Item长度
 *
 *  By GanAHE
 */
public class DropdownTextView extends LinearLayout {

    private View mDropPopView;
    private final Context mContext;

    private TextView mTextView;

    private LinearLayout mLinearLayoutCustomDropDownPopWindow;
    private CustomPopWindow mCustomPopWindow;

    private DropItemAdapter mDropItemAdapter;

    private OnClickDropPopWindowItemListener mOnClickDropPopWindowItemListener = null;

    /**
     * 自定义属性
     *
     */
    private List<CharSequence> mDropItemLists = new ArrayList<>();

    private int zoneTextViewWidth;//文本框宽度
    private int zoneTextViewHeight;//文本框高度
    private String zoneText = "";
    private int zoneTextViewBackgroundResId;//图标资源
    private int zoneTextColor;//颜色,@color
    private int zoneTextSize;//单位是sp,只拿数值
    private boolean zoneTextUnderLineEnable; //是否显示下划线
    private int zoneTextUnderLineColor; //下划线颜色

    private int iconResId;//图标资源
    private int iconBackgroundResId;//图标资源
    private int iconViewWidth;//图标宽度
    private int iconViewHeight;//图标高度

    private int popWindowHeight;
    private int popWindowWidth;
    private int popWindowTextGravity = -1; //文本对齐方式 -1左对齐；0居中 ；1右对齐
    private int popWindowBackGroundColor;
    private int popWindowTextColor;

    /**
     *
     * @param context Context
     */
    public DropdownTextView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public DropdownTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray mTypedArray = mContext.obtainStyledAttributes(attrs,
                R.styleable.dropdown_select_popwindow);
        initAttrs(mTypedArray);
        initView();
    }

    public DropdownTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        TypedArray mTypedArray = mContext.obtainStyledAttributes(attrs,
                R.styleable.dropdown_select_popwindow);
        initAttrs(mTypedArray);
        initView();
    }

    public DropdownTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        TypedArray mTypedArray = mContext.obtainStyledAttributes(attrs,
                R.styleable.dropdown_select_popwindow);
        initAttrs(mTypedArray);
        initView();
    }

    /**
     * @param :[mTypedArray]
     * @return type:void
     * @method name:init
     * @des:获取自定义属性
     * @date 创建时间:2017/5/24
     * @author Chuck
     **/
    private void initAttrs(TypedArray mTypedArray) {
        if(null !=mTypedArray.getTextArray(R.styleable.dropdown_select_popwindow_arrays)){ //用户在属性中添加
            mDropItemLists = Arrays.asList(mTypedArray.getTextArray(R.styleable.dropdown_select_popwindow_arrays));
        }

        zoneTextViewWidth = (int) mTypedArray.getDimension(R.styleable.dropdown_select_popwindow_textviewWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        zoneTextViewHeight = (int) mTypedArray.getDimension(R.styleable.dropdown_select_popwindow_textviewHeight, ViewGroup.LayoutParams.WRAP_CONTENT);
        zoneTextViewBackgroundResId = mTypedArray.getResourceId(R.styleable.dropdown_select_popwindow_textviewBackground, -1);
        zoneTextColor = mTypedArray.getColor(R.styleable.dropdown_select_popwindow_zoneTextColor, getResources().getColor(android.R.color.black));
        zoneText = mTypedArray.getString(R.styleable.dropdown_select_popwindow_zoneText);
        zoneTextSize = mTypedArray.getInteger(R.styleable.dropdown_select_popwindow_zoneTextSize, DensityUtil.sp2px(mContext,6));
        zoneTextUnderLineEnable = mTypedArray.getBoolean(R.styleable.dropdown_select_popwindow_zoneTextUnderLineEnable,true);
        zoneTextUnderLineColor = mTypedArray.getColor(R.styleable.dropdown_select_popwindow_zoneTextUnderLineColor,getResources().getColor(R.color.gray_light));

        iconViewWidth = (int) mTypedArray.getDimension(R.styleable.dropdown_select_popwindow_iconWidth, DensityUtil.dp2px(mContext,15));
        iconViewHeight = (int) mTypedArray.getDimension(R.styleable.dropdown_select_popwindow_iconHeight, DensityUtil.dp2px(mContext,15));
        iconBackgroundResId = mTypedArray.getResourceId(R.styleable.dropdown_select_popwindow_iconBackground, -1);
        iconResId = mTypedArray.getResourceId(R.styleable.dropdown_select_popwindow_iconSrc, -1);

        popWindowHeight = mTypedArray.getLayoutDimension(R.styleable.dropdown_select_popwindow_popWindowHeight,DensityUtil.dp2px(mContext,80));
        popWindowWidth =  mTypedArray.getLayoutDimension(R.styleable.dropdown_select_popwindow_popWindowWidth,ViewGroup.LayoutParams.WRAP_CONTENT);
        popWindowTextGravity = mTypedArray.getInteger(R.styleable.dropdown_select_popwindow_popWindowTextGravity, -1); //默认左对齐
        popWindowBackGroundColor = mTypedArray.getColor(R.styleable.dropdown_select_popwindow_popWindowBackGroundColor,getResources().getColor(R.color.black));
        popWindowTextColor = mTypedArray.getColor(R.styleable.dropdown_select_popwindow_popWindowTextColor, getResources().getColor(R.color.white));
        mTypedArray.recycle();
    }

    private void initView(){
       LayoutInflater.from(mContext).inflate(R.layout.dropdown_textview_widget_layout,
                this, true);

        mTextView = findViewById(R.id.textView_custom_drop_window);

        if(zoneTextUnderLineEnable){
            LayerDrawable layerDrawable = (LayerDrawable) mTextView.getBackground();
            GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.item_textview_boder);
            gradientDrawable.setStroke(DensityUtil.dp2px(getContext(),1),zoneTextUnderLineColor);
        }else {
            mTextView.setBackground(null);
        }

        if(mDropItemLists.size() > 0) {
            zoneText = mDropItemLists.get(0).toString();
        }
        mTextView.setText(zoneText);
        mTextView.setTextSize(zoneTextSize);
        //Log.d("Debug", "initView zoneTextSize: " + zoneTextSize);

        mTextView.setTextColor(zoneTextColor);
        if(zoneTextViewBackgroundResId != -1){
            mTextView.setBackgroundColor(getResources().getColor(zoneTextViewBackgroundResId));
        }

        ImageView imageViewIcon = findViewById(R.id.imageview_custom_drop_window);
        if(iconResId != -1) { //用户不使用给的默认图片
            imageViewIcon.setImageResource(iconResId);
        }
        LayoutParams layoutParamsIcon = (LayoutParams) imageViewIcon.getLayoutParams();
        layoutParamsIcon.width = iconViewWidth;
        layoutParamsIcon.height = iconViewHeight;
        imageViewIcon.setLayoutParams(layoutParamsIcon);

        if(iconBackgroundResId != -1){
            imageViewIcon.setBackgroundColor(getResources().getColor(iconBackgroundResId));
        }

        mDropPopView = LayoutInflater.from(mContext).inflate(R.layout.custom_drop_pop_window_layout, this,false);
        mDropPopView.setBackgroundColor(popWindowBackGroundColor);
        LayoutParams layoutParamsRec = new LayoutParams(mDropPopView.getLayoutParams());
        layoutParamsRec.height = popWindowHeight;
        layoutParamsRec.width = popWindowWidth;
        mDropPopView.setLayoutParams(layoutParamsRec);
        //mRecyclerView = new RecyclerView(mContext);
        /*LinearLayout.LayoutParams layoutParamsRec = (LayoutParams) new LinearLayout.LayoutParams(mView2.getLayoutParams());
        layoutParamsRec.width = LayoutParams.WRAP_CONTENT;
        layoutParamsRec.height = DensityUtil.dp2px(mContext,100);
        mView2.setLayoutParams(layoutParamsRec);*/

        RecyclerView recyclerView = mDropPopView.findViewById(R.id.recyclerView_custom_pop_window);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mDropItemAdapter = new DropItemAdapter();
        recyclerView.setAdapter(mDropItemAdapter);
        try {
            ViewGroup viewGroup = (ViewGroup) mDropPopView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(mDropPopView);
            }
        }catch (Exception e){
            Log.i("Debug","Exception mDropPopView:" + e.toString());

            e.printStackTrace();
        }
        mCustomPopWindow =  new CustomPopWindow.PopupWindowBuilder(mContext)
                .setView(mDropPopView)
                .setFocusable(true)
                .setOutsideTouchable(true)
                .size(popWindowWidth,popWindowHeight)
                .setAnimationStyle(R.style.CustomPopWindowStyleVertical) // 添加自定义显示和消失动画
                .create();



        mLinearLayoutCustomDropDownPopWindow = findViewById(R.id.linearLayout_custom_drop_window);
        /*LayoutParams layoutParams = (LayoutParams) mLinearLayoutCustomDropDownPopWindow.getLayoutParams();
        layoutParams.width = zoneTextViewWidth;
        layoutParams.height = zoneTextViewHeight;
        mLinearLayoutCustomDropDownPopWindow.setLayoutParams(layoutParams);*/

        mLinearLayoutCustomDropDownPopWindow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                    mCustomPopWindow.showAsDropDown(mLinearLayoutCustomDropDownPopWindow, 0, 0);

            }
        });
    }


    /**
     * 设置弹出列表项
     * @param arrayItems List<CharSequence>
     */
    public void setArrayItems(@NonNull List<CharSequence> arrayItems){
        this.mDropItemLists = arrayItems;
        mDropItemAdapter.notifyDataSetChanged();
        if(this.mDropItemLists.size() > 0){
            mTextView.setText(mDropItemLists.get(0));

            //调整宽度
            ViewGroup.LayoutParams layoutParams = new LayoutParams(mCustomPopWindow.getPopupWindow().getContentView().getLayoutParams());
            layoutParams.width = DensityUtil.dp2px(mContext,200);
            mCustomPopWindow.getPopupWindow().getContentView().setLayoutParams(layoutParams);
            mCustomPopWindow.getPopupWindow().update();

            LayoutParams layoutParamsRec = new LayoutParams(mDropPopView.getLayoutParams());
            layoutParamsRec.height = popWindowHeight;
            layoutParamsRec.width = popWindowWidth;
            mDropPopView.setLayoutParams(layoutParamsRec);
        }
    }

    /**
     * 设置文本信息
     * @param zoneText
     */
    public void setText(@NonNull String zoneText){
        mTextView.setText(zoneText);
    }

    /**
     * 获取文本框内的文本
     * @return
     */
    public String getText(){
        if(null == mTextView || null == mTextView.getText().toString()){
            return "";
        }
        return mTextView.getText().toString();
    }


    class DropItemAdapter extends RecyclerView.Adapter<DropItemAdapter.MyViewHolder>{

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            return new MyViewHolder(LayoutInflater.from(mDropPopView.getContext()).inflate(R.layout.custom_drop_pop_window_item_layout,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.mTextViewItem.setText(mDropItemLists.get(position));
            holder.mTextViewItem.setTextColor(popWindowTextColor);
            holder.mTextViewItem.setTextSize(zoneTextSize);
        }

        @Override
        public int getItemCount() {
            return mDropItemLists.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView mTextViewItem;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                mTextViewItem = itemView.findViewById(R.id.textView_custom_drop_window_item);
                switch (popWindowTextGravity){
                    case -1:
                        mTextViewItem.setGravity(Gravity.START);
                        break;

                    case 0:
                        mTextViewItem.setGravity(Gravity.CENTER_HORIZONTAL);
                        break;

                    case 1:
                        mTextViewItem.setGravity(Gravity.END);
                        break;
                }


                mTextViewItem.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(null != mOnClickDropPopWindowItemListener){

                            if(getBindingAdapterPosition() == -1){ //刷新状态下下标为-1
                                return;
                            }
                            //回调
                            mOnClickDropPopWindowItemListener.onResult(getBindingAdapterPosition(),mDropItemLists.get(getBindingAdapterPosition()).toString());
                        }

                        mTextView.setText(mDropItemLists.get(getBindingAdapterPosition()));

                        mCustomPopWindow.onDismiss();
                        //图标动画

                    }
                });
            }
        }
    }


    public interface OnClickDropPopWindowItemListener{
        void onResult(int position,String text);
    }

    public void setOnClickDropPopWindowItemListener(@NonNull OnClickDropPopWindowItemListener onClickDropPopWindowItemListener){
        this.mOnClickDropPopWindowItemListener = onClickDropPopWindowItemListener;
    }
}
