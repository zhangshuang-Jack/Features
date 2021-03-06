package com.malata.superclean.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

/**
 * Created by xuxiantao on 2015/9/15.
 */
public class AlertDialogFragment extends DialogFragment {

    int mIcon;
    String mTitle;
    String mMessage;
    static View mContentView;
    static DialogOnClickListener mOnClickListener;

    public static AlertDialogFragment newInstance(int icon, String title, String message, View view, DialogOnClickListener dialogOnClickListener) {
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        mOnClickListener = dialogOnClickListener;
        mContentView = view;

        Bundle bundle = new Bundle();
        bundle.putInt("icon", icon);
        bundle.putString("title", title);
        bundle.putString("message", message);
        dialogFragment.setArguments(bundle);

        return dialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mIcon = getArguments().getInt("icon");
        mTitle = getArguments().getString("title");
        mMessage = getArguments().getString("message");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
        if(mIcon > 0) {
            builder.setIcon(mIcon);
        }

        if(mTitle != null) {
            builder.setTitle(mTitle);
        }

        if(mMessage != null) {
            builder.setMessage(mMessage);
        }

        if(mContentView != null) {
            builder.setView(mContentView);
        }

        if(mOnClickListener != null) {
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(mOnClickListener != null) {
                        mOnClickListener.onPositiveClick();
                    }
                }
            });

            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(mOnClickListener != null) {
                        mOnClickListener.onNegativeClick();
                    }
                }
            });
        }

        return builder.create();
    }

    public interface DialogOnClickListener {
        public void onPositiveClick();
        public void onNegativeClick();
    }

}
