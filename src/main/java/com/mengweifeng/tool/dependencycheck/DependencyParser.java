package com.mengweifeng.tool.dependencycheck;

import java.io.*;
import java.util.*;

/**
 * 依赖解析器
 */
public abstract class DependencyParser {

    private List<Dependency> allDependency = new ArrayList<>();

    private Map<String, Set<Dependency>> duplicateDependency = new HashMap<>();

    public void parse(File treeFile) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(treeFile)));

        String line = null;
        int currentLevel = 1;
        Dependency parentDependency = Dependency.ROOT;
        do {
            line = br.readLine();
            if (line == null) {
                break;
            }
            Dependency dependency = parseDependencyLine(line);
            if (dependency.getLevel() == currentLevel) {
            } else if (dependency.getLevel() > currentLevel) {
                parentDependency = allDependency.get(allDependency.size() - 1);
            } else if (dependency.getLevel() < currentLevel) {
                parentDependency = parentDependency.getParent();
            }
            dependency.setParent(parentDependency);
            parentDependency.addChild(dependency);
            allDependency.add(dependency);
            Set<Dependency> dependencies = duplicateDependency.get(dependency.getGa());
            if (dependencies == null) {
                dependencies = new HashSet<>();
                duplicateDependency.put(dependency.getGa(), dependencies);
            }
            dependencies.add(dependency);
        } while (true);
    }


    protected abstract Dependency parseDependencyLine(String dependencyLine);

}
