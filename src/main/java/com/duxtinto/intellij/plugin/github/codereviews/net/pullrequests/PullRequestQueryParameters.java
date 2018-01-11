package com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class PullRequestQueryParameters {
    abstract State state();
    abstract String head(); // Format: user:ref-name
    abstract String base();
    abstract Sort sort();
    abstract Direction direction();
    abstract int pageSize();

    public static Builder builder() {
        return new AutoValue_PullRequestQueryParameters
                .Builder()
                .setState(State.OPEN)
                .setHead("")
                .setBase("")
                .setSort(Sort.CREATED)
                .setDirection(Direction.DESC)
                .setPageSize(100);
    }

    public String toQueryString() {
        return "per_page=" + this.pageSize() +
                "&state=" + this.state() +
                (!this.head().isEmpty() ? "&head=" + this.head() : "") +
                (!this.base().isEmpty() ? "&base=" + this.base() : "") +
                "&sort=" + this.sort() +
                "&direction=" + this.direction();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setState(State state);
        public abstract Builder setHead(String head);
        public abstract Builder setBase(String base);
        public abstract Builder setSort(Sort sort);
        public abstract Builder setDirection(Direction direction);
        public abstract Builder setPageSize(int size);
        public abstract PullRequestQueryParameters build();
    }

    public enum State {
        OPEN("open"),
        CLOSED("closed"),
        ALL("all");

        private final String state;

        State(String state) {
            this.state = state;
        }

        @Override
        public String toString() {
            return state;
        }
    }

    public enum Sort {
        CREATED("created"),
        UPDATED("updated"),
        POPULARITY("popularity"),
        LONG_RUNNING("long-runing"); // age, filtering by pulls updated in the last month

        private final String sort;

        Sort(String sort) {
            this.sort = sort;
        }

        @Override
        public String toString() {
            return sort;
        }
    }

    public enum Direction {
        ASC("asc"),
        DESC("desc");

        private final String direction;

        Direction(String direction) {
            this.direction = direction;
        }

        @Override
        public String toString() {
            return direction;
        }
    }
}
