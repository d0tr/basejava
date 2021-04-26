import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final static int CAPACITY = 10_000;

    private int size = 0;

    Resume[] storage = new Resume[CAPACITY];

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (size < CAPACITY) {
            storage[size++] = r;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0, j = 0; i < size; i++, j++) {
            if (j < size) {
                if (storage[i].uuid.equals(uuid)) {
                    storage[i] = ++j < size ? storage[j] : null;
                } else {
                    storage[i] = storage[j];
                }
            } else {
                storage[i] = null;
            }
        }
        --size;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int size = size();
        Resume[] result = new Resume[size];
        for (int i = 0; i < size; i++) {
            result[i] = storage[i];
        }
        return result;
    }

    int size() {
        return size;
    }
}
