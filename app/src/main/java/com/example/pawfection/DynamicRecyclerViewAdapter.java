package com.example.pawfection;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pawfection.DynamicRecyclerViewInterface.LoadMore;

import java.util.List;

class LoadingViewHolder extends RecyclerView.ViewHolder {

    public ProgressBar progressBar;

    public LoadingViewHolder(@NonNull View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progress_bar);
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView lostPetAlertItemTextViewPetName;
    public TextView lostPetAlertItemTextViewLastSeen;
    public TextView lostPetAlertItemTextViewContactNumber;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        lostPetAlertItemTextViewPetName = (TextView)itemView.findViewById(R.id.lostPetAlertItemTextViewPetName);
        lostPetAlertItemTextViewLastSeen = (TextView)itemView.findViewById(R.id.lostPetAlertItemTextViewLastSeen);
        lostPetAlertItemTextViewContactNumber = (TextView)itemView.findViewById(R.id.lostPetAlertItemTextViewContactNumber);
    }
}

public class DynamicRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    LoadMore loadMore;
    boolean isLoading;
    Activity activity;
    List<DynamicRecyclerViewModel> items;
    int visibleThreshold = 5;
    int lastVisibleItem, totalItemCount;

    public DynamicRecyclerViewAdapter(RecyclerView recyclerView, Activity activity, List<DynamicRecyclerViewModel> items) {
        this.activity = activity;
        this.items = items;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (loadMore != null)
                        loadMore.onLoadMore();
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }

    public void setLoadMore(LoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_layout_dynamic_recycler_view_lost_pet_alert, parent, false);
            return  new LoadingViewHolder(view);
        }
        else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.progress_bar_dynamic_recycler_view_lost_pet_alert, parent, false);
            return new LoadingViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            DynamicRecyclerViewModel item = items.get(position);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.lostPetAlertItemTextViewPetName.setText(items.get(position).getPetName());
            viewHolder.lostPetAlertItemTextViewLastSeen.setText("Last seen on " + items.get(position).getLastSeen() + ".");
            viewHolder.lostPetAlertItemTextViewContactNumber.setText("Contact number: " + items.get(position).getContactNumber());
        }
        else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setLoaded() {
        isLoading = false;
    }
}
