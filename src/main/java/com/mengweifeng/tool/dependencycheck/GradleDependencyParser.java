package com.mengweifeng.tool.dependencycheck;

public class GradleDependencyParser extends DependencyParser {
    @Override
    protected boolean isDependencyLine(String dependencyLine) {
        return false;
    }

    @Override
    protected Dependency parseDependencyLine(String dependencyLine) {
        return null;
    }
}
