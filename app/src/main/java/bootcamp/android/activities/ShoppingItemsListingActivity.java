package bootcamp.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import bootcamp.android.R;
import bootcamp.android.adapters.CustomAdapter;
import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;

import java.util.List;

public class ShoppingItemsListingActivity extends Activity {

    private ProductRepository productRepository;
    private Intent productDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        productRepository = new ProductRepository();
        GridView layout = (GridView) findViewById(R.id.listofitems);
        final List<Product> products  = productRepository.getProducts();
//        android.R.layout.simple_list_item_1  -> default Layout resource
//        layout.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products));
        layout.setAdapter(new CustomAdapter(this,products));

        layout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                productDetail = new Intent(ShoppingItemsListingActivity.this, ProductDetailsActivity.class);
                productDetail.putExtra("product_detail",products.get(position).toString());
                startActivity(productDetail);
            }
        });
    }
}
