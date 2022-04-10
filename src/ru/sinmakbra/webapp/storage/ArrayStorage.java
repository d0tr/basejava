package ru.sinmakbra.webapp.storage;

import ru.sinmakbra.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final static int CAPACITY = 10_000;

    private int size = 0;

    private Resume[] storage = new Resume[CAPACITY];

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            printNotAvailableError(r.getUuid());
        } else {
            storage[index] = r;
        }
    }

    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            printExistError(r.getUuid());
        } else if (size >= CAPACITY) {
            System.out.println("Превышение количества возможных резюме");
        } else {
            storage[size++] = r;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            printNotAvailableError(uuid);
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            printNotAvailableError(uuid);
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private void printNotAvailableError(String uuid) {
        System.out.println(String.format("Резюме с указанным uuid: %s не найдено", uuid));
    }

    private void printExistError(String uuid) {
        System.out.println(String.format("Резюме с указанным uuid: %s уже существует", uuid));
    }
}
