// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.utils;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StringUtils {

    private StringUtils() {}

    public static boolean isNullOrWhiteSpace(String input) {
        return  input == null || input.trim().length() == 0;
    }
}