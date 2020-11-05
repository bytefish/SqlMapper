// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.handlers;

import java.sql.ResultSet;

public abstract class BaseValueHandler<TTargetType> implements IValueHandler<TTargetType> {

    @Override
    public TTargetType handle(String columnName, ResultSet resultSet) {
        try {
            return internalHandle(columnName, resultSet);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract TTargetType internalHandle(String columnName, ResultSet resultSet) throws Exception;

}
