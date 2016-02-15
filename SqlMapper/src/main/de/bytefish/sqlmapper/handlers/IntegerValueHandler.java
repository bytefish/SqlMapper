// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.handlers;

import java.lang.reflect.Type;
import java.sql.ResultSet;

public class IntegerValueHandler extends BaseValueHandler<Integer> {

    @Override
    public Type getTargetType() {
        return Integer.class;
    }

    @Override
    protected Integer internalHandle(String columnName, ResultSet resultSet) throws Exception {
        return resultSet.getInt(columnName);
    }
}
