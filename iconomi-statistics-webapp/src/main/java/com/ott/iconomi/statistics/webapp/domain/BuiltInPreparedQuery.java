package com.ott.iconomi.statistics.webapp.domain;

import java.util.List;

public class BuiltInPreparedQuery extends BuiltInQuery{

    public static class Argument {

        public static enum Type {
            DATE
        }

        private String label;
        private Type type;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }
    }

    private List<Argument> arguments;

    public List<Argument> getArguments() {
        return arguments;
    }

    public void setArguments(List<Argument> arguments) {
        this.arguments = arguments;
    }
}
