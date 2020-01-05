package com.jasonette.seed.Rawfood;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

public class DjangoBackendService extends IntentService {

    public DjangoBackendService() {
        super("DjangoBackendService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String data_dir = getFilesDir().getAbsolutePath();
        Python py = Python.getInstance();

        PyObject test = py.getModule("run_server_android");
        test.callAttr("main", data_dir);
    }
}
