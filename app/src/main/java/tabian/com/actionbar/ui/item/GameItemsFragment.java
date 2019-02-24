package tabian.com.actionbar.ui.item;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import tabian.com.actionbar.R;
import tabian.com.actionbar.api.AppService;
import tabian.com.actionbar.data.Constant;
import tabian.com.actionbar.data.GameItems;
import tabian.com.actionbar.eventbus.GameItemsEvent;
import tabian.com.actionbar.retrofit.RetrofitClient;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tnno Wu on 2018/03/16.
 */

public class GameItemsFragment extends Fragment implements GameItemsAdapter.OnItemClickListener {

    private static final String TAG = GameItemsFragment.class.getSimpleName();

    private AppService mService;

    private GameItemsAdapter mAdapter;

    private RecyclerView mRcv;
    private ProgressBar mProgressBar;

    public GameItemsFragment() {
        // As a fragment, default constructor is needed.
    }

    public static GameItemsFragment newInstance() {
        return new GameItemsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initService();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_items_fragment, container, false);

        initView(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initGameItemsData();
    }

    private void initService() {
        mService = new RetrofitClient().getService();
    }

    private void initView(View view) {
        mAdapter = new GameItemsAdapter(getActivity(), this);

        mRcv = view.findViewById(R.id.rcv_items);
        mProgressBar = view.findViewById(R.id.progress_bar);

        mRcv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRcv.setHasFixedSize(true);
        mRcv.setAdapter(mAdapter);

        mProgressBar.setVisibility(View.VISIBLE);
        mRcv.setVisibility(View.GONE);
    }

    private void initGameItemsData() {
        Observable<GameItems> observable = mService.getGameItems(
                Constant.API_KEY,
                Constant.LANGUAGE
        );
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GameItems>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(GameItems gameItems) {
                        Log.d(TAG, "onNext: ");

                        mAdapter.setGameItemsDataList(gameItems.getResult().getItems());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");

                        mProgressBar.setVisibility(View.GONE);
                        mRcv.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    public void onItemClick(int cost, int secretShop, int sideShop, int recipe,
                            String localizedName, String imgUrl) {
        Log.d(TAG, "cost: " + cost);
        Log.d(TAG, "secretShop: " + secretShop);
        Log.d(TAG, "sideShop: " + sideShop);
        Log.d(TAG, "recipe: " + recipe);
        Log.d(TAG, "localizedName: " + localizedName);
        Log.d(TAG, "imgUrl: " + imgUrl);

        Intent intent = new Intent(getActivity(), GameItemsDetailActivity.class);

        EventBus.getDefault().postSticky(new GameItemsEvent(
                cost,
                secretShop,
                sideShop,
                recipe,
                localizedName,
                imgUrl
        ));

        startActivity(intent);
    }
}
