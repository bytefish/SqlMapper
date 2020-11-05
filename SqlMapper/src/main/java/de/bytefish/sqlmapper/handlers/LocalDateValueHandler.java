// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.handlers;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.time.LocalDate;

public class LocalDateValueHandler extends BaseValueHandler<LocalDate> {

    @Override
    public Type getTargetType() {
        return LocalDate.class;
    }

    @Override
    protected LocalDate internalHandle(String columnName, ResultSet resultSet) throws Exception {
        // TODO Refactor this trainwreck?
        return resultSet.getTimestamp(columnName)
                .toLocalDateTime()
                .toLocalDate();
    }
}
