package com.heaven7.android.demolib.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heaven7.android.demolib.activity.BaseActivity;
import com.heaven7.core.util.Logger;
import com.heaven7.core.util.Toaster;
import com.heaven7.core.util.ViewHelper;


/**
 * Created by heaven7 on 2015/8/14.
 */
public abstract class BaseFragment extends Fragment {

    private static final boolean sDebug = true;

    private Toaster mToaster;
    private ViewHelper mViewHelper;
    private ICallback mCallback;
    private Activity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }
    public BaseActivity getActivity2(){
        return (BaseActivity) mActivity;
    }

    protected void launchActivity(Class<?> clazz){
        mActivity.startActivity(new Intent(mActivity,clazz));
    }

    protected void launchActivity(Class<?> clazz,Bundle data){
        mActivity.startActivity(new Intent(mActivity, clazz).putExtras(data));
    }
    protected void launchActivity(Class<?> clazz,Bundle data,int flags){
        mActivity.startActivity(new Intent(mActivity, clazz)
                        .putExtras(data).addFlags(flags)
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(),container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Context ctx = view.getContext();
        mToaster = new Toaster(ctx);
        mViewHelper = new ViewHelper(view);
      //  mHttpExecutor = new VolleyUtil.HttpExecutor();
        init(ctx, savedInstanceState);
    }

    protected void showToast(String msg){
        mToaster.show(msg);
    }
    protected void showToast(int resID){
        mToaster.show(resID);
    }
    protected void showToastIfDebug(String msg){
        if(sDebug) mToaster.show(msg);
    }
    protected void showToastIfDebug(int resID){
        if(sDebug) mToaster.show(resID);
    }

    public Toaster getToaster() {
        return mToaster;
    }
    public ViewHelper getViewHelper() {
        return mViewHelper;
    }

    public ICallback getCallback() {
        return mCallback;
    }
    public void setCallback(ICallback mCallback) {
        this.mCallback = mCallback;
    }
    public void callbackIfNeed(int key,Object data){
        if(mCallback != null){
            mCallback.callback(key,data);
        }else{
            Logger.w("BaseFragment_ICallback","callbackIfNeed"," but mCallback is null !");
        }
    }

    protected abstract int getLayoutId();

    protected abstract void init(Context context, Bundle savedInstanceState);

    public  interface ICallback{
       void callback(int key, Object data);
    }
}
