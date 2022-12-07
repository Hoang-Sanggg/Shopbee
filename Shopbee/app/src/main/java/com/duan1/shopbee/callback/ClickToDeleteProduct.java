package com.duan1.shopbee.callback;

import com.duan1.shopbee.model.ProductCreate;

import java.util.List;

public interface ClickToDeleteProduct {
    public void onClickToDeleteProduct(List<ProductCreate> flashsaleList, int position);

}
