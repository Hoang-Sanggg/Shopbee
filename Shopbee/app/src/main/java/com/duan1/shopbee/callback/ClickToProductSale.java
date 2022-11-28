package com.duan1.shopbee.callback;

import com.duan1.shopbee.model.Flashsale;

import java.util.List;

public interface ClickToProductSale {
    public void onClickToProductSale(List<Flashsale> flashsaleList, int position);
}
