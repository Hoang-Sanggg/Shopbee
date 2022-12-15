package com.duan1.shopbee.callback;

import com.duan1.shopbee.model.ProductCreate;

import java.util.List;

public interface ClickToBuy {
    public void ClickToBuy(List<ProductCreate> cart, int position);

}
