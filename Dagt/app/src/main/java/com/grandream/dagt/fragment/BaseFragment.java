package com.grandream.dagt.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.grandream.dagt.R;
import com.grandream.dagt.base.BaseFragmentActivity;
import com.grandream.dagt.common.AppContext;
import com.grandream.dagt.fragment.help.FragmentExchangeConroller;
import com.grandream.dagt.utils.ACache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenjing on 2018/3/9.
 */

public class BaseFragment extends Fragment {
    public ACache aCache;
    protected BaseFragmentActivity mBaseActivity;
    public Map<String, Object> parameters = new HashMap<String, Object>();
    public final int IMAGE_CODE = 0;
    public final int CAMERA_INTENT_REQUEST = 0XFF02;
    public String FRAGMENTERROR = "ERROR";
    private static final String NAVIGATIONBAR_IS_MIN = "navigationbar_is_min";

    public static BaseFragment getInstance(Bundle bundle) {
        BaseFragment baseFragmentV2 = new BaseFragment();
        baseFragmentV2.setArguments(bundle);
        return baseFragmentV2;
    }

    public BaseFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aCache = AppContext.getInstance().getACache();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            mBaseActivity = (BaseFragmentActivity) getActivity();
        } catch (Exception e) {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
        }
    }

    @Override
    public void onPause() {
//         SenduserActionStat();
        super.onPause();
    }


    /**-----------------------------------頁面控制----------------------------------------**/
    /**
     * 页面控制返回
     */
    protected void sendKeyBackEvent() {
        if (isSoftShowing()) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        final Context context = getActivity();
        if (context instanceof BaseFragmentActivity) {
            ((BaseFragmentActivity) context).onBackPressed();
        } else if (context instanceof Activity) {
            KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_BACK);
            ((Activity) context).onKeyDown(KeyEvent.KEYCODE_BACK, keyEvent);
        }
    }


    private boolean isSoftShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getActivity().getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        //DecorView即为activity的顶级view
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //考虑到虚拟导航栏的情况（虚拟导航栏情况下：screenHeight = rect.bottom + 虚拟导航栏高度）
        //选取screenHeight*2/3进行判断
        return screenHeight * 2 / 3 > rect.bottom;
    }


    /**
     * context安全性判断，主要是各种tab的时候，在切换的时候，dialog还没有完成所有的调用，所对应的context已经被destroy或正在destroy。会导致crash
     *
     * @param c
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private boolean isValidContext(Context c) {
        Activity a = (Activity) c;
        if (a.isDestroyed() || a.isFinishing()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 销毁当前页面
     */

    public void dismissSelf() {
        FragmentExchangeConroller
                .removeFragment(getFragmentManager(), this);
    }


    /**
     * 获取页面className
     *
     * @return
     */
    public String getTagName() {
        return BaseFragment.this.getClass().getName();
    }


    public interface OnActivityResultListener {
        void onActivityResult(FragmentActivity activity, int requestCode, int resultCode, Intent data);
    }


    public void showCustomAlertDialog(Context context) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context)
                .create();
        alertDialog.show();
        WindowManager m = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay();
        Window win = alertDialog.getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = (int) (d.getWidth());
        win.setGravity(Gravity.BOTTOM);
        win.setAttributes(lp);
        win.setContentView(R.layout.choose_img_dialog);

        Button cancelBtn = (Button) win.findViewById(R.id.camera_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                alertDialog.cancel();
            }
        });
        Button camera_phone = (Button) win.findViewById(R.id.camera_phone);
        camera_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                systemPhoto();
                alertDialog.cancel();
            }

        });
    }

    /**
     * 打开系统相册
     */
    @SuppressLint("InlinedApi")
    protected void systemPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @TargetApi(19)
    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/"
                            + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection,
                        selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri,
                                       String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection,
                    selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri
                .getAuthority());
    }


}
