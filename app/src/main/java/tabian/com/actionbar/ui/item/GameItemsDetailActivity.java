package tabian.com.actionbar.ui.item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tabian.com.actionbar.R;
import tabian.com.actionbar.eventbus.GameItemsEvent;
import tabian.com.actionbar.glide.GlideApp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Tnno Wu on 2018/3/18.
 */

public class GameItemsDetailActivity extends AppCompatActivity {

    private static final String TAG = GameItemsDetailActivity.class.getSimpleName();

    private int cost, secretShop, sideShop, recipe;
    private String localizedName, imgUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_items_detail_activity);

        EventBus.getDefault().register(this);

        initView();
    }

    private void initView() {
        ImageView ivImg = findViewById(R.id.iv_item_img);
        TextView tvName = findViewById(R.id.tv_item_name);
        TextView tvCost = findViewById(R.id.tv_item_cost);
        TextView tvSecretShop = findViewById(R.id.tv_item_secret_shop);
        TextView tvSideShop = findViewById(R.id.tv_item_side_shop);
        TextView tvRecipe = findViewById(R.id.tv_item_recipe);

        // 物品图
        GlideApp.with(this).load(imgUrl).into(ivImg);

        // 物品名
        tvName.setText(localizedName);

        // 物品所需金钱
        tvCost.setText("Price:" + cost);

        // 物品是否需要通过神秘商店合成
        if (secretShop == 1) {
            tvSecretShop.setVisibility(View.VISIBLE);
        } else {
            tvSecretShop.setVisibility(View.GONE);
        }

        // 物品是否在边路商店出现
        if (sideShop == 1) {
            tvSideShop.setVisibility(View.VISIBLE);
        } else {
            tvSideShop.setVisibility(View.GONE);
        }

        // 物品是否是卷轴类
        if (recipe == 1) {
            tvRecipe.setVisibility(View.VISIBLE);
        } else {
            tvRecipe.setVisibility(View.GONE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(GameItemsEvent event) {
        cost = event.getCost();
        secretShop = event.getSecret_shop();
        sideShop = event.getSide_shop();
        recipe = event.getRecipe();
        localizedName = event.getLocalized_name();
        imgUrl = event.getImg_url();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);

        super.onDestroy();
    }
}
