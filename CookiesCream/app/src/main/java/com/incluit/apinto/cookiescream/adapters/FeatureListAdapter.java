package com.incluit.apinto.cookiescream.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.incluit.apinto.cookiescream.activities.AutofillFrameworkActivity;
import com.incluit.apinto.cookiescream.activities.AutosizingTextViewActivity;
import com.incluit.apinto.cookiescream.activities.ClustersNavigationActivity;
import com.incluit.apinto.cookiescream.activities.MainActivity;
import com.incluit.apinto.cookiescream.activities.NotificationsActivity;
import com.incluit.apinto.cookiescream.activities.PictureInPictureActivity;
import com.incluit.apinto.cookiescream.R;
import com.incluit.apinto.cookiescream.activities.UnifiedMarginsAndPaddingActivity;

import java.util.ArrayList;
import java.util.List;

public class FeatureListAdapter extends RecyclerView.Adapter<FeatureListAdapter.ViewHolder>{

    private final String PIPMODE = "Picture-in-Picture Mode";
    private final String NOTIFICATIONS = "Notifications";
    private final String AUTOFILL_FRAMEWORK = "AutoFill framework";
    private final String AUTISIZING_TEXTVIEW = "Autosizing TexView";
    private final String UNIFIED_LAYOUT = "Unified layout margin and padding";
    private final String NAVIGATION_CLUSTER = "Input and Navigation";

    private Context mContext;
    private List<String> mFeatureList;

    public FeatureListAdapter(Context context, List<String> list) {
        mContext = context;
        if (list == null) {
            mFeatureList = new ArrayList<>();
        } else {
            mFeatureList = list;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.feature_list_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.mItemName.setText(mFeatureList.get(i));
        viewHolder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class activity = MainActivity.class;
                switch (viewHolder.mItemName.getText().toString()) {
                    case PIPMODE:
                        activity = PictureInPictureActivity.class;
                        break;
                    case NOTIFICATIONS:
                        activity = NotificationsActivity.class;
                        break;
                    case AUTOFILL_FRAMEWORK:
                        activity = AutofillFrameworkActivity.class;
                        break;
                    case AUTISIZING_TEXTVIEW:
                        activity = AutosizingTextViewActivity.class;
                        break;
                    case UNIFIED_LAYOUT:
                        activity = UnifiedMarginsAndPaddingActivity.class;
                        break;
                    case NAVIGATION_CLUSTER:
                        activity = ClustersNavigationActivity.class;
                        break;
                }

                Intent intent = new Intent(mContext, activity);
                intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFeatureList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private View mItemView;
        private TextView mItemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            mItemName = itemView.findViewById(R.id.item_name);
        }
    }
}
