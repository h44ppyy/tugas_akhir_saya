package tabian.com.actionbar.ui.item;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import tabian.com.actionbar.R;
import tabian.com.actionbar.data.Constant;
import tabian.com.actionbar.data.GameItems;
import tabian.com.actionbar.glide.GlideApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tnno Wu on 2018/03/16.
 */

public class GameItemsAdapter extends RecyclerView.Adapter<GameItemsAdapter.GameItemsViewHolder> {

    private static final String TAG = GameItemsAdapter.class.getSimpleName();

    private Context mContext;

    private List<GameItems.ResultBean.ItemsBean> mList = new ArrayList<>();

    private OnItemClickListener mListener;

    public GameItemsAdapter(Context context, OnItemClickListener listener) {
        mContext = context;

        mListener = listener;
    }

    public void setGameItemsDataList(List<GameItems.ResultBean.ItemsBean> list) {
        mList = list;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GameItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.game_items_recycle_item, parent, false);
        return new GameItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameItemsViewHolder holder, int position) {
        String name = mList.get(position).getName();
        String ivName = name.substring(5, name.length());
        Log.d(TAG, "onBindViewHolder: " + ivName);

        // http://cdn.dota2.com/apps/dota2/images/items/blink_lg.png
        GlideApp.with(mContext)
                .load(Constant.ITEM_IMAGE_URL + ivName + "_lg.png")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.ivImg);

        holder.tvName.setText(mList.get(position).getLocalized_name());

        final int cost = mList.get(position).getCost();
        final int secretShop = mList.get(position).getSecret_shop();
        final int sideShop = mList.get(position).getSide_shop();
        final int recipe = mList.get(position).getRecipe();
        final String localizedName = mList.get(position).getLocalized_name();
        final String imgUrl = Constant.ITEM_IMAGE_URL + ivName + "_lg.png";

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(cost, secretShop, sideShop, recipe, localizedName, imgUrl);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class GameItemsViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImg;
        TextView tvName;

        public GameItemsViewHolder(View itemView) {
            super(itemView);
            ivImg = itemView.findViewById(R.id.iv_item_img);
            tvName = itemView.findViewById(R.id.tv_item_name);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(
                int cost,
                int secretShop,
                int sideShop,
                int recipe,
                String localizedName,
                String imgUrl
        );
    }
}
