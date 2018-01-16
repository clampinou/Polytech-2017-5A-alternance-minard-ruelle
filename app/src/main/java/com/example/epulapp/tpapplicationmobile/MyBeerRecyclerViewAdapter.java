package com.example.epulapp.tpapplicationmobile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.epulapp.tpapplicationmobile.BeerFragment.OnListFragmentInteractionListener;
import com.example.epulapp.tpapplicationmobile.dummy.DummyContent.DummyItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyBeerRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<BeerSerializable> mValues;
    private final PostItemListener mListener;
    private Context myContext;

    public MyBeerRecyclerViewAdapter(List<BeerSerializable> beers, RecyclerView recyclerView, PostItemListener listener) {
        mValues = beers;
        mListener = listener;

        // TODO : SCROLLING
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_beer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BeerViewHolder beerViewHolder = (BeerViewHolder) holder;

        BeerSerializable beer = mValues.get(position);
        beerViewHolder.beer = beer;
        beerViewHolder.mName.setText(beer.getName());
        beerViewHolder.mABV.setText(String.format("%.2f", beer.getAbv()) + "%");

        Picasso.with(this.myContext).load(beer.getImage_url()).placeholder(R.mipmap.ic_launcher).into(beerViewHolder.mImage);
    }

    public void createEmptyItem() {
        mValues.add(null);
        notifyItemInserted(mValues.size() - 1);
    }

    public void updateBeers(List<BeerSerializable> beers) {
        removeLastEmptyItem();

        for (BeerSerializable beer: beers) {
            if (!mValues.contains(beer)) {
                mValues.add(beer);
                notifyItemInserted(mValues.size());
            }
        }
    }

    public void removeLastEmptyItem() {
        mValues.remove(mValues.size() - 1);
        notifyItemRemoved(mValues.size());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.tvNomBeer);
            mContentView = (TextView) view.findViewById(R.id.tvAlcoolBeer);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public interface PostItemListener {
        void onBeerClick(BeerSerializable beer);
    }

    private BeerSerializable getBeer(int adaptedPosition) {
        return mValues.get(adaptedPosition);
    }

    public class BeerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View myView;
        public final ImageView mImage;
        public final TextView mName;
        public final TextView mABV;
        public BeerSerializable beer;
        PostItemListener myItemListener;

        public BeerViewHolder(View view) {
            super(view);
            myView = view;
            mImage = (ImageView)view.findViewById(R.id.ivBeer);
            mName = (TextView) view.findViewById(R.id.tvNomBeer);
            mABV = (TextView)view.findViewById(R.id.tvAlcoolBeer);
        }

        public BeerViewHolder(View view, PostItemListener postItemListener) {
            super(view);
            myView = view;
            mImage = (ImageView)view.findViewById(R.id.ivBeer);
            mName = (TextView) view.findViewById(R.id.tvNomBeer);
            mABV = (TextView)view.findViewById(R.id.tvAlcoolBeer);

            this.myItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            BeerSerializable beer = getBeer(getAdapterPosition());
            this.myItemListener.onBeerClick(beer);
            notifyDataSetChanged();
        }
    }
}
