package com.duan1.shopbee.callback;

import com.duan1.shopbee.model.Flashsale;
import com.duan1.shopbee.model.ProductCreate;

import java.util.List;

public interface ClickToProductSale {
    public void onClickToProductSale(List<ProductCreate> flashsaleList, int position);
}
