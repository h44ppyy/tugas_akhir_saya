package tabian.com.actionbar.ui.hero;

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
import tabian.com.actionbar.data.Heroes;
import tabian.com.actionbar.glide.GlideApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tnno Wu on 2018/03/16.
 */

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder> {

    private static final String TAG = HeroesAdapter.class.getSimpleName();

    private Context mContext;

    private List<Heroes.ResultBean.HeroesBean> mList = new ArrayList<>();

    public HeroesAdapter(Context context) {
        mContext = context;
    }

    public void setHeroesDataList(List<Heroes.ResultBean.HeroesBean> list) {
        mList = list;

        Log.d(TAG, "setHeroesDataList: " + mList.size());

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HeroesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.heroes_recycle_item, parent, false);
        return new HeroesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroesViewHolder holder, int position) {
        String name = mList.get(position).getName();
        String ivName = name.substring(14, name.length());
        Log.d(TAG, "onBindViewHolder: " + ivName);

        // http://cdn.dota2.com/apps/dota2/images/heroes/antimage_full.png
        GlideApp.with(mContext)
                .load(Constant.HERO_IMAGE_URL + ivName + "_full.png")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.ivImg);

        holder.tvName.setText(mList.get(position).getLocalized_name());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class HeroesViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImg;
        TextView tvName;

        public HeroesViewHolder(View itemView) {
            super(itemView);
            ivImg = itemView.findViewById(R.id.iv_img);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
