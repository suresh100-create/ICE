package com.ice.problem1;


public class AppConfigCommon {
    static String getPathToTargetFile(String[] args) {
        if (args.length >= 1) {
            return args[0];
        }
        return "src/main/resources/test.txt";
    }

    private AppConfigCommon() {}
}