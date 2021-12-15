package com.mengweifeng.tool.dependencycheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 依赖对象实体
 */
public class Dependency {
    public static final Dependency ROOT = new Dependency("ROOT", "ROOT", "ROOT", "ROOT", "ROOT", 0);

    /**
     * 上层依赖
     */
    private Dependency parent;
    private String group;
    private String artifactId;
    private String packaging;
    private String version;
    private String scope;
    private int level;


    private String ga;

    private List<Dependency> children = new ArrayList<>();

    public Dependency(String group, String artifactId, String packaging, String version, String scope, int level) {
        this.group = group;
        this.artifactId = artifactId;
        this.packaging = packaging;
        this.version = version;
        this.scope = scope;
        this.level = level;
        this.ga = group + ":" + artifactId;
    }

    public String getGa() {
        return ga;
    }

    public void addChild(Dependency dependency) {
        children.add(dependency);
    }

    public void setParent(Dependency parent) {
        this.parent = parent;
    }

    public Dependency getParent() {
        return parent;
    }

    public String getGroup() {
        return group;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getPackaging() {
        return packaging;
    }

    public String getVersion() {
        return version;
    }

    public String getScope() {
        return scope;
    }

    public List<Dependency> getChildren() {
        return children;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dependency that = (Dependency) o;
        return level == that.level &&
                Objects.equals(group, that.group) &&
                Objects.equals(artifactId, that.artifactId) &&
                Objects.equals(packaging, that.packaging) &&
                Objects.equals(version, that.version) &&
                Objects.equals(scope, that.scope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, artifactId, packaging, version, scope, level);
    }
}