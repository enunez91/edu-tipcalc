package eduardo.tipcalc.fragment;

import eduardo.tipcalc.models.TipRecord;

/**
 * Created by Eduardo on 6/26/2016.
 */
public interface TipHistoryListFragmentListener {
    void addToList(TipRecord record);
    void clearList();
}
