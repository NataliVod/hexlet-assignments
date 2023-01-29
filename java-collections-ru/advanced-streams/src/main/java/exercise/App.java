package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
class App {
    public static void main (String[] args) {
        String config = "[program:prepare]\n" +
                "command=sudo -HEu tirion /bin/bash -c 'cd /usr/src/app && make prepare'\n" +
                "autorestart=false\n" +
                "environment=\"X_FORWARDED_MAIL=tirion@google.com,X_FORWARDED_HOME=/home/tirion,language=en\"\n" +
                "\n" +
                "[program:http_server]\n" +
                "command=sudo -HEu tirion /bin/bash -c 'cd /usr/src/app && make environment'\n" +
                "environment=\"key5=value5,X_FORWARDED_var3=value,key6=value6\"";
        System.out.println(getForwardedVariables(config));
    }
    public static String getForwardedVariables(String config) {
        return Stream.of(config.split("\n"))
                .filter(str -> str.startsWith("environment"))
                .map(str -> str.replaceAll("environment=",""))
                .flatMap(str ->Stream.of(str.split(",")))
                .map(str -> str.replaceAll("\"",""))
                .filter(str -> str.startsWith("X_FORWARDED_"))
                .map(str -> str.replaceAll("X_FORWARDED_",""))
                .collect(Collectors.joining(","));

    }
}
//END
