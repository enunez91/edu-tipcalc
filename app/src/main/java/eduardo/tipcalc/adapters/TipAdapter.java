package eduardo.tipcalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import eduardo.tipcalc.R;
import eduardo.tipcalc.models.TipRecord;

/**
 * Created by Eduardo on 7/3/2016.
 */
public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {
    Context context;
    List<TipRecord> dataset;

    public TipAdapter(List<TipRecord> dataset,Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    public void add(TipRecord record){
        dataset.add(0,record);
        notifyDataSetChanged();
    }

    public void clear(){
        dataset.clear();
        notifyDataSetChanged();
    }

    @Override
    public TipAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_tip_history,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TipAdapter.ViewHolder holder, int position) {
        TipRecord element = dataset.get(position);
        String strTip = String.format(
                context.getString(R.string.global_message_tip)
                ,element.getTip());
        holder.outputTipHistory.setText(strTip);
    }

    @Override
    public int getItemCount() {
        if(dataset!=null){
            return dataset.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.outputTipHistory) TextView outputTipHistory;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
