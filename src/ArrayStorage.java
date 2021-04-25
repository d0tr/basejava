import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final static int SIZE = 10_000;

    Resume[] storage = new Resume[SIZE];

    void clear() {
        int size = getAll().length;
        for (int i = 0; i < size; i ++) {
            this.storage[i] = null;
        }
    }

    void save(Resume r) {
        if (size() < SIZE) {
            this.storage[size()] = r;
        }
    }

    Resume get(String uuid) {
        int size = getAll().length;
        for (int i = 0; i < size; i++) {
            if (this.storage[i].uuid.equals(uuid)) {
                return this.storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        Resume[] result = new Resume[SIZE];
        int size = getAll().length;
        for (int i = 0; i < size; i++) {
            if (this.storage[i].uuid.equals(uuid)) {
                this.storage[i] = null;
                break;
            }
        }
        int i = 0;
        for (Resume r : this.storage) {
            if (r != null) {
                result[i++] = r;
            }
        }
        this.storage = result;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int size = size();
        Resume[] result = new Resume[size];
        for (int i = 0; i < size; i++) {
            result[i] = this.storage[i];
        }
        return result;
    }

    int size() {
        return Arrays.stream(this.storage).filter(x -> x != null).toArray().length;
    }
}
