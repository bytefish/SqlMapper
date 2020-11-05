// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.handlers;

import java.lang.reflect.Type;
import java.sql.ResultSet;

public class DoubleValueHandler extends BaseValueHandler<Double> {

    @Override
    public Type getTargetType() {
        return Double.class;
    }

    @Override
    protected Double internalHandle(String columnName, ResultSet resultSet) throws Exception {
        return resultSet.getDouble(columnName);
    }
}
