package org.example.week07.TDD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public String greet(Object o) {
        if (o == null) {
            return "Hello, my friend.";
        } else if (o instanceof String) {
            if (((String) o).equals(((String) o).toUpperCase())) {
                return "HELLO, " + o + "!";
            } else {
                return "Hello, " + o + ".";
            }
        } else {
            String[] names = (String[]) o;
            StringBuilder sb = new StringBuilder("Hello, ");
            if (names.length == 2) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].contains(",")) {
                        if (names[i].contains("\"")) {
                            names[i] = names[i].replace("\"", "");
                            sb.replace(sb.length() , sb.length(),  names[i] + ".");
                        }else {
                            names[i] = names[i].split(",")[0] + " and" + names[i].split(",")[1];
                            sb.replace(sb.length() - 5, sb.length(), ", " + names[i] + ".");
                        }
                    }
                    else if (i < names.length - 1) {
                        sb.append(names[i]);
                        sb.append(" and ");
                    } else {
                        sb.append(names[i]);
                        sb.append(".");

                    }
                }

            } else if (names.length > 2) {
                String last = null;
                for (int i = 0; i < names.length; i++) {

                    if (names[i].equals(names[i].toUpperCase())) {
                        last = names[i];
                        List<String> list = new ArrayList<>(Arrays.asList(names));
                        list.remove(i);
                        names = list.toArray(new String[0]);
                    }

                }
                for (int i = 0; i < names.length; i++) {
                    if (names[i] != null) {
                        if (i < names.length - 2) {
                            sb.append(names[i]);
                            sb.append(", ");
                        } else if (i == names.length - 2) {
                            sb.append(names[i]);
                            sb.append(" and ");
                        } else {
                            sb.append(names[i]);
                            sb.append(".");
                        }
                    }
                }

                if (last != null) {
                    sb.append(" AND HELLO, " + last + "!");
                }

            }
            
            return sb.toString();
        }

    }

}





