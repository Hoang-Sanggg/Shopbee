package com.duan1.shopbee.callback;

import com.duan1.shopbee.model.Order;
import com.duan1.shopbee.model.ProductCreate;

import java.util.List;

public interface ClickNextStatus {
    public void onClickNextStatus(List<Order> list, int position, int id);
}
