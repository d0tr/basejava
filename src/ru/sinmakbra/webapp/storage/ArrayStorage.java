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
        if (isAvailableResume(r.getUuid())) {
            Resume findResume = get(r.getUuid());
            findResume = r;
        }
    }

    public void save(Resume r) {
        if (get(r.getUuid()) == null && size < CAPACITY) {
            storage[size++] = r;
        } else {
            printExistResumeError(r.getUuid());
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        printAvailableResumeError(uuid);
        return null;
    }

    public void delete(String uuid) {
        if (isAvailableResume(uuid)) {
            for (int i = 0, j = 0; i < size; i++, j++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    break;
                }
            }
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

    private boolean isAvailableResume(String uuid) {
        if (get(uuid) != null) {
            return true;
        }
        printAvailableResumeError(uuid);
        return false;
    }

    private void printAvailableResumeError(String uuid) {
        System.out.println(String.format("Резюме с указанным uuid: %s не найдено", uuid));
    }

    private void printExistResumeError(String uuid) {
        System.out.println(String.format("Резюме с указанным uuid: %s уже существует", uuid));
    }
}
