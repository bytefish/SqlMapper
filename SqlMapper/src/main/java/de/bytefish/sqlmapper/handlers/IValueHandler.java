// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.handlers;

import java.lang.reflect.Type;
import java.sql.ResultSet;

public interface IValueHandler<TTargetType> extends ValueHandler {

    TTargetType handle(String columnName, final ResultSet resultSet);

    Type getTargetType();

}
