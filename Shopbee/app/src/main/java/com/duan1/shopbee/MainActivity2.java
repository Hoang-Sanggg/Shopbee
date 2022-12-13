package com.duan1.shopbee;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duan1.shopbee.adapter.CartAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView rcvCart;
    private CartAdapter cartAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvCart = findViewById(R.id.rvc_cart);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvCart.setLayoutManager(linearLayoutManager);

        cartAdapter = new CartAdapter(getListCart());
        rcvCart.setAdapter(cartAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvCart.addItemDecoration(itemDecoration);
    }

    private List<Cart> getListCart() {
        List<Cart> list = new ArrayList<>();
        list.add(new Cart(R.drawable.avatar,"San pham A","15000D"));
        list.add(new Cart(R.drawable.avatar,"San pham B","16000D"));
        list.add(new Cart(R.drawable.avatar,"San pham C","17000D"));
        list.add(new Cart(R.drawable.avatar,"San pham D","18000D"));
        list.add(new Cart(R.drawable.avatar,"San pham E","19000D"));

        list.add(new Cart(R.drawable.avatar,"San pham AA","15D"));
        list.add(new Cart(R.drawable.avatar,"San pham BB","16D"));
        list.add(new Cart(R.drawable.avatar,"San pham CC","17D"));
        list.add(new Cart(R.drawable.avatar,"San pham DD","18D"));
        list.add(new Cart(R.drawable.avatar,"San pham EE","19D"));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =(SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                cartAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cartAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onBackPressed() {
        if(!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}