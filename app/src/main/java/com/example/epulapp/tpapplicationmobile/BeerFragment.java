package com.example.epulapp.tpapplicationmobile;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class BeerFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView myRecyclerView;
    private MyBeerRecyclerViewAdapter myBeerRecyclerViewAdapter;
    private BeerService.IBeerService iBeerService;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BeerFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static BeerFragment newInstance(int columnCount) {
        BeerFragment fragment = new BeerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            iBeerService = BeerService.getRSClient();
            myRecyclerView = (RecyclerView)view.findViewById(R.id.list);
            myBeerRecyclerViewAdapter = new MyBeerRecyclerViewAdapter(new ArrayList<BeerSerializable>(0), myRecyclerView, new MyBeerRecyclerViewAdapter.PostItemListener() {
                @Override
                public void onBeerClick(BeerSerializable beer) {
                    mListener.onListFragmentInteraction(beer);
                }
            });

            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadMoreBeers();
                }
            }, 1500);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void loadMoreBeers() {
        myBeerRecyclerViewAdapter.createEmptyItem();

        iBeerService.getBeers().enqueue(new Callback<List<BeerSerializable>>() {
            @Override
            public void onResponse(Call<List<BeerSerializable>> call, Response<List<BeerSerializable>> response) {
                if (response.isSuccessful()) {
                    List<BeerSerializable> beers = response.body();
                    if (beers.size() > 0) {
                        myBeerRecyclerViewAdapter.updateBeers(response.body());
                    } else {
                        myBeerRecyclerViewAdapter.removeLastEmptyItem();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BeerSerializable>> call, Throwable t) {
                myBeerRecyclerViewAdapter.removeLastEmptyItem();

                CharSequence charSequence = "Impossible de charger les bières :(";
                Toast toast = Toast.makeText(getContext(), charSequence, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(BeerSerializable beer);
    }
}
