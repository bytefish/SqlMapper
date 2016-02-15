// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.handlers;

import java.lang.reflect.Type;
import java.sql.ResultSet;

public class ByteValueHandler extends BaseValueHandler<Byte> {

    @Override
    protected Byte internalHandle(String columnName, ResultSet resultSet) throws Exception {
        return resultSet.getByte(columnName);
    }

    @Override
    public Type getTargetType() {
        return Byte.class;
    }


}
