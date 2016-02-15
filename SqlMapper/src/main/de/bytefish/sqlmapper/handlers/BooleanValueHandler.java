// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.handlers;

import java.lang.reflect.Type;
import java.sql.ResultSet;

public class BooleanValueHandler extends BaseValueHandler<Boolean> {
    @Override
    protected Boolean internalHandle(String columnName, ResultSet resultSet) throws Exception {
        return resultSet.getBoolean(columnName);
    }

    @Override
    public Type getTargetType() {
        return Boolean.class;
    }
}
