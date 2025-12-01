
package library.catalog;

import library.model.Item;
import library.exceptions.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic catalog that provides CRUD for items.
 */
public class GenericCatalog<T extends Item> {
    protected List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public void update(String callNumber, T updated) throws ItemNotFoundException {
        T existing = findByCallNumber(callNumber);
        existing.setTitle(updated.getTitle());
        existing.setCallNumber(updated.getCallNumber());
        // subclasses should copy their specific fields externally if needed
    }

    public void remove(String callNumber) throws ItemNotFoundException {
        T found = findByCallNumber(callNumber);
        items.remove(found);
    }

    public T findByCallNumber(String callNumber) throws ItemNotFoundException {
        for (T it : items) {
            if (it.getCallNumber().equalsIgnoreCase(callNumber)) return it;
        }
        throw new ItemNotFoundException("Item with call number " + callNumber + " not found.");
    }

    public T[] toArray() {
        // demonstrate arrays usage: convert list to array
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) items.toArray(new Item[0]);
        return arr;
    }

    public List<T> getAll() { return new ArrayList<>(items); }
}

