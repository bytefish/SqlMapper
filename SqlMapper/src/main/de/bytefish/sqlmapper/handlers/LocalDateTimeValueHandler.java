// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.handlers;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class LocalDateTimeValueHandler extends BaseValueHandler<LocalDateTime> {

    @Override
    public Type getTargetType() {
        return LocalDateTime.class;
    }

    @Override
    protected LocalDateTime internalHandle(String columnName, ResultSet resultSet) throws Exception {
        return resultSet.getTimestamp(columnName).toLocalDateTime();
    }
}
