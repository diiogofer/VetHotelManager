package hva.core.sorter;

import java.util.Comparator;
import hva.core.Identifiable;

public class SortById<T extends Identifiable> implements Comparator<T>{
    @Override
    public int compare(T t1, T t2) {
        return (t1.getId().toLowerCase()).compareTo(t2.getId().toLowerCase());
    }
}
