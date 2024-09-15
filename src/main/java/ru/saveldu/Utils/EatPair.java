package ru.saveldu.Utils;

import java.util.Objects;

//честно взято у ChatGPT
public class EatPair<A, B> {
    private A first;
    private B second;

    public EatPair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    // Getters, equals, hashCode
    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EatPair<?, ?> pair = (EatPair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
