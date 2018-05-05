package br.com.pomodoro.util;

import java.util.Collection;

/**
 * Created by filipenunes on 04/20/18.
 */

public class ListUtils {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
