package com.duan1.shopbee.callback;

import com.duan1.shopbee.model.ProductCreate;

import java.util.List;

public interface ClickToUpdateProduct {
    public void onClickToUpdateroduct(List<ProductCreate> flashsaleList, int position);
}
