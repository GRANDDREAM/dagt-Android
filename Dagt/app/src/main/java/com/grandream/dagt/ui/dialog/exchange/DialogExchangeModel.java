package com.grandream.dagt.ui.dialog.exchange;

import android.view.Gravity;
import android.view.View.OnClickListener;


import com.grandream.dagt.ui.dialog.CustomerFragmentCallBack;
import com.grandream.dagt.ui.dialog.DialogType;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/2/2.
 */
public class DialogExchangeModel implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3685432164096360692L;
    public DialogExchangeModelBuilder DialogExchangeModelBuilder;

    public OnClickListener compatibilityListener;// 错误弹框 按键点击事件

    public OnClickListener compatibilityPositiveListener;

    public OnClickListener compatibilityNegativeListener;

    public DialogExchangeModel(
            DialogExchangeModelBuilder DialogExchangeModelBuilder) {
        this.DialogExchangeModelBuilder = DialogExchangeModelBuilder;
    }

    public DialogType getDialogType() {
        return DialogExchangeModelBuilder.dialogType;
    }

    public String getDialogTitle() {
        return DialogExchangeModelBuilder.dialogTitle;
    }

    public String getDialogContext() {
        return DialogExchangeModelBuilder.dialogContext;
    }

    public boolean isHasTitle() {
        return DialogExchangeModelBuilder.hasTitle;
    }

    public String getPostiveText() {
        return DialogExchangeModelBuilder.postiveText;
    }

    public String getNegativeText() {
        return DialogExchangeModelBuilder.negativeText;
    }

    public String getTag() {
        return DialogExchangeModelBuilder.tag;
    }

    public String getSingleText() {
        return DialogExchangeModelBuilder.singleText;
    }

    public boolean isBussinessCancleable() {
        return DialogExchangeModelBuilder.isBussinessCancleable;
    }

    public boolean isBackable() {
        return DialogExchangeModelBuilder.isBackable;
    }

    public boolean isSpaceable() {
        return DialogExchangeModelBuilder.isSpaceable;
    }

    public int getGravity() {
        return DialogExchangeModelBuilder.gravity;
    }

    public String getOldTag() {
        return DialogExchangeModelBuilder.oldTag;
    }

    public DialogType getOldDialogType() {
        return DialogExchangeModelBuilder.oldDialogType;
    }

    public CustomerFragmentCallBack getCustomerFragmentCallBack() {
        return DialogExchangeModelBuilder.customerFragmentCallBack;
    }

    public static class DialogExchangeModelBuilder implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = -3685432164096360693L;
        /**
         * 弹出框类型
         */
        private DialogType dialogType = DialogType.SINGLE;
        /**
         * 弹出框类型
         */
        private DialogType oldDialogType = DialogType.SINGLE;
        /**
         * 弹出框标题
         */
        private String dialogTitle = "";
        /**
         * 弹出框内容
         */
        private String dialogContext = "";
        /**
         * 是否显示标题
         */
        private boolean hasTitle = true;
        /**
         * 确认按键
         */
        private String postiveText = "";
        /**
         * 取消按键
         */
        private String negativeText = "";
        /**
         * 单按键
         */
        private String singleText = "";
        /**
         * tag
         */
        private String tag = "";
        /**
         * 转换前oldTag
         */
        private String oldTag = "";
        /**
         * back可点（默认可点）
         */
        private boolean isBackable = true;
        /**
         * 空白可点（默认可点）
         */
        private boolean isSpaceable = true;
        /**
         * 服务可取消(默认可取消)
         */
        private boolean isBussinessCancleable = true;

        private int gravity = Gravity.CENTER;

        public OnClickListener compatibilityListener;// 错误弹框 按键点击事件

        public OnClickListener compatibilityPositiveListener;

        public OnClickListener compatibilityNegativeListener;

        public OnClickListener onceClickListener;//用户信息过期

        public CustomerFragmentCallBack customerFragmentCallBack;

        public DialogExchangeModelBuilder(DialogType HDDialogType,
                                          String tag) {
            this.dialogType = HDDialogType;
            this.tag = tag;
        }

        public DialogExchangeModelBuilder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public DialogExchangeModelBuilder setOldTag(String oldTag) {
            this.oldTag = oldTag;
            return this;
        }

        public DialogExchangeModelBuilder setOldDialogType(
                DialogType oldDialogType) {
            this.oldDialogType = oldDialogType;
            return this;
        }

        public DialogExchangeModelBuilder setDialogType(
                DialogType HDDialogType) {
            this.dialogType = HDDialogType;
            return this;
        }

        public DialogExchangeModelBuilder setDialogTitle(String dialogTitle) {
            this.dialogTitle = dialogTitle;
            return this;
        }

        public DialogExchangeModelBuilder setDialogContext(
                String dialogContext) {
            this.dialogContext = dialogContext;
            return this;
        }

        public DialogExchangeModelBuilder setHasTitle(boolean hasTitle) {
            this.hasTitle = hasTitle;
            return this;
        }

        public DialogExchangeModelBuilder setPostiveText(String postiveText) {
            this.postiveText = postiveText;
            return this;
        }

        public DialogExchangeModelBuilder setNegativeText(String negativeText) {
            this.negativeText = negativeText;
            return this;
        }

        public DialogExchangeModelBuilder setSingleText(String singleText) {
            this.singleText = singleText;
            return this;
        }

        public DialogExchangeModelBuilder setBackable(boolean isBackable) {
            this.isBackable = isBackable;
            return this;
        }

        public DialogExchangeModelBuilder setBussinessCancleable(
                boolean isBussinessCancleable) {
            this.isBussinessCancleable = isBussinessCancleable;
            return this;
        }

        public DialogExchangeModelBuilder setSpaceable(boolean isSpaceable) {
            this.isSpaceable = isSpaceable;
            return this;
        }

        public DialogExchangeModelBuilder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public DialogExchangeModelBuilder setCompatibilityListener(
                OnClickListener compatibilityListener) {
            this.compatibilityListener = compatibilityListener;
            return this;
        }

        public DialogExchangeModelBuilder setCompatibilityPositiveListener(
                OnClickListener compatibilityPositiveListener) {
            this.compatibilityPositiveListener = compatibilityPositiveListener;
            return this;
        }

        public DialogExchangeModelBuilder setCompatibilityNegativeListener(
                OnClickListener compatibilityNegativeListener) {
            this.compatibilityNegativeListener = compatibilityNegativeListener;
            return this;
        }

        public DialogExchangeModelBuilder setOnClick(OnClickListener clickListener) {
            this.onceClickListener = clickListener;
            return this;

        }

        public DialogExchangeModelBuilder setCustomerFragmentCallBack(
                CustomerFragmentCallBack callback) {
            this.customerFragmentCallBack = callback;
            return this;
        }

        public DialogExchangeModel creat() {
            return new DialogExchangeModel(this);
        }
    }
}
