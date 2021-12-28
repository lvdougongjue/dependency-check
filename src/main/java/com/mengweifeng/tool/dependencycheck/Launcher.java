package com.mengweifeng.tool.dependencycheck;

import java.io.File;
import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0) {
            System.exit(1);
        }

        String codeDir = args[0];
        String type = args[1];

        DependencyParser parser = null;
        File treeFile = new File(codeDir, "tree.txt");
        if ("maven".equals(type)) {
            parser = new MavenDependencyParser();
        } else if ("gradle".equals(type)) {
            parser = new GradleDependencyParser();
        } else {
            System.exit(1);
        }

        parser.parse(treeFile);

    }
}
