package eduardo.tipcalc.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import eduardo.tipcalc.R;
import eduardo.tipcalc.adapters.TipAdapter;
import eduardo.tipcalc.models.TipRecord;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener{
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
        if(adapter!=null){
            adapter = new TipAdapter(new ArrayList<TipRecord>()
                    ,getActivity().getApplicationContext());
        }
    }

    private void initRecyclerView(){
        recyclerViewTipHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewTipHistory.setAdapter(adapter);
    }
}
