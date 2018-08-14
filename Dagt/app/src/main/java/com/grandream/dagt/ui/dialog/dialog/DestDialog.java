package com.grandream.dagt.ui.dialog.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.grandream.dagt.R;


public class DestDialog extends DialogFragment {
	private static final String TAG = "DestDialog";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_FRAME, R.style.ThemeHolo);
	}

	public void fixedShow(FragmentActivity activity, String tag) {
		if (activity == null || activity.isFinishing()) {
			Log.d(TAG, String.format("activity [%s] is null or is finishing!",
					activity));
			return;
		}
		FragmentTransaction ft = activity.getSupportFragmentManager()
				.beginTransaction();
		ft.add(this, tag);
		ft.commitAllowingStateLoss();
	}

	

}
