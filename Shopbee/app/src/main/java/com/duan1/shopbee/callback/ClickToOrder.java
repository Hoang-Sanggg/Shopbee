package com.duan1.shopbee.callback;

import com.duan1.shopbee.model.Order;


import java.util.List;

public interface ClickToOrder {
    public void ClickToOrder(List<Order> orderList, int position);
}
