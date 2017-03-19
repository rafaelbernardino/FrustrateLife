package com.bernardino.frustratelife.listener;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Rafael on 18/03/2017.
 */

public interface OnItemLongClickListener {
    public void onItemLongClick(AdapterView<?> adapterView, View view, int position, long id);
}
