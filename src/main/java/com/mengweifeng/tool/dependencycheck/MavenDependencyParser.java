package com.mengweifeng.tool.dependencycheck;

public class MavenDependencyParser extends DependencyParser {
    @Override
    protected boolean isDependencyLine(String dependencyLine) {
        if (dependencyLine.indexOf("+- ") < 0 && dependencyLine.indexOf("\\- ") < 0) {
            return false;
        }
        return true;
    }

    @Override
    protected Dependency parseDependencyLine(String dependencyLine) {
        dependencyLine = dependencyLine.substring(7);

        int level = 1;
        do {
            if (dependencyLine.startsWith("+- ") || dependencyLine.startsWith("\\- ")) {
                dependencyLine = dependencyLine.substring(3);
                break;
            }
            dependencyLine = dependencyLine.substring(3);
            level = level + 1;
        } while (true);

        String[] fields = dependencyLine.split(":");
        String group = (fields[0]);
        String artifactId = (fields[1]);
        String packaging = (fields[2]);
        String version = (fields[3]);
        String scope = (fields[4]);
        Dependency dependency = new Dependency(group, artifactId, packaging, version, scope, level);

        return dependency;
    }
}
