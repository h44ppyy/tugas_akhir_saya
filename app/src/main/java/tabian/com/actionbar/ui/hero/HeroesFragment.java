package tabian.com.actionbar.ui.hero;

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
import tabian.com.actionbar.data.Heroes;
import tabian.com.actionbar.retrofit.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Heroes
 *
 * Created by Tnno Wu on 2018/03/16.
 */

public class HeroesFragment extends Fragment {

    private static final String TAG = HeroesFragment.class.getSimpleName();

    private AppService mService;

    private HeroesAdapter mAdapter;

    private RecyclerView mRcv;
    private ProgressBar mProgressBar;

    public HeroesFragment() {
        // As a fragment, default constructor is needed.
    }

    public static HeroesFragment newInstance() {
        return new HeroesFragment();
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
        View view = inflater.inflate(R.layout.heroes_fragment, container, false);

        initView(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initHeroesData();
    }

    private void initService() {
        mService = new RetrofitClient().getService();
    }

    private void initView(View view) {
        mAdapter = new HeroesAdapter(getActivity());

        mRcv = view.findViewById(R.id.rcv_heroes);
        mProgressBar = view.findViewById(R.id.progress_bar);

        mRcv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRcv.setHasFixedSize(true);
        mRcv.setAdapter(mAdapter);

        mProgressBar.setVisibility(View.VISIBLE);
        mRcv.setVisibility(View.GONE);
    }

    private void initHeroesData() {
        Observable<Heroes> observable = mService.getHeroes(
                Constant.API_KEY,
                Constant.LANGUAGE
        );
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Heroes>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Heroes heroes) {
                        Log.d(TAG, "onNext: ");

                        mAdapter.setHeroesDataList(heroes.getResult().getHeroes());
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
}
