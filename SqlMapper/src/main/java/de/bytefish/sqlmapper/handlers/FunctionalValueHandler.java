// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.handlers;

import de.bytefish.sqlmapper.functional.Func3;

import java.lang.reflect.Type;
import java.sql.ResultSet;

public class FunctionalValueHandler<TTargetType> extends BaseValueHandler<TTargetType> {

    private Class<TTargetType> targetType;
    private Func3<String, ResultSet, TTargetType> func;

    public FunctionalValueHandler(Func3 func, Class<TTargetType> targetType) {
        this.func = func;
        this.targetType = targetType;
    }

    @Override
    public TTargetType internalHandle(String columnName, ResultSet resultSet) throws Exception {
        return func.invoke(columnName, resultSet);
    }

    @Override
    public Type getTargetType() {
        return targetType;
    }
}
