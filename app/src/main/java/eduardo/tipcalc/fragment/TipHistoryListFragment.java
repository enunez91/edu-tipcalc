package eduardo.tipcalc.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import eduardo.tipcalc.R;
import eduardo.tipcalc.activity.TipDetailActivity;
import eduardo.tipcalc.adapters.OnItemClickListener;
import eduardo.tipcalc.adapters.TipAdapter;
import eduardo.tipcalc.models.TipRecord;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener,
        OnItemClickListener
{
    @BindView(R.id.recyclerViewTipHistory)
    RecyclerView recyclerViewTipHistory;
    TipAdapter adapter;

    public TipHistoryListFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        ButterKnife.bind(this,view);
        initAdapter();
        initRecyclerView();
        return view;
    }

    @Override
    public void addToList(TipRecord record) {
        adapter.add(record);
    }

    @Override
    public void clearList() {
        adapter.clear();
    }

    private void initAdapter(){
        if(adapter == null){
            adapter = new TipAdapter(getActivity().getApplicationContext(),this);
        }
    }

    private void initRecyclerView(){
        recyclerViewTipHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewTipHistory.setAdapter(adapter);
    }

    @Override
    public void onItemClick(TipRecord tipRecord) {
        Intent intent = new Intent(getContext(),TipDetailActivity.class);
        intent.putExtra(TipDetailActivity.BILL_TOTAL_KEY,tipRecord.getBill());
        intent.putExtra(TipDetailActivity.TIP_KEY,tipRecord.getTip());
        intent.putExtra(TipDetailActivity.DATE_KEY,tipRecord.getDateFormatted());
        startActivity(intent);
    }
}
